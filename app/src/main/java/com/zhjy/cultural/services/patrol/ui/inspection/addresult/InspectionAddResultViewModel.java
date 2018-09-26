package com.zhjy.cultural.services.patrol.ui.inspection.addresult;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.zhjy.cultural.services.patrol.biz.api.CultureApi;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetTreasuresInfoRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.PostRecordSaveRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetTreasuresInfoResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.PostRecordSaveResponse;

import javax.inject.Inject;

/**
 * Created by jialg on 2018/3/9.
 */

public class InspectionAddResultViewModel extends ViewModel {

    CultureApi cultureApi;

    @Inject
    InspectionAddResultViewModel(CultureApi cultureApi){
        this.cultureApi =cultureApi;
    }

    public LiveData<GetTreasuresInfoResponse> getTreasuresInfoResult(GetTreasuresInfoRequest request) {
        LiveData<GetTreasuresInfoResponse> liveData = cultureApi.getTreasuresInfoResult(request);
        return liveData;
    }

    public LiveData<PostRecordSaveResponse> postRecordSaveResult(PostRecordSaveRequest request){
        LiveData<PostRecordSaveResponse> liveData = cultureApi.postRecordSaveResult(request);
        return liveData;
    }



}
