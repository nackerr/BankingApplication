package com.mybank.BankOfRyan;

public class CreditCardCharge extends Debits{

    public CreditCardCharge(int id, double amnt, String dstDate) {
        super(id, amnt, dstDate);
    }


    public String toString(){
        StringBuilder strBldr = new StringBuilder();
        strBldr.append(getDebitDate())
                .append("\t  CHARGE #" )
                .append(getIdentifier())
                .append(" \t\t ")
                .append(getDebitAmount());
        return strBldr.toString();
    }
}