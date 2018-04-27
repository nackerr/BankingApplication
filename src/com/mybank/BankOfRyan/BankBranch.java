package com.mybank.BankOfRyan;
import java.util.List;

public class BankBranch extends Bank {
    private String bankState;
    private long zipCode;
    private String bankCountry;
    private String bankName;
    private String phoneNumber;
    private List<AccountHolder> accountHolders;
    private int accountNum;

    @Override
    public String toString() {
        return "Bank Branch [bankName=" + bankName + ", bankState=" + bankState + ", bankZip=" + zipCode + ", bankCountry=" + bankCountry + ", bankNumber=" + phoneNumber +"]";
    }

    public BankBranch(String state, long zip, String country, String name, String number) {
        bankState = state;
        zipCode = zip;
        bankCountry = country;
        bankName = name;
        phoneNumber = number;

    }

    public List<AccountHolder> getAccountHolders() {
        return accountHolders;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void setName(String name) {
        this.bankName = name;
    }

    public String getName() {
        return bankName;
    }

    public String getState() {
        return bankState;
    }

    public long getZipCode() {
        return zipCode;
    }

    public String getCountry() {
        return bankCountry;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    public void setState(String state) {
        this.bankState = state;
    }

    public void setZipCode(long zipCode) {
        this.zipCode = zipCode;
    }

    public void setCountry(String country) {
        this.bankCountry = country;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

