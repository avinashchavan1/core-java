package com.avinash.employees;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CEO extends Employee implements IEmployee, Flyer {
    private final String ceoRegex = "avgStockPrice=(?<avgStockPrice>\\d+)\\s*.*$";
    private int avgStockPrice;
    private Flyer flyer = new Pilot(1000, false);

    public Flyer getFlyer() {
        return flyer;
    }

    public void setFlyer(Flyer flyer) {
        this.flyer = flyer;
    }

    public int getAvgStockPrice() {
        return avgStockPrice;
    }

    public void setAvgStockPrice(int avgStockPrice) {
        this.avgStockPrice = avgStockPrice;
    }

    public String getCeoRegex() {
        return ceoRegex;
    }

    public int getHoursFlown() {
        return flyer.getHoursFlown();
    }

    public void setHoursFlown(int hoursFlown) {
        flyer.setHoursFlown(hoursFlown);
    }

    public boolean isIfr() {
        return flyer.isIfr();
    }

    public void setIfr(boolean ifr) {
        flyer.setIfr(ifr);
    }

    public void fly() {
        flyer.fly();
    }

    public CEO(String infoText) {
        super(infoText);
        peopleMatcher = PEOPLE_PATTERN.matcher(infoText);
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
