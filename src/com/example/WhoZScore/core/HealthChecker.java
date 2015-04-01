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
        return  null;
    }

    private WeightResult calculateWeightResultForAgeLessThanFiveYears(Patient patient, WeightForAge weightForAge){
        WeightResult weightResult = new WeightResult();
        double weight = patient.getWeight();
        String message = null;
        boolean isHealthy;
        if(weight > weightForAge.getThreeScore() ){
            message = "Greater than 3 index: "+ weightForAge.getThreeScore()+"kg";
            isHealthy = false;

        }else if(weight <= weightForAge.getThreeScore() && weight > weightForAge.getTwoScore()){
            message = "Inbetween 3 index: "+weightForAge.getThreeScore()+"kg and 2 index: "+weightForAge.getTwoScore()+"kg";
            isHealthy = false;

        }else if(weight <= weightForAge.getTwoScore() && weight > weightForAge.getZeroScore()){
            message = "Inbetween 2 index: "+weightForAge.getTwoScore()+"kg and 0 index: "+weightForAge.getZeroScore()+"kg";
            isHealthy = true;

        }else if(weight <= weightForAge.getZeroScore() && weight > weightForAge.getMinusTwoScore()){
            message = "Inbetween 0 index: "+weightForAge.getZeroScore()+"kg and -2 index: "+weightForAge.getMinusTwoScore()+"kg";
            isHealthy = true;

        }else if(weight <= weightForAge.getMinusTwoScore() && weight > weightForAge.getMinusThreeScore()){
            message = "Inbetween -2 index: "+weightForAge.getMinusTwoScore()+"kg and -3 index: "+weightForAge.getMinusThreeScore()+"kg";
            isHealthy = false;
        }else{
            message = "Lesser than -3 index: "+weightForAge.getMinusThreeScore()+"kg";
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
            message = "Greater than 3 index : "+ weightForAge.getThreeScore()+"kg";
            isHealthy = false;

        }else if(weight <= weightForAge.getThreeScore() && weight > weightForAge.getTwoScore()){
            message = "Inbetween 3 index: "+weightForAge.getThreeScore()+"kg and 2 index: "+weightForAge.getTwoScore()+"kg";
            isHealthy = false;

        }else if(weight <= weightForAge.getTwoScore() && weight > weightForAge.getOneScore()){
            message = "Inbetween 2 index: "+weightForAge.getTwoScore()+"kg and 1 index: "+weightForAge.getOneScore()+"kg";
            isHealthy = true;

        }else if(weight <= weightForAge.getOneScore() && weight > weightForAge.getZeroScore()){
            message = "Inbetween 1 index: "+weightForAge.getOneScore()+"kg and 0 index: "+weightForAge.getZeroScore()+"kg";
            isHealthy = true;

        }else if(weight <= weightForAge.getZeroScore() && weight > weightForAge.getMinusOneScore()){
            message = "Inbetween 0 index: "+weightForAge.getZeroScore()+"kg and -1 index: "+weightForAge.getMinusOneScore()+"kg";
            isHealthy = true;

        }else if(weight <= weightForAge.getMinusOneScore() && weight > weightForAge.getMinusTwoScore()){
            message = "Inbetween -1 index: "+weightForAge.getMinusOneScore()+"kg and -2 index: "+weightForAge.getMinusTwoScore()+"kg";
            isHealthy = true;

        }else if(weight <= weightForAge.getMinusTwoScore() && weight > weightForAge.getMinusThreeScore()){
            message = "Inbetween -2 index: "+weightForAge.getMinusTwoScore()+"kg and -3 index: "+weightForAge.getMinusThreeScore()+"kg";
            isHealthy = false;
        }else{
            message = "Lesser than -3 index: "+weightForAge.getMinusThreeScore()+"kg";
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
