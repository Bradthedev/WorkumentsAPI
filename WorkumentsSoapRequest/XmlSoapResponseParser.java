package Workuments.WorkumentsAPI.WorkumentsSoapRequest;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Bradley on 5/16/2016.
 */
public class XmlSoapResponseParser {

    // DocumentBuilder variable for parser
    private DocumentBuilderFactory _docFactory;
    private DocumentBuilder _docBuilder;

    // Document variables
    private StringBuilder _xmlStringBuilder;
    private ByteArrayInputStream _inputStream;
    private Document _xmlDocument;

    public XmlSoapResponseParser(String xml) throws ParserConfigurationException, IOException, SAXException {
        // init of document information
        _docFactory = DocumentBuilderFactory.newInstance();
        _docBuilder = _docFactory.newDocumentBuilder();
        // init input stream information
        _xmlStringBuilder = new StringBuilder();
        _xmlStringBuilder.append("<?xml version=\"1.0\"?>" + xml);
        _inputStream = new ByteArrayInputStream(_xmlStringBuilder.toString().getBytes("UTF-8"));
        // create xml document
        _xmlDocument = _docBuilder.parse(_inputStream);
        _xmlDocument.getDocumentElement().normalize();
    }

    public ArrayList<SoapParameter> getResults(String parentTag) {
        ArrayList<SoapParameter> rv = new ArrayList<SoapParameter>();
        // create a list of nodes where we get out key pair values
        NodeList nodeList = _xmlDocument.getElementsByTagName(parentTag);

        for (int temp = 0; temp < nodeList.getLength(); temp++) {
            Node curNode = nodeList.item(temp);
            Element node = (Element) curNode;
            String key = node.getElementsByTagName("key").item(0).getTextContent();
            String value = node.getElementsByTagName("value").item(0).getTextContent();
            rv.add(new SoapParameter(key, value));
        }

        return rv;
    }
}
