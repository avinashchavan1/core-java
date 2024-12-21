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

    public static IEmployee getEmployeeType(String personText) {
        Matcher peopleMatcher = Employee.PEOPLE_PATTERN.matcher(personText);
        if (peopleMatcher.find()) {
            return switch (peopleMatcher.group("role")) {
                case "SoftwareEngineer" -> new SoftwareEngineer(personText);
                case "Manager" -> new Manager(personText);
                case "Analyst" -> new Analyst(personText);
                case "CEO" -> new CEO(personText);
                default -> () -> 0;
            };
        } else {
            return () -> 0;
        }
    }

    public double getBonus() {
        return getSalary() * 0.10;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s - %s", firstName, lastName, dob.toString(), numberFormat.format(getSalary()), numberFormat.format(getBonus()));
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Matcher getPeopleMatcher() {
        return peopleMatcher;
    }

    public void setPeopleMatcher(Matcher peopleMatcher) {
        this.peopleMatcher = peopleMatcher;
    }

    public DateTimeFormatter getDateTimeFormatter() {
        return dateTimeFormatter;
    }

    public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }

    public NumberFormat getNumberFormat() {
        return numberFormat;
    }

    public void setNumberFormat(NumberFormat numberFormat) {
        this.numberFormat = numberFormat;
    }
}
