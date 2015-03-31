package com.example.WhoZScore.views;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

    private TextView ageText, weightText, zScoreMessageText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.result_view, container, false);
        ageText = (TextView) view.findViewById(R.id.ageResultTextId);
        weightText = (TextView) view.findViewById(R.id.weightResultTextId);
        zScoreMessageText = (TextView) view.findViewById(R.id.zScoreResultTextId);

        Result result = ((WhoZScore) getActivity()).getResult();
        Patient patient = ((WhoZScore) getActivity()).getPatient();
        weightText.setText(String.valueOf(patient.getWeight()) + "kg");
        ageText.setText(getAge(patient));
        zScoreMessageText.setText(result.getzScoreMessage());


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
