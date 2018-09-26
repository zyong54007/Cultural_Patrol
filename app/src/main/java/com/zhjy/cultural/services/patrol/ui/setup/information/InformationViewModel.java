package com.zhjy.cultural.services.patrol.ui.setup.information;

import android.arch.lifecycle.ViewModel;

import com.zhjy.cultural.services.patrol.biz.api.CultureApi;

import javax.inject.Inject;

/**
 * Created by jialg on 2018/1/31.
 */

public class InformationViewModel extends ViewModel {

    CultureApi cultureApi;

    @Inject
    InformationViewModel(CultureApi cultureApi) {
        this.cultureApi = cultureApi;
    }

}
