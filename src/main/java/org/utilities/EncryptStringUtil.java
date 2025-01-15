package org.utilities;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.WebDriver;

public class EncryptStringUtil {

    public EncryptStringUtil(WebDriver driver) {
    }

    public static String stringEncrypt(String str) {
        byte[] encodedBytes = Base64.encodeBase64(str.getBytes());
        String encodedString = new String(encodedBytes);
        System.out.println("Encoded String: " + encodedString);
        return encodedString;
    }
}