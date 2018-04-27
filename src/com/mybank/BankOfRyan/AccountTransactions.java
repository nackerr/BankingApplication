package com.mybank.BankOfRyan;

import java.util.ArrayList;
import java.util.List;
public class AccountTransactions {

    private List<Transaction> transactionList;
    private List<Debits> debitsList = new ArrayList<Debits>();
    private List<Deposits> depositsList = new ArrayList<Deposits>();

    public void addDebitTransaction(Debits debit) {
        this.getDebitsList().add(debit);


    }
    public void addCreditTransaction(Deposits deposit){
        this.getDepositsList().add(deposit);
    }


    public List<Debits> getDebitsList() {
        return debitsList;
    }

    public List<Deposits> getDepositsList() {

        return depositsList;
    }



}