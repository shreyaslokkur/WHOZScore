package com.example.WhoZScore.data.dao;

import android.content.Context;
import android.database.Cursor;
import com.example.WhoZScore.data.MySqliteHelper;
import com.example.WhoZScore.data.entities.HeightForAge;
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
public class HeightForAgeDataSource {


    private MySqliteHelper dbHelper;

    public static final String BOYS_HEIGHT_FOR_AGE = "BoysHeightForAge";
    public static final String GIRLS_HEIGHT_FOR_AGE = "GirlsHeightForAge";

    private final int MINUS_THREE_SCORE_COLUMN_INDEX = 0;
    private final int MINUS_TWO_SCORE_COLUMN_INDEX = 1;
    private final int MINUS_ONE_SCORE_COLUMN_INDEX = 2;
    private final int ZERO_SCORE_COLUMN_INDEX = 3;
    private final int ONE_SCORE_COLUMN_INDEX = 4;
    private final int TWO_SCORE_COLUMN_INDEX = 5;
    private final int THREE_SCORE_COLUMN_INDEX = 6;

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


    private String[] scoreColumns = { COLUMN_MINUS_THREE_SCORE,COLUMN_MINUS_TWO_SCORE,COLUMN_MINUS_ONE_SCORE,COLUMN_ZERO_SCORE,COLUMN_ONE_SCORE,COLUMN_TWO_SCORE,COLUMN_THREE_SCORE };

    public HeightForAgeDataSource(Context context) {

        try {
            dbHelper = new MySqliteHelper(context);
            dbHelper.createDataBase();
            dbHelper.openDataBase();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }


    private HeightForAge getScoreForBoys(int weeks, int months, int years) {
        HeightForAge heightForAge = null;
        String whereClaue = COLUMN_WEEKS + "=?" + " and " + COLUMN_MONTHS + "=?" + " and " + COLUMN_YEARS + "=?" ;
        String[] whereParameters = new String[]{String.valueOf(weeks),String.valueOf(months),String.valueOf(years)};


        Cursor cursor = dbHelper.myDataBase.query(BOYS_HEIGHT_FOR_AGE,
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

    public HeightForAge getScore(int weeks, int months, int years, Sex sex){
        if(years >= 5){
            int minMonth=months;
            int maxMonth = months;
            int maxYear = years;
            HeightForAge scoreForMinMonth = null;
            HeightForAge scoreForMaxMonth = null;
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

            return averageHeightForAge(scoreForMinMonth, scoreForMaxMonth);
        }else {
            return getScoreForBoys(weeks,months,years);
        }

    }

    private HeightForAge averageHeightForAge(HeightForAge scoreForBoysForMinMonth, HeightForAge scoreForBoysForMaxMonth) {
        HeightForAge heightForAge = new HeightForAge();
        heightForAge.setMinusOneScore(scoreForBoysForMinMonth.getMinusOneScore());
        heightForAge.setMinusTwoScore(scoreForBoysForMinMonth.getMinusTwoScore());
        heightForAge.setMinusThreeScore(scoreForBoysForMinMonth.getMinusThreeScore());
        heightForAge.setOneScore(scoreForBoysForMaxMonth.getOneScore());
        heightForAge.setTwoScore(scoreForBoysForMaxMonth.getTwoScore());
        heightForAge.setThreeScore(scoreForBoysForMaxMonth.getThreeScore());
        double averageZeroScore = (scoreForBoysForMaxMonth.getZeroScore() + scoreForBoysForMinMonth.getZeroScore()) / 2;
        heightForAge.setZeroScore(averageZeroScore);
        return heightForAge;
    }

    private HeightForAge getScoreForGirls(int weeks, int months, int years) {
        HeightForAge heightForAge = null;
        String whereClaue = COLUMN_WEEKS + "=?" + " and " + COLUMN_MONTHS + "=?" + " and " + COLUMN_YEARS + "=?" ;
        String[] whereParameters = new String[]{String.valueOf(weeks),String.valueOf(months),String.valueOf(years)};


        Cursor cursor = dbHelper.myDataBase.query(GIRLS_HEIGHT_FOR_AGE,
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

    public List<HeightForAge> getScoreRange(int minWeeks, int maxWeeks, int minMonths, int maxMonths, int minYears, int maxYears, Sex sex){
        List<HeightForAge> heightForAgeList = new ArrayList<HeightForAge>();
        String whereClause = COLUMN_WEEKS + " BETWEEN " + "?" + "AND " + "?" + " AND " + COLUMN_MONTHS + " BETWEEN " + "?" + "AND " + "?" + " AND " + COLUMN_YEARS + " BETWEEN " + "?" + "AND " + "?" ;

        String[] whereParameters = new String[]{String.valueOf(minWeeks),String.valueOf(maxWeeks),String.valueOf(minMonths),String.valueOf(maxMonths),String.valueOf(minYears), String.valueOf(maxYears)};

        String tableName;
        if(Sex.MALE.equals(sex)){
            tableName = BOYS_HEIGHT_FOR_AGE;
        }else {
            tableName = GIRLS_HEIGHT_FOR_AGE;
        }

        Cursor cursor = dbHelper.myDataBase.query(tableName,
                scoreColumns, whereClause, whereParameters, null, null, null);

        cursor.moveToFirst();
        HeightForAge heightForAge = null;
        while (!cursor.isAfterLast()) {
            heightForAge = cursorToHeightForAge(cursor);
            heightForAgeList.add(heightForAge);
            cursor.moveToNext();

        }


        // make sure to close the cursor
        cursor.close();
        return heightForAgeList;

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
