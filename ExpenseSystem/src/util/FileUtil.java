package util;

import model.*;
import java.io.*;
import java.util.*;

public class FileUtil {

    private static final String USER_FILE = "users.txt";
    private static final String RECORD_FILE = "records.txt";

    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length >= 2) {
                    users.add(new User(p[0], p[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("User file not found.");
        }

        return users;
    }

    public static void saveUsers(List<User> users) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE))) {
            for (User u : users) {
                bw.write(u.getUsername() + "," + u.getPassword());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Transaction> loadRecords() {
        List<Transaction> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(RECORD_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length >= 5) {
                    list.add(new Transaction(
                            p[0],
                            Double.parseDouble(p[1]),
                            RecordType.valueOf(p[2]),
                            Category.valueOf(p[3]),
                            p[4]
                    ));
                }
            }
        } catch (IOException e) {
            System.out.println("Record file not found.");
        }

        return list;
    }

    public static void saveRecords(List<Transaction> records) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RECORD_FILE))) {
            for (Transaction r : records) {
                bw.write(r.getUsername() + "," +
                        r.getAmount() + "," +
                        r.getType() + "," +
                        r.getCategory() + "," +
                        r.getDate());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}