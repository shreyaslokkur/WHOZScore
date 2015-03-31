package com.example.WhoZScore.data.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.WhoZScore.data.MySqliteHelper;
import com.example.WhoZScore.data.entities.WeightForAge;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 3/26/15
 * Time: 6:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class LengthForAgeDataSource {


    private MySqliteHelper dbHelper;

    public static final String BOYS_LENGTH_FOR_AGE = "BoysLengthForAge";
    public static final String GIRLS_LENGTH_FOR_AGE = "GirlsLengthForAge";

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

    public LengthForAgeDataSource(Context context) {

        try {
            dbHelper = new MySqliteHelper(context);
            dbHelper.createDataBase();
            dbHelper.openDataBase();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }


    public WeightForAge getScoreForBoys(int weeks, int months, int years) {
        WeightForAge weightForAge = null;
        String whereClaue = COLUMN_WEEKS + "=?" + " and " + COLUMN_MONTHS + "=?" + " and " + COLUMN_YEARS + "=?" ;
        String[] whereParameters = new String[]{String.valueOf(weeks),String.valueOf(months),String.valueOf(years)};


        Cursor cursor = dbHelper.myDataBase.query(BOYS_LENGTH_FOR_AGE,
                scoreColumns, whereClaue, whereParameters, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            weightForAge = cursorToWeightForAge(cursor);
            cursor.moveToNext();

        }


        // make sure to close the cursor
        cursor.close();
        return weightForAge;
    }

    public WeightForAge getScoreForGirls(int weeks, int months, int years) {
        WeightForAge weightForAge = null;
        String whereClaue = COLUMN_WEEKS + "=?" + " and " + COLUMN_MONTHS + "=?" + " and " + COLUMN_YEARS + "=?" ;
        String[] whereParameters = new String[]{String.valueOf(weeks),String.valueOf(months),String.valueOf(years)};


        Cursor cursor = dbHelper.myDataBase.query(GIRLS_LENGTH_FOR_AGE,
                scoreColumns, whereClaue, whereParameters, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            weightForAge = cursorToWeightForAge(cursor);
            cursor.moveToNext();

        }


        // make sure to close the cursor
        cursor.close();
        return weightForAge;
    }

    private WeightForAge cursorToWeightForAge(Cursor cursor) {
        WeightForAge weightForAge = new WeightForAge();
        weightForAge.setThreeScore(cursor.getDouble(THREE_SCORE_COLUMN_INDEX));
        weightForAge.setTwoScore(cursor.getDouble(TWO_SCORE_COLUMN_INDEX));
        weightForAge.setOneScore(cursor.getDouble(ONE_SCORE_COLUMN_INDEX));
        weightForAge.setZeroScore(cursor.getDouble(ZERO_SCORE_COLUMN_INDEX));
        weightForAge.setMinusOneScore(cursor.getDouble(MINUS_ONE_SCORE_COLUMN_INDEX));
        weightForAge.setMinusTwoScore(cursor.getDouble(MINUS_TWO_SCORE_COLUMN_INDEX));
        weightForAge.setMinusThreeScore(cursor.getDouble(MINUS_THREE_SCORE_COLUMN_INDEX));
        return weightForAge;
    }
}
