package com.example.WhoZScore.data.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.WhoZScore.data.MySqliteHelper;
import com.example.WhoZScore.data.entities.WeightForAge;
import com.example.WhoZScore.enums.Sex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 3/26/15
 * Time: 6:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class WeightForAgeDataSource {

    private SQLiteDatabase database;
    private MySqliteHelper dbHelper;

    public static final String BOYS_WEIGHT_FOR_AGE = "BoysWeightForAge";
    public static final String GIRLS_WEIGHT_FOR_AGE = "GirlsWeightForAge";

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

    public WeightForAgeDataSource(Context context) {

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


        Cursor cursor = dbHelper.myDataBase.query(BOYS_WEIGHT_FOR_AGE,
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

    public WeightForAge getScore(int weeks, int months, int years, Sex sex){
        if(years >= 5){
            int minMonth=months;
            int maxMonth = months;
            int maxYear = years;
            WeightForAge scoreForMinMonth = null;
            WeightForAge scoreForMaxMonth = null;
            if(months > 0 && months < 3 ){
                minMonth = 0;
                maxMonth = 3;
            }else if(months > 3 && months <6){
                minMonth = 3;
                maxMonth = 6;
            }else if(months > 6 && months <9){
                minMonth = 6;
                maxMonth = 9;
            }else if(months > 9) {
                minMonth = 9;
                maxMonth = 0;
                maxYear = maxYear + 1;
            }
            if(Sex.FEMALE.equals(sex)){
                scoreForMinMonth = getScoreForGirls(weeks, minMonth, years);
                scoreForMaxMonth = getScoreForGirls(weeks, maxMonth, maxYear);

            }else {
                scoreForMinMonth = getScoreForBoys(weeks, minMonth, years);
                scoreForMaxMonth = getScoreForBoys(weeks, maxMonth, maxYear);
            }

            return averageWeightForAge(scoreForMinMonth, scoreForMaxMonth);
        }else {
            if(Sex.FEMALE.equals(sex)){
                return getScoreForGirls(weeks,months,years);
            }else {
                return getScoreForBoys(weeks,months,years);
            }

        }

    }

    private WeightForAge averageWeightForAge(WeightForAge scoreForBoysForMinMonth, WeightForAge scoreForBoysForMaxMonth) {
        WeightForAge weightForAge = new WeightForAge();
        weightForAge.setMinusOneScore(scoreForBoysForMinMonth.getMinusOneScore());
        weightForAge.setMinusTwoScore(scoreForBoysForMinMonth.getMinusTwoScore());
        weightForAge.setMinusThreeScore(scoreForBoysForMinMonth.getMinusThreeScore());
        weightForAge.setOneScore(scoreForBoysForMaxMonth.getOneScore());
        weightForAge.setTwoScore(scoreForBoysForMaxMonth.getTwoScore());
        weightForAge.setThreeScore(scoreForBoysForMaxMonth.getThreeScore());
        double averageZeroScore = (scoreForBoysForMaxMonth.getZeroScore() + scoreForBoysForMinMonth.getZeroScore()) / 2;
        weightForAge.setZeroScore(averageZeroScore);
        return weightForAge;
    }


    public WeightForAge getScoreForGirls(int weeks, int months, int years) {
        WeightForAge weightForAge = null;
        String whereClaue = COLUMN_WEEKS + "=?" + " and " + COLUMN_MONTHS + "=?" + " and " + COLUMN_YEARS + "=?" ;
        String[] whereParameters = new String[]{String.valueOf(weeks),String.valueOf(months),String.valueOf(years)};


        Cursor cursor = dbHelper.myDataBase.query(GIRLS_WEIGHT_FOR_AGE,
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

    public List<WeightForAge> getScoreRange(int minWeeks, int maxWeeks, int minMonths, int maxMonths, int minYears, int maxYears, Sex sex){
        List<WeightForAge> weightForAgeList = new ArrayList<WeightForAge>();
        String whereClause = COLUMN_WEEKS + " BETWEEN " + "?" + "AND " + "?" + " AND " + COLUMN_MONTHS + " BETWEEN " + "?" + "AND " + "?" + " AND " + COLUMN_YEARS + " BETWEEN " + "?" + "AND " + "?" ;

        String[] whereParameters = new String[]{String.valueOf(minWeeks),String.valueOf(maxWeeks),String.valueOf(minMonths),String.valueOf(maxMonths),String.valueOf(minYears), String.valueOf(maxYears)};

        String tableName;
        if(Sex.MALE.equals(sex)){
            tableName = BOYS_WEIGHT_FOR_AGE;
        }else {
            tableName = GIRLS_WEIGHT_FOR_AGE;
        }

        Cursor cursor = dbHelper.myDataBase.query(tableName,
                scoreColumns, whereClause, whereParameters, null, null, null);

        cursor.moveToFirst();
        WeightForAge weightForAge = null;
        while (!cursor.isAfterLast()) {
            weightForAge = cursorToWeightForAge(cursor);
            weightForAgeList.add(weightForAge);
            cursor.moveToNext();

        }


        // make sure to close the cursor
        cursor.close();
        return weightForAgeList;

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
