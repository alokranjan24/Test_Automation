package org.utilities;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.WebDriver;
import java.io.IOException;

public class DecryptStringUtil {

    public DecryptStringUtil(WebDriver driver) {
    }

    public String stringDecrypt(String str) throws IOException {
        byte[] decodedBytes = Base64.decodeBase64(str);
        String decodedString = new String(decodedBytes);
        return decodedString;
    }
}