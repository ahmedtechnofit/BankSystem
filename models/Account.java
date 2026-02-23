package models;

import java.sql.Timestamp;

public class Account {
    private int accountId;
    private int userId;
    private String accountType;      // saving أو checking
    private String accountNumber;
    private double balance;
    private double transactionLimit;
    private Timestamp createdAt;


    public Account(int accountId, int userId, String accountType, String accountNumber,
                   double balance, double transactionLimit, Timestamp createdAt) {
        this.accountId = accountId;
        this.userId = userId;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactionLimit = transactionLimit;
        this.createdAt = createdAt;
    }

    public Account(int accountId, int userId, String accountType, double balance) {
        this.accountId = accountId;
        this.userId = userId;
        this.accountType = accountType;
        this.balance = balance;
    }


    // Getters & Setters
    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public double getTransactionLimit() { return transactionLimit; }
    public void setTransactionLimit(double transactionLimit) { this.transactionLimit = transactionLimit; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}






















/*
package models;

import java.sql.Timestamp;
import java.util.Date;

public class Account {
    private int accountId;
    private int userId;
    private String accountType;      // saving أو checking
    private String accountNumber;
    private double balance;
    private double transactionLimit;
    private Timestamp createdAt;

    public Account(int accountId, int userId, String accountType, String accountNumber,
                   double balance, double transactionLimit, Timestamp createdAt) {
        this.accountId = accountId;
        this.userId = userId;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactionLimit = transactionLimit;
        this.createdAt = createdAt;
    }

    // Getters & Setters
    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public double getTransactionLimit() { return transactionLimit; }
    public void setTransactionLimit(double transactionLimit) { this.transactionLimit = transactionLimit; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}

*/
