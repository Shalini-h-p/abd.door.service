package com.abd.exam.Service;

import com.abd.exam.model.Employee;
import com.abd.exam.model.History;
import com.abd.exam.repository.EmployeeRepository;
import com.abd.exam.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private HistoryRepository historyRepository;

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployee(int id) {
        return employeeRepository.findById(id);
    }

    public Employee createEmployee(Employee employee) {
        Random rand = new Random();
        employee.setCreatedOn(LocalDate.now());
        employee.setLastModifiedOn(LocalDate.now());
        employee.setCode(rand.nextInt(10000 - 1000) + 1000);
        Employee result = employeeRepository.save(employee);
        return result;
    }

    public Employee updateEmployee(Employee employee) {
        Optional<Employee> dbEmployee = employeeRepository.findById(employee.getId());
        if (dbEmployee.isPresent()) {
            Employee oldEmployee = dbEmployee.get();
            oldEmployee.setCode(employee.getCode());
            oldEmployee.setEmail(employee.getEmail());
            oldEmployee.setFullName(employee.getFullName());
            oldEmployee.setLastModifiedOn(LocalDate.now());
            employeeRepository.save(oldEmployee);
            return oldEmployee;
        } else {
            return createEmployee(employee);
        }
    }

    public void deleteEmployee(int employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            List<History> histories = historyRepository.findAllByEmployee(employee.get());
            historyRepository.deleteAll(histories);
            employeeRepository.delete(employee.get());
        }
    }
}
