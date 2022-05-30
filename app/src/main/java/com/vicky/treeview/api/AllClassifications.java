package com.vicky.treeview.api;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by shameem on 5/25/17.
 */

@Root(name = "GetClassificationForMobileResult", strict = false)
public class AllClassifications {
    @Element(name = "ClassificationID", required = false)
    private int classificationID;
    @Element(name = "ClassificationDesc", required = false)
    private String classificationDesc;
    @Element(name = "ParentClassificationID", required = false)
    private int parentClassificationID;
    @Element(name = "ClassificationDescEn", required = false)
    private String classificationDescEn;

    public int getClassificationID() {
        return classificationID;
    }

    public void setClassificationID(int classificationID) {
        this.classificationID = classificationID;
    }

    public String getClassificationDesc() {
        return classificationDesc;
    }

    public void setClassificationDesc(String classificationDesc) {
        this.classificationDesc = classificationDesc;
    }

    public int getParentClassificationID() {
        return parentClassificationID;
    }

    public void setParentClassificationID(int parentClassificationID) {
        this.parentClassificationID = parentClassificationID;
    }

    public String getClassificationDescEn() {
        return classificationDescEn;
    }

    public void setClassificationDescEn(String classificationDescEn) {
        this.classificationDescEn = classificationDescEn;
    }
}
