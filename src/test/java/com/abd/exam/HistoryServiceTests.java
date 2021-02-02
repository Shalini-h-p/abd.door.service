package com.abd.exam;
import com.abd.exam.Service.EmployeeService;
import com.abd.exam.Service.HistoryService;
import com.abd.exam.common.LogType;
import com.abd.exam.dto.LogDto;
import com.abd.exam.model.Employee;
import com.abd.exam.model.History;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HistoryServiceTests {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private HistoryService historyService;

    @Test
    @Order(1)
    public void getHistoriesTest() {
        List<History> histories = historyService.getHistories();
        Assertions.assertTrue(histories != null);
    }

    @Test
    @Order(2)
    public void addHistoryTest() {
        Employee employee = new Employee();
        employee.setFullName("log Test User");
        employee.setEmail("logtest@demo.com");
        employee = employeeService.createEmployee(employee);

        LogDto logDto = new LogDto();
        logDto.setType(LogType.ENTER.name());
        logDto.setCode(employee.getCode());

        Optional<History> history = historyService.addHistory(logDto);
        Assertions.assertTrue(history.isPresent());

        employeeService.deleteEmployee(employee.getId());
    }
}
