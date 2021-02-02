package com.abd.exam.controller;
import com.abd.exam.Service.EmployeeService;
import com.abd.exam.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String index() {
        return "redirect:/employee";
    }

    @GetMapping("employee")
    public String getEmployees(Model model) {
        List<Employee> result = employeeService.getEmployees();
        model.addAttribute("employees", result);
        return "employee";
    }

    // Delete Employee
    @GetMapping("/employee-delete/{id}")
    public String deleteEmployee(@PathVariable("id") int id, Model model) {
        employeeService.deleteEmployee(id);
        return "redirect:/employee";
    }

    // Add Employee
    @GetMapping("/employee-add")
    public String createEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee_add";
    }

    @PostMapping("/employee-add")
    public String createEmployee(@Valid Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "employee_add";
        }
        employeeService.createEmployee(employee);
        return "redirect:/employee";
    }

    // Edit Employee
    @GetMapping("/employee-update/{id}")
    public String EditEmployee(@PathVariable("id") int id, Model model) {
        Optional<Employee> result = employeeService.getEmployee(id);
        if(result.isPresent())
        {
            model.addAttribute("employee", result.get());
            return "employee_update";
        } else  {
            return "redirect:/employee";
        }
    }

    @PostMapping("/employee-update/{id}")
    public String EditEmployee(@PathVariable("id") int id, @Valid Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "employee_update";
        }
        Employee updateResult = employeeService.updateEmployee(employee);
        return "redirect:/employee";
    }
}
