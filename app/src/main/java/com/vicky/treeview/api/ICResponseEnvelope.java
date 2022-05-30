package com.vicky.treeview.api;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

@Root(name = "soap:Envelope")
@NamespaceList({
        @Namespace(prefix = "soap", reference = "http://schemas.xmlsoap.org/soap/envelope/"),
        @Namespace(prefix = "xsi", reference = "http://www.w3.org/2001/XMLSchema-instance"),
        @Namespace(prefix = "xsd", reference = "http://www.w3.org/2001/XMLSchema")
})
public class ICResponseEnvelope {
    @Element(name = "Body")
    @Namespace(prefix = "soap")
    public ICResponseBody icResponseBody;

    public static class ICResponseBody {
        @Element(name = "GetClassificationForMobileResponse", required = false)
        @Namespace(reference = "http://tempuri.org/")
        public ICResponseModel icResponseModel;
    }
}
