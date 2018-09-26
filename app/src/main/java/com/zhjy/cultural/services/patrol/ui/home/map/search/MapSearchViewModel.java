package com.zhjy.cultural.services.patrol.ui.home.map.search;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.biz.api.CultureApi;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.TreasuresBean;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetMapDataRequest;

import java.util.List;

import javax.inject.Inject;

public class MapSearchViewModel extends ViewModel {

    AppContext appContext;

    CultureApi cultureApi;

    @Inject
    MapSearchViewModel(AppContext appContext, CultureApi cultureApi) {
        this.appContext = appContext;
        this.cultureApi = cultureApi;
    }

    public LiveData<List<TreasuresBean>> getMapDataListResult(GetMapDataRequest request){
        LiveData<List<TreasuresBean>> liveData = cultureApi.getMapDataListResult(request);
        return liveData;
    }

}
