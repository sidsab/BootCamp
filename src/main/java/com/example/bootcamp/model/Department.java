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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_Generator")
    @SequenceGenerator(name = "sequence_Generator", sequenceName = "department_Seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "read_only")
    private Boolean read_only;

    @Column(name = "mandatory")
    private Boolean mandatory;

    @ManyToMany()
    @JoinTable(
            name = "map_employee_department",
            joinColumns = @JoinColumn(name = "id_department"),
            inverseJoinColumns = @JoinColumn(name = "id_employee")
    )
    private Set<Employee> employees;

}
