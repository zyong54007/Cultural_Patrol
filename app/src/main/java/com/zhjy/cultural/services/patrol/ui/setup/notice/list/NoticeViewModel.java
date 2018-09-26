package com.zhjy.cultural.services.patrol.ui.setup.notice.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.zhjy.cultural.services.patrol.biz.api.CultureApi;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetNullListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetNullListResponse;

import javax.inject.Inject;

/**
 * Created by jialg on 2018/1/31.
 */

public class NoticeViewModel extends ViewModel {

    CultureApi cultureApi;

    @Inject
    NoticeViewModel(CultureApi cultureApi) {
        this.cultureApi = cultureApi;
    }

    public LiveData<GetNullListResponse> getMainListResponse(GetNullListRequest request) {
        LiveData<GetNullListResponse> liveData = cultureApi.getNullListResult(request);
        return liveData;
    }
}
