package com.example.bas;

import java.util.ArrayList;
import java.util.Scanner;

public class BankSimulation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Account> accounts = new ArrayList<>();
        boolean running = true;

        while (running) {
            System.out.println("\n--- Bank Account Simulation ---");
            System.out.println("1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Show Balance");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");
            System.out.print("Select option: ");
            int option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter Account Number: ");
                    int accNum = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Account Holder Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    double initial = sc.nextDouble();
                    // Check if account number already exists
                    boolean exists = false;
                    for (Account a : accounts) {
                        if (a.getAccountNumber() == accNum) {
                            exists = true;
                            break;
                        }
                    }
                    if (exists) {
                        System.out.println("Account with this number already exists.");
                    } else {
                        accounts.add(new Account(accNum, name, initial));
                        System.out.println("Account created!");
                    }
                    break;
                case 2:
                    Account acc = findAccount(sc, accounts, "deposit into");
                    if (acc != null) {
                        System.out.print("Enter deposit amount: ");
                        double amt = sc.nextDouble();
                        acc.deposit(amt);
                    }
                    break;
                case 3:
                    acc = findAccount(sc, accounts, "withdraw from");
                    if (acc != null) {
                        System.out.print("Enter withdraw amount: ");
                        double amt = sc.nextDouble();
                        acc.withdraw(amt);
                    }
                    break;
                case 4:
                    acc = findAccount(sc, accounts, "show balance for");
                    if (acc != null) {
                        acc.showBalance();
                    }
                    break;
                case 5:
                    acc = findAccount(sc, accounts, "view transactions for");
                    if (acc != null) {
                        acc.showTransactions();
                    }
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting simulation.");
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
        sc.close();
    }

    private static Account findAccount(Scanner sc, ArrayList<Account> accounts, String action) {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available. Create one first.");
            return null;
        }
        System.out.print("Enter Account Number to " + action + ": ");
        int accNum = sc.nextInt();
        for (Account a : accounts) {
            if (a.getAccountNumber() == accNum) {
                return a;
            }
        }
        System.out.println("Account not found.");
        return null;
    }
}
