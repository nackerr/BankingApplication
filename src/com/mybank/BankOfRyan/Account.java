package com.mybank.BankOfRyan;

public class Account {

    private double accountBalance;

    private AccountTransactions accountTransactions;

    public AccountTransactions getAccountTransactionsList() {
        return accountTransactions;
    }


    public Account(double balance) {
        accountBalance = balance;
        accountTransactions = new AccountTransactions();
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public int deposit(Deposits deposit) {
        if(deposit.getDepositAmount() > 0) {
            accountBalance = accountBalance + deposit.getDepositAmount();
            accountTransactions.addCreditTransaction(deposit);
            return Constants.AMOUNT_DEPOSITED;
        } else
            return Constants.INVALID_AMOUNT;
    }
    public int addDebit(Debits debitTransaction) {
        if(accountBalance- debitTransaction.getDebitAmount() <0){
            return -1;
        }
        accountBalance = accountBalance - debitTransaction.getDebitAmount();
        accountTransactions.addDebitTransaction(debitTransaction);
        return 1;
    }


}