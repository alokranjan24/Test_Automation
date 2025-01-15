package org.utilities;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;

public class LoggerUtil {

    private FileHandler fh = null;

    String file;

    public void CaptureLog(String fileName, String text){
        SimpleDateFormat format = new SimpleDateFormat("Md_HHmmss");
        try{
            file = "src/main/java/org/serverLogs/" + fileName + "_" + format.format(Calendar.getInstance().getTime()) + ".log";
            FileWriter writer = new FileWriter(file);
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Logs stored at location: " + file + "\n");
    }

}
