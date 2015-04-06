package com.example.WhoZScore.model;

import com.example.WhoZScore.enums.Age;
import com.example.WhoZScore.enums.ZScoreGraphTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 4/5/15
 * Time: 7:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class GraphModel {
    private List<Double> minusThreeScore = new ArrayList<Double>();
    private List<Double> minusTwoScore = new ArrayList<Double>();
    private List<Double> minusOneScore = new ArrayList<Double>();
    private List<Double> zeroScore = new ArrayList<Double>();
    private List<Double> oneScore = new ArrayList<Double>();
    private List<Double> twoScore = new ArrayList<Double>();
    private List<Double> threeScore = new ArrayList<Double>();
    private List<Integer> xAxis = new ArrayList<Integer>();
    private Age age;
    private ZScoreGraphTypes zScoreGraphTypes;

    public List<Double> getMinusThreeScore() {
        return minusThreeScore;
    }

    public void setMinusThreeScore(List<Double> minusThreeScore) {
        this.minusThreeScore = minusThreeScore;
    }

    public List<Double> getMinusTwoScore() {
        return minusTwoScore;
    }

    public void setMinusTwoScore(List<Double> minusTwoScore) {
        this.minusTwoScore = minusTwoScore;
    }

    public List<Double> getMinusOneScore() {
        return minusOneScore;
    }

    public void setMinusOneScore(List<Double> minusOneScore) {
        this.minusOneScore = minusOneScore;
    }

    public List<Double> getZeroScore() {
        return zeroScore;
    }

    public void setZeroScore(List<Double> zeroScore) {
        this.zeroScore = zeroScore;
    }

    public List<Double> getOneScore() {
        return oneScore;
    }

    public void setOneScore(List<Double> oneScore) {
        this.oneScore = oneScore;
    }

    public List<Double> getTwoScore() {
        return twoScore;
    }

    public void setTwoScore(List<Double> twoScore) {
        this.twoScore = twoScore;
    }

    public List<Double> getThreeScore() {
        return threeScore;
    }

    public void setThreeScore(List<Double> threeScore) {
        this.threeScore = threeScore;
    }

    public List<Integer> getxAxis() {
        return xAxis;
    }

    public void setxAxis(List<Integer> xAxis) {
        this.xAxis = xAxis;
    }

    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }

    public ZScoreGraphTypes getzScoreGraphTypes() {
        return zScoreGraphTypes;
    }

    public void setzScoreGraphTypes(ZScoreGraphTypes zScoreGraphTypes) {
        this.zScoreGraphTypes = zScoreGraphTypes;
    }
}
