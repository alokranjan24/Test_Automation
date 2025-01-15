package org.utilities;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;

public class XMLReadWriteUtil {

    private FileHandler fh = null;

    String file;

    public static String readXMLResponse(String response, String key) throws ParserConfigurationException, IOException, SAXException {

        //parse xml response
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new java.io.ByteArrayInputStream(response.getBytes(StandardCharsets.UTF_8)));

        //extract desired value
        NodeList nodes = document.getElementsByTagName(key);

        //iterate through the mode list and print the value
        String value = null;
        for(int i = 0; i < nodes.getLength(); i++){
            if(nodes.getLength() > 0){
                Element element = (Element) nodes.item(i);
                value = element.getTextContent();
                System.out.println("Value of " + key + ": " + value);
            } else {
                System.out.println("Element " + key + " not found!!");
            }
        }
        return value;
    }

    //capture xml response in a xml file in a folder
    public void writeXMLResponse(String fileName, String response){
        SimpleDateFormat format = new SimpleDateFormat("Md_HHmmss");
        try{
            file = "src/main/java/org/xmlSoapResponse/" + fileName + "_" + format.format(Calendar.getInstance().getTime()) + ".xml";
            FileWriter writer = new FileWriter(file);
            writer.write(response);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("XML Response stored at location: " + file + "\n");
    }
}
