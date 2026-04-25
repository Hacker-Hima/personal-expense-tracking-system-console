package model;

public class Transaction {
    private String username;
    private double amount;
    private RecordType type;
    private Category category;
    private String date;

    public Transaction(String username, double amount, RecordType type, Category category, String date) {
        this.username = username;
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.date = date;
    }

    public String getUsername() { return username; }
    public double getAmount() { return amount; }
    public RecordType getType() { return type; }
    public Category getCategory() { return category; }
    public String getDate() { return date; }
}