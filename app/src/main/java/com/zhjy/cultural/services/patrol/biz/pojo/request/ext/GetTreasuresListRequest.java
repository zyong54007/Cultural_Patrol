package com.zhjy.cultural.services.patrol.biz.pojo.request.ext;

import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.biz.pojo.request.BaseRequest;
import com.zhjy.cultural.services.patrol.network.Contant;
import com.zhjy.cultural.services.patrol.util.SPUtils;

public class GetTreasuresListRequest extends BaseRequest {

    private int wwType;

    private String title;

    private int offset;


    public GetTreasuresListRequest(int offset) {
        this.offset = offset;
    }

    public GetTreasuresListRequest(int wwType, String title, int offset) {
        this.wwType = wwType;
        this.title = title;
        this.offset = offset;
    }


    @Override
    public String toString() {
        super.toString();
        String result = "?";

        String jessionid = SPUtils.get(Contant.JESSSIONID, "");
        if (jessionid != null) {
            result = String.format(";jsessionid=%s?", AppContext.getJsessionid());
        }
        result += String.format("wwType=%d&", wwType);
        if (null != title && !title.isEmpty())
            result += String.format("title=%s&", title);
        if (offset >= 0)
            result += String.format("pager.offset=%d*9", offset);
        return result;
    }

}
