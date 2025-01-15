package org.sampleCode;

import com.jcraft.jsch.JSchException;
import org.soapAPITest.Payload;
import org.utilities.XMLReadWriteUtil;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XMLResponseTest {

    public static void main(String[] args) throws JSchException, IOException, ParserConfigurationException, SAXException {
        XMLReadWriteUtil.readXMLResponse(Payload.xmlResponseTest(), "request-id");
        XMLReadWriteUtil.readXMLResponse(Payload.xmlResponseTest(), "status");
        XMLReadWriteUtil.readXMLResponse(Payload.xmlResponseTest(), "failure-reason-code");
        XMLReadWriteUtil.readXMLResponse(Payload.xmlResponseTest(), "failure-reason-description");
    }

}
