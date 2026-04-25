package model;

import java.time.LocalDate;

public class Income {

    int incomeId;
    private int userId;
    private double amount;
    private LocalDate date;
    private String source;

    public Income(int incomeId, int userId, double amount, LocalDate date, String source) {
        this.incomeId = incomeId;
        this.userId = userId;
        this.amount = amount;
        this.date = date;
        this.source = source;
    }

    public int getUserId() {
        return userId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getSource() {
        return source;
    }
}
