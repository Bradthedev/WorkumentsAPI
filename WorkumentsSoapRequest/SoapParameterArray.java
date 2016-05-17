package Workuments.WorkumentsAPI.WorkumentsSoapRequest;

import java.util.ArrayList;

/**
 * Created by Bradley on 5/16/2016.
 */
public class SoapParameterArray extends ArrayList<SoapParameter> {

    public String getParameterXml() {
        String XmlParams = "<parameters>";

        int itemCount = this.size();

        for (int i = 0; i < itemCount; i++) {
            SoapParameter param = this.get(i);
            XmlParams += param.toXml();
        }
        XmlParams += "</parameters>";
        return XmlParams;
    }
}
