package com.rysource.runner;

import org.junit.Assert;

import java.util.Date;

public class EnhancedAssertion {

    private EnhancedAssertion() {}

    private boolean result;

    private String message;

    private boolean isHard;

    private boolean isInfo;

    private Date runTime;

    public Date getRunTime() {
        return runTime;
    }

    public void setRunTime(Date runTime) {
        this.runTime = runTime;
    }



    public boolean isInfo() {
        return isInfo;
    }

    public void setInfo(boolean info) {
        isInfo = info;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isHard() {
        return isHard;
    }

    public void setHard(boolean hard) {
        isHard = hard;
    }

    public static void hardAssertCondition(String message, boolean condition) {
        hardAssertCondition(condition, message);
    }

    public static void hardAssertCondition(boolean condition, String message) {
        assertCondition(condition, message, true);

        if (!condition) {
            Assert.fail(message);
        }
    }

    public static void softAssertCondition(String message, boolean condition) {
        softAssertCondition(condition, message);
    }

    public static void softAssertCondition(boolean condition, String message) {
        assertCondition(condition, message, false, false);
    }
    public static void addlogInfo(boolean condition, String message, boolean isHard, boolean isInfo) {
        assertCondition(condition, message, isHard, isInfo);
    }
    private static void assertCondition(boolean condition, String message, boolean isHard) {
        assertCondition(condition, message, isHard, false);
    }
    private static void assertCondition(boolean condition, String message, boolean isHard, boolean isInfo) {
        EnhancedAssertion enhancedAssertion = new EnhancedAssertion();
        enhancedAssertion.setResult(condition);
        enhancedAssertion.setMessage(message);
        enhancedAssertion.setHard(isHard);
        enhancedAssertion.setInfo(isInfo);
        enhancedAssertion.setRunTime(new Date(System.currentTimeMillis()));
        EnhancedTestNotifier.enhancedAssertions.add(enhancedAssertion);
    }
}

