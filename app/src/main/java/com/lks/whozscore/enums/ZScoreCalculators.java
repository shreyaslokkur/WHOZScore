package com.lks.whozscore.enums;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 5/28/15
 * Time: 10:30 PM
 * To change this template use File | Settings | File Templates.
 */
public enum ZScoreCalculators {

    WEIGHT_FOR_AGE("WEIGHT FOR AGE"),
    HEIGHT_FOR_AGE("HEIGHT FOR AGE"),
    WEIGHT_FOR_HEIGHT("WEIGHT FOR HEIGHT"),
    HEAD_CIRCUMFERENCE_FOR_AGE("HEAD_CIRCUMEFERENCE FOR AGE");

    private String types;

    ZScoreCalculators(String types) {
        this.types = types;
    }

    public String getTypes() {
        return types;
    }
}
