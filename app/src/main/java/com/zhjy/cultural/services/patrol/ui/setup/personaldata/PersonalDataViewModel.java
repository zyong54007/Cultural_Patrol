package com.zhjy.cultural.services.patrol.ui.setup.personaldata;

import android.arch.lifecycle.ViewModel;

import com.zhjy.cultural.services.patrol.biz.api.CultureApi;

import javax.inject.Inject;

/**
 * Created by jialg on 2018/3/23.
 */

public class PersonalDataViewModel  extends ViewModel {

    CultureApi cultureApi;

    @Inject
    PersonalDataViewModel(CultureApi cultureApi) {
        this.cultureApi = cultureApi;
    }

}
