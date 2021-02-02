package com.abd.exam.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import javax.validation.Valid;
import javax.persistence.*;
import java.time.LocalDate;

@JsonIgnoreProperties(value = {"LastModifiedOn", "CreatedOn"},
        allowGetters = true)
@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int id;

    @Valid
    public String fullName;

    @Valid
    public String email;

    @Valid
    public int code;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    public LocalDate createdOn;

    @Column(nullable = false, updatable = false)
    @LastModifiedDate
    public LocalDate lastModifiedOn;
}