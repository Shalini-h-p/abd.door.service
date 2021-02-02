package com.abd.exam.Service;

import com.abd.exam.common.LogType;
import com.abd.exam.dto.LogDto;
import com.abd.exam.model.Employee;
import com.abd.exam.model.History;
import com.abd.exam.repository.EmployeeRepository;
import com.abd.exam.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<History> getHistories() {
        return historyRepository.findAll();
    }

    public Optional<History> addHistory(LogDto logDto) {
        History history = new History();
        Optional<Employee> employee = employeeRepository.findByCode(logDto.getCode());
        if(!employee.isPresent()) {
            return Optional.empty();
        }
        history.setCodeUsed(logDto.getCode());
        history.setCreatedOn(LocalDate.now());
        history.setEmployee(employee.get());
        history.setType(LogType.valueOf(logDto.getType()));
        return Optional.of(historyRepository.save(history));
    }
}
