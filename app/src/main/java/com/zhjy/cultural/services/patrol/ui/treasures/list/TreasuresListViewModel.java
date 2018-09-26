package com.zhjy.cultural.services.patrol.ui.treasures.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.zhjy.cultural.services.patrol.biz.api.CultureApi;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetTreasuresListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetTreasuresListResponse;

import javax.inject.Inject;

/**
 * Created by jialg on 2018/1/25.
 */

public class TreasuresListViewModel extends ViewModel {

    CultureApi cultureApi;

    @Inject
    TreasuresListViewModel(CultureApi cultureApi) {
        this.cultureApi = cultureApi;
    }

    public LiveData<GetTreasuresListResponse> getTreasuresListReqult(GetTreasuresListRequest request) {
        LiveData<GetTreasuresListResponse> liveData = cultureApi.getTreasuresListResult(request);
        return liveData;
    }

}
