package com.project.javacrm.utils;

import java.io.File;

public class PackageUtils {

    public static String createPackage(String packageName) {
        String minName = StringUtils.toKebabCase(packageName).toLowerCase();
        File file = new File("src/main/java/com/project/javacrm/" + minName + "/");
        file.mkdir();
        return minName;
    }

    public static String createFile(String packageName, String fileName, String category) {
        String camelFileName = StringUtils.majStart(StringUtils.toCamelCase(fileName));
        String camelCategory = StringUtils.majStart(StringUtils.toCamelCase(category));
        File file = new File("src/main/java/com/project/javacrm/" + packageName + "/" + camelFileName + camelCategory + ".java");
        try {
            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }

}
