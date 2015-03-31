package com.example.WhoZScore.data.entities;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 3/30/15
 * Time: 10:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class AbstractZScoreForAge {
    private int id;
    private int weeks;
    private int months;
    private int years;
    private double threeScore;
    private double twoScore;
    private double oneScore;
    private double zeroScore;
    private double minusOneScore;
    private double minusTwoScore;
    private double minusThreeScore;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeeks() {
        return weeks;
    }

    public void setWeeks(int weeks) {
        this.weeks = weeks;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public double getThreeScore() {
        return threeScore;
    }

    public void setThreeScore(double threeScore) {
        this.threeScore = threeScore;
    }

    public double getTwoScore() {
        return twoScore;
    }

    public void setTwoScore(double twoScore) {
        this.twoScore = twoScore;
    }

    public double getOneScore() {
        return oneScore;
    }

    public void setOneScore(double oneScore) {
        this.oneScore = oneScore;
    }

    public double getZeroScore() {
        return zeroScore;
    }

    public void setZeroScore(double zeroScore) {
        this.zeroScore = zeroScore;
    }

    public double getMinusOneScore() {
        return minusOneScore;
    }

    public void setMinusOneScore(double minusOneScore) {
        this.minusOneScore = minusOneScore;
    }

    public double getMinusTwoScore() {
        return minusTwoScore;
    }

    public void setMinusTwoScore(double minusTwoScore) {
        this.minusTwoScore = minusTwoScore;
    }

    public double getMinusThreeScore() {
        return minusThreeScore;
    }

    public void setMinusThreeScore(double minusThreeScore) {
        this.minusThreeScore = minusThreeScore;
    }
}
