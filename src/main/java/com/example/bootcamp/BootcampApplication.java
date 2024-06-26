package com.example.bootcamp;

import com.example.bootcamp.controller.DepartmentController;
import com.example.bootcamp.repository.DepartmentRepository;
import com.example.bootcamp.service.DepartmentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class BootcampApplication {

    public static void main(String[] args) {

        SpringApplication.run(BootcampApplication.class, args);
    }

}
