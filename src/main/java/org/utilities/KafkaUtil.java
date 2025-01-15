package org.utilities;

import com.jcraft.jsch.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KafkaUtil {

    static String filePath = "src/main/java/org/testData/testData.properties";
    private static String user = null;
    private static String password = null;
    private static String host = null;
    private static String port = null;
    private static String fileName = null;
    static Session session = null;
    static Channel channel = null;

    //static block to read values from testData.properties file
    static {
        try{
            user = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "kafka_user");
            password = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "kafka_password");
            host = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "kafka_host");
            port = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "kafka_port");
            fileName = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "kafka_filename");
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

    //execute the command on the ssh session and returns the output
    private static String runCommand(Session session, String command, String logMessage) throws JSchException, IOException {
        ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
        channelExec.setPty(true);
        channelExec.setCommand(command);

        //capture the command output
        InputStream in = channelExec.getInputStream();
        channelExec.connect();

        //read the output from the input stream
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        LoggerUtil loggerUtil = new LoggerUtil();
        loggerUtil.CaptureLog(fileName, reader.readLine());

        StringBuilder outputBuffer = new StringBuilder();
        String line;
        System.out.println();
        while((line = reader.readLine()) != null){
            outputBuffer.append((line + "\n"));
        }

        channelExec.disconnect();
        return outputBuffer.toString();

    }

    //restart server
    public static void restartServer(String directoryPath, String restartWebService){
        try{
            //change directory, switch to appuser using sudo and run a command to restart the server
            String restartServer = "cd " + directoryPath + " && pwd" + "&& sudo -S -u appuser " + restartWebService + " restart";
            String dirOutput = runCommand(session, restartServer, "Restarting Server");
            System.out.println("Server Restarted: " + dirOutput);

        } catch (JSchException | IOException e) {
            e.printStackTrace();
        }
    }

    //copy a file on the server
    public static void createCopyOfFileOnServer(String directoryPath, String sourceFile, String destinationFile){
        try{
            //change directory, switch to appuser using sudo and copy a file on the server
            String fileCopy = "cd " + directoryPath + " && pwd" + "&& sudo -S -u appuser " + "cp " + sourceFile + " " + destinationFile;
            String dirOutput = runCommand(session, fileCopy, "Copying file on the server path: " +directoryPath);
            System.out.println("File : \"" +destinationFile + "\" copied on Server Path: " + dirOutput + " from Source File: \"" + sourceFile + "\"");
        } catch (JSchException | IOException e) {
            e.printStackTrace();
        }
    }

    //close session connection
    public static void closeConnection() {
        session.disconnect();
        System.out.println("Disconnected from the server");
    }
}
