package com.zhjy.cultural.services.patrol.ui.treasures.info;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.baidu.location.BDLocation;
import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.biz.api.CultureApi;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetRecordListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetTreasuresInfoRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetCultureListResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetRecordListResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetTreasuresInfoResponse;
import com.zhjy.cultural.services.patrol.ui.home.main.BDLocationLiveData;
import com.zhjy.cultural.services.patrol.ui.map.BaiduLocationService;

import javax.inject.Inject;

/**
 * Created by jialg on 2018/2/5.
 */

public class TreasuresViewModel extends ViewModel {


    AppContext appContext;

    CultureApi cultureApi;

    BaiduLocationService baiduLocationService;

    MutableLiveData<GetCultureListResponse> cultureListResponse ;

    @Inject
    TreasuresViewModel(AppContext appContext, CultureApi cultureApi, BaiduLocationService baiduLocationService) {
        this.appContext = appContext;
        this.cultureApi = cultureApi;
        this.baiduLocationService = baiduLocationService;
        cultureListResponse = new MutableLiveData<>();
    }

    public LiveData<GetTreasuresInfoResponse> getTreasuresInfoResult(GetTreasuresInfoRequest request) {
        LiveData<GetTreasuresInfoResponse> liveData = cultureApi.getTreasuresInfoResult(request);
        return liveData;
    }

    public LiveData<GetRecordListResponse> getRecordListResult(GetRecordListRequest request) {
        LiveData<GetRecordListResponse> liveData = cultureApi.getRecordListResult(request);
        return liveData;
    }

    public LiveData<BDLocation> getLicationLiveData(){
        LiveData<BDLocation> liveData = BDLocationLiveData.get(appContext,baiduLocationService);
        return liveData;
    }

}
