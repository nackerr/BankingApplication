package com.mybank.BankOfRyan;

public class WireDeposit extends Deposits{

    public WireDeposit( double wdAmt, String wdDate) {
        super (wdAmt, wdDate);
    }


    public String toString(){
        StringBuilder strBldr = new StringBuilder();
        strBldr.append(getDepositDate())
                .append("\t  Wire Transfer" )
                .append(" \t ")
                .append(getDepositAmount());
        return strBldr.toString();
    }
}