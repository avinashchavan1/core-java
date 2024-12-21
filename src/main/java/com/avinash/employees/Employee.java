package com.avinash.employees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Employee {

    protected String firstName;
    protected String lastName;
    protected LocalDate dob;
    protected Matcher peopleMatcher;
    protected final static String PEOPLE_REGEX = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+),\\s*\\{(?<details>.*)}\\n";
    protected final static Pattern PEOPLE_PATTERN = Pattern.compile(PEOPLE_REGEX);
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);

    public Employee(String infoText) {
        peopleMatcher = Employee.PEOPLE_PATTERN.matcher(infoText);
        if (peopleMatcher.find()) {
            this.firstName = peopleMatcher.group("firstName");
            this.lastName = peopleMatcher.group("lastName");
            this.dob = LocalDate.from(dateTimeFormatter.parse(peopleMatcher.group("dob")));

        }
    }

    public abstract int getSalary();

    public static Employee getEmployeeType(String personText) {
        Matcher peopleMatcher = Employee.PEOPLE_PATTERN.matcher(personText);
        if (peopleMatcher.find()) {
            return switch (peopleMatcher.group("role")) {
                case "SoftwareEngineer" -> new SoftwareEngineer(personText);
                case "Manager" -> new Manager(personText);
                case "Analyst" -> new Analyst(personText);
                case "CEO" -> new CEO(personText);
                default -> null;
            };
        } else {
            return null;
        }
    }

    public double getBonus() {
        return getSalary() * 0.10;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s - %s", firstName, lastName, dob.toString(), numberFormat.format(getSalary()), numberFormat.format(getBonus()));
    }
}
