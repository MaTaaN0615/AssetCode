package com.example.angkanak.assetcode;

public class CheckAsset {
    private int _id;
    private String CHASTagNumber;
    private String CHASDescription;
    private String CHASCostCenter;
    private String CHASLocaDepartment;
    private String CHASLocaSection;
    private String CHASLocationMain;
    private String DepartmentPresent;
    private String CostCenterPresent;
    private String LocationSectionPresent;
    private String AssetInArea;
    private String AssetInspector;

    public CheckAsset(String CHASTagNumber, String CHASDescription, String CHASCostCenter, String CHASLocaDepartment, String CHASLocaSection, String CHASLocationMain, String departmentPresent, String costCenterPresent, String locationSectionPresent, String assetInArea, String assetInspector) {
        this.CHASTagNumber = CHASTagNumber;
        this.CHASDescription = CHASDescription;
        this.CHASCostCenter = CHASCostCenter;
        this.CHASLocaDepartment = CHASLocaDepartment;
        this.CHASLocaSection = CHASLocaSection;
        this.CHASLocationMain = CHASLocationMain;
        this.DepartmentPresent = departmentPresent;
        this.CostCenterPresent = costCenterPresent;
        this.LocationSectionPresent = locationSectionPresent;
        this.AssetInArea = assetInArea;
        this.AssetInspector = assetInspector;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getCHASTagNumber() {
        return CHASTagNumber;
    }

    public void setCHASTagNumber(String CHASTagNumber) {
        this.CHASTagNumber = CHASTagNumber;
    }

    public String getCHASDescription() {
        return CHASDescription;
    }

    public void setCHASDescription(String CHASDescription) {
        this.CHASDescription = CHASDescription;
    }

    public String getCHASCostCenter() {
        return CHASCostCenter;
    }

    public void setCHASCostCenter(String CHASCostCenter) {
        this.CHASCostCenter = CHASCostCenter;
    }

    public String getCHASLocaDepartment() {
        return CHASLocaDepartment;
    }

    public void setCHASLocaDepartment(String CHASLocaDepartment) {
        this.CHASLocaDepartment = CHASLocaDepartment;
    }

    public String getCHASLocaSection() {
        return CHASLocaSection;
    }

    public void setCHASLocaSection(String CHASLocaSection) {
        this.CHASLocaSection = CHASLocaSection;
    }

    public String getCHASLocationMain() {
        return CHASLocationMain;
    }

    public void setCHASLocationMain(String CHASLocationMain) {
        this.CHASLocationMain = CHASLocationMain;
    }


    public String getCostCenterPresent() {
        return CostCenterPresent;
    }

    public void setCostCenterPresent(String costCenterPresent) {
        this.CostCenterPresent = costCenterPresent;
    }

    public String getLocationSectionPresent() {
        return LocationSectionPresent;
    }

    public void setLocationSectionPresent(String locationSectionPresent) {
        this.LocationSectionPresent = locationSectionPresent;
    }

    public String getDepartmentPresent() {
        return DepartmentPresent;
    }

    public void setDepartmentPresent(String departmentPresent) {
        DepartmentPresent = departmentPresent;
    }

    public String getAssetInArea() {
        return AssetInArea;
    }

    public void setAssetInArea(String assetInArea) {
        AssetInArea = assetInArea;
    }

    public String getAssetInspector() {
        return AssetInspector;
    }

    public void setAssetInspector(String assetInspector) {
        AssetInspector = assetInspector;
    }
}
