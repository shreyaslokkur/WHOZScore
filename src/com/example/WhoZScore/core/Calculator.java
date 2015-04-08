package com.example.WhoZScore.core;

import android.content.Context;
import com.example.WhoZScore.data.dao.HeightForAgeDataSource;
import com.example.WhoZScore.data.dao.WeightForAgeDataSource;
import com.example.WhoZScore.data.entities.HeightForAge;
import com.example.WhoZScore.data.entities.WeightForAge;
import com.example.WhoZScore.enums.Age;
import com.example.WhoZScore.enums.AgeGroup;
import com.example.WhoZScore.enums.Sex;
import com.example.WhoZScore.enums.ZScoreGraphTypes;
import com.example.WhoZScore.model.GraphModel;
import com.example.WhoZScore.model.Patient;

import java.util.ArrayList;
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

        weightForAgeDataSource = new WeightForAgeDataSource(context);
        WeightForAge weightForAge = weightForAgeDataSource.getScore(patient.getAgeInWeeks(), patient.getAgeInMonths(), patient.getAgeInYears(), patient.getSex());
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

        int maxYears = 0;
        Age age= Age.MONTHS;
        AgeGroup ageGroup = null;
        GraphModel graphModel = null;
        if(patient.getAgeInWeeks() > 0)  {
            ageGroup = AgeGroup.WEEKS;
            age = Age.WEEKS;
        }else if(patient.getAgeInMonths() > 0 && (patient.getAgeInYears() >= 0 && patient.getAgeInYears() < 1)){
            ageGroup = AgeGroup.TILLONEYEAR;
        }else if(patient.getAgeInMonths() >= 0 && (patient.getAgeInYears() >=1 && patient.getAgeInYears() <2)){
            ageGroup = AgeGroup.TILLTWOYEARS;
        }else if(patient.getAgeInMonths() >= 0 && (patient.getAgeInYears() >= 2 && patient.getAgeInYears() < 3)){
            ageGroup = AgeGroup.TILLTHREEYEARS;
        }else if(patient.getAgeInMonths() >= 0 && (patient.getAgeInYears() >= 3 && patient.getAgeInYears() < 4)){
            ageGroup = AgeGroup.TILLFOURYEARS;
        }else {
            ageGroup = AgeGroup.TILLFIVEYEARS;
        }

        List<Integer> xAxis = createXAxis(age, ageGroup.getMaxYears());
        List<HeightForAge> scoreRangeForHeight = null;
        List<WeightForAge> scoreRangeForWeight = null;

        switch (zScoreGraphTypes){
            case HEIGHT_FOR_AGE_BOYS:
                scoreRangeForHeight = getScoreRange(ageGroup, patient.getSex(), zScoreGraphTypes);
                break;
            case HEIGHT_FOR_AGE_GIRLS:
                scoreRangeForHeight = getScoreRange(ageGroup, patient.getSex(), zScoreGraphTypes);
                break;
            case WEIGHT_FOR_AGE_BOYS:
                scoreRangeForWeight = getScoreRange(ageGroup, patient.getSex(), zScoreGraphTypes);
                break;
            case WEIGHT_FOR_AGE_GIRLS:
                scoreRangeForWeight = getScoreRange(ageGroup, patient.getSex(), zScoreGraphTypes);
                break;
        }
        if(scoreRangeForHeight != null){
            graphModel = createGraphModelForHeight(scoreRangeForHeight, zScoreGraphTypes, age, patient);
        }else {
            graphModel = createGraphModelForWeight(scoreRangeForWeight, zScoreGraphTypes, age, patient);
        }
        graphModel.setxAxis(xAxis);
        graphModel.setAgeInWeeks(patient.getAgeInWeeks());
        graphModel.setAgeInMonths(patient.getAgeInMonths());
        graphModel.setAgeInYears(patient.getAgeInYears());
        graphModel.setAgeGroup(ageGroup);


        return graphModel;
    }

    private List getScoreRange(AgeGroup ageGroup, Sex sex, ZScoreGraphTypes zScoreGraphTypes){
        List scoreRange = new ArrayList();
        if(AgeGroup.WEEKS.equals(ageGroup)){
            scoreRange.addAll(getScoreRange(0, 12, 0, 0, 0, 0, sex, zScoreGraphTypes ));
        }else if(AgeGroup.TILLONEYEAR.equals(ageGroup)){
            scoreRange.addAll(getScoreRange(0, 0, 3, 11, 0, 0, sex, zScoreGraphTypes));
        }else if(AgeGroup.TILLTWOYEARS.equals(ageGroup)){
            scoreRange.addAll(getScoreRange(0, 0, 0, 11, 1, 1,sex,zScoreGraphTypes));
        }else if(AgeGroup.TILLTHREEYEARS.equals(ageGroup)){
            scoreRange.addAll(getScoreRange(0, 0, 0, 11, 2, 2,sex,zScoreGraphTypes));
        }else if(AgeGroup.TILLFOURYEARS.equals(ageGroup)){
            scoreRange.addAll(getScoreRange(0,0,0,11,3,3,sex,zScoreGraphTypes));
        }else if(AgeGroup.TILLFIVEYEARS.equals(ageGroup)){
            scoreRange.addAll(getScoreRange(0,0,0,11,4,5,sex,zScoreGraphTypes));
        }
        return scoreRange;
    }

    private List getScoreRange(int minWeeks, int maxWeeks, int minMonths, int maxMonths, int minYears, int maxYears, Sex sex, ZScoreGraphTypes zScoreGraphTypes){
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
            return scoreRangeForHeight;
        }else {
            return scoreRangeForWeight;
        }

    }

    private GraphModel createGraphModelForWeight(List<WeightForAge> scoreRangeForWeight, ZScoreGraphTypes zScoreGraphTypes, Age age, Patient patient) {
        GraphModel graphModel = new GraphModel();
        graphModel.setzScoreGraphTypes(zScoreGraphTypes);
        graphModel.setAge(age);
        Double minusThreeScoreDoubleValue = new Double(scoreRangeForWeight.get(0).getMinusThreeScore());
        Double maxThreeScoreDoubleValue = new Double(scoreRangeForWeight.get(scoreRangeForWeight.size() - 1).getThreeScore());
        graphModel.setyMin( minusThreeScoreDoubleValue.intValue() - 10);
        graphModel.setyMax(maxThreeScoreDoubleValue.intValue() + 10);
        for(WeightForAge weightForAge : scoreRangeForWeight){
            graphModel.getMinusThreeScore().add(weightForAge.getMinusThreeScore());
            graphModel.getMinusTwoScore().add(weightForAge.getMinusTwoScore());
            graphModel.getMinusOneScore().add(weightForAge.getMinusOneScore());
            graphModel.getZeroScore().add(weightForAge.getZeroScore());
            graphModel.getOneScore().add(weightForAge.getOneScore());
            graphModel.getTwoScore().add(weightForAge.getTwoScore());
            graphModel.getThreeScore().add(weightForAge.getThreeScore());

        }

        graphModel.setPatientWeight(patient.getWeight());
        return graphModel;
    }

    private GraphModel createGraphModelForHeight(List<HeightForAge> scoreRangeForHeight, ZScoreGraphTypes zScoreGraphTypes, Age age, Patient patient) {
        GraphModel graphModel = new GraphModel();
        graphModel.setzScoreGraphTypes(zScoreGraphTypes);
        graphModel.setAge(age);
        Double minusThreeScoreDoubleValue = new Double(scoreRangeForHeight.get(0).getMinusThreeScore());
        Double maxThreeScoreDoubleValue = new Double(scoreRangeForHeight.get(scoreRangeForHeight.size() - 1).getThreeScore());
        graphModel.setyMin( minusThreeScoreDoubleValue.intValue() - 10);
        graphModel.setyMax(maxThreeScoreDoubleValue.intValue() + 10);
        for(HeightForAge heightForAge : scoreRangeForHeight){
            graphModel.getMinusThreeScore().add(heightForAge.getMinusThreeScore());
            graphModel.getMinusTwoScore().add(heightForAge.getMinusTwoScore());
            graphModel.getMinusOneScore().add(heightForAge.getMinusOneScore());
            graphModel.getZeroScore().add(heightForAge.getZeroScore());
            graphModel.getOneScore().add(heightForAge.getOneScore());
            graphModel.getTwoScore().add(heightForAge.getTwoScore());
            graphModel.getThreeScore().add(heightForAge.getThreeScore());

        }

        graphModel.setPatientHeight(patient.getHeight());

        return graphModel;
    }

    private List<Integer> createXAxis(Age age, int maxYears){
        List<Integer> xAxis = new ArrayList<Integer>();
        if(Age.WEEKS.equals(age)){
            for(int i=0; i<=12; i++){
                xAxis.add(i);
            }

        }
        if(Age.MONTHS.equals(age)){
            if(maxYears == 1){
                for(int i = 3; i< 12; i++){
                    xAxis.add(i);
                }
            }else if(maxYears == 2){
                for(int i = 12; i <24; i++){
                    xAxis.add(i);
                }
            }else if(maxYears == 3){
                for(int i = 24; i <36; i++){
                    xAxis.add(i);
                }
            }else if(maxYears == 4){
                for(int i = 36; i <48; i++){
                    xAxis.add(i);
                }
            }else if(maxYears == 5){
                for(int i = 48; i< 60; i++){
                    xAxis.add(i);
                }
            }
        }
        return xAxis;
    }

    private int calculateAgeInMonths(int months, int years){
        return ((years * 12) + months);
    }


}
