package com.mybank.BankOfRyan;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class AccountTransactions {

    private final int numberOfTransactions = 100;
    private List<Transaction> transactionList;

    public AccountTransactions() {
        transactionList = new ArrayList<Transaction>();
    }

    public int addTransaction(String type, int tNum, double amount, String date) {
        if (tNum > 0 && tNum <= numberOfTransactions) {
            Transaction t = new Transaction(type, tNum, amount, date);
            if(!transactionList.contains(t)) {
                transactionList.add(t);
                return Constants.TRANSACTION_WRITTEN;
            } else
                return Constants.DUPLICATE_TRANSACTION_NUMBER;
        }
        return Constants.INVALID_TRANSACTION_NUMBER;
    }

    public void addDepositSlip(double amount, String date) {
        Transaction t = new Transaction("DEPOSIT", 0, amount, date);
        transactionList.add(t);
    }

    public List<Transaction> transactionRange(int start, int end) {
        Transaction t;
        Iterator<Transaction> it = transactionList.iterator();
        List<Transaction> transactions = new ArrayList<Transaction>();
        while (it.hasNext()) {
            t = it.next();
            int chequeNumber = t.getTransactionNumber();
            if (chequeNumber >= start && chequeNumber <= end && chequeNumber != -1)
                transactions.add(t);
        }
        return transactions;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }
}