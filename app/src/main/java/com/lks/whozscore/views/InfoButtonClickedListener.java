package com.lks.whozscore.views;

import android.app.Fragment;
import android.view.View;

import com.lks.whozscore.WhoZScoreActivity;
import com.lks.whozscore.core.PatientInterface;
import com.lks.whozscore.enums.Sex;
import com.lks.whozscore.enums.ZScoreCalculators;
import com.lks.whozscore.enums.ZScoreGraphTypes;
import com.lks.whozscore.model.Patient;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 3/29/15
 * Time: 1:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class InfoButtonClickedListener implements View.OnClickListener {

    ZScoreCalculators zScoreCalculators;
    PatientInterface patientInterface;
    public InfoButtonClickedListener(ZScoreCalculators zScoreCalculators, PatientInterface patientInterface) {
        this.zScoreCalculators = zScoreCalculators;
        this.patientInterface = patientInterface;

    }

    @Override
    public void onClick(View v) {

        WhoZScoreActivity whoZScore = (WhoZScoreActivity) patientInterface;
        final Fragment individualInfoView = new IndividualInfoView();
        ((IndividualInfoView)individualInfoView).setZScoreCalculators(zScoreCalculators);


        whoZScore.replaceFragment(individualInfoView);




    }
}
