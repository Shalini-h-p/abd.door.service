package com.abd.exam;
import com.abd.exam.Service.EmployeeService;
import com.abd.exam.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeServiceTests {

    @Autowired
    private EmployeeService employeeService;
    private static Employee employee = new Employee();

    @BeforeAll
    public static void setup() {
        UUID uuid = UUID.randomUUID();
        employee.setFullName("User " + uuid);
        employee.setEmail(uuid + "@demo.com");
    }

    @Test
    @Order(2)
    public void getEmployeesTest() {
        List<Employee> employees = employeeService.getEmployees();
        Assertions.assertTrue(employees != null);
    }

    @Test
    @Order(1)
    public void createEmployeeTest() {
        employee = employeeService.createEmployee(employee);
        Assertions.assertTrue(employee instanceof Employee);
    }

    @Test
    @Order(3)
    public void getEmployeeTest() {
        Optional<Employee> emp = employeeService.getEmployee(employee.getId());
        Assertions.assertTrue(emp.isPresent());
    }

    @Test
    @Order(4)
    public void updateEmployeeTest() {
        employee.setFullName("User1");
        employee = employeeService.updateEmployee(employee);
        Assertions.assertTrue(employee instanceof Employee);
    }

    @Test
    @Order(5)
    public void deleteEmployeeTest() {
        employeeService.deleteEmployee(employee.getId());
        Optional<Employee> deletedEmployee = employeeService.getEmployee(employee.getId());
        Assertions.assertTrue(!deletedEmployee.isPresent());
    }
}
