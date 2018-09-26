package com.zhjy.cultural.services.patrol.ui.inspection.feedback;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.zhjy.cultural.services.patrol.biz.api.CultureApi;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetFeedBackRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetTreasuresInfoRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.PostHandleSaveRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetFeedBackResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetTreasuresInfoResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.PostHandleSaveResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.PostImageResponse;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by jialg on 2018/3/9.
 */

public class InspectionFeedBackViewModel extends ViewModel {

    CultureApi cultureApi;

    @Inject
    InspectionFeedBackViewModel(CultureApi cultureApi){
        this.cultureApi = cultureApi;
    }

    public LiveData<PostHandleSaveResponse> postHandleSaveResult(PostHandleSaveRequest request){
        LiveData<PostHandleSaveResponse> liveData = cultureApi.postHandleSaveResult(request);
        return liveData;
    }

    public LiveData<GetTreasuresInfoResponse> getTreasuresInfoResult(GetTreasuresInfoRequest request) {
        LiveData<GetTreasuresInfoResponse> liveData = cultureApi.getTreasuresInfoResult(request);
        return liveData;
    }

    public LiveData<PostImageResponse> postImageResult(List<File> files) {
        LiveData<PostImageResponse> liveData = cultureApi.postImageResult(files);
        return liveData;
    }

    public LiveData<GetFeedBackResponse> getFeedBackResult(GetFeedBackRequest request) {
        LiveData<GetFeedBackResponse> liveData = cultureApi.getFeedBackResult(request);
        return liveData;
    }
}
