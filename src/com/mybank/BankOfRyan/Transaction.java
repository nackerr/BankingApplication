package com.mybank.BankOfRyan;
import com.mybank.BankOfRyan.Constants;

public class Transaction {
    private int transactionNumber;
    private double transactionAmount;
    private String transactionDate;
    private String transactionType;

    public Transaction(String type, int tNum, double tAmt, String tDate) {
        transactionNumber = tNum;
        transactionAmount = tAmt;
        transactionDate = tDate;
        transactionType = type;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setTransactionNumber(int transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public int getTransactionNumber() {
        return transactionNumber;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }
}