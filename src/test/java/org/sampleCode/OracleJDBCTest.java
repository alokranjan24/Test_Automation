package org.sampleCode;

import org.utilities.OracleJDBCUtil;
import org.utilities.ReadWritePropertiesFileUtil;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class OracleJDBCTest {

    public static void main(String[] args) throws IOException {
        String filePath = "src/main/java/org/testData/testData.properties";

        //**test to fetch the single record from database
        String SQLQuery = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "SQLQuery_Read1");
        String token_id = OracleJDBCUtil.executeFetchValueFromDB(SQLQuery, "TOKEN_ID");


        //**test to update single record in database
        String expected = "false";
        String SQLQuery1 = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "SQLQuery_Write1");
        String SQLQuery2 = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "SQLQuery_Write2");
        String SQLQuery3 = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "SQLQuery_Write3");
        OracleJDBCUtil.executeUpdateValueInDatabase(SQLQuery1);
        OracleJDBCUtil.executeUpdateValueInDatabase(SQLQuery2);
        String actual = OracleJDBCUtil.executeFetchValueFromDB(SQLQuery3, "VALUE");

        System.out.println("\nExpected value to be updated in database: " +actual);
        System.out.println("Actual value updated in database: " + actual);

        //assert value updated in database
        if(expected.equals(actual)){
            System.out.println("\nDatabase successfully updated");
        } else {
            System.out.println("\nDatabase NOT updated!!");
        }


        //**test to fetch multiple records from database
        String tableFetch = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "tableFetch");
        String[] columns = {"TOKEN_ID", "STATUS", "CREATE_DT"};
        String condition = "identifier = 'sd2171@cxc.net'";

        List<String[]> results = OracleJDBCUtil.fetchMultipleValuesFromDatabase(tableFetch, columns, condition);
        System.out.println("Table Name: " + tableFetch);

        int i = 0;
        for(String[] row : results) {
            for (String value : row) {
                System.out.println("Fetched value \"" + value + "\" for Column \"" + columns[i] + "\"");
            }
            System.out.println();
        }


        //**test to update multiple records in database
        String record1 = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "record1");
        String record2 = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "record2");
        String record3 = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "record3");
        String tableUpdate = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "tableUpdate");
        String columnToUpdate = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "columnToUpdate");
        String newValue = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "newValue");
        String keyColumn = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "keyColumn");

        List<Object> records = Arrays.asList(record1, record2, record3);
        OracleJDBCUtil.updateMultipleRecordsInDatabase(tableUpdate, columnToUpdate, newValue, keyColumn, records);
        System.out.println("Table Name: " + tableUpdate + "\n" +
                            "Updated value \"" + newValue + "\" in Column \"" + keyColumn + "\"" );

    }
}
