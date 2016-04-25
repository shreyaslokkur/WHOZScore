package com.lks.whozscore.core.calculator;

import android.content.Context;
import com.lks.whozscore.data.dao.HeightForAgeDataSource;
import com.lks.whozscore.data.dao.WeightForAgeDataSource;
import com.lks.whozscore.data.entities.HeightForAge;
import com.lks.whozscore.data.entities.IZScoreEntity;
import com.lks.whozscore.data.entities.WeightForAge;
import com.lks.whozscore.enums.Age;
import com.lks.whozscore.enums.AgeGroup;
import com.lks.whozscore.enums.Sex;
import com.lks.whozscore.enums.ZScoreGraphTypes;
import com.lks.whozscore.model.GraphModel;
import com.lks.whozscore.model.Patient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 3/29/15
 * Time: 3:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class AbstractCalculator implements ICalculator  {



    public List getScoreRange(AgeGroup ageGroup, Sex sex, ZScoreGraphTypes zScoreGraphTypes){
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

    public List getScoreRange(int minWeeks, int maxWeeks, int minMonths, int maxMonths, int minYears, int maxYears, Sex sex, ZScoreGraphTypes zScoreGraphTypes){
        System.out.println("Method not supported by:"+ this.getClass());
        return null;
    }
    public List getScoreRange(int minHeight, int maxHeight, Sex sex, ZScoreGraphTypes zScoreGraphTypes){
        System.out.println("Method not supported by:"+ this.getClass());
        return null;
    }



    public List<Integer> createXAxis(Age age, int maxYears){
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
            }else if(maxYears == 5){
                for(int i = 0; i<= 12; i++){
                    xAxis.add(i);
                }
            }else{
                for(int i = 0; i <12; i++){
                    xAxis.add(i);
                }
            }
        }
        return xAxis;
    }

    public List<String> createXTextLabels(AgeGroup ageGroup){
        List<String> xTextLabels = new ArrayList<String>();
        if(AgeGroup.WEEKS.equals(ageGroup)){
            for(int i = 0; i<= 12; i++){
                xTextLabels.add(String.valueOf(i));
            }
        }else if(AgeGroup.TILLONEYEAR.equals(ageGroup)){
            xTextLabels.add("3 months");
            for(int i = 4; i< 12; i++){
                xTextLabels.add(String.valueOf(i));
            }
        }else if(AgeGroup.TILLTWOYEARS.equals(ageGroup)){
            xTextLabels.add("1 year");
            for(int i = 1; i< 12; i++){
                xTextLabels.add(String.valueOf(i));
            }
        }else if(AgeGroup.TILLTHREEYEARS.equals(ageGroup)){
            xTextLabels.add("2 years");
            for(int i = 1; i< 12; i++){
                xTextLabels.add(String.valueOf(i));
            }
        }else if(AgeGroup.TILLFOURYEARS.equals(ageGroup)){
            xTextLabels.add("3 years");
            for(int i = 1; i< 12; i++){
                xTextLabels.add(String.valueOf(i));
            }
        }else if(AgeGroup.TILLFIVEYEARS.equals(ageGroup)){
            xTextLabels.add("4 years");
            for(int i = 1; i< 12; i++){
                xTextLabels.add(String.valueOf(i));
            }
            xTextLabels.add("5 years");
        }

        return xTextLabels;
    }


    @Override
    public IZScoreEntity calculateZScore(Patient patient, Context context) {
        System.out.println("Method not supported by:"+ this.getClass());
        return null;
    }

    @Override
    public GraphModel getGraphModel(Patient patient, ZScoreGraphTypes zScoreGraphTypes, Context context) {
        System.out.println("Method not supported by:"+ this.getClass());
        return null;
    }
}
