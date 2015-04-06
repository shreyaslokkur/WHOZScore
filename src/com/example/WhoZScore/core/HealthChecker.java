package com.example.WhoZScore.core;

import com.example.WhoZScore.data.entities.HeightForAge;
import com.example.WhoZScore.data.entities.WeightForAge;
import com.example.WhoZScore.model.HeightResult;
import com.example.WhoZScore.model.Patient;
import com.example.WhoZScore.model.Result;
import com.example.WhoZScore.model.WeightResult;

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
        WeightResult resultForWeight = null;
        HeightResult resultForHeight = null;
        if(weightForAge != null){
           resultForWeight = getResultForWeight(patient, weightForAge);
        }
        if(heightForAge != null){
            resultForHeight = getResultForHeight(patient, heightForAge);
        }
        result = setResult(resultForWeight, resultForHeight);
        return result;
    }

    private WeightResult getResultForWeight(Patient patient, WeightForAge weightForAge){
        WeightResult weightResult;
        if(patient.getAgeInYears() < 5){
            weightResult = calculateWeightResultForAgeLessThanFiveYears(patient, weightForAge);
        }else {
            weightResult = calculateWeightResultForAgeGreaterThanFiveYears(patient, weightForAge);
        }
        return weightResult;

    }

    private HeightResult getResultForHeight(Patient patient, HeightForAge heightForAge){
        HeightResult heightResult;
        if(patient.getAgeInYears() < 5){
            heightResult = calculateHeightResultForAgeLessThanFiveYears(patient, heightForAge);
        }else {
            heightResult = calculateHeightResultForAgeGreaterThanFiveYears(patient, heightForAge);
        }
        return heightResult;
    }

    private HeightResult calculateHeightResultForAgeGreaterThanFiveYears(Patient patient, HeightForAge heightForAge) {
        HeightResult heightResult = new HeightResult();
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
        heightResult.setHealthy(isHealthy);
        heightResult.setzScoreHeightMessage(message);
        return heightResult;

    }

    private HeightResult calculateHeightResultForAgeLessThanFiveYears(Patient patient, HeightForAge heightForAge) {
        HeightResult heightResult = new HeightResult();
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
        heightResult.setHealthy(isHealthy);
        heightResult.setzScoreHeightMessage(message);
        return heightResult;
    }

    private WeightResult calculateWeightResultForAgeLessThanFiveYears(Patient patient, WeightForAge weightForAge){
        WeightResult weightResult = new WeightResult();
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
        weightResult.setHealthy(isHealthy);
        weightResult.setzScoreWeightMessage(message);
        return weightResult;
    }

    private WeightResult calculateWeightResultForAgeGreaterThanFiveYears(Patient patient, WeightForAge weightForAge){
        WeightResult weightResult = new WeightResult();
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
        weightResult.setHealthy(isHealthy);
        weightResult.setzScoreWeightMessage(message);
        return weightResult;

    }

    private Result setResult(WeightResult weightResult, HeightResult heightResult){
        Result result = new Result();
        if(weightResult != null){
            result.setzScoreWeightMessage(weightResult.getzScoreWeightMessage());

        }
        if(heightResult != null){
            result.setzScoreHeightMessage(heightResult.getzScoreHeightMessage());
        }
        return result;
    }
}
