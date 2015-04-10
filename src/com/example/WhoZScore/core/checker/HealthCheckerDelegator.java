package com.example.WhoZScore.core.checker;

import com.example.WhoZScore.data.entities.HeightForAge;
import com.example.WhoZScore.data.entities.WeightForAge;
import com.example.WhoZScore.data.entities.WeightForHeight;
import com.example.WhoZScore.model.*;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 3/29/15
 * Time: 3:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class HealthCheckerDelegator {

    HeightForAgeHealthChecker heightForAgeHealthChecker = new HeightForAgeHealthChecker();
    WeightForAgeHealthChecker weightForAgeHealthChecker = new WeightForAgeHealthChecker();
    WeightForHeightHealthChecker weightForHeightHealthChecker = new WeightForHeightHealthChecker();

    public Result getHealthResult(Patient patient, WeightForAge weightForAge, HeightForAge heightForAge, WeightForHeight weightForHeight){
        Result result = null;
        WeightForAgeResult resultForWeight = null;
        HeightForAgeResult resultForHeight = null;
        WeightForHeightResult weightForHeightResult = null;
        if(weightForAge != null){
           resultForWeight = (WeightForAgeResult) weightForAgeHealthChecker.getHealthResult(patient, weightForAge);
        }
        if(heightForAge != null){
            resultForHeight = (HeightForAgeResult) heightForAgeHealthChecker.getHealthResult(patient, heightForAge);
        }
        if(weightForHeight != null){
           weightForHeightResult = (WeightForHeightResult) weightForHeightHealthChecker.getHealthResult(patient, weightForHeight);
        }

        result = setResult(resultForWeight, resultForHeight, weightForHeightResult);
        return result;
    }


    private Result setResult(WeightForAgeResult weightForAgeResult, HeightForAgeResult heightForAgeResult, WeightForHeightResult weightForHeightResult){
        Result result = new Result();
        if(weightForAgeResult != null){
            result.setzScoreWeightForAgeMessage(weightForAgeResult.getzScoreWeightMessage());

        }
        if(heightForAgeResult != null){
            result.setzScoreHeightForAgeMessage(heightForAgeResult.getzScoreHeightMessage());
        }
        if(weightForHeightResult != null){
            result.setzScoreWeightForHeightMessage(weightForHeightResult.getzScoreWeightForHeightMessage());
        }
        return result;
    }
}
