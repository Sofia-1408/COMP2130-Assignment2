package com.assignment2.hrmps.service;

import com.assignment2.hrmps.model.AttendanceRecord;

import java.io.*;
import java.util.ArrayList;

public class AttendanceService {

    private ArrayList<AttendanceRecord> records;
    private final String FILE_NAME = "attendance.ser";

    public AttendanceService() {
        records = loadRecords();
    }

    public ArrayList<AttendanceRecord> getAllRecords() {
        return records;
    }

    public void addRecord(AttendanceRecord record) {
        records.add(record);
        saveRecords();
    }

    private ArrayList<AttendanceRecord> loadRecords() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<AttendanceRecord>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private void saveRecords() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(records);
        } catch (IOException e) {
            System.out.println("Error saving attendance records.");
        }
    }
}