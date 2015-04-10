package com.example.WhoZScore.core.checker;

import com.example.WhoZScore.data.entities.IZScoreEntity;
import com.example.WhoZScore.data.entities.WeightForHeight;
import com.example.WhoZScore.model.IResult;
import com.example.WhoZScore.model.Patient;
import com.example.WhoZScore.model.WeightForHeightResult;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 4/10/15
 * Time: 5:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class WeightForHeightHealthChecker implements IHealthChecker {

    @Override
    public IResult getHealthResult(Patient patient, IZScoreEntity zScoreEntity) {
        IResult weightForHeightResult;
        weightForHeightResult = calculateWeightForHeightResult(patient, zScoreEntity);
        return weightForHeightResult;
    }

    private IResult calculateWeightForHeightResult(Patient patient, IZScoreEntity zScoreEntity) {
        WeightForHeightResult weightForHeightResult = new WeightForHeightResult();
        WeightForHeight weightForHeight = (WeightForHeight) zScoreEntity;
        double weight = patient.getWeight();
        String message = null;
        boolean isHealthy;
        if(weight > weightForHeight.getThreeScore() ){
            message = "Greater than 3 ZScore";
            isHealthy = false;

        }else if(weight <= weightForHeight.getThreeScore() && weight >= weightForHeight.getTwoScore()){
            message = "Between 2 and 3 ZScore ";
            isHealthy = false;

        }else if(weight < weightForHeight.getTwoScore() && weight >= weightForHeight.getZeroScore()){
            message = "Between 0 and 2 ZScore ";
            isHealthy = true;

        }else if(weight < weightForHeight.getZeroScore() && weight >= weightForHeight.getMinusTwoScore()){
            message = "Between -2 and 0 ZScore ";
            isHealthy = true;

        }else if(weight < weightForHeight.getMinusTwoScore() && weight >= weightForHeight.getMinusThreeScore()){
            message = "Between -3 and -2 ZScore ";
            isHealthy = false;
        }else{
            message = "Lesser than -3 ZScore";
            isHealthy = false;
        }
        weightForHeightResult.setHealthy(isHealthy);
        weightForHeightResult.setzScoreWeightForHeightMessage(message);
        return weightForHeightResult;
    }
}
