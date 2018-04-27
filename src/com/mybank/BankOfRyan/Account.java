package com.mybank.BankOfRyan;
import java.util.List;


public class Account {

    private long accountNumber;
    private String typeOfAccount;
    private double accountBalance;

    private AccountTransactions accountTransactions;

    public AccountTransactions getTransactionsList() {
        return accountTransactions;
    }


    public Account(long accNum, String accType, double balance) {
        accountNumber = accNum;
        typeOfAccount = accType;
        accountBalance = balance;
        accountTransactions = new AccountTransactions();
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTypeOfAccount() {
        return typeOfAccount;
    }

    public void setTypeOfAccount(String typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public int writeCheque(int tNum, double amount, String date) {
        if (amount > 0) {
            if (amount <= accountBalance) {
                int isAdded = accountTransactions.addTransaction("CHECK", tNum, amount, date);
                if (isAdded == Constants.TRANSACTION_WRITTEN) {
                    accountBalance = accountBalance - amount;
                    return Constants.TRANSACTION_WRITTEN;
                } else
                    return isAdded;
            }
            return Constants.NOT_ENOUGH_BALANCE;
        }
        return Constants.INVALID_AMOUNT;
    }

    public int deposit(double amount, String date) {
        if(amount > 0) {
            accountBalance = accountBalance + amount;
            accountTransactions.addDepositSlip(amount, date);
            return Constants.AMOUNT_DEPOSITED;
        } else
            return Constants.INVALID_AMOUNT;
    }

    public int charge(int id, double amount, String date) {
        if(accountBalance-amount <0){
            return 4;
        }
        accountBalance = accountBalance - amount;
        accountTransactions.addTransaction("DEB/CRED", id, amount, date);
        return 7;
    }

    public List<Transaction> getRegister() {
        return accountTransactions.getTransactionList();
    }
}
