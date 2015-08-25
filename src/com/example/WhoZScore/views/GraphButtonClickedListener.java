package com.example.WhoZScore.views;

import android.app.Fragment;
import android.view.View;
import com.example.WhoZScore.WhoZScoreActivity;
import com.example.WhoZScore.core.PatientInterface;
import com.example.WhoZScore.enums.Sex;
import com.example.WhoZScore.enums.ZScoreCalculators;
import com.example.WhoZScore.enums.ZScoreGraphTypes;
import com.example.WhoZScore.model.Patient;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 3/29/15
 * Time: 1:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class GraphButtonClickedListener implements View.OnClickListener {

    ZScoreCalculators zScoreCalculators;
    PatientInterface patientInterface;
    public GraphButtonClickedListener(ZScoreCalculators zScoreCalculators, PatientInterface patientInterface) {
        this.zScoreCalculators = zScoreCalculators;
        this.patientInterface = patientInterface;

    }

    @Override
    public void onClick(View v) {

        WhoZScoreActivity whoZScore = (WhoZScoreActivity) patientInterface;
        Patient patient = patientInterface.getPatient();
        final Fragment graphView = new GraphView();
        if(zScoreCalculators.equals(ZScoreCalculators.WEIGHT_FOR_AGE)){
            if(Sex.FEMALE.equals(patient.getSex()))
                ((GraphView)graphView).setScoreGraphTypes(ZScoreGraphTypes.WEIGHT_FOR_AGE_GIRLS);
            else
                ((GraphView)graphView).setScoreGraphTypes(ZScoreGraphTypes.WEIGHT_FOR_AGE_BOYS);

        }else if(zScoreCalculators.equals(ZScoreCalculators.HEIGHT_FOR_AGE)){
            if(Sex.FEMALE.equals(patient.getSex()))
                ((GraphView)graphView).setScoreGraphTypes(ZScoreGraphTypes.HEIGHT_FOR_AGE_GIRLS);
            else
                ((GraphView)graphView).setScoreGraphTypes(ZScoreGraphTypes.HEIGHT_FOR_AGE_BOYS);

        }else if(zScoreCalculators.equals(ZScoreCalculators.WEIGHT_FOR_HEIGHT)){
            if(Sex.FEMALE.equals(patient.getSex()))
                ((GraphView)graphView).setScoreGraphTypes(ZScoreGraphTypes.WEIGHT_FOR_HEIGHT_GIRLS);
            else
                ((GraphView)graphView).setScoreGraphTypes(ZScoreGraphTypes.WEIGHT_FOR_HEIGHT_BOYS);

        }else if(zScoreCalculators.equals(ZScoreCalculators.HEAD_CIRCUMFERENCE_FOR_AGE)){
            if(Sex.FEMALE.equals(patient.getSex()))
                ((GraphView)graphView).setScoreGraphTypes(ZScoreGraphTypes.HEAD_CIRCUMFERENCE_FOR_AGE_GIRLS);
            else
                ((GraphView)graphView).setScoreGraphTypes(ZScoreGraphTypes.HEAD_CIRCUMFERENCE_FOR_AGE_BOYS);

        }


        whoZScore.replaceFragment(graphView);




    }
}
