package com.example.bootcamp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class
Department {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Boolean read_only;
    private Boolean mandatory;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "map_employee_department",
            joinColumns = @JoinColumn(name = "id_department"),
            inverseJoinColumns = @JoinColumn(name = "id_employee")
    )
    private Set<Employee> Employees;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Department that = (Department) o;
//        return Objects.equals(id, that.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
}
