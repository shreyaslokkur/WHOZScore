package com.example.WhoZScore.core;

import com.example.WhoZScore.WhoZScore;
import com.example.WhoZScore.data.entities.WeightForAge;
import com.example.WhoZScore.model.Patient;
import com.example.WhoZScore.model.Result;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 3/29/15
 * Time: 3:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class HealthChecker {

    public Result checkIfHealthy(Patient patient, WeightForAge weightForAge){
        double weight = patient.getWeight();
        String message = null;
        Result result = null;
        if(patient.getAgeInYears() < 5){
            result = ageLessThanFiveYears(patient,weightForAge);
        }else {
            result = ageGreaterThanFiveYears(patient,weightForAge);
        }
        return result;
    }

    private Result ageLessThanFiveYears(Patient patient, WeightForAge weightForAge){
        double weight = patient.getWeight();
        Result result = new Result();
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
        result.setHealthy(isHealthy);
        result.setzScoreMessage(message);
        return result;
    }

    private Result ageGreaterThanFiveYears(Patient patient, WeightForAge weightForAge){
        double weight = patient.getWeight();
        Result result = new Result();
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
        result.setHealthy(isHealthy);
        result.setzScoreMessage(message);
        return result;

    }
}
