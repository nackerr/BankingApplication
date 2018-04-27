package com.mybank.BankOfRyan;

public class DirectDeposit extends Deposits{

    public DirectDeposit( double ddAmt, String ddDate) {
        super(ddAmt, ddDate);
    }


    public String toString(){
        StringBuilder strBldr = new StringBuilder();
        strBldr.append(getDepositDate())
                .append("\t  Direct Deposit" )
                .append(" \t ")
                .append(getDepositAmount());
        return strBldr.toString();
    }
}