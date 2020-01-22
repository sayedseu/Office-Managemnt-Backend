package com.example.oficemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Information {
    @Id
    private String id;
    private LocalDate joiningDate;
    private String designation;
    private String responsibility;
    private String assignedTask;
    private String currentLocation;
}
