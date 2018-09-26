package com.zhjy.cultural.services.patrol.ui.home.inspection;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.zhjy.cultural.services.patrol.biz.api.CultureApi;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetRecordAppListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetRecordListResponse;

import javax.inject.Inject;

/**
 * Created by jialg on 2018/1/24.
 */

public class InspectionViewModel  extends ViewModel {

    CultureApi cultureApi;

    @Inject
    InspectionViewModel(CultureApi cultureApi) {
        this.cultureApi = cultureApi;
    }

    public LiveData<GetRecordListResponse> getRecordListResult(GetRecordAppListRequest request) {
        LiveData<GetRecordListResponse> liveData = cultureApi.getRecordAppListResult(request);
        return liveData;
    }

}
