package com.zhjy.cultural.services.patrol.ui.home.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.baidu.location.BDLocation;
import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.biz.api.CultureApi;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetCultureListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetNullListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetRecordListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetTotalMessageRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetTreasuresListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetCultureListResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetNullListResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetRecordListResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetTotalMessageResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetTreasuresListResponse;
import com.zhjy.cultural.services.patrol.ui.map.BaiduLocationService;

import javax.inject.Inject;


/**
 * Created by jialg on 2018/1/12.
 */

public class MainListViewModel extends ViewModel {

    AppContext appContext;

    CultureApi cultureApi;

    BaiduLocationService baiduLocationService;

    MutableLiveData<GetCultureListResponse> cultureListResponse ;

    @Inject
    MainListViewModel(AppContext appContext, CultureApi cultureApi, BaiduLocationService baiduLocationService) {
        this.appContext = appContext;
        this.cultureApi = cultureApi;
        this.baiduLocationService = baiduLocationService;
        cultureListResponse = new MutableLiveData<>();
    }

    public LiveData<GetCultureListResponse> getMainListResponse(GetCultureListRequest request) {
        LiveData<GetCultureListResponse> liveData = cultureApi.getCultureList(request);
        return liveData;
    }

    public LiveData<BDLocation> getLicationLiveData(){
        LiveData<BDLocation> liveData = BDLocationLiveData.get(appContext,baiduLocationService);
        return liveData;
    }

    public LiveData<GetTreasuresListResponse> getTreasuresListReqult(GetTreasuresListRequest request) {
        LiveData<GetTreasuresListResponse> liveData = cultureApi.getTreasuresListResult(request);
        return liveData;
    }

    public LiveData<GetRecordListResponse> getRecordListResult(GetRecordListRequest request) {
        LiveData<GetRecordListResponse> liveData = cultureApi.getRecordListResult(request);
        return liveData;
    }

    public LiveData<GetTotalMessageResponse> getTotalMessageResult(GetTotalMessageRequest request){
        LiveData<GetTotalMessageResponse> liveData = cultureApi.getTotalMessageResult(request);
        return liveData;
    }

    public LiveData<GetNullListResponse> getTotalNullResult(GetNullListRequest request){
        LiveData<GetNullListResponse> liveData = cultureApi.getNullListResult(request);
        return liveData;
    }


}
