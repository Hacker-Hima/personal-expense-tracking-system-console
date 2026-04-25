/*package service;

import model.Expense;
import model.RecordType;
import model.User;

public class ReportService {

    public void generateMonthlyReport(int userId, User user, Expense[] records, int count) {

        double totalExpense = 0;

        for (int i = 0; i < count; i++) {
            if (records[i].getUserId() == userId &&
                records[i].getType() == RecordType.EXPENSE) {

                totalExpense += records[i].calculate();
            }
        }

        double income = user.getIncome();
        double balance = income - totalExpense;

        System.out.println("\n===== MONTHLY REPORT =====");
        System.out.println("Total Income  : " + income);
        System.out.println("Total Expense : " + totalExpense);
        System.out.println("Balance       : " + balance);
        System.out.println("==========================");
    }
}

*/