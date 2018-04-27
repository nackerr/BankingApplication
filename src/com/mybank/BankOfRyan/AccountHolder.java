package com.mybank.BankOfRyan;

public class AccountHolder {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String mailId;
    private String streetAddress;
    private String city;
    private String state;
    private String country;
    private long zipCode;
    private long branchZipCode;

    private Account account;


    public AccountHolder(long branchZip, String firstName, Account account) {
        super();
        this.firstName = firstName;
        this.account = account;
        this.branchZipCode = branchZip;
    }

    public long getBranchZipCode() {
        return branchZipCode;
    }

    public void setBranchZipCode(long branchZipCode) {
        this.branchZipCode = branchZipCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getZipCode() {
        return zipCode;
    }

    public void setZipCode(long zipCode) {
        this.zipCode = zipCode;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}