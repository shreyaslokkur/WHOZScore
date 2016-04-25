package com.lks.whozscore.views;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.lks.whozscore.R;
import com.lks.whozscore.WhoZScoreActivity;
import com.lks.whozscore.model.Patient;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 8/18/14
 * Time: 3:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class RegisterView extends Fragment {

    // Declare Variables
    Button signup;
    String usernametxt;
    String passwordtxt;
    String confirmPasswordtxt;
    EditText password;
    EditText username;
    EditText confirmPassword;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.register_view, container, false);
        final Fragment homeView = new HomeView();

        username = (EditText) view.findViewById(R.id.usernameRegisterId);
        password = (EditText) view.findViewById(R.id.passwordRegisterId);
        confirmPassword = (EditText) view.findViewById(R.id.confirmPasswordRegisterId);
        ((WhoZScoreActivity)getActivity()).showOverflowMenu(false);


        signup = (Button) view.findViewById(R.id.registerButton);


        // Sign up Button Click Listener
        signup.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // Retrieve the text entered from the EditText
                usernametxt = username.getText().toString();
                passwordtxt = password.getText().toString();
                confirmPasswordtxt = confirmPassword.getText().toString();
                if(emailAddressIsValid(username, usernametxt) && passwordIsValid(password,passwordtxt) && confirmPasswordIsValid(confirmPassword, confirmPasswordtxt, passwordtxt)){
                    // Save new user data into Parse.com Data Storage
                    ParseUser user = new ParseUser();
                    user.setUsername(usernametxt);
                    user.setPassword(passwordtxt);
                    user.setEmail(usernametxt);
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                ((WhoZScoreActivity)getActivity()).setPatient(new Patient());
                                ((WhoZScoreActivity)getActivity()).replaceFragment(homeView);
                            } else {
                                Toast.makeText(getActivity().getApplicationContext(),
                                        "Sign up Error", Toast.LENGTH_LONG)
                                        .show();
                            }

                        }


                    });

                }




            }
        });



        // Inflate the layout for this fragment
        return view;

    }

    private boolean confirmPasswordIsValid(EditText confirmPassword, String confirmPasswordtxt, String passwordtxt) {
        if(confirmPasswordtxt.equals("")){
            confirmPassword.setError("Please reenter the password");
            return false;
        }
        if(!isPasswordMatch(passwordtxt, confirmPasswordtxt)){
            confirmPassword.setError("The password does not match");
            return false;
        }
        return true;
    }

    private boolean passwordIsValid(EditText password, String passwordtxt) {
        if(passwordtxt.equals("")){
            password.setError("Please provide a password");
            return false;
        }
        return true;
    }

    private boolean emailAddressIsValid(EditText username, String usernametxt) {
        if(usernametxt.equals("")){
            username.setError("Please enter an email address");
            return false;
        }

        if(!isValidEmail(usernametxt)){
            username.setError("Please enter a valid email address");
            return false;
        }
        return true;
    }

    private boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    private boolean isPasswordMatch(String password, String confirmPassword){
        if(password == null || confirmPassword == null){
            return false;
        }else if(!password.equals(confirmPassword)){
            return false;
        }
        return true;
    }



    @Override
    public String toString(){
        return "LoginView";
    }
}
