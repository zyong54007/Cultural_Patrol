package com.zhjy.cultural.services.patrol.ui.setup.resetpass;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.zhjy.cultural.services.patrol.biz.api.CultureApi;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetUpdatePwdRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetUpdatePwdResponse;
import com.zhjy.cultural.services.patrol.util.SimpleObserver;

import javax.inject.Inject;

/**
 * Created by jialg on 2018/1/31.
 */

public class ResetPassViewModel extends ViewModel {

    CultureApi cultureApi;

    MutableLiveData<GetUpdatePwdResponse> updatePwdInfo = new MutableLiveData<>();

    @Inject
    ResetPassViewModel(CultureApi cultureApi) {
        this.cultureApi = cultureApi;
    }

    LiveData<GetUpdatePwdResponse> getLoginInfo(GetUpdatePwdRequest request){
        cultureApi.getUpdatePwdResult(request).observeForever(new SimpleObserver<>(updatePwdInfo));
        return updatePwdInfo;
    }

}
