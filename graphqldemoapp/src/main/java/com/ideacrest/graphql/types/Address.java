package com.ideacrest.graphql.types;

public class Address {
    private String line1;
    private String line2;
    private String pinCode;
    private AddressType addressType;

    public enum AddressType{
        BILLING, HOME
    }

    public Address(String line1, String line2, String pinCode, AddressType addressType) {
        this.line1 = line1;
        this.line2 = line2;
        this.pinCode = pinCode;
        this.addressType = addressType;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }
}
