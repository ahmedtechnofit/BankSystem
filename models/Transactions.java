
package models;

import java.sql.Timestamp;

public class Transactions {
    private int transactionId;
    private int accountId;
    private Integer fromAccountId;
    private Integer toAccountId;
    private String type;            // deposit, withdraw, transfer
    private double amount;
    private String description;
    private Timestamp transactionDate;


    public Transactions(int transactionId, int accountId, Integer fromAccountId, Integer toAccountId,
                        String type, double amount, String description, Timestamp transactionDate) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.transactionDate = transactionDate;
    }

    public Transactions(
            int accountId,
            Integer fromAccountId,
            Integer toAccountId,
            String type,
            double amount,
            String description
    ) {
        this.accountId = accountId;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.type = type;
        this.amount = amount;
        this.description = description;
    }

    public Transactions(int accountId, Integer fromAccountId, Integer toAccountId,
                        String type, double amount, String description, Timestamp transactionDate) {
        this.accountId = accountId;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.transactionDate = transactionDate;
    }



    // Getters & Setters
    public int getTransactionId() { return transactionId; }
    public void setTransactionId(int transactionId) { this.transactionId = transactionId; }
    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }
    public Integer getFromAccountId() { return fromAccountId; }
    public void setFromAccountId(Integer fromAccountId) { this.fromAccountId = fromAccountId; }
    public Integer getToAccountId() { return toAccountId; }
    public void setToAccountId(Integer toAccountId) { this.toAccountId = toAccountId; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Timestamp getTransactionDate() { return transactionDate; }
    public void setTransactionDate(Timestamp transactionDate) { this.transactionDate = transactionDate; }
}






















/*
package models;

import java.sql.Timestamp;

public class Transactions {
    private int transactionId;
    private int accountId;
    private Integer fromAccountId;  // ممكن تكون null
    private Integer toAccountId;    // ممكن تكون null
    private String type;            // deposit, withdraw, transfer
    private double amount;
    private String description;
    private Timestamp transactionDate;

    public Transactions(int transactionId, int accountId, Integer fromAccountId, Integer toAccountId,
                       String type, double amount, String description, Timestamp transactionDate) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.transactionDate = transactionDate;
    }

    // Getters & Setters
    public int getTransactionId() { return transactionId; }
    public void setTransactionId(int transactionId) { this.transactionId = transactionId; }
    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }
    public Integer getFromAccountId() { return fromAccountId; }
    public void setFromAccountId(Integer fromAccountId) { this.fromAccountId = fromAccountId; }
    public Integer getToAccountId() { return toAccountId; }
    public void setToAccountId(Integer toAccountId) { this.toAccountId = toAccountId; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Timestamp getTransactionDate() { return transactionDate; }
    public void setTransactionDate(Timestamp transactionDate) { this.transactionDate = transactionDate; }
}
*/
