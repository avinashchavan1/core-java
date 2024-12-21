package com.avinash.employees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager extends Employee implements IEmployee {
    private final String managerRegex = "orgSize=(?<orgSize>\\d+)\\s*,dr=\\s*(?<dr>\\d+).*$";

    private int orgSize;
    private int dr;

    public Manager(String infoText) {
        super(infoText);
        peopleMatcher = peoplePattern.matcher(infoText);
        if (peopleMatcher.find()) {
            Pattern managerPattern = Pattern.compile(managerRegex);
            Matcher managerMatcher = managerPattern.matcher(infoText);
            if (managerMatcher.find()) {
                this.orgSize = Integer.parseInt(managerMatcher.group("orgSize"));
                this.dr = Integer.parseInt(managerMatcher.group("dr"));
            }

        }

    }

    public int getSalary() {
        return 1000 + dr * orgSize;
    }

}
