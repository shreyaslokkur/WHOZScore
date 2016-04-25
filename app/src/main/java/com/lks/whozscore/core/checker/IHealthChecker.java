package com.lks.whozscore.core.checker;

import android.content.Context;
import com.lks.whozscore.data.entities.IZScoreEntity;
import com.lks.whozscore.model.IResult;
import com.lks.whozscore.model.Patient;
import com.lks.whozscore.model.Result;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 4/10/15
 * Time: 5:54 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IHealthChecker {

    IResult getHealthResult(Patient patient, IZScoreEntity zScoreEntity, Context context);
}
