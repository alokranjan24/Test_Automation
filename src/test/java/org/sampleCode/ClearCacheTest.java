package org.sampleCode;

import com.jcraft.jsch.JSchException;
import org.utilities.CitrixLogValidationUtil;
import org.utilities.ClearCacheUtil;

import java.io.IOException;

public class ClearCacheTest {

    public static void main(String[] args) throws JSchException, IOException {
        ClearCacheUtil.clearCache();
    }
}
