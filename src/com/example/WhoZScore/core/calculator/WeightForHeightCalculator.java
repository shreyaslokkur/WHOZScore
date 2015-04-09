package com.example.WhoZScore.core.calculator;

import android.content.Context;
import com.example.WhoZScore.data.entities.IZScoreEntity;
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
    @Override
    public IZScoreEntity calculateZScore(Patient patient, Context context) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public GraphModel getGraphModel(Patient patient, ZScoreGraphTypes zScoreGraphTypes, Context context) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List getScoreRange(int minWeeks, int maxWeeks, int minMonths, int maxMonths, int minYears, int maxYears, Sex sex, ZScoreGraphTypes zScoreGraphTypes) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
