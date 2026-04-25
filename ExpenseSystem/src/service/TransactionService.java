package service;

import model.*;
import util.FileUtil;
import java.util.*;

public class TransactionService {

    private List<Transaction> records;

    public TransactionService() {
        records = FileUtil.loadRecords();
    }

    public void add(Transaction r) {
        records.add(r);
        FileUtil.saveRecords(records);
    }

    public List<Transaction> getUserRecords(String username) {
        List<Transaction> list = new ArrayList<>();
        for (Transaction r : records) {
            if (r.getUsername().equals(username)) {
                list.add(r);
            }
        }
        return list;
    }

    public double getTotal(String username, RecordType type) {
        double sum = 0;
        for (Transaction r : records) {
            if (r.getUsername().equals(username) && r.getType() == type) {
                sum += r.getAmount();
            }
        }
        return sum;
    }

    public Map<Category, Double> getCategoryReport(String username) {
        Map<Category, Double> map = new HashMap<>();

        for (Transaction r : records) {
            if (r.getUsername().equals(username)) {
                map.put(r.getCategory(),
                        map.getOrDefault(r.getCategory(), 0.0) + r.getAmount());
            }
        }

        return map;
    }
    public List<Transaction> getAllRecords() {
    return records;
}

public double getTotalAll(RecordType type) {
    double sum = 0;

    for (Transaction t : records) {
        if (t.getType() == type) {
            sum += t.getAmount();
        }
    }

    return sum;
}
}