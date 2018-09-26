package com.zhjy.cultural.services.patrol.ui.inspection.abnormality;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.biz.api.CultureApi;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetRecordErrorInfoRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetTreasuresInfoRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetRecordErrorInfoResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetTreasuresInfoResponse;

import javax.inject.Inject;

/**
 * Created by jialg on 2018/3/9.
 */

public class InspectionAbnormalityViewModel extends ViewModel {

    AppContext appContext;

    CultureApi cultureApi;

    @Inject
    InspectionAbnormalityViewModel(AppContext appContext, CultureApi cultureApi){
        this.appContext = appContext;
        this.cultureApi = cultureApi;
    }

    public LiveData<GetTreasuresInfoResponse> getTreasuresInfoResult(GetTreasuresInfoRequest request) {
        LiveData<GetTreasuresInfoResponse> liveData = cultureApi.getTreasuresInfoResult(request);
        return liveData;
    }

    public LiveData<GetRecordErrorInfoResponse> getRecordErrorInfoResult(GetRecordErrorInfoRequest request) {
        LiveData<GetRecordErrorInfoResponse> liveData = cultureApi.getRecordErrorInfoResult(request);
        return liveData;
    }

    /*public LiveData<List<ImageBean>> getImageListResult(GetImageListRequest request) {
        LiveData<List<ImageBean>> liveData = cultureApi.getImageListResult(request);
        return liveData;
    }*/

}


