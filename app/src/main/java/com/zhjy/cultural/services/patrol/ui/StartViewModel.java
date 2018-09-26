package com.zhjy.cultural.services.patrol.ui;

import android.arch.lifecycle.ViewModel;

import com.zhjy.cultural.services.patrol.biz.api.CultureApi;

import javax.inject.Inject;


/**
 * StartViewModel <br/>
 * Created by xiaqiulei on 2017-05-25.
 */
public class StartViewModel extends ViewModel {

    CultureApi cultureApi;

    @Inject
    StartViewModel(CultureApi cultureApi) {
        this.cultureApi = cultureApi;
    }


}