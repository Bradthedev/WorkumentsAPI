package Workuments.WorkumentsAPI.WorkumentsSoapRequest;

/**
 * Created by Bradley on 5/16/2016.
 */
public class SoapParameter {
    private String key;
    private String value;

    public SoapParameter() {
        key = "";
        value = "";
    }

    public SoapParameter(String _key, String _value) {
        key = _key;
        value = _value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String _key) {
        key = _key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String _value) {
        value = _value;
    }

    public void setKeyPairValue(String _key, String _value) {
        key = _key;
        value = _value;
    }

    public String toXml() {
        return "<parameter><key>" + key + "</key><value>" + value + "</value></parameter>";
    }

}