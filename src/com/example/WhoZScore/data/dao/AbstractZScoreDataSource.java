package com.example.WhoZScore.data.dao;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 4/9/15
 * Time: 6:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class AbstractZScoreDataSource {

    public final int MINUS_THREE_SCORE_COLUMN_INDEX = 0;
    public final int MINUS_TWO_SCORE_COLUMN_INDEX = 1;
    public final int MINUS_ONE_SCORE_COLUMN_INDEX = 2;
    public final int ZERO_SCORE_COLUMN_INDEX = 3;
    public final int ONE_SCORE_COLUMN_INDEX = 4;
    public final int TWO_SCORE_COLUMN_INDEX = 5;
    public final int THREE_SCORE_COLUMN_INDEX = 6;

    public final String COLUMN_WEEKS = "weeks";
    public final String COLUMN_MONTHS = "months";
    public final String COLUMN_YEARS = "years";
    public final String COLUMN_THREE_SCORE = "threeScore";
    public final String COLUMN_TWO_SCORE = "twoScore";
    public final String COLUMN_ONE_SCORE = "oneScore";
    public final String COLUMN_ZERO_SCORE = "zeroScore";
    public final String COLUMN_MINUS_ONE_SCORE = "minusOneScore";
    public final String COLUMN_MINUS_TWO_SCORE = "minusTwoScore";
    public final String COLUMN_MINUS_THREE_SCORE = "minusThreeScore";
}
