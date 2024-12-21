package com.avinash.employees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SoftwareEngineer extends Employee implements IEmployee {
    private final String softwareEngineerRegex = "code=(?<code>\\d+)\\s*,yoe=\\s*(?<yoe>\\d+),iq=\\s*(?<iq>\\d+).*$";
    private int code;
    private int yoe;
    private int iq;

    public SoftwareEngineer(String infoText) {
        super(infoText);
        peopleMatcher = peoplePattern.matcher(infoText);
        if (peopleMatcher.find()) {
            Pattern softwareEngineerPattern = Pattern.compile(softwareEngineerRegex);
            Matcher softwareEngineerMatcher = softwareEngineerPattern.matcher(infoText);

            if (softwareEngineerMatcher.find()) {
                this.iq = Integer.parseInt(softwareEngineerMatcher.group("iq"));
                this.yoe = Integer.parseInt(softwareEngineerMatcher.group("yoe"));
                this.code = Integer.parseInt(softwareEngineerMatcher.group("code"));
            }

        }

    }

    public int getSalary() {
        return 1000 + iq * yoe * code;
    }


}
