package com.pluralsight.model;

public class Shipper {
    private int shipperID;
    private String companyName;
    private String phoneNumber;

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

    @Override
    public String toString() {
        return "Shipper{" +
                "companyName='" + companyName + '\'' +
                ", shipperID=" + shipperID +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
