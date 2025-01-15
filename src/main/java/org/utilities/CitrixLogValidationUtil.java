package org.utilities;

import com.jcraft.jsch.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CitrixLogValidationUtil {

    static String filePath = "src/main/java/org/testData/testData.properties";
    private static String user = null;
    private static String password = null;
    private static String host = null;
    private static String port = null;
    private static String command = null;
    private static String fileName = null;
    static Session session = null;
    static Channel channel = null;

    //static block to read values from testData.properties file
    static {
        try{
            user = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "ssh_user");
            password = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "ssh_password");
            host = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "ssh_host");
            port = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "ssh_port");
            command = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "ssh_command");
            fileName = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "ssh_filename");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Method to get a connection to ssh
    public static void getSSHConnection(){
        try{
            JSch jsch = new JSch();
            session = jsch.getSession(user, host, Integer.parseInt(port));
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            System.out.println("Successfully connected to the SSH server" +"\n" + "User: " + user + "\n" + "Host: " + host + "\n" + "Port: " + port + "\n");
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    //Reusable method to validate logs on the server
    public static String validateLogs() throws JSchException, IOException {
        //Connect to the server
        CitrixLogValidationUtil.getSSHConnection();

        //Open the SSH channel and execute the command
        channel = session.openChannel("exec");
        ((ChannelExec) channel).setCommand(command);
        channel.connect();
        System.out.println("Command Executed: " + command);

        //Read the command output
        InputStream in = ((ChannelExec) channel).getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        LoggerUtil loggerUtil = new LoggerUtil();
        loggerUtil.CaptureLog(fileName, reader.readLine());
        System.out.println("Output of the command: ");
        String line;
        String logs = null;
        boolean isErrorFound = false;
        while ((line = reader.readLine()) != null){
            System.out.println(line);
            logs = logs + line;
            if (line.contains("ERROR")){
                isErrorFound = true;
                break;
            }
        }
        channel.disconnect();
        closeConnection();
        if(isErrorFound){
            throw new AssertionError("Error found in Citrix Logs");
        } else {
            System.out.println("\nNo Error found in Citrix Logs!");
        }
        return logs;
    }

    public static void closeConnection(){
        session.disconnect();
        System.out.println("\nDisconnected from the server");
    }

}
