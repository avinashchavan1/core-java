package com.avinash.employees;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analyst extends Employee implements IEmployee {
    private final String analystRegex = "projectCount=(?<projectCount>\\d+)\\s*.*$";
    private int projectCount;

    public Analyst(String infoText) {
        super(infoText);
        peopleMatcher = PEOPLE_PATTERN.matcher(infoText);
        if (peopleMatcher.find()) {
            Pattern analystPattern = Pattern.compile(analystRegex);
            Matcher analystMatcher = analystPattern.matcher(infoText);
            if (analystMatcher.find()) {
                this.projectCount = Integer.parseInt(analystMatcher.group("projectCount"));
            }
        }

    }

    public int getSalary() {
        return 1000 + projectCount * projectCount * projectCount;
    }


}
