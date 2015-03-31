package com.example.WhoZScore;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import com.example.WhoZScore.core.Calculator;
import com.example.WhoZScore.core.HealthChecker;
import com.example.WhoZScore.data.dao.WeightForAgeDataSource;
import com.example.WhoZScore.data.entities.WeightForAge;
import com.example.WhoZScore.enums.Age;
import com.example.WhoZScore.enums.Sex;
import com.example.WhoZScore.model.Patient;
import com.example.WhoZScore.model.Result;
import com.example.WhoZScore.views.FragmentChangeListener;
import com.example.WhoZScore.views.HomeView;

public class WhoZScore extends Activity implements FragmentChangeListener {

    private Patient patient = null;
    private Calculator calculator = new Calculator();
    private HealthChecker healthChecker = new HealthChecker();
    private Result result = null;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        patient = new Patient();
        HomeView newFragment = new HomeView();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.view, newFragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    public void setPatientGender(Sex sex){
        patient.setSex(sex);
    }

    public void setPatientAgeInYears(int years){
        patient.setAgeInYears(years);
    }

    public void setPatientAgeInMonths(int months){
        patient.setAgeInMonths(months);
    }

    public void setPatientAgeInWeeks(int weeks){
        patient.setAgeInWeeks(weeks);
    }

    public void setPatientWeight(double weight){
        patient.setWeight(weight);
    }

    public void onFormSubmit(){
        WeightForAge weightForAge = calculator.calculateWeightForAgeZScore(patient, this);
        result = healthChecker.checkIfHealthy(patient, weightForAge);

    }

    public Result getResult(){
        return result;
    }

    public Patient getPatient() {
        return patient;
    }

    @Override
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.view, fragment, fragment.toString());
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.commit();
    }

    public void toggleSpinners(Age age, boolean enable){
        View monthsSpinner = findViewById(R.id.monthsId);
        View yearsSpinner = findViewById(R.id.yearsId);
        View weeksSpinner = findViewById(R.id.weeksId);
        switch (age){
            case WEEKS: weeksSpinner.setEnabled(enable);
                        if(!enable) patient.setAgeInWeeks(0);
                        break;
            case MONTHS: monthsSpinner.setEnabled(enable);
                         if(!enable) patient.setAgeInMonths(0);
                         break;
            case YEARS: yearsSpinner.setEnabled(enable);
                        if(!enable) patient.setAgeInYears(0);
                        break;
        }
    }

}
