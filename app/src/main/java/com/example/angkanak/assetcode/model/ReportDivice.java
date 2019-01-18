package com.example.angkanak.assetcode.model;

public class ReportDivice {
    private String TagNumber;
    private String Description;
    private String Thisasset;
    private String Inarea;

    public ReportDivice(String tagNumber, String description, String thisasset, String inarea) {
        TagNumber = tagNumber;
        Description = description;
        Thisasset = thisasset;
        Inarea = inarea;
    }

    public String getTagNumber() {
        return TagNumber;
    }

    public void setTagNumber(String tagNumber) {
        TagNumber = tagNumber;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getThisasset() {
        return Thisasset;
    }

    public void setThisasset(String thisasset) {
        Thisasset = thisasset;
    }

    public String getInarea() {
        return Inarea;
    }

    public void setInarea(String inarea) {
        Inarea = inarea;
    }
}
