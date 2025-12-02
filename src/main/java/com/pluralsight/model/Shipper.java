package com.pluralsight.model;

public class Shipper {
    private int shipperID;
    private String companyName;
    private String phoneNumber;

    public Shipper(String companyName, String phoneNumber) {
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
    }

    public Shipper(String companyName, String phoneNumber, int shipperID) {
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.shipperID = shipperID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getShipperID() {
        return shipperID;
    }

    public void setShipperID(int shipperID) {
        this.shipperID = shipperID;
    }

    @Override
    public String toString() {
        return shipperID + " | " + companyName + " | " + phoneNumber;
    }
}
