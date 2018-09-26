package com.zhjy.cultural.services.patrol.ui.inspection.punchclock;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.baidu.location.BDLocation;
import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.biz.api.CultureApi;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetPointSaveRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetPointSaveResponse;
import com.zhjy.cultural.services.patrol.ui.home.main.BDLocationLiveData;
import com.zhjy.cultural.services.patrol.ui.map.BaiduLocationService;

import javax.inject.Inject;

/**
 * Created by jialg on 2018/3/7.
 */

public class PunchClockViewModel extends ViewModel{

    AppContext appContext;

    CultureApi cultureApi;

    BaiduLocationService baiduLocationService;

    @Inject
    PunchClockViewModel(AppContext appContext, CultureApi cultureApi, BaiduLocationService baiduLocationService){
        this.appContext = appContext;
        this.cultureApi = cultureApi;
        this.baiduLocationService = baiduLocationService;

    }

    public LiveData<BDLocation> getLicationLiveData(){
        LiveData<BDLocation> liveData = BDLocationLiveData.get(appContext,baiduLocationService);
        return liveData;
    }

    public LiveData<GetPointSaveResponse> getPointResult(GetPointSaveRequest request){
        LiveData<GetPointSaveResponse>  liveData = cultureApi.getPointSaveResult(request);
        return liveData;
    }


}
