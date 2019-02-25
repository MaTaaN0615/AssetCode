package com.example.angkanak.assetcode.model;

public class Asset {
    private int _id;
    private String TagNumber;
    private String Description;
    private String LocaDepartment;
    private String CostCenter;
    private String LocaSection;
    private String LocationMain;
    private String DateInService;
    private String Units;
    private String CurrentCost;
    private String NetBookValue;
    private String Boi;
    private String BoiNumber;

//    private String SerialNymber; //ของUHF RFID
//    private String TagCardID;

    public Asset(String tagNumber, String description, String locaDepartment, String costCenter,
                 String locaSection, String locationMain, String dateInService, String units,
                 String currentCost, String netBookValue, String boi, String boiNumber) {
        this.TagNumber = tagNumber;
        this.Description = description;
        this.LocaDepartment = locaDepartment;
        this.CostCenter = costCenter;
        this.LocaSection = locaSection;
        this.LocationMain = locationMain;
        this.DateInService = dateInService;
        this.Units = units;
        this.CurrentCost = currentCost;
        this.NetBookValue = netBookValue;
        this.Boi = boi;
        this.BoiNumber = boiNumber;

    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
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

    public String getLocaDepartment() {
        return LocaDepartment;
    }

    public void setLocaDepartment(String locaDepartment) {
        LocaDepartment = locaDepartment;
    }

    public String getCostCenter() {
        return CostCenter;
    }

    public void setCostCenter(String costCenter) {
        CostCenter = costCenter;
    }

    public String getLocaSection() {
        return LocaSection;
    }

    public void setLocaSection(String locaSection) {
        LocaSection = locaSection;
    }

    public String getLocationMain() {
        return LocationMain;
    }

    public void setLocationMain(String locationMain) {
        LocationMain = locationMain;
    }

    public String getDateInService() {
        return DateInService;
    }

    public void setDateInService(String dateInService) {
        DateInService = dateInService;
    }

    public String getUnits() {
        return Units;
    }

    public void setUnits(String units) {
        Units = units;
    }

    public String getCurrentCost() {
        return CurrentCost;
    }

    public void setCurrentCost(String currentCost) {
        CurrentCost = currentCost;
    }

    public String getNetBookValue() {
        return NetBookValue;
    }

    public void setNetBookValue(String netBookValue) {
        NetBookValue = netBookValue;
    }

    public String getBoi() {
        return Boi;
    }

    public void setBoi(String boi) {
        Boi = boi;
    }

    public String getBoiNumber() {
        return BoiNumber;
    }

    public void setBoiNumber(String boiNumber) {
        BoiNumber = boiNumber;
    }
}
