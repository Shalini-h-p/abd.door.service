package com.abd.exam.repository;

import com.abd.exam.model.Employee;
import com.abd.exam.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository  extends JpaRepository<History, Integer> {
    List<History> findAllByEmployee(Employee employee);
}

