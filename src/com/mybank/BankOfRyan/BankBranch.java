package com.mybank.BankOfRyan;
import com.mybank.BankOfRyan.Constants;
import java.util.List;

public class BankBranch extends Bank {
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String phoneNumber;
    private List<AccountHolder> accountHolders;

    @Override
    public String toString() {
        return "Bank Branch [bankAddress=" + streetAddress + ", bankCity=" + city + ", bankState=" + state + ", bankZip=" + zipCode + ", bankCountry=" + country + ", bankNumber=" + phoneNumber +"]";
    }

    public List<AccountHolder> getAccountHolders() {
        return accountHolders;
    }
    public void setAccountHolders(List<AccountHolder> accountHolders) {
        this.accountHolders = accountHolders;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCountry() {
        return country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

