package com.assignment2.hrmps.authentication;

import java.io.*;
import java.util.ArrayList;

public class AuthService {

    private ArrayList<User> users;
    private final String FILE_NAME = "users.ser";

    public AuthService() {
        users = loadUsers();

        // Add default users if none exist
        if (users.isEmpty()) {
            users.add(new User("admin", "admin123", Role.ADMIN));
            users.add(new User("employee", "emp123", Role.EMPLOYEE));
            saveUsers();
        }
    }

    public boolean login(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void addUser(User user) {
        users.add(user);
        saveUsers();
    }

    private ArrayList<User> loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<User>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(users);
        } catch (IOException e) {
            System.out.println("Error saving users.");
        }
    }

    public User findUser(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }
}
