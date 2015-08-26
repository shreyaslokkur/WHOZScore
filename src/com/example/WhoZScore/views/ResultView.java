package com.example.WhoZScore.views;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.example.WhoZScore.R;
import com.example.WhoZScore.WhoZScoreActivity;
import com.example.WhoZScore.core.PatientInterface;
import com.example.WhoZScore.enums.ZScoreCalculators;
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

    private TextView ageText, weightText, heightText, headCircumferenceText;


    private ResultCardArrayAdapter cardArrayAdapter;
    private ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.result_view, container, false);
        ((WhoZScoreActivity)getActivity()).showOverflowMenu(false);
        final Fragment graphView = new GraphView();
        ageText = (TextView) view.findViewById(R.id.ageResultTextId);
        weightText = (TextView) view.findViewById(R.id.weightResultTextId);
        heightText = (TextView) view.findViewById(R.id.heightResultTextId);
        headCircumferenceText = (TextView) view.findViewById(R.id.headCircumferenceResultTextId);

        listView = (ListView) view.findViewById(R.id.card_listView);

        listView.addHeaderView(new View(getActivity()));
        listView.addFooterView(new View(getActivity()));

        cardArrayAdapter = new ResultCardArrayAdapter(getActivity().getApplicationContext(), R.layout.result_list_item_card, (PatientInterface) getActivity());


        Result result = ((WhoZScoreActivity) getActivity()).getResult();
        final Patient patient = ((WhoZScoreActivity) getActivity()).getPatient();

        if(result.getzScoreWeightForAgeMessage() != null){
            ResultCard resultCard = new ResultCard();
            resultCard.setHeader("Weight For Age");
            resultCard.setzScoreResult(setMessage(result.getzScoreWeightForAgeMessage(), result.getHealthyWeightForAgeMessage()));
            resultCard.setzScoreCalculators(ZScoreCalculators.WEIGHT_FOR_AGE);
            cardArrayAdapter.add(resultCard);
            weightText.setText(String.valueOf(patient.getWeight()) + "kg");
            weightText.setTextColor(Color.BLACK);

        }

        if(result.getzScoreHeightForAgeMessage() != null){
            ResultCard resultCard = new ResultCard();
            resultCard.setHeader("Height For Age");
            resultCard.setzScoreResult(setMessage(result.getzScoreHeightForAgeMessage(), result.getHealthyHeightForAgeMessage()));
            resultCard.setzScoreCalculators(ZScoreCalculators.HEIGHT_FOR_AGE);
            cardArrayAdapter.add(resultCard);
            heightText.setText(String.valueOf(patient.getHeight()) + "cms");
            heightText.setTextColor(Color.BLACK);

        }

        if(result.getzScoreWeightForHeightMessage() != null){
            ResultCard resultCard = new ResultCard();
            resultCard.setHeader("Weight For Height");
            resultCard.setzScoreResult(setMessage(result.getzScoreWeightForHeightMessage(), result.getHealthyWeightForHeightMessage()));
            resultCard.setzScoreCalculators(ZScoreCalculators.WEIGHT_FOR_HEIGHT);
            cardArrayAdapter.add(resultCard);
        }

        if(result.getzScoreHeadCircumferenceForAgeMessage() != null){
            ResultCard resultCard = new ResultCard();
            resultCard.setHeader("HeadCircumference For Age");
            resultCard.setzScoreResult(result.getzScoreHeadCircumferenceForAgeMessage());
            resultCard.setzScoreCalculators(ZScoreCalculators.HEAD_CIRCUMFERENCE_FOR_AGE);
            cardArrayAdapter.add(resultCard);
            headCircumferenceText.setText(String.valueOf(patient.getHeadCircumference()) + "cms");
            headCircumferenceText.setTextColor(Color.BLACK);

        }




        listView.setAdapter(cardArrayAdapter);

        ageText.setText(getAge(patient));





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
