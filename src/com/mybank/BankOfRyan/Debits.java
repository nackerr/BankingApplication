package com.mybank.BankOfRyan;

public class Debits {
    private int debitNumber;
    private double debitAmount;
    private String debitDate;

    public Debits(int debNum, double debAmt, String debDate) {
        debitNumber = debNum;
        debitAmount = debAmt;
        debitDate = debDate;

    }

    public double getDebitAmount(){

        return debitAmount;
    }

    public String getDebitDate(){

        return  debitDate;
    }

    public int getIdentifier(){

        return debitNumber;
    }


}





