package com.zhjy.cultural.services.patrol.network;

import com.zhjy.cultural.services.patrol.NoticeDate;
import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.bean.AppNumEntity;
import com.zhjy.cultural.services.patrol.bean.HandleMatter;
import com.zhjy.cultural.services.patrol.bean.InSpecListEntity;
import com.zhjy.cultural.services.patrol.bean.LoginEntity;
import com.zhjy.cultural.services.patrol.bean.Msg;
import com.zhjy.cultural.services.patrol.bean.SearchRoutingEntity;
import com.zhjy.cultural.services.patrol.bean.SearchTreasuresEntity;
import com.zhjy.cultural.services.patrol.bean.TreasuresEntity;
import com.zhjy.cultural.services.patrol.network.response.StatusResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

public interface GemService {

    String HEADERS = "Content-type:application/json;charset=UTF-8";

    /**
     * 请求安装序列号
     *
     * @param param
     * @return
     */
    @GET(URLs.INSTALL)
    Observable<StatusResponse<LoginEntity>> Instll(@Query(Contant.PARAM) String param);

    /**
     * 请求定位距离
     *
     * @return
     */
    @GET
    Call<ResponseBody> DISTANCE(@Url String url);

    /**
     * 我的消息
     */
    @Headers("Connection:close")
    @POST
    Observable<NoticeList> getnotice(@Url String url);

    @Headers("Connection:close")
    @GET
    Observable<NoticeDetails> getnoticedetails(@Url String url);

    @Streaming
    @GET
    Observable<ResponseBody> downloadfile(@Url String url);


    /**
     * 我监管的文物
     *
     * @param url
     * @return
     */
    @Headers("Connection:close")
    @GET
    Observable<TreasuresEntity> getTreasureslist(@Url String url);


    /**
     * 首页异常待办
     *
     * @param url
     * @return
     */
    @Headers("Connection:close")
    @GET
    Observable<SearchRoutingEntity> getSearchRouting(@Url String url);


    /**
     * 巡检记录
     *
     * @param url
     * @return
     */
    @Headers("Connection:close")
    @GET
    Observable<InSpecListEntity> getInspeclist(@Url String url);


    /**
     * 搜索我的文物
     *
     * @param url
     * @return
     */
    @Headers("Connection:close")
    @GET
    Observable<SearchTreasuresEntity> SearchTreasures(@Url String url);


    /**
     * 我的文物列表
     *
     * @param url
     * @return
     */
    @Headers("Connection:close")
    @GET
    Observable<TreasuresEntity> getTreasuresList(@Url String url);


    /**
     * 异常待办
     *
     * @param url
     * @return
     */
    @Headers("Connection:close")
    @GET
    Observable<List<HandleMatter>> getHomeErrorMatter(@Url String url);


    /**
     * 全局需要的条目个数
     *
     * @param url
     * @return
     */
    @Headers("Connection:close")
    @GET
    Observable<AppNumEntity> getappnum(@Url String url);


    /**
     * 获取未读消息数量
     *
     * @param url
     * @return
     */
    @Headers("Connection:close")
    @GET
    Observable<Msg> getmeaage(@Url String url);


}
