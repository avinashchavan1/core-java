package com.avinash.employees;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CEO extends Employee implements IEmployee {
    private final String ceoRegex = "avgStockPrice=(?<avgStockPrice>\\d+)\\s*.*$";
    private int avgStockPrice;


    public CEO(String infoText) {
        super(infoText);
        peopleMatcher = peoplePattern.matcher(infoText);
        if (peopleMatcher.find()) {
            Pattern ceoPattern = Pattern.compile(ceoRegex);
            Matcher ceoMatcher = ceoPattern.matcher(infoText);
            if (ceoMatcher.find()) {
                this.avgStockPrice = Integer.parseInt(ceoMatcher.group("avgStockPrice"));
            }

        }

    }

    public int getSalary() {
        return 1000 + avgStockPrice * avgStockPrice;
    }

}
