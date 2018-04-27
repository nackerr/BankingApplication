package com.mybank.BankOfRyan;
import java.util.List;

public class Bank {
    private String bankName;
    private String addrHO;
    private List<BankBranch> bankBranches;

    @Override
    public String toString() {
        return "Bank [bankName=" + bankName + ", bankBranches=" + bankBranches + "]";
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setBankBranches(List<BankBranch> bankBranches) {
        this.bankBranches = bankBranches;
    }

    public void setAddrHO(String addrHO) {
        this.addrHO = addrHO;
    }

    public String getAddrHO() {
        return addrHO;
    }

    public String getBankName() {
        return bankName;
    }
}