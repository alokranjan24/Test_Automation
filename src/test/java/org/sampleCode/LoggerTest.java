package org.sampleCode;

import com.jcraft.jsch.JSchException;
import org.utilities.LoggerUtil;

import java.io.IOException;

public class LoggerTest {

    public static void main(String[] args) throws JSchException, IOException {
        LoggerUtil loggerUtil = new LoggerUtil();
        loggerUtil.CaptureLog("s5562qalv", "Sample Text");
    }

}
