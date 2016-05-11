package com.lks.whozscore.views;

import android.app.Fragment;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.lks.whozscore.R;
import com.lks.whozscore.WhoZScoreActivity;
import com.parse.*;


/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 8/18/14
 * Time: 3:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginView extends Fragment {

    // Declare Variables
    Button loginbutton;
    TextView signup;
    TextView forgotPassword;
    String usernametxt;
    String passwordtxt;
    EditText password;
    EditText username;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.login_view, container, false);
        final Fragment homeView = new HomeView();
        final Fragment registerView = new RegisterView();
        ((WhoZScoreActivity)getActivity()).showOverflowMenu(false);

        username = (EditText) view.findViewById(R.id.username);
        password = (EditText) view.findViewById(R.id.password);
        forgotPassword = (TextView) view.findViewById(R.id.forgotPassword);

        loginbutton = (Button) view.findViewById(R.id.loginButton);
        signup = (TextView) view.findViewById(R.id.signUpTextId);

        // Login Button Click Listener
        loginbutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // Retrieve the text entered from the EditText
                usernametxt = username.getText().toString();
                passwordtxt = password.getText().toString();

                // Send data to Parse.com for verification
                ParseUser.logInInBackground(usernametxt, passwordtxt,
                        new LogInCallback() {
                            public void done(ParseUser user, ParseException e) {
                                if (user != null) {
                                    // If user exist and authenticated, send user to HomeView
                                    ((WhoZScoreActivity)getActivity()).replaceFragment(homeView);

                                } else {
                                    Toast.makeText(
                                            getActivity().getApplicationContext(),
                                            "Invalid Username or Password",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
        // Sign up Button Click Listener
        signup.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                ((WhoZScoreActivity)getActivity()).replaceFragment(registerView);


            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                //check if username is valid email
                if(isValidEmail(username.getText())){
                    ParseUser.requestPasswordResetInBackground(username.getText().toString(), new RequestPasswordResetCallback() {
                        public void done(ParseException e) {
                            if (e == null) {
                                Toast.makeText(
                                        getActivity().getApplicationContext(),
                                        "A password reset mail was sent to the email address: "+ username.getText(),
                                        Toast.LENGTH_LONG).show();
                            } else if(e.getCode() == 205) {
                                Toast.makeText(
                                        getActivity().getApplicationContext(),
                                        "Unable to send password reset email. No user with the email id: "+ username.getText()+" is found.",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(
                                        getActivity().getApplicationContext(),
                                        "Unable to send password reset email. Please try again later.",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }else{
                    Toast.makeText(
                            getActivity().getApplicationContext(),
                            "Enter a valid email in the username field",
                            Toast.LENGTH_LONG).show();

                }



            }
        });



        // Inflate the layout for this fragment
        return view;

    }

    private boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }



    @Override
    public String toString(){
        return "LoginView";
    }
}
