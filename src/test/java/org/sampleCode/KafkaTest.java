package org.sampleCode;

import org.utilities.KafkaUtil;
import org.utilities.ReadWritePropertiesFileUtil;

import java.io.IOException;

public class KafkaTest {

    public static void main(String[] args) throws IOException {
        String filePath = "src/main/java/org/testData/testData.properties";
        String zsdDirectoryPath = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "zsd_directory_path");
        String zspPaymentPath = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "zsp_payment_path");
        String restartZSDDirectory = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "zsd_directory_webservices");
        String restartZSPPayment = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "zsp_payment_webservices");
        String sourceFile = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "source_file");
        String destinationFile = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "destination_file");

        KafkaUtil.getSSHConnection();
        KafkaUtil.createCopyOfFileOnServer(zsdDirectoryPath, sourceFile, destinationFile);
        KafkaUtil.restartServer(zsdDirectoryPath, restartZSDDirectory);

        KafkaUtil.createCopyOfFileOnServer(zspPaymentPath, sourceFile, destinationFile);
        KafkaUtil.restartServer(zspPaymentPath, restartZSPPayment);
        KafkaUtil.closeConnection();


    }
}
