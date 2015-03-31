package com.example.WhoZScore.model;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 3/29/15
 * Time: 3:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class Result {
    private boolean isHealthy;
    private String zScoreMessage;
    private String healthyMessage;

    public boolean isHealthy() {
        return isHealthy;
    }

    public void setHealthy(boolean healthy) {
        isHealthy = healthy;
    }

    public String getzScoreMessage() {
        return zScoreMessage;
    }

    public void setzScoreMessage(String zScoreMessage) {
        this.zScoreMessage = zScoreMessage;
    }

    public String getHealthyMessage() {
        return healthyMessage;
    }

    public void setHealthyMessage(String healthyMessage) {
        this.healthyMessage = healthyMessage;
    }
}
