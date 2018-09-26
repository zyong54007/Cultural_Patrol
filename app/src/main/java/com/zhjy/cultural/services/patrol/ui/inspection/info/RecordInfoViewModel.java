package com.zhjy.cultural.services.patrol.ui.inspection.info;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.zhjy.cultural.services.patrol.biz.api.CultureApi;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.ImageBean;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetImageListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetRecordInfoRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetTreasuresInfoRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetRecordInfoResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetTreasuresInfoResponse;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by jialg on 2018/3/9.
 */

public class RecordInfoViewModel extends ViewModel {

    CultureApi cultureApi;

    @Inject
    RecordInfoViewModel(CultureApi cultureApi){
        this.cultureApi = cultureApi;
    }

    public LiveData<GetRecordInfoResponse> getRecordInfoResult(GetRecordInfoRequest request) {
        LiveData<GetRecordInfoResponse> liveData = cultureApi.getRecordInfoResult(request);
        return liveData;
    }

    public LiveData<GetTreasuresInfoResponse> getTreasuresInfoResult(GetTreasuresInfoRequest request) {
        LiveData<GetTreasuresInfoResponse> liveData = cultureApi.getTreasuresInfoResult(request);
        return liveData;
    }

    public LiveData<List<ImageBean>> getImageListResult(GetImageListRequest request){
        LiveData<List<ImageBean>> liveData = cultureApi.getImageListResult(request);
        return liveData;
    }


}
