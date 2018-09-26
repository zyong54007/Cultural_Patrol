package com.zhjy.cultural.services.patrol.ui.treasures.map;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.zhjy.cultural.services.patrol.biz.api.CultureApi;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetCultureListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetCultureListResponse;

import javax.inject.Inject;

/**
 * Created by jialg on 2018/3/22.
 */

public class TreasuresMapSearchViewModel extends ViewModel {

    CultureApi cultureApi;

    @Inject
    TreasuresMapSearchViewModel(CultureApi cultureApi) {
        this.cultureApi = cultureApi;
    }

    public LiveData<GetCultureListResponse> getMainListResponse(GetCultureListRequest request) {
        LiveData<GetCultureListResponse> liveData = cultureApi.getCultureList(request);
        return liveData;
    }

}
