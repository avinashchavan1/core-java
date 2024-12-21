package com.avinash.employees;

import java.text.Format;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String people = """
                Flinstone1, Fred1, 1/1/1900, SoftwareEngineer, {code=2000,yoe=10,iq=140}
                Flinstone2, Fred2, 1/1/1900, SoftwareEngineer, {code=3000,yoe=13,iq=143}
                Flinstone3, Fred3, 1/1/1900, SoftwareEngineer, {code=4000,yoe=15,iq=154}
                Flinstone4, Fred4, 1/1/1900, SoftwareEngineer, {code=500,yoe=5,iq=120}
                Rubble1, Barney1, 2/2/1905, Manager, {orgSize=300,dr=10}
                Rubble2, Barney2, 2/2/1905, Manager, {orgSize=340,dr=14}
                Rubble3, Barney3, 2/2/1905, Manager, {orgSize=344,dr=5}
                Rubble4, Barney4, 2/2/1905, Manager, {orgSize=52,dr=5}
                Flinstone1, Wilma1, 3/3/1910, Analyst, {projectCount=3}
                Flinstone2, Wilma2, 3/3/1910, Analyst, {projectCount=4}
                Flinstone3, Wilma3, 3/3/1910, Analyst, {projectCount=6}
                Flinstone4, Wilma4, 3/3/1910, Analyst, {projectCount=1}
                Flinstone5, Wilma5, 3/3/1910, Analyst, {projectCount=7}
                Rubble, Betty, 4/4/1915, CEO, {avgStockPrice=1637}
                """;

        String regex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+),\\s*\\{(?<details>.*)}\\n";

        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher(people);

        int totalSalary = 0;
        IEmployee employee = null;
        while (mat.find()) {
            employee = switch (mat.group("role")) {
                case "SoftwareEngineer" -> new SoftwareEngineer(mat.group());
                case "Manager" -> new Manager(mat.group());
                case "Analyst" -> new Analyst(mat.group());
                case "CEO" -> new CEO(mat.group());
                default -> null;
            };
            System.out.println(employee);
            totalSalary += employee.getSalary();
            employee = null;
        }
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);

        System.out.println(numberFormat.format(totalSalary));
    }
}
