package com.example.WhoZScore.core;

import android.content.Context;
import com.example.WhoZScore.data.dao.HeightForAgeDataSource;
import com.example.WhoZScore.data.dao.WeightForAgeDataSource;
import com.example.WhoZScore.data.entities.AbstractZScoreForAge;
import com.example.WhoZScore.data.entities.HeightForAge;
import com.example.WhoZScore.data.entities.WeightForAge;
import com.example.WhoZScore.enums.Age;
import com.example.WhoZScore.enums.Sex;
import com.example.WhoZScore.enums.ZScoreGraphTypes;
import com.example.WhoZScore.model.GraphModel;
import com.example.WhoZScore.model.Patient;

import java.util.List;

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

        heightForAgeDataSource = new HeightForAgeDataSource(context);
        HeightForAge heightForAge = heightForAgeDataSource.getScore(patient.getAgeInWeeks(), patient.getAgeInMonths(), patient.getAgeInYears(), patient.getSex());

        return heightForAge;

    }

    public GraphModel getGraphModel(Patient patient, ZScoreGraphTypes zScoreGraphTypes, Context context){
        heightForAgeDataSource = new HeightForAgeDataSource(context);
        weightForAgeDataSource = new WeightForAgeDataSource(context);
        int minWeeks = 0;
        int maxWeeks = 0;
        int minMonths = 0;
        int maxMonths = 0;
        int minYears = 0;
        int maxYears = 0;
        Age age= null;
        GraphModel graphModel = null;
        if(patient.getAgeInWeeks() > 0)  {
            maxWeeks = 12;
            age = Age.WEEKS;
        }else if(patient.getAgeInMonths() > 0 && (patient.getAgeInYears() >= 0 && patient.getAgeInYears() <= 2)){
            maxMonths = 11;
            maxYears = 2;
            age = Age.MONTHS;
        }else if(patient.getAgeInYears() >2 && patient.getAgeInYears() <=5){
            maxMonths = 11;
            minYears = 2;
            maxYears = 5;
            age = Age.MONTHS;
        }else {
            maxMonths = 11;
            minYears = 5;
            maxYears = 10;
            age = Age.MONTHS;
        }
        List<HeightForAge> scoreRangeForHeight = null;
        List<WeightForAge> scoreRangeForWeight = null;
        switch (zScoreGraphTypes){
            case HEIGHT_FOR_AGE_BOYS:
                scoreRangeForHeight = heightForAgeDataSource.getScoreRange(minWeeks, maxWeeks, minMonths, maxMonths, minYears, maxYears, Sex.MALE);
                break;
            case HEIGHT_FOR_AGE_GIRLS:
                scoreRangeForHeight = heightForAgeDataSource.getScoreRange(minWeeks, maxWeeks, minMonths, maxMonths, minYears, maxYears, Sex.FEMALE);
                break;
            case WEIGHT_FOR_AGE_BOYS:
                scoreRangeForWeight = weightForAgeDataSource.getScoreRange(minWeeks, maxWeeks, minMonths, maxMonths, minYears, maxYears, Sex.MALE);
                break;
            case WEIGHT_FOR_AGE_GIRLS:
                scoreRangeForWeight = weightForAgeDataSource.getScoreRange(minWeeks, maxWeeks, minMonths, maxMonths, minYears, maxYears, Sex.FEMALE);
                break;
        }
        if(scoreRangeForHeight != null){
            graphModel = createGraphModelForHeight(scoreRangeForHeight, zScoreGraphTypes, age);
        }else {
            graphModel = createGraphModelForWeight(scoreRangeForWeight, zScoreGraphTypes, age);
        }
        return graphModel;
    }

    private GraphModel createGraphModelForWeight(List<WeightForAge> scoreRangeForWeight, ZScoreGraphTypes zScoreGraphTypes, Age age) {
        GraphModel graphModel = new GraphModel();
        graphModel.setzScoreGraphTypes(zScoreGraphTypes);
        graphModel.setAge(age);
        for(WeightForAge weightForAge : scoreRangeForWeight){
            graphModel.getMinusThreeScore().add(weightForAge.getMinusThreeScore());
            graphModel.getMinusTwoScore().add(weightForAge.getMinusTwoScore());
            graphModel.getMinusOneScore().add(weightForAge.getMinusOneScore());
            graphModel.getZeroScore().add(weightForAge.getZeroScore());
            graphModel.getOneScore().add(weightForAge.getOneScore());
            graphModel.getTwoScore().add(weightForAge.getTwoScore());
            graphModel.getThreeScore().add(weightForAge.getThreeScore());

        }

        return graphModel;
    }

    private GraphModel createGraphModelForHeight(List<HeightForAge> scoreRangeForHeight, ZScoreGraphTypes zScoreGraphTypes, Age age) {
        GraphModel graphModel = new GraphModel();
        graphModel.setzScoreGraphTypes(zScoreGraphTypes);
        graphModel.setAge(age);
        for(HeightForAge heightForAge : scoreRangeForHeight){
            graphModel.getMinusThreeScore().add(heightForAge.getMinusThreeScore());
            graphModel.getMinusTwoScore().add(heightForAge.getMinusTwoScore());
            graphModel.getMinusOneScore().add(heightForAge.getMinusOneScore());
            graphModel.getZeroScore().add(heightForAge.getZeroScore());
            graphModel.getOneScore().add(heightForAge.getOneScore());
            graphModel.getTwoScore().add(heightForAge.getTwoScore());
            graphModel.getThreeScore().add(heightForAge.getThreeScore());

        }

        return graphModel;
    }


}
