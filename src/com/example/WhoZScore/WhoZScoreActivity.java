package com.example.WhoZScore;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.WhoZScore.core.PatientInterface;
import com.example.WhoZScore.core.calculator.*;
import com.example.WhoZScore.core.checker.HealthCheckerDelegator;
import com.example.WhoZScore.data.entities.HeadCircumferenceForAge;
import com.example.WhoZScore.data.entities.HeightForAge;
import com.example.WhoZScore.data.entities.WeightForAge;
import com.example.WhoZScore.data.entities.WeightForHeight;
import com.example.WhoZScore.enums.Age;
import com.example.WhoZScore.enums.Sex;
import com.example.WhoZScore.exceptions.WHOZScoreException;
import com.example.WhoZScore.model.Patient;
import com.example.WhoZScore.model.Result;
import com.example.WhoZScore.views.FragmentChangeListener;
import com.example.WhoZScore.views.HomeView;
import com.example.WhoZScore.views.LoginView;
import com.parse.LogOutCallback;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseUser;

public class WhoZScoreActivity extends Activity implements FragmentChangeListener, PatientInterface {

    private Patient patient = null;
    private ICalculator calculator;
    private HealthCheckerDelegator healthChecker = new HealthCheckerDelegator();
    private Result result = null;
    Menu menu;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        // Determine whether the current user is an anonymous user
        if (ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {
            LoginView loginView = new LoginView();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.view, loginView);
            transaction.commit();

        }
        else {
            // If current user is NOT anonymous user
            // Get current user data from Parse.com
            ParseUser currentUser = ParseUser.getCurrentUser();
            if (currentUser != null) {

                patient = new Patient();
                HomeView newFragment = new HomeView();

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.add(R.id.view, newFragment);
                transaction.commit();
            } else {
                // Send user to LoginSignupActivity.class
                LoginView loginView = new LoginView();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.add(R.id.view, loginView);
                transaction.commit();

            }
        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, this.menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.logout:
                logout();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showOverflowMenu(boolean showMenu){
        if(this.menu == null)
            return;
        menu.setGroupVisible(R.id.main_menu_group, showMenu);
    }

    private void logout() {
        ParseUser.logOutInBackground(new LogOutCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {

                    replaceFragment(new LoginView());
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Encountered an error during logout", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }

    public void setPatientGender(Sex sex){
        patient.setSex(sex);
    }

    public void setPatientAgeInYears(int years){
        patient.setAgeInYears(years);
        patient.setDisplayAgeInYears(years);
    }

    public void setPatientAgeInMonths(int months){
        patient.setAgeInMonths(months);
        patient.setDisplayAgeInMonths(months);
    }

    public void setPatientAgeInWeeks(int weeks){
        patient.setAgeInWeeks(weeks);
        patient.setDisplayAgeInWeeks(weeks);
    }

    public void setPatientWeight(double weight){
        patient.setWeight(weight);
    }

    public void setPatientHeight(double height){
        patient.setHeight(height);
    }

    public void setPatientHeadCircumference(double headCircumference){
        patient.setHeadCircumference(headCircumference);
    }

    public void onFormSubmit(){
        WeightForAge weightForAge = null;
        HeightForAge heightForAge = null;
        WeightForHeight weightForHeight = null;
        HeadCircumferenceForAge headCircumferenceForAge = null;

        try{

            restructureAge(patient.getAgeInWeeks(), patient.getAgeInMonths(), patient.getAgeInYears());

            if(patient.getWeight() > 0.0){
                calculator = new WeightForAgeCalculator();
                weightForAge = (WeightForAge) calculator.calculateZScore(patient, this);
            }
            if(patient.getHeight() > 0.0){
                calculator = new HeightForAgeCalculator();
                heightForAge = (HeightForAge) calculator.calculateZScore(patient, this);
            }
            if(patient.getWeight() > 0.0 && patient.getHeight() > 0.0){
                calculator = new WeightForHeightCalculator();
                weightForHeight = (WeightForHeight) calculator.calculateZScore(patient, this);
            }
            if(patient.getHeadCircumference() > 0.0){
                calculator = new HeadCircumferenceForAgeCalculator();
                headCircumferenceForAge = (HeadCircumferenceForAge) calculator.calculateZScore(patient,this);
            }

            result = healthChecker.getHealthResult(patient, weightForAge, heightForAge, weightForHeight, headCircumferenceForAge, this);

        }catch (WHOZScoreException e){
            //TODO : Show the exception in a message box
        }



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
        Spinner monthsSpinner = (Spinner) findViewById(R.id.monthsId);
        Spinner yearsSpinner = (Spinner) findViewById(R.id.yearsId);
        Spinner weeksSpinner = (Spinner) findViewById(R.id.weeksId);
        switch (age){
            case WEEKS: weeksSpinner.setEnabled(enable);
                        if(!enable) {
                            patient.setAgeInWeeks(0);
                            weeksSpinner.setSelection(0);
                        }
                        break;
            case MONTHS: monthsSpinner.setEnabled(enable);
                         if(!enable){
                             patient.setAgeInMonths(0);
                             monthsSpinner.setSelection(0);
                         }
                         break;
            case YEARS: yearsSpinner.setEnabled(enable);
                        if(!enable){
                            patient.setAgeInYears(0);
                            yearsSpinner.setSelection(0);
                        }
                        break;
        }
    }

    public void restructureAge(int weeks, int months, int years){

        if(years == 0 && weeks == 0 && months <=3){

            patient.setAgeInMonths(0);
            if(months == 1) patient.setAgeInWeeks(4);
            else if(months == 2) patient.setAgeInWeeks(8);
            else if(months == 3) patient.setAgeInWeeks(12);
        }


    }

}
