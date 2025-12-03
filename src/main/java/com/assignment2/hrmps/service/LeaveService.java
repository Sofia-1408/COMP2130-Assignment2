package com.assignment2.hrmps.service;

import com.assignment2.hrmps.model.LeaveRecord;

import java.io.*;
import java.util.ArrayList;

public class LeaveService {

    private ArrayList<LeaveRecord> records;
    private final String FILE_NAME = "leave.ser";

    public LeaveService() {
        records = loadRecords();
    }

    public ArrayList<LeaveRecord> getAllRecords() {
        return records;
    }

    public void addRecord(LeaveRecord record) {
        records.add(record);
        saveRecords();
    }

    private ArrayList<LeaveRecord> loadRecords() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<LeaveRecord>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private void saveRecords() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(records);
        } catch (IOException e) {
            System.out.println("Error saving leave records.");
        }
    }
}