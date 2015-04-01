package com.example.WhoZScore.data.dao;

import android.content.Context;
import android.database.Cursor;
import com.example.WhoZScore.data.MySqliteHelper;
import com.example.WhoZScore.data.entities.HeightForAge;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 3/26/15
 * Time: 6:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class HeightForAgeDataSource {


    private MySqliteHelper dbHelper;

    public static final String BOYS_LENGTH_FOR_AGE = "BoysHeightForAge";
    public static final String GIRLS_LENGTH_FOR_AGE = "GirlsHeightForAge";

    private final int THREE_SCORE_COLUMN_INDEX = 0;
    private final int TWO_SCORE_COLUMN_INDEX = 1;
    private final int ONE_SCORE_COLUMN_INDEX = 2;
    private final int ZERO_SCORE_COLUMN_INDEX = 3;
    private final int MINUS_ONE_SCORE_COLUMN_INDEX = 4;
    private final int MINUS_TWO_SCORE_COLUMN_INDEX = 5;
    private final int MINUS_THREE_SCORE_COLUMN_INDEX = 6;

    private final String COLUMN_WEEKS = "weeks";
    private final String COLUMN_MONTHS = "months";
    private final String COLUMN_YEARS = "years";
    private final String COLUMN_THREE_SCORE = "threeScore";
    private final String COLUMN_TWO_SCORE = "twoScore";
    private final String COLUMN_ONE_SCORE = "oneScore";
    private final String COLUMN_ZERO_SCORE = "zeroScore";
    private final String COLUMN_MINUS_ONE_SCORE = "minusOneScore";
    private final String COLUMN_MINUS_TWO_SCORE = "minusTwoScore";
    private final String COLUMN_MINUS_THREE_SCORE = "minusThreeScore";


    private String[] scoreColumns = { COLUMN_THREE_SCORE,COLUMN_TWO_SCORE,COLUMN_ONE_SCORE,COLUMN_ZERO_SCORE,COLUMN_MINUS_ONE_SCORE,COLUMN_MINUS_TWO_SCORE,COLUMN_MINUS_THREE_SCORE };

    public HeightForAgeDataSource(Context context) {

        try {
            dbHelper = new MySqliteHelper(context);
            dbHelper.createDataBase();
            dbHelper.openDataBase();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }


    public HeightForAge getScoreForBoys(int weeks, int months, int years) {
        HeightForAge heightForAge = null;
        String whereClaue = COLUMN_WEEKS + "=?" + " and " + COLUMN_MONTHS + "=?" + " and " + COLUMN_YEARS + "=?" ;
        String[] whereParameters = new String[]{String.valueOf(weeks),String.valueOf(months),String.valueOf(years)};


        Cursor cursor = dbHelper.myDataBase.query(BOYS_LENGTH_FOR_AGE,
                scoreColumns, whereClaue, whereParameters, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            heightForAge = cursorToHeightForAge(cursor);
            cursor.moveToNext();

        }


        // make sure to close the cursor
        cursor.close();
        return heightForAge;
    }

    public HeightForAge getScoreForGirls(int weeks, int months, int years) {
        HeightForAge heightForAge = null;
        String whereClaue = COLUMN_WEEKS + "=?" + " and " + COLUMN_MONTHS + "=?" + " and " + COLUMN_YEARS + "=?" ;
        String[] whereParameters = new String[]{String.valueOf(weeks),String.valueOf(months),String.valueOf(years)};


        Cursor cursor = dbHelper.myDataBase.query(GIRLS_LENGTH_FOR_AGE,
                scoreColumns, whereClaue, whereParameters, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            heightForAge = cursorToHeightForAge(cursor);
            cursor.moveToNext();

        }


        // make sure to close the cursor
        cursor.close();
        return heightForAge;
    }

    private HeightForAge cursorToHeightForAge(Cursor cursor) {
        HeightForAge heightForAge = new HeightForAge();
        heightForAge.setThreeScore(cursor.getDouble(THREE_SCORE_COLUMN_INDEX));
        heightForAge.setTwoScore(cursor.getDouble(TWO_SCORE_COLUMN_INDEX));
        heightForAge.setOneScore(cursor.getDouble(ONE_SCORE_COLUMN_INDEX));
        heightForAge.setZeroScore(cursor.getDouble(ZERO_SCORE_COLUMN_INDEX));
        heightForAge.setMinusOneScore(cursor.getDouble(MINUS_ONE_SCORE_COLUMN_INDEX));
        heightForAge.setMinusTwoScore(cursor.getDouble(MINUS_TWO_SCORE_COLUMN_INDEX));
        heightForAge.setMinusThreeScore(cursor.getDouble(MINUS_THREE_SCORE_COLUMN_INDEX));
        return heightForAge;
    }
}
