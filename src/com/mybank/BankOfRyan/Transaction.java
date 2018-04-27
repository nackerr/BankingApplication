package com.mybank.BankOfRyan;

public class Transaction {
    private int transactionNumber;

    public Transaction(int tNum) {
        transactionNumber = tNum;
    }

    public int getTransactionNumber() {
        return transactionNumber;
    }
}