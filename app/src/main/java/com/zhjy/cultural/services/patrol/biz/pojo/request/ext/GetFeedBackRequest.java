package com.zhjy.cultural.services.patrol.biz.pojo.request.ext;

import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.biz.pojo.request.BaseRequest;
import com.zhjy.cultural.services.patrol.util.SPUtils;

/**
 * Created by jialg on 2018/4/3.
 */

public class GetFeedBackRequest extends BaseRequest {

    private String content;

    private String ids;

    public GetFeedBackRequest(String content) {
        this.content = content;
    }

    public GetFeedBackRequest(String content, String ids) {
        this.content = content;
        this.ids = ids;
    }

//    private void processIds() {
//
//
//    }

    @Override
    public String toString() {
        super.toString();
        String result = String.format("?content=%s", content);
        if (null != ids)
            result = String.format("%s&ids=%s", result, ids);
        if (null != AppContext.getJsessionid()) {
            result = String.format(";jsessionid=%s%s", SPUtils.get("userid", ""), result);
        }
        return result;
    }
}
