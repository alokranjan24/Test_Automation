package org.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadWritePropertiesFileUtil {

    //read from properties file
    public static String getPropertyValue(String filePath, String key) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        Properties prop = new Properties();
        prop.load(fis);
        String value = prop.getProperty(key);
        fis.close();
        return value;
    }

    //write to properties file
    public static void setPropertyValue(String filePath, String key, String value){
        try{
            FileInputStream fis = new FileInputStream(filePath);
            Properties prop = new Properties();
            prop.load(fis);

            FileOutputStream out = new FileOutputStream(filePath);
            prop.setProperty(key, value);
            prop.store(out, null);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
