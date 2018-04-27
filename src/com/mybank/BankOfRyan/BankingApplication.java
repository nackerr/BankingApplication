package com.mybank.BankOfRyan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class BankingApplication {

    public enum Command {
        CREATE, CREATEBRANCH, BALANCE, CHARGE, CHECK, TRANSACTIONS, WIREDEPOSIT, DIRECTDEPOSIT, REGISTER ,DEPOSITS, CHECKS, INVALID_COMMAND;

        public static Command toStr(String str) {
            try {
                return valueOf(str);
            } catch (Exception ex) {
                return INVALID_COMMAND;
            }
        }
    }

    public static void main(String[] args) {
        Account account = null;
        AccountHolder accountHolder = null;
        BankingApplication myBank = new BankingApplication();
        BankBranch branch = null;


        // 2. To read input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 3. Tokenize the input
        StringTokenizer st;
        String query = "";
        String[] input;
        int chkNum;
        double chkAmount;
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String currentDate = dateFormat.format(Calendar.getInstance().getTime());

        List list;

        System.out.println("Create a new branch using the command 'CreateBranch [Name] [Zip] [State] [Country] [Number (No Spaces)]' to do transactions");

        while (true) {
            try {
                // 2. To read input
                query = br.readLine();
                if (query.equalsIgnoreCase("quit"))
                    break;
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 3. Tokenize the input
            st = new StringTokenizer(query);
            input = new String[st.countTokens()];
            int i = 0;
            while (st.hasMoreTokens()) {
                String token = st.nextToken();
                input[i] = token;
                i++;
            }
            switch (Command.toStr(input[0].toUpperCase())) {
                case CREATEBRANCH: {
                    if (input.length == 6) {
                        String name = input[1];
                        Long zip = Long.parseLong(input[2]);
                        String state = input[3];
                        String country = input[4];
                        String number = input[5];
                        branch = new BankBranch(state, zip, country, name, number);
                        System.out.println(
                                "Branch Created with ZIP: " + zip);

                        System.out.println("Create a new account using the command 'Create [Name] [Account Type] [BankZIP] [Minimum Amount]' to do transactions");
                        while (true) {
                            try {
                                query = br.readLine();
                                if (query.equalsIgnoreCase("quit"))
                                    break;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            st = new StringTokenizer(query);
                            input = new String[st.countTokens()];
                            int i2 = 0;
                            while (st.hasMoreTokens()) {
                                String token = st.nextToken();
                                input[i2] = token;
                                i2++;
                            }

                            switch (Command.toStr(input[0].toUpperCase())) {
                                case CREATE: {
                                    if (input.length == 5) {
                                        String name2 = input[1];
                                        long bankZip = Long.parseLong(input[3]);
                                        double minAmt = Double.parseDouble(input[4]);
                                        account = new Account(minAmt);
                                        accountHolder = new AccountHolder(bankZip, name2, account);
                                        System.out.println(
                                                "Account created with minimum balance #" + minAmt);
                                        branch.setAccountNum(branch.getAccountNum() + 1);
                                    } else
                                        System.out.println(
                                                "Please use the Command 'Create [Name] [Account Type] [Minimum Amount]'");
                                    break;
                                }
                                case CHECK: {
                                    if (input.length == 3) {
                                        chkNum = Integer.parseInt(input[1]);
                                        chkAmount = Double.parseDouble(input[2]);
                                        Cheque chk =
                                                new Cheque(chkNum, chkAmount, currentDate);
                                        account.addDebit(chk);
                                        System.out.println("CHECK " + chkNum + " for $" + chk.getDebitAmount() + " written");

                                    } else
                                        System.out.println(
                                                "Cheque Number or Amount is missing. Please use the Command 'write [Cheque Number] [Amount]'");
                                    break;
                                }
                                case BALANCE: {
                                    if (input.length == 1)
                                        System.out.println("Your Balance is " + account.getAccountBalance());
                                    else
                                        System.out.println("Command Error: PLEASE USE the command '[Balance]'");
                                    break;
                                }
                                case REGISTER: {
                                    if (input.length == 1) {
                                        myBank.printList(account);
                                        System.out.println("\t\t\t         Balance " + account.getAccountBalance());
                                    } else
                                        System.out.println("Command Error: PLEASE USE the command '[Register]'");
                                    break;
                                }
                                case CHARGE: {

                                    if (input.length == 3) {
                                        chkNum = Integer.parseInt(input[1]);
                                        chkAmount = Double.parseDouble(input[2]);
                                        CreditCardCharge chk =
                                                new CreditCardCharge(chkNum, chkAmount, currentDate);
                                        if(chkAmount <= account.getAccountBalance()){
                                            account.addDebit(chk);
                                            System.out.println("CREDITCARD " + chkNum + " for $" + chk.getDebitAmount() + " written");
                                        } else
                                            System.out.println(
                                                    "You do not have sufficient balance"
                                            );

                                    }
                                    else
                                        System.out.println(
                                                "Cheque Number or Amount is missing. Please use the Command 'write [Cheque Number] [Amount]'");
                                    break;
                                }
                                case DIRECTDEPOSIT: {
                                    if (input.length == 2) {
                                        try {
                                            double amt = Double.parseDouble(input[1]);
                                            int isValid = account.deposit(new DirectDeposit(amt, currentDate));
                                            if (isValid == Constants.AMOUNT_DEPOSITED)
                                                System.out.println("Deposited $" + input[1] + " on " + currentDate);
                                            else if (isValid == Constants.INVALID_AMOUNT)
                                                System.out.println("Please enter a valid Amount");
                                        } catch (NumberFormatException e) {
                                            System.out.println("Please Enter a Number");
                                        }
                                    } else
                                        System.out.println("Please Enter the Deposit Amount.");
                                    break;
                                }
                                case WIREDEPOSIT: {
                                    if (input.length == 2) {
                                        try {
                                            double amt = Double.parseDouble(input[1]);
                                            int isValid = account.deposit(new WireDeposit(amt, currentDate));
                                            if (isValid == Constants.AMOUNT_DEPOSITED)
                                                System.out.println("Deposited $" + input[1] + " on " + currentDate);
                                            else if (isValid == Constants.INVALID_AMOUNT)
                                                System.out.println("Please enter a valid Amount");
                                        } catch (NumberFormatException e) {
                                            System.out.println("Please Enter a Number");
                                        }
                                    } else
                                        System.out.println("Please Enter the Deposit Amount.");
                                    break;
                                }
                                case INVALID_COMMAND: {
                                    System.out.println("Invalid Command. Commands - CREATE, CREATEBRANCH, CHECK, WIREDEPOSIT, DIRECTDEPOSIT, BALANCE, TRANSACTIONS, REGISTER");
                                    break;
                                }
                            }
                        }

                    } else
                        System.out.println(
                                "Please use the Command 'CreateBranch [Name] [Zip] [State] [Country] [Number (No Spaces)'");
                    break;
                }
            }
        }

    }
    public void printList(Account thisAccount) {
        List<Debits> debits = thisAccount.getAccountTransactionsList().getDebitsList();
        if (debits == null) {
            debits = new ArrayList<Debits>();
        }
        List<Deposits> credits = thisAccount.getAccountTransactionsList().getDepositsList();
        if (credits == null) {
            credits = new ArrayList<Deposits>();
        }
        if (debits.isEmpty() && credits.isEmpty()) {
            System.out.println("No Transactions");
        } else
            System.out.println("Date\t\t Transaction Type\t Amount");
        if (debits != null) {
            for (Debits deb : debits) {

                System.out.println(deb);
            }
        }
        if (credits != null)
            for (Deposits dep : credits) {

                System.out.println(dep);
            }
    }

}