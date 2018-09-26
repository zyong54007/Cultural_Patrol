package com.zhjy.cultural.services.patrol.ui.setup.feedback;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.zhjy.cultural.services.patrol.biz.api.CultureApi;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetFeedBackRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetFeedBackResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.PostImageResponse;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by jialg on 2018/1/31.
 */

public class FeedBackViewModel  extends ViewModel {

    CultureApi cultureApi;

    @Inject
    FeedBackViewModel(CultureApi cultureApi) {
        this.cultureApi = cultureApi;
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
