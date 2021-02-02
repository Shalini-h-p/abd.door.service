package com.abd.exam.model;
import com.abd.exam.common.LogType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDate;

@JsonIgnoreProperties(
        value = {"CreatedOn"},
        allowGetters = true
)
@Data
@Entity
public class History {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int id;

    @ManyToOne
    @JoinColumn(name="Employee_Id", nullable = true)
    public Employee employee;

    @Enumerated(EnumType.STRING)
    public LogType type;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    public LocalDate createdOn;

    @Valid
    public int codeUsed;
}
