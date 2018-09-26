package com.zhjy.cultural.services.patrol.ui.setup.aboutus;

import android.arch.lifecycle.ViewModel;

import com.zhjy.cultural.services.patrol.biz.api.CultureApi;

import javax.inject.Inject;

/**
 * Created by jialg on 2018/1/31.
 */

public class AboutUsViewModel extends ViewModel {

    CultureApi cultureApi;

    @Inject
    AboutUsViewModel(CultureApi cultureApi) {
        this.cultureApi = cultureApi;
    }



}
