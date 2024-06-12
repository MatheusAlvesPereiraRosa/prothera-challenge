package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;

// Extendendo da classe pessoa
public class Employee extends Person {

    // Atributos obrigatórios: salário e função
    private BigDecimal salary;
    private final String function;

    public Employee(String name, LocalDate dateOfBirth, BigDecimal salary, String function) {
        super(name, dateOfBirth);
        this.salary = salary;
        this.function = function;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getFunction() {
        return function;
    }
}
