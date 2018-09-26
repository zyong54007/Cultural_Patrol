package com.zhjy.cultural.services.patrol.ui.treasures.choice;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.zhjy.cultural.services.patrol.biz.api.CultureApi;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetCultureListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetTreasuresListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetCultureListResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetTreasuresListResponse;

import javax.inject.Inject;

/**
 * Created by jialg on 2018/3/22.
 */

public class TreasuresChoiceViewModel extends ViewModel {

    CultureApi cultureApi;

    @Inject
    TreasuresChoiceViewModel(CultureApi cultureApi) {
        this.cultureApi = cultureApi;
    }

    public LiveData<GetCultureListResponse> getMainListResponse(GetCultureListRequest request) {
        LiveData<GetCultureListResponse> liveData = cultureApi.getCultureList(request);
        return liveData;
    }

    public LiveData<GetTreasuresListResponse> getTreasuresListReqult(GetTreasuresListRequest request) {
        LiveData<GetTreasuresListResponse> liveData = cultureApi.getTreasuresListResult(request);
        return liveData;
    }

}
