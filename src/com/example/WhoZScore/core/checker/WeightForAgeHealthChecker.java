package com.example.WhoZScore.core.checker;

import com.example.WhoZScore.data.entities.IZScoreEntity;
import com.example.WhoZScore.data.entities.WeightForAge;
import com.example.WhoZScore.model.IResult;
import com.example.WhoZScore.model.Patient;
import com.example.WhoZScore.model.WeightForAgeResult;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 4/10/15
 * Time: 5:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class WeightForAgeHealthChecker implements IHealthChecker {

    public IResult getHealthResult(Patient patient, IZScoreEntity zScoreEntity){
        WeightForAgeResult weightForAgeResult;
        weightForAgeResult = calculateWeightResultForAge(patient, zScoreEntity);
        return weightForAgeResult;

    }

    private WeightForAgeResult calculateWeightResultForAge(Patient patient, IZScoreEntity zScoreEntity){
        WeightForAgeResult weightForAgeResult = new WeightForAgeResult();
        WeightForAge weightForAge = (WeightForAge) zScoreEntity;
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

}
