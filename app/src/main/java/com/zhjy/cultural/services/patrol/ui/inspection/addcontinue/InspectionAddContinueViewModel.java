package com.zhjy.cultural.services.patrol.ui.inspection.addcontinue;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.zhjy.cultural.services.patrol.biz.api.CultureApi;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetTreasuresInfoRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.PostHandleSaveRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetTreasuresInfoResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.PostHandleSaveResponse;

import javax.inject.Inject;

/**
 * Created by jialg on 2018/3/9.
 */

public class InspectionAddContinueViewModel extends ViewModel {

    CultureApi cultureApi;

    @Inject
    InspectionAddContinueViewModel(CultureApi cultureApi){
        this.cultureApi = cultureApi;
    }

    public LiveData<PostHandleSaveResponse> postHandleSaveResult(PostHandleSaveRequest request){
        LiveData<PostHandleSaveResponse> liveData = cultureApi.postHandleSaveResult(request);
        return liveData;
    }

    public LiveData<GetTreasuresInfoResponse> getTreasuresInfoResult(GetTreasuresInfoRequest request) {
        LiveData<GetTreasuresInfoResponse> liveData = cultureApi.getTreasuresInfoResult(request);
        return liveData;
    }

}
