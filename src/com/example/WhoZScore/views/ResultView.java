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
import com.example.WhoZScore.enums.Sex;
import com.example.WhoZScore.enums.ZScoreGraphTypes;
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

    private TextView ageText, weightText, heightText, headCircumferenceText, zScoreWeightMessageText, zScoreHeightMessageText, zScoreWeightForHeightMessageText, zScoreHeadCircumferenceForAgeMessageText, weightHeader, heightHeader, weightForHeightHeader, headCircumferenceHeader;

    private LinearLayout heightResultLayout, weightForHeightLayout, weightResultLayout, headCircumferenceResultLayout;

    private LinearLayout heightHeaderLayout, weightHeaderLayout, weightForHeightHeaderLayout, headCircumferenceHeaderLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.result_view, container, false);
        final Fragment graphView = new GraphView();
        ageText = (TextView) view.findViewById(R.id.ageResultTextId);
        weightText = (TextView) view.findViewById(R.id.weightResultTextId);
        heightText = (TextView) view.findViewById(R.id.heightResultTextId);
        headCircumferenceText = (TextView) view.findViewById(R.id.headCircumferenceResultTextId);
        weightHeader = (TextView) view.findViewById(R.id.weightHeader);
        heightHeader = (TextView) view.findViewById(R.id.heightHeader);
        headCircumferenceHeader = (TextView) view.findViewById(R.id.headCircumferenceForAgeHeader);
        zScoreWeightMessageText = (TextView) view.findViewById(R.id.zScoreWeightResultTextId);
        zScoreHeightMessageText = (TextView) view.findViewById(R.id.zScoreHeightResultTextId);
        zScoreWeightForHeightMessageText = (TextView) view.findViewById(R.id.zScoreWeightForHeightResultTextId);
        zScoreHeadCircumferenceForAgeMessageText = (TextView) view.findViewById(R.id.zScoreHeadCircumferenceForAgeResultTextId);
        weightResultLayout = (LinearLayout) view.findViewById(R.id.weightResultLayout);
        heightResultLayout = (LinearLayout) view.findViewById(R.id.heightResultLayout);
        weightForHeightHeader = (TextView) view.findViewById(R.id.weightForHeightHeader);
        weightForHeightLayout = (LinearLayout) view.findViewById(R.id.weightForHeightResultLayout);
        headCircumferenceResultLayout = (LinearLayout) view.findViewById(R.id.headCircumferenceForAgeResultLayout);

        heightHeaderLayout = (LinearLayout) view.findViewById(R.id.heightHeaderLayout);
        weightHeaderLayout = (LinearLayout) view.findViewById(R.id.weightHeaderLayout);
        weightForHeightHeaderLayout = (LinearLayout) view.findViewById(R.id.weightForHeightHeaderLayout);
        headCircumferenceHeaderLayout = (LinearLayout) view.findViewById(R.id.headCircumferenceForAgeHeaderLayout);

        Result result = ((WhoZScore) getActivity()).getResult();
        final Patient patient = ((WhoZScore) getActivity()).getPatient();
        if(patient.getHeight() == 0){

            ((LinearLayout)heightHeaderLayout.getParent()).removeView(heightHeaderLayout);
            ((LinearLayout)weightForHeightHeaderLayout.getParent()).removeView(weightForHeightHeaderLayout);
            ((LinearLayout)heightResultLayout.getParent()).removeView(heightResultLayout);
            ((LinearLayout)weightForHeightLayout.getParent()).removeView(weightForHeightLayout);
        }else {
            heightText.setText(String.valueOf(patient.getHeight()) + "cms");
            heightText.setTextColor(Color.BLACK);
            zScoreHeightMessageText.setText(setMessage(result.getzScoreHeightForAgeMessage(), result.getHealthyHeightForAgeMessage()));
        }

        if(patient.getWeight() == 0){

            ((LinearLayout)weightHeaderLayout.getParent()).removeView(weightHeaderLayout);
            ((LinearLayout)weightForHeightHeaderLayout.getParent()).removeView(weightForHeightHeaderLayout);
            ((LinearLayout)weightResultLayout.getParent()).removeView(weightResultLayout);
            ((LinearLayout)weightForHeightLayout.getParent()).removeView(weightForHeightLayout);
        }else {
            weightText.setText(String.valueOf(patient.getWeight()) + "kg");
            weightText.setTextColor(Color.BLACK);
            zScoreWeightMessageText.setText(setMessage(result.getzScoreWeightForAgeMessage(), result.getHealthyWeightForAgeMessage()));
        }

        if(patient.getHeight() > 0 && patient.getWeight() > 0){
            zScoreWeightForHeightMessageText.setText(setMessage(result.getzScoreWeightForHeightMessage(), result.getHealthyWeightForHeightMessage()));
        }

        if(patient.getHeadCircumference() == 0){


            ((LinearLayout)headCircumferenceHeaderLayout.getParent()).removeView(headCircumferenceHeaderLayout);

            ((LinearLayout)headCircumferenceResultLayout.getParent()).removeView(headCircumferenceResultLayout);


        }else {
            headCircumferenceText.setText(String.valueOf(patient.getHeadCircumference()) + "cms");
            headCircumferenceText.setTextColor(Color.BLACK);
            zScoreHeadCircumferenceForAgeMessageText.setText(result.getzScoreHeadCircumferenceForAgeMessage());
        }




        ageText.setText(getAge(patient));


        heightHeader.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(Sex.FEMALE.equals(patient.getSex()))
                    ((GraphView)graphView).setScoreGraphTypes(ZScoreGraphTypes.HEIGHT_FOR_AGE_GIRLS);
                else
                    ((GraphView)graphView).setScoreGraphTypes(ZScoreGraphTypes.HEIGHT_FOR_AGE_BOYS);

                ((WhoZScore)getActivity()).replaceFragment(graphView);

            }
        });

        weightHeader.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(Sex.FEMALE.equals(patient.getSex()))
                    ((GraphView)graphView).setScoreGraphTypes(ZScoreGraphTypes.WEIGHT_FOR_AGE_GIRLS);
                else
                    ((GraphView)graphView).setScoreGraphTypes(ZScoreGraphTypes.WEIGHT_FOR_AGE_BOYS);

                ((WhoZScore)getActivity()).replaceFragment(graphView);

            }
        });

        weightForHeightHeader.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(Sex.FEMALE.equals(patient.getSex()))
                    ((GraphView)graphView).setScoreGraphTypes(ZScoreGraphTypes.WEIGHT_FOR_HEIGHT_GIRLS);
                else
                    ((GraphView)graphView).setScoreGraphTypes(ZScoreGraphTypes.WEIGHT_FOR_HEIGHT_BOYS);

                ((WhoZScore)getActivity()).replaceFragment(graphView);

            }
        });

        headCircumferenceHeader.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(Sex.FEMALE.equals(patient.getSex()))
                    ((GraphView)graphView).setScoreGraphTypes(ZScoreGraphTypes.HEAD_CIRCUMFERENCE_FOR_AGE_GIRLS);
                else
                    ((GraphView)graphView).setScoreGraphTypes(ZScoreGraphTypes.HEAD_CIRCUMFERENCE_FOR_AGE_BOYS);

                ((WhoZScore)getActivity()).replaceFragment(graphView);

            }
        });


        // Inflate the layout for this fragment
        return view;

    }

    private String getAge(Patient patient){
        String age = patient.getDisplayAgeInYears() + " years, "+patient.getDisplayAgeInMonths()+" months and "+patient.getDisplayAgeInWeeks() +" weeks";
        return age;
    }

    private String setMessage(String zScore, String healthyMessage){
        StringBuilder message = new StringBuilder(zScore);
        /*message.append(" ( ").append(healthyMessage).append(" )");*/

        return message.toString();
    }



    @Override
    public String toString(){
        return "ResultView";
    }
}
