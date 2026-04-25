package service;

import model.User;
import util.FileUtil;
import java.util.*;

public class UserService {

    private List<User> users;
    private Map<String, User> userMap;

    public UserService() {
        users = FileUtil.loadUsers();
        userMap = new HashMap<>();

        for (User u : users) {
            userMap.put(u.getUsername(), u);
        }
    }

    public boolean register(String username, String password) {
        if (userMap.containsKey(username)) return false;

        User u = new User(username, password);
        users.add(u);
        userMap.put(username, u);

        FileUtil.saveUsers(users);
        return true;
    }

    public User login(String username, String password) {
        User u = userMap.get(username);
        if (u != null && u.getPassword().equals(password)) return u;
        return null;
    }
    public List<User> getAllUsers() {
    return users;
}
}