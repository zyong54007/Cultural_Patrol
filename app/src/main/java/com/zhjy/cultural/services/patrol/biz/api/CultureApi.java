package com.zhjy.cultural.services.patrol.biz.api;

import android.arch.lifecycle.LiveData;

import com.zhjy.cultural.services.patrol.biz.pojo.bean.ImageBean;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.TreasuresBean;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetCultureListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetErrorMessageListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetFeedBackRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetImageListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetLoginRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetMapDataRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetNullListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetPointListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetPointSaveRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetRecordAppListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetRecordErrorInfoRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetRecordInfoRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetRecordListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetTotalMessageRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetTotalNullRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetTreasuresInfoRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetTreasuresListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetUpdatePwdRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.PostHandleSaveRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.PostRecordSaveRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetCultureListResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetErrorMessageListResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetFeedBackResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetLoginResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetNullListResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetPointListResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetPointSaveResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetRecordErrorInfoResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetRecordInfoResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetRecordListResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetTotalMessageResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetTotalNullResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetTreasuresInfoResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetTreasuresListResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetUpdatePwdResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.PostHandleSaveResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.PostImageResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.PostRecordSaveResponse;

import java.io.File;
import java.util.List;


/**
 * ZhihuApi <br/>
 * Created by xiaqiulei on 2015-12-30.
 */
public interface CultureApi {

    LiveData<GetCultureListResponse> getCultureList(GetCultureListRequest request);

    //用户登录
    LiveData<GetLoginResponse> getLoginResult(GetLoginRequest request);

    //修改密码
    LiveData<GetUpdatePwdResponse> getUpdatePwdResult(GetUpdatePwdRequest request);

    //我监管的文物列表
    LiveData<GetTreasuresListResponse> getTreasuresListResult(GetTreasuresListRequest request);

    //文物详情页
    LiveData<GetTreasuresInfoResponse> getTreasuresInfoResult(GetTreasuresInfoRequest request);


    //巡检信息列表
    LiveData<GetRecordListResponse> getRecordListResult(GetRecordListRequest request);

    //巡检详情页
    LiveData<GetRecordInfoResponse> getRecordInfoResult(GetRecordInfoRequest request);

    //巡检异常详情页
    LiveData<GetRecordErrorInfoResponse> getRecordErrorInfoResult(GetRecordErrorInfoRequest request);

    //上传图片
    LiveData<PostImageResponse> postImageResult(List<File> files);

    //意见反馈
    LiveData<GetFeedBackResponse> getFeedBackResult(GetFeedBackRequest request);

    //保存巡检轨迹点
    LiveData<GetPointSaveResponse> getPointSaveResult(GetPointSaveRequest request);

    //巡检轨迹点列表
    LiveData<GetPointListResponse> getPointListResult(GetPointListRequest request);

    //我的消息未读条数
    LiveData<GetTotalMessageResponse> getTotalMessageResult(GetTotalMessageRequest request);

    //未巡检记录条数
    LiveData<GetTotalNullResponse> getTotalNullResult(GetTotalNullRequest request);

    //异常处理提醒列表
    LiveData<GetErrorMessageListResponse> getErrorMessageListResult(GetErrorMessageListRequest request);

    //未巡检记录提醒列表
    LiveData<GetNullListResponse> getNullListResult(GetNullListRequest request);

    //文物地图列表
    LiveData<List<TreasuresBean>> getMapDataListResult(GetMapDataRequest request);

    //巡检记录列表
    LiveData<GetRecordListResponse> getRecordAppListResult(GetRecordAppListRequest request);

    //添加巡检信息
    LiveData<PostRecordSaveResponse> postRecordSaveResult(PostRecordSaveRequest request);

    //提交普通用户异常处理
    LiveData<PostHandleSaveResponse> postHandleSaveResult(PostHandleSaveRequest request);

    //获取图片列表
    LiveData<List<ImageBean>> getImageListResult(GetImageListRequest request);
}