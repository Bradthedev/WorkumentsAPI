package Workuments.WorkumentsAPI.WorkumentsSoapRequest;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Bradley on 5/16/2016.
 */


public class WorkumentsSoapRequest {

    private static String XML_WEB_SERVICE = "ExecuteWebServiceWithXML";

    private static String DEFAULT_PARENT_TAG = "Results";

    private String orgID;
    private String workumentsDomain;

    private SoapParameterArray requestParameters;

    private String scriptName;
    private String functionName;
    private String params;

    private SoapPrimitive response;

    public WorkumentsSoapRequest(String _workumentsDomain, String _orgId) {
        orgID = _orgId;
        workumentsDomain = _workumentsDomain;
        requestParameters = new SoapParameterArray();
    }

    public void addParameter(String _key, String _value) {
        requestParameters.add(new SoapParameter(_key, _value));
    }

    public void setParameterList(ArrayList<SoapParameter> _paramList) {
        requestParameters = (SoapParameterArray) _paramList;
    }

    public void sendRequest(String _scriptName, String _functionName) throws IOException, XmlPullParserException {
        scriptName = _scriptName;
        functionName = _functionName;
        params = requestParameters.getParameterXml();

        SoapSerializationEnvelope soapRequestEnvelope = this.createRequest();
        HttpTransportSE transport = new HttpTransportSE(workumentsDomain + "/services/app/api/public.asmx?wsdl");

        transport.call(workumentsDomain + "/api/" + XML_WEB_SERVICE, soapRequestEnvelope);

        response = (SoapPrimitive) soapRequestEnvelope.getResponse();
    }

    public SoapParameterArray getResponseArray() throws IOException, SAXException, ParserConfigurationException {
        XmlSoapResponseParser parser = new XmlSoapResponseParser(response.toString());
        return (SoapParameterArray) parser.getResults(DEFAULT_PARENT_TAG);
    }

    public SoapParameterArray getResponseArray(String _parentTag) throws IOException, SAXException, ParserConfigurationException {
        XmlSoapResponseParser parser = new XmlSoapResponseParser(response.toString());
        return (SoapParameterArray) parser.getResults(_parentTag);
    }

    private SoapSerializationEnvelope createRequest() {
        SoapObject request = new SoapObject(workumentsDomain + "/api", XML_WEB_SERVICE);

        request.addProperty("OrgID", orgID);
        request.addProperty("ScriptID", scriptName);
        request.addProperty("FunctionName", functionName);
        request.addProperty("FunctionParamsXML", params);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);


        return envelope;
    }

}
