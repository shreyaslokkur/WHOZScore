package com.example.WhoZScore.data.dao;

import android.content.Context;
import android.database.Cursor;
import com.example.WhoZScore.data.MySqliteHelper;
import com.example.WhoZScore.data.entities.WeightForHeight;
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
public class WeightForHeightDataSource {


    private MySqliteHelper dbHelper;

    public static final String BOYS_WEIGHT_FOR_HEIGHT = "BoysWeightForHeight";
    public static final String GIRLS_WEIGHT_FOR_HEIGHT = "GirlsWeightForHeight";

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

    public WeightForHeightDataSource(Context context) {

        try {
            dbHelper = new MySqliteHelper(context);
            dbHelper.createDataBase();
            dbHelper.openDataBase();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }


    private WeightForHeight getScoreForBoys(int weeks, int months, int years) {
        WeightForHeight weightForHeight = null;
        String whereClaue = COLUMN_WEEKS + "=?" + " and " + COLUMN_MONTHS + "=?" + " and " + COLUMN_YEARS + "=?" ;
        String[] whereParameters = new String[]{String.valueOf(weeks),String.valueOf(months),String.valueOf(years)};


        Cursor cursor = dbHelper.myDataBase.query(BOYS_WEIGHT_FOR_HEIGHT,
                scoreColumns, whereClaue, whereParameters, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            weightForHeight = cursorToWeightForHeight(cursor);
            cursor.moveToNext();

        }


        // make sure to close the cursor
        cursor.close();
        return weightForHeight;
    }

    public WeightForHeight getScore(int weeks, int months, int years, Sex sex){
        if(years >= 5){
            int minMonth=months;
            int maxMonth = months;
            int maxYear = years;
            WeightForHeight scoreForMinMonth = null;
            WeightForHeight scoreForMaxMonth = null;
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

            return averageWeightForHeight(scoreForMinMonth, scoreForMaxMonth);
        }else {
            if(Sex.FEMALE.equals(sex)){
                return getScoreForGirls(weeks,months,years);
            }else {
                return getScoreForBoys(weeks,months,years);
            }

        }

    }

    private WeightForHeight averageWeightForHeight(WeightForHeight scoreForBoysForMinMonth, WeightForHeight scoreForBoysForMaxMonth) {
        WeightForHeight weightForHeight = new WeightForHeight();
        weightForHeight.setMinusOneScore(scoreForBoysForMinMonth.getMinusOneScore());
        weightForHeight.setMinusTwoScore(scoreForBoysForMinMonth.getMinusTwoScore());
        weightForHeight.setMinusThreeScore(scoreForBoysForMinMonth.getMinusThreeScore());
        weightForHeight.setOneScore(scoreForBoysForMaxMonth.getOneScore());
        weightForHeight.setTwoScore(scoreForBoysForMaxMonth.getTwoScore());
        weightForHeight.setThreeScore(scoreForBoysForMaxMonth.getThreeScore());
        double averageZeroScore = (scoreForBoysForMaxMonth.getZeroScore() + scoreForBoysForMinMonth.getZeroScore()) / 2;
        weightForHeight.setZeroScore(averageZeroScore);
        return weightForHeight;
    }

    private WeightForHeight getScoreForGirls(int weeks, int months, int years) {
        WeightForHeight weightForHeight = null;
        String whereClaue = COLUMN_WEEKS + "=?" + " and " + COLUMN_MONTHS + "=?" + " and " + COLUMN_YEARS + "=?" ;
        String[] whereParameters = new String[]{String.valueOf(weeks),String.valueOf(months),String.valueOf(years)};


        Cursor cursor = dbHelper.myDataBase.query(GIRLS_WEIGHT_FOR_HEIGHT,
                scoreColumns, whereClaue, whereParameters, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            weightForHeight = cursorToWeightForHeight(cursor);
            cursor.moveToNext();

        }


        // make sure to close the cursor
        cursor.close();
        return weightForHeight;
    }

    public List<WeightForHeight> getScoreRange(int minWeeks, int maxWeeks, int minMonths, int maxMonths, int minYears, int maxYears, Sex sex){
        List<WeightForHeight> weightForHeightList = new ArrayList<WeightForHeight>();
        String whereClause = COLUMN_WEEKS + " BETWEEN " + "?" + "AND " + "?" + " AND " + COLUMN_MONTHS + " BETWEEN " + "?" + "AND " + "?" + " AND " + COLUMN_YEARS + " BETWEEN " + "?" + "AND " + "?" ;

        String[] whereParameters = new String[]{String.valueOf(minWeeks),String.valueOf(maxWeeks),String.valueOf(minMonths),String.valueOf(maxMonths),String.valueOf(minYears), String.valueOf(maxYears)};

        String tableName;
        if(Sex.MALE.equals(sex)){
            tableName = BOYS_WEIGHT_FOR_HEIGHT;
        }else {
            tableName = GIRLS_WEIGHT_FOR_HEIGHT;
        }

        Cursor cursor = dbHelper.myDataBase.query(tableName,
                scoreColumns, whereClause, whereParameters, null, null, null);

        cursor.moveToFirst();
        WeightForHeight weightForHeight = null;
        while (!cursor.isAfterLast()) {
            weightForHeight = cursorToWeightForHeight(cursor);
            weightForHeightList.add(weightForHeight);
            cursor.moveToNext();

        }


        // make sure to close the cursor
        cursor.close();
        return weightForHeightList;

    }

    private WeightForHeight cursorToWeightForHeight(Cursor cursor) {
        WeightForHeight weightForHeight = new WeightForHeight();
        weightForHeight.setThreeScore(cursor.getDouble(THREE_SCORE_COLUMN_INDEX));
        weightForHeight.setTwoScore(cursor.getDouble(TWO_SCORE_COLUMN_INDEX));
        weightForHeight.setOneScore(cursor.getDouble(ONE_SCORE_COLUMN_INDEX));
        weightForHeight.setZeroScore(cursor.getDouble(ZERO_SCORE_COLUMN_INDEX));
        weightForHeight.setMinusOneScore(cursor.getDouble(MINUS_ONE_SCORE_COLUMN_INDEX));
        weightForHeight.setMinusTwoScore(cursor.getDouble(MINUS_TWO_SCORE_COLUMN_INDEX));
        weightForHeight.setMinusThreeScore(cursor.getDouble(MINUS_THREE_SCORE_COLUMN_INDEX));
        return weightForHeight;
    }
}
