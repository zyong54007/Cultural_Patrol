package com.zhjy.cultural.services.patrol.ui.setup.notice.info;

import android.arch.lifecycle.ViewModel;

import com.zhjy.cultural.services.patrol.biz.api.CultureApi;

import javax.inject.Inject;

/**
 * Created by jialg on 2018/1/31.
 */

public class NoticeInfoViewModel extends ViewModel {

    CultureApi cultureApi;

    @Inject
    NoticeInfoViewModel(CultureApi cultureApi) {
        this.cultureApi = cultureApi;
    }

}
