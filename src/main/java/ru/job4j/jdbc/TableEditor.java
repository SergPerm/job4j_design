package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (InputStream io = TableEditor.class.getClassLoader().
                getResourceAsStream("app.properties")) {
            properties.load(io);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");

        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void exectStatement(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exectStatement(String sql, String tableName) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println(getTableScheme(connection, tableName)); //временная строка
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void createTable(String tableName) {
        String sql = String.format(
                "create table if not exists %s();",
                tableName
        );
        exectStatement(sql, tableName);
    }

    public void dropTable(String tableName) {
        String sql = String.format(
                "drop table if exists %s;",
                tableName
        );
        exectStatement(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format(
                "ALTER TABLE IF EXISTS %s ADD COLUMN IF NOT EXISTS %s %s;",
                tableName, columnName, type
        );
        exectStatement(sql, tableName);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format(
                "ALTER TABLE IF EXISTS %s DROP COLUMN %s;",
                tableName, columnName
        );
        exectStatement(sql, tableName);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format(
                "ALTER TABLE IF EXISTS %s RENAME COLUMN %s TO %s;",
                tableName, columnName, newColumnName
        );
        exectStatement(sql, tableName);
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        TableEditor tb = new TableEditor(new Properties());
        tb.initConnection();
        tb.createTable("TblEdt");
        tb.addColumn("TblEdt", "name2", "text");
        tb.renameColumn("TblEdt", "name2", "name3");
        tb.dropColumn("TblEdt", "name3");
        tb.dropTable("TblEdt");
    }
}