package com.example.WhoZScore.enums;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 4/6/15
 * Time: 11:02 PM
 * To change this template use File | Settings | File Templates.
 */
public enum AgeGroup {

    WEEKS(0),
    TILLONEYEAR(1),
    TILLTWOYEARS(2),
    TILLTHREEYEARS(3),
    TILLFOURYEARS(4),
    TILLFIVEYEARS(5);

    private int maxYears;

    private AgeGroup(int maxYears) {
        this.maxYears = maxYears;
    }

    public int getMaxYears() {
        return maxYears;
    }
}
