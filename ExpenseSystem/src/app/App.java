package app;

import model.*;
import service.*;
import java.util.*;

public class App {

    private Scanner sc = new Scanner(System.in);
    private UserService userService = new UserService();
    private TransactionService service = new TransactionService();

    private final String ADMIN_USER = "admin";
    private final String ADMIN_PASS = "1234";

    public void start() {

        while (true) {
            System.out.println("\n===== EXPENSE TRACKER =====");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Register");
            System.out.println("4. Exit");
            System.out.println("========================");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 4) break;

            System.out.println("\n===== ENTER VALID CREDENTIALS =====");
            System.out.print("Username: ");
            String username = sc.nextLine();

            System.out.print("Password: ");
            String password = sc.nextLine();
            System.out.println("========================");

            if (choice == 1) {
                if (username.equals(ADMIN_USER) && password.equals(ADMIN_PASS)) {
                    adminMenu();
                } else {
                    System.out.println("Invalid Admin Login!");
                }
            }

            else if (choice == 2) {
                User user = userService.login(username, password);
                if (user != null) {
                    userMenu(username);
                } else {
                    System.out.println("Invalid User Login!");
                }
            }

            else if (choice == 3) {
                if (userService.register(username, password)) {
                    System.out.println("User Registered!");
                } else {
                    System.out.println("User already exists!");
                }
            }
        }
    }

    // ================= ADMIN MENU =================

    private void adminMenu() {
        while (true) {
            System.out.println("\n===== ADMIN PANEL =====");
            System.out.println("1. View All Users");
            System.out.println("2. View All Transactions");
            System.out.println("3. System Report");
            System.out.println("4. Logout");
            System.out.println("========================");

            int op = sc.nextInt();
            sc.nextLine();

            if (op == 4) break;

            if (op == 1) {
                List<User> users = userService.getAllUsers();
                for (User u : users) {
                    System.out.println("User: " + u.getUsername());
                }
            }

            if (op == 2) {
                List<Transaction> list = service.getAllRecords();
                System.out.println("\n--- ALL TRANSACTIONS ---");

                for (Transaction t : list) {
                    System.out.println(
                        t.getUsername() + " | " +
                        t.getType() + " | " +
                        t.getCategory() + " | ₹" +
                        t.getAmount()
                    );
                }
                System.out.println("========================");
            }

            if (op == 3) {
                double exp = service.getTotalAll(RecordType.EXPENSE);
                double inc = service.getTotalAll(RecordType.INCOME);

                System.out.println("\n====== SYSTEM REPORT ======");
                System.out.println("Total Expense: " + exp);
                System.out.println("Total Income: " + inc);
                System.out.println("System Balance: " + (inc - exp));
                System.out.println("===========================");
            }
        }
    }

    // ================= USER MENU =================

    private void userMenu(String username) {
        while (true) {
            System.out.println("\n===== USER PANEL =====");
            System.out.println("1. Add Expense");
            System.out.println("2. Add Income");
            System.out.println("3. View Report");
            System.out.println("4. View Transactions");
            System.out.println("5. Logout");
            System.out.println("========================");

            int op = sc.nextInt();
            sc.nextLine();

            if (op == 5) break;

            // ================= ADD TRANSACTION =================
            if (op == 1 || op == 2) {

                System.out.print("Amount: ");
                double amt = sc.nextDouble();
                sc.nextLine();

                System.out.println("\n------------------------");
                System.out.println("Select Category:");

                Category[] categories = Category.values();

                for (int i = 0; i < categories.length; i++) {
                    System.out.println((i + 1) + ". " + categories[i]);
                }

                System.out.print("Enter choice: ");
                int catChoice = sc.nextInt();
                sc.nextLine();

                Category category;

                if (catChoice >= 1 && catChoice <= categories.length) {
                    category = categories[catChoice - 1];
                } else {
                    System.out.println("Invalid choice! Using OTHER.");
                    category = Category.OTHER;
                }

                System.out.println("Selected Category: " + category);

                // 🔥 OPTIONAL DESCRIPTION
                System.out.print("Description: ");
                //String desc = sc.nextLine();

                RecordType type = (op == 1) ? RecordType.EXPENSE : RecordType.INCOME;

                service.add(new Transaction(
                        username,
                        amt,
                        type,
                        category,
                        java.time.LocalDate.now().toString()
                ));

                System.out.println("Transaction Added Successfully!");
                System.out.println("------------------------");
            }

            // ================= REPORT =================
            if (op == 3) {
                Map<Category, Double> map = service.getCategoryReport(username);

                System.out.println("\n--- CATEGORY REPORT ---");

                for (Map.Entry<Category, Double> entry : map.entrySet()) {
                    System.out.printf("%-10s : %.2f\n",
                            entry.getKey(), entry.getValue());
                }

                double exp = service.getTotal(username, RecordType.EXPENSE);
                double inc = service.getTotal(username, RecordType.INCOME);

                System.out.println("------------------------");
                System.out.println("Expense: " + exp);
                System.out.println("Income: " + inc);
                System.out.println("Balance: " + (inc - exp));
                System.out.println("------------------------");
            }

            // ================= VIEW TRANSACTIONS =================
            if (op == 4) {
                List<Transaction> list = service.getUserRecords(username);

                System.out.println("\n--- TRANSACTIONS ---");

                for (Transaction t : list) {
                    System.out.println(
                        t.getDate() + " | " +
                        t.getType() + " | " +
                        t.getCategory() + " | ₹" +
                        t.getAmount()
                    );
                }

                System.out.println("------------------------");
            }
        }
    }
}