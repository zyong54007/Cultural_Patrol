package com.zhjy.cultural.services.patrol.ui.list;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.zhjy.cultural.services.patrol.biz.api.CultureApi;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetCultureListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetCultureListResponse;
import com.zhjy.cultural.services.patrol.util.AbsentLiveData;

import javax.inject.Inject;


/**
 * Created by jialg on 2018/1/12.
 */

public class CultureListViewModel extends ViewModel {

    CultureApi cultureApi;
    LiveData<GetCultureListResponse> cultureListResponse ;

    MutableLiveData<Integer> typeid = new MutableLiveData<>();
    MutableLiveData<Integer> p = new MutableLiveData<>();
    MutableLiveData<GetCultureListRequest> request = new MutableLiveData<>();


    @Inject
    CultureListViewModel(CultureApi cultureApi) {
        this.cultureApi = cultureApi;
        //cultureListResponse = new MutableLiveData<>();
        cultureListResponse = Transformations.switchMap(request, new Function<GetCultureListRequest, LiveData<GetCultureListResponse>> (){
            @Override
            public LiveData<GetCultureListResponse> apply(GetCultureListRequest request) {
                if(request == null)
                    return AbsentLiveData.create();
                else
                    return getLastThemeResponse(request);
            }
        });
    }

    public LiveData<GetCultureListResponse> getLastThemeResponse(GetCultureListRequest request) {
        return cultureApi.getCultureList(request);
    }

    public LiveData<GetCultureListResponse> getLiveData(){
        return cultureListResponse;
    }

    public void setRequest(int typeid , int p ) {
        GetCultureListRequest request = new GetCultureListRequest(typeid,p);
        this.request.setValue(request);
    }

}
