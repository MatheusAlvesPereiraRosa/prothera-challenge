package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        // 3.1 - Inserindo funcionários
        employees.add(new Employee("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        employees.add(new Employee("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        employees.add(new Employee("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        employees.add(new Employee("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        employees.add(new Employee("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Repcionista"));
        employees.add(new Employee("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        employees.add(new Employee("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        employees.add(new Employee("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        employees.add(new Employee("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        employees.add(new Employee("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        // 3.2 - Removendo “João” da lista
        employees.removeIf(e -> e.getName().equals("João"));

        // 3.3 - Imprimindo todos os funcionários com suas informações
        System.out.print("---------------------------------------------\n");
        System.out.print("3.3 - Imprimindo todos os funcionarios com suas informacoes\n\n");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        employees.forEach(e -> {
            System.out.printf("Nome: %s, Data de nascimento: %s, Salario: %,.2f, Funcao: %s%n",
                    e.getName(),
                    e.getDateOfBirth().format(dateFormatter),
                    e.getSalary(),
                    e.getFunction()
            );
        });

        // 3.4 - Funcionários recebendo aumento de 10%
        employees.forEach(e -> e.setSalary(e.getSalary().multiply(new BigDecimal("1.10"))));

        // 3.5 - Agrupando funcionários pela função MAP
        System.out.print("---------------------------------------------\n");
        System.out.print("EXTRA - Imprimindo os funcionarios agrupados por funçao (cargo) depois da funcao MAP\n\n");
        Map<String, List<Employee>> groupedByFunction = employees.stream()
                .collect(Collectors.groupingBy(Employee::getFunction));

        // Imprimindo os funcionários agrupados por função
        groupedByFunction.forEach((function, empList) -> {
            System.out.println("Funcao: " + function);
            empList.forEach(e -> {
                System.out.printf("  Nome: %s, Data de nascimento: %s, Salario: %,.2f%n",
                        e.getName(),
                        e.getDateOfBirth().format(dateFormatter),
                        e.getSalary()
                );
            });
        });

        // 3.6 - Imprimindo funcionários pela função
        System.out.print("---------------------------------------------\n");
        System.out.print("3.6 - Imprimindo funcionarios pela função\n\n");
        groupedByFunction.forEach((function, empList) -> {
            System.out.println("\nFunção: " + function);
            empList.forEach(e -> {
                System.out.printf("Nome: %s, Data de nascimento: %s, Salario: %,.2f%n",
                        e.getName(),
                        e.getDateOfBirth().format(dateFormatter),
                        e.getSalary()
                );
            });
        });

        // 3.8 - Imprimir funcionários cujos aniversários são nos dias 10 e 12
        System.out.print("---------------------------------------------\n");
        System.out.print("3.8 - Imprimir funcionarios cujos aniversarios sao nos dias 10 e 12\n\n");
        employees.stream()
                .filter(e -> e.getDateOfBirth().getMonthValue() == 10 || e.getDateOfBirth().getMonthValue() == 12)
                .forEach(e -> System.out.println("Funcionarios que fazem aniversario no dia 10 ou 12: " + e.getName()));

        // 3.9 - Imprimir o funcionário com maior idade
        System.out.print("---------------------------------------------\n");
        System.out.print("3.9 - Imprimir o funcionario com maior idade\n\n");
        Employee oldest = employees.stream()
                .min(Comparator.comparing(Employee::getDateOfBirth))
                .orElseThrow(NoSuchElementException::new);
        System.out.printf("Funcionário mais velho: Nome: %s, Idade: %d%n", oldest.getName(),
                LocalDate.now().getYear() - oldest.getDateOfBirth().getYear());

        // 3.10 - Imprimir a lista de funcionários em ordem alfabética
        System.out.print("---------------------------------------------\n");
        System.out.print(" 3.10 - Imprimir a lista de funcionarios em ordem alfabetica\n\n");
        employees.stream()
                .sorted(Comparator.comparing(Employee::getName))
                .forEach(e -> System.out.println("Funcionarios em ordem alfabetica: " + e.getName()));

        // 3.11 - Imprimir o total de salários dos funcionários
        System.out.print("---------------------------------------------\n");
        System.out.print("3.11 - Imprimir o total de salarios dos funcionarios\n\n");
        BigDecimal totalSalaries = employees.stream()
                .map(Employee::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.printf("Salário total dos funcionarios: %,.2f%n", totalSalaries);

        // 3.12 - Imprima quantos salários mínimos cada funcionário ganha
        System.out.print("---------------------------------------------\n");
        System.out.print("3.12 - Imprima quantos salários minimos cada funcionario ganha\n\n");
        BigDecimal minimumWage = new BigDecimal("1212.00");
        employees.forEach(e -> {
            BigDecimal numOfMinimumWages = e.getSalary().divide(minimumWage, 2, BigDecimal.ROUND_HALF_UP);
            System.out.printf("%s recebe %.2f salarios min.%n ", e.getName(), numOfMinimumWages);
        });
        System.out.print("---------------------------------------------\n");
    }
}
