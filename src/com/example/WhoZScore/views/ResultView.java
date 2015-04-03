package com.example.WhoZScore.views;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.WhoZScore.R;
import com.example.WhoZScore.WhoZScore;
import com.example.WhoZScore.model.Patient;
import com.example.WhoZScore.model.Result;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 8/18/14
 * Time: 3:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResultView extends Fragment {

    private TextView ageText, weightText, heightText, zScoreWeightMessageText, zScoreHeightMessageText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.result_view, container, false);
        ageText = (TextView) view.findViewById(R.id.ageResultTextId);
        weightText = (TextView) view.findViewById(R.id.weightResultTextId);
        heightText = (TextView) view.findViewById(R.id.heightResultTextId);
        zScoreWeightMessageText = (TextView) view.findViewById(R.id.zScoreWeightResultTextId);
        zScoreHeightMessageText = (TextView) view.findViewById(R.id.zScoreHeightResultTextId);


        Result result = ((WhoZScore) getActivity()).getResult();
        Patient patient = ((WhoZScore) getActivity()).getPatient();
        if(patient.getHeight() == 0){
            View heightHeader = view.findViewById(R.id.heightHeader);
            View heightResultLayout = view.findViewById(R.id.heightResultLayout);

            ((LinearLayout)heightHeader.getParent()).removeView(heightHeader);
            ((LinearLayout)heightResultLayout.getParent()).removeView(heightResultLayout);
        }else {
            heightText.setText(String.valueOf(patient.getHeight()) + "cms");
            heightText.setTextColor(Color.BLACK);
            zScoreHeightMessageText.setText(result.getzScoreHeightMessage());
        }

        if(patient.getWeight() == 0){
            View weightHeader = view.findViewById(R.id.weightHeader);
            View weightResultLayout = view.findViewById(R.id.weightResultLayout);

            ((LinearLayout)weightHeader.getParent()).removeView(weightHeader);
            ((LinearLayout)weightResultLayout.getParent()).removeView(weightResultLayout);
        }else {
            weightText.setText(String.valueOf(patient.getWeight()) + "kg");
            weightText.setTextColor(Color.BLACK);
            zScoreWeightMessageText.setText(result.getzScoreWeightMessage());
        }


        ageText.setText(getAge(patient));




        // Inflate the layout for this fragment
        return view;

    }

    private String getAge(Patient patient){
        String age = patient.getAgeInYears() + " years, "+patient.getAgeInMonths()+" months and "+patient.getAgeInWeeks() +" weeks";
        return age;
    }

    @Override
    public String toString(){
        return "ResultView";
    }
}
