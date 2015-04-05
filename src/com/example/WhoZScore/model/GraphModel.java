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
    private List<Integer> minusThreeScore = new ArrayList<Integer>();
    private List<Integer> minusTwoScore = new ArrayList<Integer>();
    private List<Integer> minusOneScore = new ArrayList<Integer>();
    private List<Integer> zeroScore = new ArrayList<Integer>();
    private List<Integer> oneScore = new ArrayList<Integer>();
    private List<Integer> twoScore = new ArrayList<Integer>();
    private List<Integer> threeScore = new ArrayList<Integer>();
    private List<Integer> xAxis = new ArrayList<Integer>();
    private Age age;
    private ZScoreGraphTypes zScoreGraphTypes;

    public List<Integer> getMinusThreeScore() {
        return minusThreeScore;
    }

    public void setMinusThreeScore(List<Integer> minusThreeScore) {
        this.minusThreeScore = minusThreeScore;
    }

    public List<Integer> getMinusTwoScore() {
        return minusTwoScore;
    }

    public void setMinusTwoScore(List<Integer> minusTwoScore) {
        this.minusTwoScore = minusTwoScore;
    }

    public List<Integer> getMinusOneScore() {
        return minusOneScore;
    }

    public void setMinusOneScore(List<Integer> minusOneScore) {
        this.minusOneScore = minusOneScore;
    }

    public List<Integer> getZeroScore() {
        return zeroScore;
    }

    public void setZeroScore(List<Integer> zeroScore) {
        this.zeroScore = zeroScore;
    }

    public List<Integer> getOneScore() {
        return oneScore;
    }

    public void setOneScore(List<Integer> oneScore) {
        this.oneScore = oneScore;
    }

    public List<Integer> getTwoScore() {
        return twoScore;
    }

    public void setTwoScore(List<Integer> twoScore) {
        this.twoScore = twoScore;
    }

    public List<Integer> getThreeScore() {
        return threeScore;
    }

    public void setThreeScore(List<Integer> threeScore) {
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
