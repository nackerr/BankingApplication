package com.mybank.BankOfRyan;
public class Cheque extends Debits{

    public Cheque(int id, double amount, String dstDate) {
        super(id, amount, dstDate);
    }


    public String toString(){
        StringBuilder strBldr = new StringBuilder();
        strBldr.append(getDebitDate())
                .append("\t  Check #" )
                .append(getIdentifier())
                .append(" \t\t\t ")
                .append(getDebitAmount());
        return strBldr.toString();
    }

}
