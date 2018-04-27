package com.mybank.BankOfRyan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import com.mybank.BankOfRyan.Account;
import com.mybank.BankOfRyan.AccountHolder;
import com.mybank.BankOfRyan.Transaction;
import com.mybank.BankOfRyan.Constants;

public class BankingApplication {

    public enum Command {
        CREATE, CREATEBRANCH, BALANCE, CHECK, TRANSACTIONS, WIREDEPOSIT, DIRECTDEPOSIT, REGISTER, CHARGE, INVALID_COMMAND;
        public static Command toStr(String str) {
            try {
                return valueOf(str);
            } catch (Exception ex) {
                return INVALID_COMMAND;
            }
        }
    }

    public static void main(String[] args) {
        AccountHolder accountHolder = null;
        Account account = null;
        BankBranch branch = null;
        BankingApplication myBank = new BankingApplication();

        // 2. To read input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 3. Tokenize the input
        StringTokenizer st;
        String query = "";
        String[] input;
        int tNum;
        double tAmount;
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
                        // 6. Repeat until user enters quit
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
                            int i2 = 0;
                            while (st.hasMoreTokens()) {
                                String token = st.nextToken();
                                input[i2] = token;
                                i2++;
                            }

                            // 5. Find the command
                            switch (Command.toStr(input[0].toUpperCase())) {
                                case CREATE: {
                                    if (input.length == 5) {
                                        String name2 = input[1];
                                        String accType = input[2];
                                        long bankZip = Long.parseLong(input[3]);
                                        double minAmt = Double.parseDouble(input[4]);
                                        account = new Account(12006, accType, minAmt);
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
                                        tNum = Integer.parseInt(input[1]);
                                        tAmount = Double.parseDouble(input[2]);
                                        myBank.processWriteCommand(tNum, tAmount, currentDate, account);
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
                                        list = account.getRegister();
                                        myBank.printList(list);
                                        System.out.println("\t\t\t         Balance " + account.getAccountBalance());
                                    } else
                                        System.out.println("Command Error: PLEASE USE the command '[Register]'");
                                    break;
                                }
                                case TRANSACTIONS: {
                                    if (input.length == 3) {
                                        list = account.getTransactionsList().transactionRange(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
                                        myBank.printList(list);
                                    } else
                                        System.out.println("Please Enter the Start and End Range");
                                    break;
                                }
                                case CHARGE: {
                                    if (input.length == 3) {
                                        try {

                                            int id = Integer.parseInt(input[1]);
                                            double amt = Double.parseDouble(input[2]);
                                            int isValid = account.charge(id, amt, currentDate);
                                            if (isValid == Constants.AMOUNT_CHARGED)
                                                System.out.println("Charged $" + input[2] + " on " + currentDate);
                                            else if (isValid == Constants.INVALID_AMOUNT)
                                                System.out.println("Please enter a valid Amount");
                                        } catch (NumberFormatException e) {
                                            System.out.println("Please Enter a Number");
                                        }
                                    } else
                                        System.out.println("Please Enter the Charge Amount.");
                                    break;
                                }
                                case DIRECTDEPOSIT: {
                                    if (input.length == 2) {
                                        try {
                                            double amt = Double.parseDouble(input[1]);
                                            int isValid = account.deposit(amt, currentDate);
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
                                            int isValid = account.deposit(amt, currentDate);
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

    public void processWriteCommand(int chkNum, double chkAmount, String date, Account account) {
        int isValid = account.writeCheque(chkNum, chkAmount, date);
        switch (isValid) {
            case Constants.TRANSACTION_WRITTEN:
                System.out.println("Cheque #" + chkNum + " for $" + chkAmount + " written");
                break;
            case Constants.NOT_ENOUGH_BALANCE:
                System.out.println("You do not have sufficient balance");
                break;
            case Constants.INVALID_TRANSACTION_NUMBER:
                System.out.println("Invalid Cheque Number.");
                break;
            case Constants.DUPLICATE_TRANSACTION_NUMBER:
                System.out.println("Cheque number #" + chkNum + " is already used");
                break;
            case Constants.INVALID_AMOUNT:
                System.out.println("Please enter a valid Amount");
                break;
        }
    }

    public void printList(List<Transaction> list) {
        Iterator<Transaction> it = list.iterator();
        if (list.isEmpty())
            System.out.println("No Transactions");
        else {
            System.out.println("Date\t\t Transaction Type\t Amount");
            while (it.hasNext()) {
                Transaction t = it.next();
                int chkNum = t.getTransactionNumber();
                double chkAmount = t.getTransactionAmount();
                String date = t.getTransactionDate();
                String type = t.getTransactionType();
                if (chkNum == -1) {
                    System.out.print(date);
                    System.out.print("\t     Deposit \t\t ");
                    System.out.println(chkAmount);
                } else {
                    System.out.print(date);
                    System.out.print("\t     " + type + " #" + chkNum + " \t\t ");
                    System.out.println(chkAmount);
                }
            }
        }
    }
}