package com.example.angkanak.assetcode;

import android.app.Application;

public class PresentLocation extends Application {



    private String preDepartment;
    private String preCostcenter;
    private String preSection;
    private String preInspector;

//    public PresentLocation(String preDepartment, String preCostcenter, String preSection, String preInspector) {
//        this.preDepartment = preDepartment;
//        this.preCostcenter = preCostcenter;
//        this.preSection = preSection;
//        this.preInspector = preInspector;
//    }
    public PresentLocation(){}

    public String getPreDepartment() {
        return preDepartment;
    }

    public void setPreDepartment(String preDepartment) {
        this.preDepartment = preDepartment;
    }

    public String getPreCostcenter() {
        return preCostcenter;
    }

    public void setPreCostcenter(String preCostcenter) {
        this.preCostcenter = preCostcenter;
    }

    public String getPreSection() {
        return preSection;
    }

    public void setPreSection(String preSection) {
        this.preSection = preSection;
    }

    public String getPreInspector() {
        return preInspector;
    }

    public void setPreInspector(String preInspector) {
        this.preInspector = preInspector;
    }


}
