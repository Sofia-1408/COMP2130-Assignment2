package com.assignment2.hrmps.service;

import com.assignment2.hrmps.model.Employee;
import com.assignment2.hrmps.model.PayrollRecord;
import com.assignment2.hrmps.repository.EmployeeRepository;
import com.assignment2.hrmps.repository.PayrollRepository;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportService {

    private final PayrollRepository payrollRepository;
    private final EmployeeRepository employeeRepository;
    //Constructor
    public ReportService(PayrollRepository payrollRepository,
                         EmployeeRepository employeeRepository) {
        this.payrollRepository = payrollRepository;
        this.employeeRepository = employeeRepository;
    }
    //Returns records for a specific employee
    public List<PayrollRecord> getPayrollForEmployee(String employeeId) {
        return payrollRepository.findByEmployee(employeeId);
    }
    //Returns pay for a specific employee
    public double getTotalPaidToEmployee(String employeeId) {
        return payrollRepository.findByEmployee(employeeId).stream()
                .mapToDouble(PayrollRecord::getNetPay)
                .sum();
    }
    //Returns pay for a specific department
    public double getTotalPaidForDepartment(String departmentId) {
        Map<String, Employee> employeesById = employeeRepository.findAll().stream()
                .filter(e -> e.getDepartment() != null)
                .filter(e -> departmentId.equals(e.getDepartment().getId()))
                .collect(Collectors.toMap(Employee::getId, e -> e));

        return payrollRepository.findAll().stream()
                .filter(p -> employeesById.containsKey(p.getEmployeeId()))
                .mapToDouble(PayrollRecord::getNetPay)
                .sum();
    }
    //Returns pay for a specific department for a specific month
    public double getTotalPaidForDepartmentInPeriod(String departmentId,
                                                    YearMonth from,
                                                    YearMonth to) {
        Map<String, Employee> employeesById = employeeRepository.findAll().stream()
                .filter(e -> e.getDepartment() != null)
                .filter(e -> departmentId.equals(e.getDepartment().getId()))
                .collect(Collectors.toMap(Employee::getId, e -> e));

        return payrollRepository.findAll().stream()
                .filter(p -> employeesById.containsKey(p.getEmployeeId()))
                .filter(p -> !p.getPeriod().isBefore(from) && !p.getPeriod().isAfter(to))
                .mapToDouble(PayrollRecord::getNetPay)
                .sum();
    }
}
