package com.lks.whozscore.views;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lks.whozscore.R;
import com.lks.whozscore.WhoZScoreActivity;
import com.lks.whozscore.data.entities.AbstractZScoreEntity;
import com.lks.whozscore.data.entities.HeadCircumferenceForAge;
import com.lks.whozscore.data.entities.HeightForAge;
import com.lks.whozscore.data.entities.WeightForAge;
import com.lks.whozscore.data.entities.WeightForHeight;
import com.lks.whozscore.enums.ZScoreCalculators;
import com.lks.whozscore.enums.ZScoreGraphTypes;
import com.lks.whozscore.model.Patient;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 8/18/14
 * Time: 3:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class IndividualInfoView extends Fragment {

    Patient patient;
    ZScoreCalculators zScoreCalculators;
    TextView individualHeaderText;
    TextView ageText;
    LinearLayout headerLinearLayout;
    AbstractZScoreEntity zScoreEntity;
    String unit = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.individual_info, container, false);
        patient = ((WhoZScoreActivity) getActivity()).getPatient();
        ageText = (TextView) view.findViewById(R.id.individualAgeResultTextId);
        individualHeaderText = (TextView) view.findViewById(R.id.individualResultCardHeader);
        ageText.setText(getAge(patient));
        individualHeaderText.setText(zScoreCalculators.getTypes());

        headerLinearLayout = (LinearLayout) view.findViewById(R.id.individualHeaderLayout);

        getZScoreEntity();

        populateTable(view);

        // Inflate the layout for this fragment
        return view;

    }

    private void populateTable(View view) {
        setIndividualCellText((TextView) view.findViewById(R.id.individualMinus3ScoreText), zScoreEntity.getMinusThreeScore());
        setIndividualCellText((TextView) view.findViewById(R.id.individualMinus2ScoreText), zScoreEntity.getMinusTwoScore());
        setIndividualCellText((TextView) view.findViewById(R.id.individualMinus1ScoreText), zScoreEntity.getMinusOneScore());
        setIndividualCellText((TextView) view.findViewById(R.id.individual0ScoreText), zScoreEntity.getZeroScore());
        setIndividualCellText((TextView) view.findViewById(R.id.individual1ScoreText), zScoreEntity.getOneScore());
        setIndividualCellText((TextView) view.findViewById(R.id.individual2ScoreText), zScoreEntity.getTwoScore());
        setIndividualCellText((TextView) view.findViewById(R.id.individual3ScoreText), zScoreEntity.getThreeScore());

    }

    private void setIndividualCellText(TextView textView, double score) {
        if(score == 0.0) {
            textView.setText("NA\n");
        } else {
            textView.setText(Double.toString(score) + "\n" + unit);
        }

    }


    private String getAge(Patient patient){
        String age = patient.getDisplayAgeInYears() + " years, "+patient.getDisplayAgeInMonths()+" months and "+patient.getDisplayAgeInWeeks() +" weeks";
        return age;
    }

    private void createIndividualParameterHeaders(String parameterName, String parameterValue){
        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        TextView parameterNameTextView = new TextView(getActivity());
        parameterNameTextView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        parameterNameTextView.setText(parameterName);
        parameterNameTextView.setTextSize(14);
        parameterNameTextView.setTypeface(null, Typeface.BOLD);
        parameterNameTextView.setTextColor(Color.parseColor("#122e4d"));
        parameterNameTextView.setGravity(Gravity.LEFT);
        linearLayout.addView(parameterNameTextView);

        TextView parameterValueTextView = new TextView(getActivity());
        parameterValueTextView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        parameterValueTextView.setText(parameterValue + unit);
        linearLayout.addView(parameterValueTextView);

        headerLinearLayout.addView(linearLayout);

    }

    public void setZScoreCalculators(ZScoreCalculators zScoreCalculators) {
        this.zScoreCalculators = zScoreCalculators;
    }

    public void getZScoreEntity() {
        WhoZScoreActivity whoZScoreActivity = (WhoZScoreActivity) getActivity();
        if(zScoreCalculators.equals(ZScoreCalculators.WEIGHT_FOR_AGE)) {
            zScoreEntity = whoZScoreActivity.getWeightForAge();
            unit = "kg";
            createIndividualParameterHeaders("Weight: ", String.valueOf(patient.getWeight()));
        }
        else if(zScoreCalculators.equals(ZScoreCalculators.HEIGHT_FOR_AGE)) {
            zScoreEntity = whoZScoreActivity.getHeightForAge();
            unit = "cms";
            createIndividualParameterHeaders("Height: ", String.valueOf(patient.getHeight()));
        }
        else if(zScoreCalculators.equals(ZScoreCalculators.WEIGHT_FOR_HEIGHT)) {
            zScoreEntity = whoZScoreActivity.getWeightForHeight();
            unit = "kg";
            createIndividualParameterHeaders("Weight: ", String.valueOf(patient.getWeight()));
            createIndividualParameterHeaders("Height: ", String.valueOf(patient.getHeight()));
        }
        else if(zScoreCalculators.equals(ZScoreCalculators.HEAD_CIRCUMFERENCE_FOR_AGE)) {
            zScoreEntity = whoZScoreActivity.getHeadCircumferenceForAge();
            unit = "cms";
            createIndividualParameterHeaders("Head Circumference: ", String.valueOf(patient.getHeadCircumference()));
        }
    }

    @Override
    public String toString(){
        return "IndividualInfoView";
    }
}
