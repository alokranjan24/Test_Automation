package org.sampleCode;

import com.jcraft.jsch.JSchException;
import org.utilities.EncryptStringUtil;

import java.io.IOException;

public class EncryptStringTest {
    public static void main(String[] args) throws JSchException, IOException {
        EncryptStringUtil.stringEncrypt("Madhubani@567");
    }
}
