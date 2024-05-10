package com.example.bootcamp.model;


import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class EmployeeDTO {

    public Integer recordVersion;
    public Integer yearOfBirth;
    public String nameFirst;
    public String nameLast;
    public String streetAddress;

    // What are the uses and differences for equals and hashCode

    // Make these work
    static Set<EmployeeDTO> allEmployeesLatestVersion = new HashSet<>();
    static Set<EmployeeDTO> allEmployeesAllVersions = new HashSet<>();
}
