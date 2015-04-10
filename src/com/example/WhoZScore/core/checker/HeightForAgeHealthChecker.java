package com.example.WhoZScore.core.checker;

import com.example.WhoZScore.data.entities.HeightForAge;
import com.example.WhoZScore.data.entities.IZScoreEntity;
import com.example.WhoZScore.model.HeightForAgeResult;
import com.example.WhoZScore.model.IResult;
import com.example.WhoZScore.model.Patient;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 4/10/15
 * Time: 5:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class HeightForAgeHealthChecker implements IHealthChecker {



    public IResult getHealthResult(Patient patient, IZScoreEntity zScoreEntity){
        IResult heightForAgeResult;
        heightForAgeResult = calculateHeightResultForAge(patient, zScoreEntity);
        return heightForAgeResult;
    }

    private IResult calculateHeightResultForAge(Patient patient, IZScoreEntity zScoreEntity) {
        HeightForAgeResult heightForAgeResult = new HeightForAgeResult();
        HeightForAge heightForAge = (HeightForAge) zScoreEntity;
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

}
