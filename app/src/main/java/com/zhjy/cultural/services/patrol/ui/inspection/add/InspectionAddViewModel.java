package com.zhjy.cultural.services.patrol.ui.inspection.add;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.zhjy.cultural.services.patrol.biz.api.CultureApi;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetTreasuresInfoRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetTreasuresInfoResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.PostImageResponse;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by jialg on 2018/3/23.
 */

public class InspectionAddViewModel extends ViewModel {

    CultureApi cultureApi;

    @Inject
    InspectionAddViewModel(CultureApi cultureApi){
        this.cultureApi = cultureApi;
    }

    public LiveData<GetTreasuresInfoResponse> getTreasuresInfoResult(GetTreasuresInfoRequest request) {
        LiveData<GetTreasuresInfoResponse> liveData = cultureApi.getTreasuresInfoResult(request);
        return liveData;
    }

    public LiveData<PostImageResponse> postImageResult(List<File> files) {
        LiveData<PostImageResponse> liveData = cultureApi.postImageResult(files);
        return liveData;
    }

}
