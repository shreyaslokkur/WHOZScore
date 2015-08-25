package com.example.WhoZScore;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

/**
 * Created by lokkur on 7/29/2015.
 */
public class WhoZScoreApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Add your initialization code here
        Parse.initialize(this, "ov89x4kDpXMixnN5zHzjLl4QHwocKZHmqa3b0ISL", "lBUgwzHLd5hqInqYdpE8EALZeqNCKsy0GNtNG1wc");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }
}
