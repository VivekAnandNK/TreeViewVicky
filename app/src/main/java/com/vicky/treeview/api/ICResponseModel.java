package com.vicky.treeview.api;


import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "GetClassificationForMobileResult", strict = false)
@Namespace(reference = "http://tempuri.org/")
public class ICResponseModel {
    @ElementList(name = "GetClassificationForMobileResult", required = false)
    public List<AllClassifications> allClassifications;
}
