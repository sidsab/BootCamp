package com.example.bootcamp.model;


import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
public class EmployeeDTO {

    public Integer recordVersion;
    public Integer yearOfBirth;
    public String nameFirst;
    public String nameLast;
    public String streetAddress;

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;

        if(obj == null || obj.getClass()!= this.getClass())
            return false;

        EmployeeDTO employeeDTO = (EmployeeDTO) obj;
        return (employeeDTO.nameFirst.equals(this.nameFirst)  && employeeDTO.nameLast.equals(this.nameLast) && employeeDTO.recordVersion.equals(this.recordVersion));
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(nameFirst, nameLast, recordVersion);
    }
    // What are the uses and differences for equals and hashCode

    // Make these work
    static Set<EmployeeDTO> allEmployeesLatestVersion = new HashSet<>();
    static Set<EmployeeDTO> allEmployeesAllVersions = new HashSet<>();

}
