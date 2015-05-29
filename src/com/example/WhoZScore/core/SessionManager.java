package com.example.WhoZScore.core;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.example.WhoZScore.views.LoginView;
import com.example.WhoZScore.views.ValidateView;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 5/29/15
 * Time: 6:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class SessionManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "WHOZScorePref";
    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String IS_VALIDATED = "IsValidated";
    public static final String KEY_USERNAME = "email";
    public static final String KEY_NAME = "name";

    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String name, String email){
        editor.putBoolean(IS_LOGIN, true);
        editor.putBoolean(IS_VALIDATED, true);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_USERNAME, email);
        editor.commit();
    }

    public void checkLogin(){
        if(!this.isLoggedIn() && !this.isValidated()){
            Intent i = new Intent(_context, LoginView.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }else if(this.isLoggedIn() && !this.isValidated()){
            Intent i = new Intent(_context, ValidateView.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }


    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_USERNAME, pref.getString(KEY_USERNAME, null));
        return user;
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, LoginView.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }


    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    public boolean isValidated(){
        return pref.getBoolean(IS_VALIDATED, false);
    }

    public void registerNewUser(String name, String email){
        editor.putBoolean(IS_LOGIN, false);
        editor.putBoolean(IS_VALIDATED, false);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_USERNAME, email);
        editor.commit();

    }


}