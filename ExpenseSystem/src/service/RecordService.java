/*package service;

import model.*;
import util.FileUtil;
import java.util.*;

public class RecordService {

    private List<Record> records;

    public RecordService() {
        records = FileUtil.loadRecords();
    }

    public void addRecord(Record record) {
        records.add(record);
        FileUtil.saveRecords(records);
    }

    public List<Record> getUserRecords(String username) {
        List<Record> result = new ArrayList<>();

        for (Record r : records) {
            if (r.getUsername().equals(username)) {
                result.add(r);
            }
        }

        return result;
    }

    public double getTotal(String username, RecordType type) {
        double total = 0;

        for (Record r : records) {
            if (r.getUsername().equals(username) && r.getType() == type) {
                total += r.getAmount();
            }
        }

        return total;
    }

    public Map<Category, Double> getCategoryReport(String username) {
        Map<Category, Double> map = new HashMap<>();

        for (Record r : records) {
            if (r.getUsername().equals(username)) {
                map.put(r.getCategory(),
                        map.getOrDefault(r.getCategory(), 0.0) + r.getAmount());
            }
        }

        return map;
    }
}*/