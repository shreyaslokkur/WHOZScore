package com.example.WhoZScore.model;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 4/1/15
 * Time: 11:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class Result {

    private boolean isHealthy;
    private String zScoreWeightMessage;
    private String healthyWeightMessage;
    private String zScoreHeightMessage;
    private String healthyHeightMessage;

    public boolean isHealthy() {
        return isHealthy;
    }

    public void setHealthy(boolean healthy) {
        isHealthy = healthy;
    }

    public String getzScoreWeightMessage() {
        return zScoreWeightMessage;
    }

    public void setzScoreWeightMessage(String zScoreWeightMessage) {
        this.zScoreWeightMessage = zScoreWeightMessage;
    }

    public String getHealthyWeightMessage() {
        return healthyWeightMessage;
    }

    public void setHealthyWeightMessage(String healthyWeightMessage) {
        this.healthyWeightMessage = healthyWeightMessage;
    }

    public String getzScoreHeightMessage() {
        return zScoreHeightMessage;
    }

    public void setzScoreHeightMessage(String zScoreHeightMessage) {
        this.zScoreHeightMessage = zScoreHeightMessage;
    }

    public String getHealthyHeightMessage() {
        return healthyHeightMessage;
    }

    public void setHealthyHeightMessage(String healthyHeightMessage) {
        this.healthyHeightMessage = healthyHeightMessage;
    }
}
