package com.example.WhoZScore.core.calculator;

import android.content.Context;
import com.example.WhoZScore.data.dao.IZScoreDataSource;
import com.example.WhoZScore.data.dao.WeightForHeightDataSource;
import com.example.WhoZScore.data.entities.IZScoreEntity;
import com.example.WhoZScore.data.entities.WeightForHeight;
import com.example.WhoZScore.enums.Age;
import com.example.WhoZScore.enums.AgeGroup;
import com.example.WhoZScore.enums.Sex;
import com.example.WhoZScore.enums.ZScoreGraphTypes;
import com.example.WhoZScore.model.GraphModel;
import com.example.WhoZScore.model.Patient;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 4/9/15
 * Time: 12:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class WeightForHeightCalculator extends AbstractCalculator implements ICalculator {

    IZScoreDataSource weightForHeightDataSource;
    @Override
    public IZScoreEntity calculateZScore(Patient patient, Context context) {
        weightForHeightDataSource = new WeightForHeightDataSource(context);
        WeightForHeight weightForHeight = (WeightForHeight) weightForHeightDataSource.getScore(patient.getAgeInWeeks(), patient.getAgeInMonths(), patient.getAgeInYears(), patient.getSex());

        return weightForHeight;
    }

    @Override
    public GraphModel getGraphModel(Patient patient, ZScoreGraphTypes zScoreGraphTypes, Context context) {
        weightForHeightDataSource = new WeightForHeightDataSource(context);

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
        List<WeightForHeight> scoreRangeForWeightForHeight = null;

        switch (zScoreGraphTypes){

            case WEIGHT_FOR_HEIGHT_BOYS:
                scoreRangeForWeightForHeight = getScoreRange(ageGroup, patient.getSex(), zScoreGraphTypes);
                break;
            case WEIGHT_FOR_HEIGHT_GIRLS:
                scoreRangeForWeightForHeight = getScoreRange(ageGroup, patient.getSex(), zScoreGraphTypes);
                break;
        }

        graphModel = createGraphModelForWeightForHeight(scoreRangeForWeightForHeight, zScoreGraphTypes, age, patient);
        graphModel.setxAxis(xAxis);
        graphModel.setAgeInWeeks(patient.getAgeInWeeks());
        graphModel.setAgeInMonths(patient.getAgeInMonths());
        graphModel.setAgeInYears(patient.getAgeInYears());
        graphModel.setAgeGroup(ageGroup);


        return graphModel;
    }

    private GraphModel createGraphModelForWeightForHeight(List<WeightForHeight> scoreRangeForWeightForHeight, ZScoreGraphTypes zScoreGraphTypes, Age age, Patient patient) {
        GraphModel graphModel = new GraphModel();
        graphModel.setzScoreGraphTypes(zScoreGraphTypes);
        graphModel.setAge(age);
        Double minusThreeScoreDoubleValue = new Double(scoreRangeForWeightForHeight.get(0).getMinusThreeScore());
        Double maxThreeScoreDoubleValue = new Double(scoreRangeForWeightForHeight.get(scoreRangeForWeightForHeight.size() - 1).getThreeScore());
        graphModel.setyMin( minusThreeScoreDoubleValue.intValue() - 10);
        graphModel.setyMax(maxThreeScoreDoubleValue.intValue() + 10);
        for(WeightForHeight weightForHeight : scoreRangeForWeightForHeight){
            graphModel.getMinusThreeScore().add(weightForHeight.getMinusThreeScore());
            graphModel.getMinusTwoScore().add(weightForHeight.getMinusTwoScore());
            graphModel.getMinusOneScore().add(weightForHeight.getMinusOneScore());
            graphModel.getZeroScore().add(weightForHeight.getZeroScore());
            graphModel.getOneScore().add(weightForHeight.getOneScore());
            graphModel.getTwoScore().add(weightForHeight.getTwoScore());
            graphModel.getThreeScore().add(weightForHeight.getThreeScore());

        }
        graphModel.setPatientHeight(patient.getHeight());
        graphModel.setPatientWeight(patient.getWeight());
        return graphModel;
    }


    @Override
    public List getScoreRange(int minWeeks, int maxWeeks, int minMonths, int maxMonths, int minYears, int maxYears, Sex sex, ZScoreGraphTypes zScoreGraphTypes) {
        List<WeightForHeight> scoreRangeForWeightForHeight = null;
        switch (zScoreGraphTypes){

            case WEIGHT_FOR_HEIGHT_BOYS:
                scoreRangeForWeightForHeight = weightForHeightDataSource.getScoreRange(minWeeks, maxWeeks, minMonths, maxMonths, minYears, maxYears, Sex.MALE);
                break;
            case WEIGHT_FOR_HEIGHT_GIRLS:
                scoreRangeForWeightForHeight = weightForHeightDataSource.getScoreRange(minWeeks, maxWeeks, minMonths, maxMonths, minYears, maxYears, Sex.FEMALE);
                break;
        }

        return scoreRangeForWeightForHeight;
    }
}
