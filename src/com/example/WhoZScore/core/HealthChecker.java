package com.example.WhoZScore.core;

import com.example.WhoZScore.data.entities.HeightForAge;
import com.example.WhoZScore.data.entities.WeightForAge;
import com.example.WhoZScore.model.HeightForAgeResult;
import com.example.WhoZScore.model.Patient;
import com.example.WhoZScore.model.Result;
import com.example.WhoZScore.model.WeightForAgeResult;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 3/29/15
 * Time: 3:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class HealthChecker {

    public Result checkIfHealthy(Patient patient, WeightForAge weightForAge, HeightForAge heightForAge){
        Result result = null;
        WeightForAgeResult resultForWeight = null;
        HeightForAgeResult resultForHeight = null;
        if(weightForAge != null){
           resultForWeight = getResultForWeight(patient, weightForAge);
        }
        if(heightForAge != null){
            resultForHeight = getResultForHeight(patient, heightForAge);
        }
        result = setResult(resultForWeight, resultForHeight);
        return result;
    }

    private WeightForAgeResult getResultForWeight(Patient patient, WeightForAge weightForAge){
        WeightForAgeResult weightForAgeResult;
        if(patient.getAgeInYears() < 5){
            weightForAgeResult = calculateWeightResultForAgeLessThanFiveYears(patient, weightForAge);
        }else {
            weightForAgeResult = calculateWeightResultForAgeGreaterThanFiveYears(patient, weightForAge);
        }
        return weightForAgeResult;

    }

    private HeightForAgeResult getResultForHeight(Patient patient, HeightForAge heightForAge){
        HeightForAgeResult heightForAgeResult;
        if(patient.getAgeInYears() < 5){
            heightForAgeResult = calculateHeightResultForAgeLessThanFiveYears(patient, heightForAge);
        }else {
            heightForAgeResult = calculateHeightResultForAgeGreaterThanFiveYears(patient, heightForAge);
        }
        return heightForAgeResult;
    }

    private HeightForAgeResult calculateHeightResultForAgeGreaterThanFiveYears(Patient patient, HeightForAge heightForAge) {
        HeightForAgeResult heightForAgeResult = new HeightForAgeResult();
        double height = patient.getHeight();
        String message = null;
        boolean isHealthy;
        if(height > heightForAge.getThreeScore() ){
            message = "Greater than 3 Zscore";
            isHealthy = false;

        }else if(height <= heightForAge.getThreeScore() && height >= heightForAge.getTwoScore()){
            message = "Between 2 and 3 ZScore";
            isHealthy = false;

        }else if(height < heightForAge.getTwoScore() && height >= heightForAge.getOneScore()){
            message = "Between 1 and 2 ZScore";
            isHealthy = true;

        }else if(height < heightForAge.getOneScore() && height >= heightForAge.getZeroScore()){
            message = "Between 0 and 1 ZScore";
            isHealthy = true;

        }else if(height < heightForAge.getZeroScore() && height >= heightForAge.getMinusOneScore()){
            message = "Between -1 and 0 ZScore";
            isHealthy = true;

        }else if(height < heightForAge.getMinusOneScore() && height >= heightForAge.getMinusTwoScore()){
            message = "Between -2 and -1 ZScore";
            isHealthy = true;

        }else if(height < heightForAge.getMinusTwoScore() && height >= heightForAge.getMinusThreeScore()){
            message = "Between -3 and -2 ZScore";
            isHealthy = false;
        }else{
            message = "Lesser than -3 ZScore";
            isHealthy = false;
        }
        heightForAgeResult.setHealthy(isHealthy);
        heightForAgeResult.setzScoreHeightMessage(message);
        return heightForAgeResult;

    }

    private HeightForAgeResult calculateHeightResultForAgeLessThanFiveYears(Patient patient, HeightForAge heightForAge) {
        HeightForAgeResult heightForAgeResult = new HeightForAgeResult();
        double height = patient.getHeight();
        String message = null;
        boolean isHealthy;
        if(height > heightForAge.getThreeScore() ){
            message = "Greater than 3 ZScore";
            isHealthy = false;

        }else if(height <= heightForAge.getThreeScore() && height >= heightForAge.getTwoScore()){
            message = "Between 2 and 3 ZScore ";
            isHealthy = false;

        }else if(height < heightForAge.getTwoScore() && height >= heightForAge.getZeroScore()){
            message = "Between 0 and 2 ZScore ";
            isHealthy = true;

        }else if(height < heightForAge.getZeroScore() && height >= heightForAge.getMinusTwoScore()){
            message = "Between -2 and 0 ZScore ";
            isHealthy = true;

        }else if(height < heightForAge.getMinusTwoScore() && height >= heightForAge.getMinusThreeScore()){
            message = "Between -3 and -2 ZScore ";
            isHealthy = false;
        }else{
            message = "Lesser than -3 ZScore";
            isHealthy = false;
        }
        heightForAgeResult.setHealthy(isHealthy);
        heightForAgeResult.setzScoreHeightMessage(message);
        return heightForAgeResult;
    }

    private WeightForAgeResult calculateWeightResultForAgeLessThanFiveYears(Patient patient, WeightForAge weightForAge){
        WeightForAgeResult weightForAgeResult = new WeightForAgeResult();
        double weight = patient.getWeight();
        String message = null;
        boolean isHealthy;
        if(weight > weightForAge.getThreeScore() ){
            message = "Greater than 3 ZScore ";
            isHealthy = false;

        }else if(weight <= weightForAge.getThreeScore() && weight >= weightForAge.getTwoScore()){
            message = "Between 2 and 3 ZScore ";
            isHealthy = false;

        }else if(weight < weightForAge.getTwoScore() && weight >= weightForAge.getZeroScore()){
            message = "Between 0 and 2 ZScore ";
            isHealthy = true;

        }else if(weight < weightForAge.getZeroScore() && weight >= weightForAge.getMinusTwoScore()){
            message = "Between -2 and 0 ZScore ";
            isHealthy = true;

        }else if(weight < weightForAge.getMinusTwoScore() && weight >= weightForAge.getMinusThreeScore()){
            message = "Between -3 and -2 ZScore ";
            isHealthy = false;
        }else{
            message = "Lesser than -3 ZScore";
            isHealthy = false;
        }
        weightForAgeResult.setHealthy(isHealthy);
        weightForAgeResult.setzScoreWeightMessage(message);
        return weightForAgeResult;
    }

    private WeightForAgeResult calculateWeightResultForAgeGreaterThanFiveYears(Patient patient, WeightForAge weightForAge){
        WeightForAgeResult weightForAgeResult = new WeightForAgeResult();
        double weight = patient.getWeight();
        String message = null;
        boolean isHealthy;
        if(weight > weightForAge.getThreeScore() ){
            message = "Greater than 3 ZScore ";
            isHealthy = false;

        }else if(weight <= weightForAge.getThreeScore() && weight >= weightForAge.getTwoScore()){
            message = "Between 2 and 3 ZScore ";
            isHealthy = false;

        }else if(weight < weightForAge.getTwoScore() && weight >= weightForAge.getOneScore()){
            message = "Between 1 and 2 ZScore ";
            isHealthy = true;

        }else if(weight < weightForAge.getOneScore() && weight >= weightForAge.getZeroScore()){
            message = "Between 0 and 1 ZScore ";
            isHealthy = true;

        }else if(weight < weightForAge.getZeroScore() && weight >= weightForAge.getMinusOneScore()){
            message = "Between -1 and 0 ZScore ";
            isHealthy = true;

        }else if(weight < weightForAge.getMinusOneScore() && weight >= weightForAge.getMinusTwoScore()){
            message = "Between -2 and -1 ZScore ";
            isHealthy = true;

        }else if(weight < weightForAge.getMinusTwoScore() && weight >= weightForAge.getMinusThreeScore()){
            message = "Between -3 and -2 ZScore ";
            isHealthy = false;
        }else{
            message = "Lesser than -3 ZScore";
            isHealthy = false;
        }
        weightForAgeResult.setHealthy(isHealthy);
        weightForAgeResult.setzScoreWeightMessage(message);
        return weightForAgeResult;

    }

    private Result setResult(WeightForAgeResult weightForAgeResult, HeightForAgeResult heightForAgeResult){
        Result result = new Result();
        if(weightForAgeResult != null){
            result.setzScoreWeightForAgeMessage(weightForAgeResult.getzScoreWeightMessage());

        }
        if(heightForAgeResult != null){
            result.setzScoreHeightForAgeMessage(heightForAgeResult.getzScoreHeightMessage());
        }
        return result;
    }
}
