package org.utilities;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.Session;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleJDBCUtil {

    static String filePath = "src/main/java/org/testData/testData.properties";
    private static String URL = null;
    private static String USERNAME = null;
    private static String PASSWORD = null;

    //static block to read values from testData.properties file
    static {
        try{
            URL = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "db_url");
            USERNAME = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "db_username");
            PASSWORD = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "db_password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //static block to load Oracle JDBC driver (optional for JDBC 4.0+)
    static {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver NOT found");
            e.printStackTrace();
        }
    }

    //get connection to Oracle database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    //fetch data from database -- single record
    public static String executeFetchValueFromDB(String query, String column_name){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String result = null;

        try{
            //establish database connection
            connection = getConnection();
            if (connection!=null){
                System.out.println("\n Successfully connected to Oracle database");
                System.out.println("Connection details: " + URL + "\n" + "Environment: " + USERNAME);
            }

            //prepare the sql statement with placeholder
            System.out.println("\nSQL Query: " + query);
            preparedStatement = connection.prepareStatement(query);

            //execute the query
            resultSet = preparedStatement.executeQuery();

            //fetch the result
            if(resultSet.next()){
                result = resultSet.getString(column_name);
                System.out.println("\nValue of " + column_name + " fetched from Oracle Database: " + result);
            }
        } catch (SQLException e) {
            System.out.println("Connection to Oracle Database failed");
            e.printStackTrace();
        } finally {
            //close resources in reverse order of their opening
            closeResources(resultSet, preparedStatement, connection);
        }
        return result;
    }

    //execute INSERT/UPDATE/DELETE statement --> single record
    public static int executeUpdateValueInDatabase(String query){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsAffected = 0;

        try{
            //establish database connection
            connection = getConnection();
            if (connection!=null){
                System.out.println("\n Successfully connected to Oracle database");
                System.out.println("Connection details: " + URL + "\n" + "Environment: " + USERNAME);
            }

            //prepare the sql statement with placeholder
            System.out.println("\nSQL Query: " + query);
            preparedStatement = connection.prepareStatement(query);

            //execute the update and get the number of rows affected
            rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Number of rows updated: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("Connection to Oracle Database failed");
            e.printStackTrace();
        } finally {
            //close resources in reverse order of their opening
            try {
                if(preparedStatement!=null) preparedStatement.close();
                if(connection!=null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rowsAffected;

    }

    //update multiple records in database
    public static void updateMultipleRecordsInDatabase(String tableName, String columnToUpdate, Object newValue, String keyColumn, List<Object> keyValues){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsAffected = 0;

        try{
            //establish database connection
            connection = getConnection();
            if (connection!=null){
                System.out.println("\n Successfully connected to Oracle database");
                System.out.println("Connection details: " + URL + "\n" + "Environment: " + USERNAME);
            }

            if(keyValues == null || keyValues.isEmpty()){
                throw new IllegalArgumentException("No key values provided for WHERE IN clause");
            }

            //prepare the dynamic IN clause placeholder
            StringBuilder queryBuilder = new StringBuilder("UPDATE ")
                    .append(tableName)
                    .append(" SET ")
                    .append(columnToUpdate)
                    .append(" = ? WHERE ")
                    .append(keyColumn)
                    .append(" IN (");

            for(int i = 0; i< keyValues.size(); i++){
                queryBuilder.append("?");
                if(i < keyValues.size() - 1){
                    queryBuilder.append(", ");
                }
            }
            queryBuilder.append(")");
            System.out.println("\nUpdate Query: " + queryBuilder.toString());

            //prepare the sql statement with placeholder
            preparedStatement = connection.prepareStatement(queryBuilder.toString());
            //set the value for the column to update
            preparedStatement.setObject(1, newValue);
            //set the key value for the WHERE IN clause
            int index = 2; //start at 2 because the first placeholder is for the column to update
            for(Object keyValue : keyValues){
                preparedStatement.setObject(index++, keyValue);
            }

            //execute the update and get the number of rows affected
            rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Number of rows updated: " + rowsAffected + "\n");
        } catch (SQLException e) {
            System.out.println("Connection to Oracle Database failed");
            e.printStackTrace();
        } finally {
            //close resources in reverse order of their opening
            try {
                if(preparedStatement!=null) preparedStatement.close();
                if(connection!=null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //fetch multiple records from database
    public static List<String[]> fetchMultipleValuesFromDatabase(String tableFetch, String[] columns, String condition){
        Connection connection = null;
        ResultSet resultSet = null;
        List<String[]> resultList = new ArrayList<>();

        try{
            //establish database connection
            connection = getConnection();
            if (connection!=null){
                System.out.println("\n Successfully connected to Oracle database");
                System.out.println("Connection details: " + URL + "\n" + "Environment: " + USERNAME);
            }

            Statement statement = connection.createStatement();
            String query = buildQuery(tableFetch, columns, condition);
            resultSet = statement.executeQuery(query);

            //get metadata to determine column count
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            //iterate through the result set
            while(resultSet.next()){
                String[] row = new String[columnCount];
                for(int i = 1; i<=columnCount; i++){
                    row[i-1] = resultSet.getString(i);
                }
                resultList.add(row);
            }
        } catch (SQLException e) {
            System.out.println("Connection to Oracle Database failed");
            e.printStackTrace();
        } finally {
            //close resources in reverse order of their opening
            try {
                if(resultSet!=null) resultSet.close();
                if(connection!=null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    //query builder method to create a dynamic query
    public static String buildQuery(String tableName, String[] columns, String condition){
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT ");
        if (columns == null || columns.length == 0){
            queryBuilder.append("*");
        } else {
            for (int i = 0; i<columns.length; i++){
                queryBuilder.append(columns[i]);
                if(i < columns.length-1){
                    queryBuilder.append(", ");
                }
            }
        }
        queryBuilder.append(" FROM ").append(tableName);
        if(condition!=null && !condition.isEmpty()){
            queryBuilder.append(" WHERE ").append(condition);
            System.out.println("\nSQL Query: " +queryBuilder.toString() + "\n");
        }
        return queryBuilder.toString();
    }

    //close all JDBC resources
    public static void closeResources(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection){
        try {
            if(resultSet!=null) resultSet.close();
            if(preparedStatement!=null) preparedStatement.close();
            if(connection!=null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
