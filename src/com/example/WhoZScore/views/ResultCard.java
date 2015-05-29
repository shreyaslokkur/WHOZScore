package com.example.WhoZScore.views;

import com.example.WhoZScore.enums.ZScoreCalculators;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 5/28/15
 * Time: 6:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResultCard {

    private String header;
    private String zScoreResult;
    private ZScoreCalculators zScoreCalculators;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getzScoreResult() {
        return zScoreResult;
    }

    public void setzScoreResult(String zScoreResult) {
        this.zScoreResult = zScoreResult;
    }

    public ZScoreCalculators getzScoreCalculators() {
        return zScoreCalculators;
    }

    public void setzScoreCalculators(ZScoreCalculators zScoreCalculators) {
        this.zScoreCalculators = zScoreCalculators;
    }
}
