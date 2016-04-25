package com.lks.whozscore.core.calculator;

import android.content.Context;
import com.lks.whozscore.data.entities.IZScoreEntity;
import com.lks.whozscore.enums.ZScoreGraphTypes;
import com.lks.whozscore.model.GraphModel;
import com.lks.whozscore.model.Patient;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 4/9/15
 * Time: 12:09 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ICalculator {

     IZScoreEntity calculateZScore(Patient patient, Context context);
     GraphModel getGraphModel(Patient patient, ZScoreGraphTypes zScoreGraphTypes, Context context);
}
