package com.example.WhoZScore.core;

import android.content.Context;
import com.example.WhoZScore.data.dao.HeightForAgeDataSource;
import com.example.WhoZScore.data.dao.WeightForAgeDataSource;
import com.example.WhoZScore.data.entities.HeightForAge;
import com.example.WhoZScore.data.entities.WeightForAge;
import com.example.WhoZScore.enums.Sex;
import com.example.WhoZScore.model.Patient;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 3/29/15
 * Time: 3:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class Calculator {

    WeightForAgeDataSource weightForAgeDataSource;
    HeightForAgeDataSource heightForAgeDataSource;

    public WeightForAge calculateWeightForAgeZScore(Patient patient, Context context){
        WeightForAge weightForAge;
        weightForAgeDataSource = new WeightForAgeDataSource(context);
        if(Sex.FEMALE.equals(patient.getSex())){
            weightForAge = weightForAgeDataSource.getScoreForGirls(patient.getAgeInWeeks(), patient.getAgeInMonths(), patient.getAgeInYears());

        }else {
            weightForAge = weightForAgeDataSource.getScoreForBoys(patient.getAgeInWeeks(), patient.getAgeInMonths(), patient.getAgeInYears());
        }
        return weightForAge;

    }

    public HeightForAge calculateHeightForAgeZScore(Patient patient, Context context){
        HeightForAge heightForAge;
        heightForAgeDataSource = new HeightForAgeDataSource(context);
        if(Sex.FEMALE.equals(patient.getSex())){
            heightForAge = heightForAgeDataSource.getScoreForGirls(patient.getAgeInWeeks(), patient.getAgeInMonths(), patient.getAgeInYears());

        }else {
            heightForAge = heightForAgeDataSource.getScoreForBoys(patient.getAgeInWeeks(), patient.getAgeInMonths(), patient.getAgeInYears());
        }
        return heightForAge;

    }


}
