package com.example.WhoZScore.core.calculator;

import android.content.Context;
import com.example.WhoZScore.data.dao.HeightForAgeDataSource;
import com.example.WhoZScore.data.dao.WeightForAgeDataSource;
import com.example.WhoZScore.data.entities.HeightForAge;
import com.example.WhoZScore.data.entities.IZScoreEntity;
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
