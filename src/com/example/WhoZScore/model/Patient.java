package com.example.WhoZScore.model;

import com.example.WhoZScore.enums.Sex;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 8/18/14
 * Time: 1:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class Patient {

    private Sex sex;
    private int ageInYears;
    private int ageInMonths;
    private int ageInWeeks;
    private double weight;
    private int zScore;

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public int getAgeInYears() {
        return ageInYears;
    }

    public void setAgeInYears(int ageInYears) {
        this.ageInYears = ageInYears;
    }

    public int getAgeInMonths() {
        return ageInMonths;
    }

    public void setAgeInMonths(int ageInMonths) {
        this.ageInMonths = ageInMonths;
    }

    public int getAgeInWeeks() {
        return ageInWeeks;
    }

    public void setAgeInWeeks(int ageInWeeks) {
        this.ageInWeeks = ageInWeeks;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getzScore() {
        return zScore;
    }

    public void setzScore(int zScore) {
        this.zScore = zScore;
    }
}
