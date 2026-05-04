package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.util.FileUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private static final String FILE = "users.txt";

    public void addUser(User u) {
        u.setUserId(FileUtil.generateId(FILE, "USR"));
        FileUtil.appendLine(FILE, u.toFileString());
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        for (String line : FileUtil.readLines(FILE)) {
            try { list.add(User.fromFileString(line)); }
            catch (Exception e) { System.err.println("Skipping bad line: " + line); }
        }
        return list;
    }

    public User findById(String userId) {
        for (User u : getAllUsers()) {
            if (u.getUserId().equals(userId)) return u;
        }
        return null;
    }

    public User findByUsername(String username) {
        for (User u : getAllUsers()) {
            if (u.getUsername().equalsIgnoreCase(username)) return u;
        }
        return null;
    }

    public boolean updateUser(int index, User updated) {
        return FileUtil.updateLine(FILE, index, updated.toFileString());
    }

    public boolean deleteUser(int index) {
        return FileUtil.deleteLine(FILE, index);
    }

    public int getIndexById(String userId) {
        List<String> lines = FileUtil.readLines(FILE);
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).startsWith(userId + ",")) return i;
        }
        return -1;
    }
}