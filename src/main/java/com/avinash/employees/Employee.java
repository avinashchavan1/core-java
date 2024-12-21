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
    protected final String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+),\\s*\\{(?<details>.*)}\\n";
    Pattern peoplePattern = Pattern.compile(peopleRegex);
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);

    public Employee(String infoText) {
        peopleMatcher = peoplePattern.matcher(infoText);
        if (peopleMatcher.find()) {
            this.firstName = peopleMatcher.group("firstName");
            this.lastName = peopleMatcher.group("lastName");
            this.dob = LocalDate.from(dateTimeFormatter.parse(peopleMatcher.group("dob")));

        }
    }

    public abstract int getSalary();


    public double getBonus() {
        return getSalary() * 0.10;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s - %s", firstName, lastName, dob.toString(), numberFormat.format(getSalary()), numberFormat.format(getBonus()));
    }
}
