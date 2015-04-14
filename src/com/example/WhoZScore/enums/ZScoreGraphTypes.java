package com.example.WhoZScore.enums;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 4/5/15
 * Time: 7:35 PM
 * To change this template use File | Settings | File Templates.
 */
public enum ZScoreGraphTypes {

    WEIGHT_FOR_AGE_BOYS("Weight for Age Boys","Weight (kg)"),
    WEIGHT_FOR_AGE_GIRLS("Weight for Age Girls","Weight (kg)"),
    HEIGHT_FOR_AGE_BOYS("Height for Age Boys","Height (cms)"),
    HEIGHT_FOR_AGE_GIRLS("Height for Age Girls","Height (cms)"),
    WEIGHT_FOR_HEIGHT_BOYS("Weight for Height Boys","Weight (kg)"),
    WEIGHT_FOR_HEIGHT_GIRLS("Weight for Height Girls","Weight (kg)"),
    HEAD_CIRCUMFERENCE_FOR_AGE_BOYS("Head circumference for Age Boys", "Height (cms)"),
    HEAD_CIRCUMFERENCE_FOR_AGE_GIRLS("Head circumference for Age Girls", "Height (cms)");

    private String graphNames;
    private String yAxis;
    private ZScoreGraphTypes(String graphNames, String yAxis) {
        this.graphNames = graphNames;
        this.yAxis = yAxis;
    }

    public String getGraphNames() {
        return graphNames;
    }

    public String getyAxis() {
        return yAxis;
    }
}
