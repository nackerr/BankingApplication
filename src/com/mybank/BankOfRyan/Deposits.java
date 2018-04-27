package com.mybank.BankOfRyan;

public class Deposits {
    private double depositAmount;
    private String depositDate;

    public Deposits(double debAmt, String debDate) {
        depositAmount = debAmt;
        depositDate = debDate;
    }

    public double getDepositAmount(){

        return depositAmount;
    }

    public String getDepositDate(){

        return depositDate;
    }
}
