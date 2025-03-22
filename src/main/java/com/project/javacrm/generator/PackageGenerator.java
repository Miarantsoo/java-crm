package com.project.javacrm.generator;

import com.project.javacrm.utils.PackageUtils;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import com.project.javacrm.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

@Component
public class PackageGenerator {

    public PackageGenerator() {
    }

    public void generatePackage(String tableName, List<ColumnDetails> columns) {
        String packageName = PackageUtils.createPackage(tableName);

        generateModel(packageName, tableName, columns);
        generateRepository(packageName, tableName);
        generateService(packageName, tableName);
        generateController(packageName, tableName);
    }

    private void generateModel(String packageName, String tableName, List<ColumnDetails> columns) {
        String className = StringUtils.majStart(StringUtils.toCamelCase(tableName));
        String lowerCaseClassName = StringUtils.minStart(className);

        InputStream is = getClass().getResourceAsStream("/ModelTemplate.txt");
        if (is == null) {
            System.err.println("Model template not found.");
            return;
        }

        String template;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            template = reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        StringBuilder fields = new StringBuilder();
        String foreigns = "";
        boolean containsDate = false;
        for (ColumnDetails col : columns) {
            if (col.isPrimary()) {
                fields.append("    @Id\n")
                        .append("    @GeneratedValue(strategy = GenerationType.IDENTITY)\n");
            } else if (col.isForeign()) {
                fields.append("    @ManyToOne\n")
                        .append("    @JoinColumn(name = \"").append(col.getName()).append("\")\n");
            } else if(col.getName().equals("id")) {
                fields.append("    @Id\n")
                        .append("    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = \"seq_").append(tableName).append("\")\n")
                        .append("    @SequenceGenerator(name = \"seq_").append(tableName).append("\", sequenceName = \"seq_").append(tableName).append("\", allocationSize = 1)\n");
            }
            else {
                fields.append("    @Column(name = \"").append(col.getName()).append("\")\n");
            }

            String javaType = "";
            if(col.isForeign()){
                javaType += StringUtils.majStart(StringUtils.toCamelCase(col.getReferencedTable()));
                foreigns += "import com.project.javacrm."+packageName+";\n";
            } else {
                javaType += mapSqlTypeToJavaType(col.getType());
                if(javaType.equals("LocalDateTime")){
                    containsDate = true;
                }
            }
            fields.append("    private ").append(javaType).append(" ").append(StringUtils.toCamelCase(col.getName())).append(";\n\n");
        }

        if(!foreigns.equals("")){
            template = template.replace("${foreigns}", foreigns);
        } else {
            template = template.replace("${foreigns}", "");
        }

        if(containsDate) {
            template = template.replace("${localDateTime}", "import java.time.LocalDateTime;");
        }

        template = template.replace("${packageName}", packageName)
                .replace("${className}", className)
                .replace("${tableName}", tableName)
                .replace("${fields}", fields.toString());

        String filePath = PackageUtils.createFile(packageName, tableName, "");

        writeToFile(filePath, template);
    }

    private void generateRepository(String packageName, String tableName) {
        String className = StringUtils.majStart(StringUtils.toCamelCase(tableName));
        String lowerCaseClassName = StringUtils.minStart(className);

        InputStream is = getClass().getResourceAsStream("/RepositoryTemplate.txt");
        if (is == null) {
            System.err.println("Repository template not found.");
            return;
        }

        String template;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            template = reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        template = template.replace("${packageName}", packageName)
                .replace("${className}", className)
                .replace("${lowerCase(className)}", lowerCaseClassName);

        String filePath = PackageUtils.createFile(packageName, tableName, "repository");

        writeToFile(filePath, template);
    }


    private void generateService(String packageName, String tableName) {
        String className = StringUtils.majStart(StringUtils.toCamelCase(tableName));
        String lowerCaseClassName = StringUtils.minStart(className);

        InputStream is = getClass().getResourceAsStream("/ServiceTemplate.txt");
        if (is == null) {
            System.err.println("Service template not found.");
            return;
        }

        String template;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            template = reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        template = template.replace("${packageName}", packageName)
                .replace("${className}", className)
                .replace("${lowerCase(className)}", lowerCaseClassName)
                .replace("${displayName}", className);

        String filePath = PackageUtils.createFile(packageName, tableName, "service");

        writeToFile(filePath, template);
    }

    private void generateController(String packageName, String tableName) {
        String className = StringUtils.majStart(StringUtils.toCamelCase(tableName));
        String lowerCaseClassName = StringUtils.minStart(className);

        String endpoint = StringUtils.toKebabCase(tableName);

        InputStream is = getClass().getResourceAsStream("/ControllerTemplate.txt");
        if (is == null) {
            System.err.println("Controller template not found.");
            return;
        }

        String template;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            template = reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        template = template.replace("${packageName}", packageName)
                .replace("${className}", className)
                .replace("${lowerCase(className)}", lowerCaseClassName)
                .replace("${displayName}", className)
                .replace("${endpoint}", endpoint);

        String filePath = PackageUtils.createFile(packageName, tableName, "controller");

        writeToFile(filePath, template);
    }

    private void writeToFile(String filePath, String content) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String mapSqlTypeToJavaType(String sqlType) {
        switch(sqlType.toLowerCase()){
            case "tinyint":
            case "smallint":
            case "mediumint":
            case "int":
            case "integer":
            case "serial":
                return "Integer";
            case "bigint":
            case "bigserial":
                return "Long";
            case "varchar":
            case "char":
            case "text":
            case "tinytext":
            case "mediumtext":
            case "longtext":
            case "enum":
            case "set":
                return "String";
            case "float":
            case "double":
            case "decimal":
                return "Double";
            case "date":
                return "LocalDate";
            case "datetime":
            case "timestamp":
                return "LocalDateTime";
            case "time":
                return "LocalTime";
            case "bit":
            case "boolean":
                return "Boolean";
            case "json":
                return "JsonNode";
            case "year":
                return "Integer";
            case "binary":
            case "varbinary":
            case "blob":
            case "tinyblob":
            case "mediumblob":
            case "longblob":
                return "byte[]";
            case "geometry":
            case "point":
            case "linestring":
            case "polygon":
            case "multipoint":
            case "multilinestring":
            case "multipolygon":
            case "geometrycollection":
                return "String"; // or a specific type if you have a library for spatial data
            default:
                return "String";
}
    }
}
