package com.zhjy.cultural.services.patrol.network;

import com.zhjy.cultural.services.patrol.app.AppContext;

public class URLs {


    public static final String BASE_URL = "http://wwgl.hdggwh.com/wwgl/";
//    public static final String BASE_URL = "http://192.168.100.188:8093/wwgl/";

    public static final String BASE_URL_FILE = "http://wwgl.hdggwh.com/wwgl/wwfile";

//    public static final String DEBUG_BASE_URL_FILE = "http://192.168.100.188:8093/wwgl/wwfile";
    public static final String DEBUG_BASE_URL_FILE = "http://wwgl.hdggwh.com/wwgl/wwfile";

    /**
     * 获取公告信息
     */

    public static final String LOGINUSER = "loginInterface?";

    //    http://wwgl.hdggwh.com/wwgl/wwInfo/listInterface;jsessionid=142C60F14CC81FF92DCAD978E2B13538?wwType=0&pager.offset=0
    public static final String WWLIST = "/wwInfo/listInterface;jsessionid";


    public static final String INSTALL = "checkAppInterface";

    public static final String DISTANCE = "task/getDistance";


    public static final String NORMALNUM = "normalnum";
    public static final String ERRORNUM = "errornum";
    public static final String WAITNUM = "waitnum";
    public static final String SUMNUM = "sumnum";
}
