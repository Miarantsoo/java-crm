package com.project.javacrm.generator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ColumnRetriever {

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    public List<ColumnDetails> getColumns(String tableName) {
        List<ColumnDetails> columns = new ArrayList<>();

        try(Connection connection = DriverManager.getConnection(databaseUrl, username, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getColumns(null, null, tableName, null);
            while (resultSet.next()) {
                ColumnDetails column = new ColumnDetails();
                column.setName(resultSet.getString("COLUMN_NAME"));
                column.setType(resultSet.getString("TYPE_NAME"));
                column.setPrimary(resultSet.getString("IS_AUTOINCREMENT").equals("YES"));
                column.setForeign(false);
                column.setReferencedTable(null);
                column.setReferencedColumn(null);
                column.setReferencedColumnType(null);
                columns.add(column);
            }

            ResultSet foreignKeys = metaData.getImportedKeys(null, null, tableName);
            while (foreignKeys.next()) {
                String fkColumnName = foreignKeys.getString("FKCOLUMN_NAME");
                for (ColumnDetails column : columns) {
                    if (column.getName().equals(fkColumnName)) {
                        column.setForeign(true);
                        column.setReferencedTable(foreignKeys.getString("PKTABLE_NAME"));
                        column.setReferencedColumn(foreignKeys.getString("PKCOLUMN_NAME"));

                        ResultSet pkColumn = metaData.getColumns(null, null, foreignKeys.getString("PKTABLE_NAME"), foreignKeys.getString("PKCOLUMN_NAME"));
                        if (pkColumn.next()) {
                            column.setReferencedColumnType(pkColumn.getString("TYPE_NAME"));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columns;
    }



}
