package com.avinash.employees;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager extends Employee implements IEmployee {
    private final String managerRegex = "orgSize=(?<orgSize>\\d+)\\s*,dr=\\s*(?<dr>\\d+).*$";

    private int orgSize;
    private int dr;

    public Manager(String infoText) {
        super(infoText);
        peopleMatcher = PEOPLE_PATTERN.matcher(infoText);
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
