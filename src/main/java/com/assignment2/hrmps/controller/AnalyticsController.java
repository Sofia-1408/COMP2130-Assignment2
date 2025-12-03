package com.assignment2.hrmps.controller;

import com.assignment2.hrmps.model.LeaveRecord;
import com.assignment2.hrmps.service.LeaveService;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

import java.util.HashMap;
import java.util.Map;

public class AnalyticsController {

    @FXML
    private PieChart leavePieChart;

    private LeaveService leaveService = new LeaveService();

    @FXML
    public void initialize() {
        // Load all leave records
        var records = leaveService.getAllRecords();

        // Count leaves by type (Vacation, Sick, Personal, etc.)
        Map<String, Integer> counts = new HashMap<>();
        for (LeaveRecord record : records) {
            String type = record.getType();
            counts.put(type, counts.getOrDefault(type, 0) + 1);
        }

        // Populate the pie chart
        leavePieChart.getData().clear();
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            leavePieChart.getData().add(
                    new PieChart.Data(entry.getKey(), entry.getValue())
            );
        }
    }
}
