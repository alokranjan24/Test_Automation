package org.sampleCode;

import com.jcraft.jsch.JSchException;
import org.utilities.CitrixLogValidationUtil;

import java.io.IOException;

public class CitrixLogValidationTest {

    public static void main(String[] args) throws JSchException, IOException {
        CitrixLogValidationUtil.validateLogs();
    }

}
