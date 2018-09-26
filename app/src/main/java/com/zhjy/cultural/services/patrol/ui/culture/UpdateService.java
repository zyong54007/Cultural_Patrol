package com.zhjy.cultural.services.patrol.ui.culture;

import android.util.Log;

import com.allenliu.versionchecklib.core.AVersionService;

/**
 * Created by jialg on 2018/1/10.
 */

public class UpdateService extends AVersionService {
    private static String TAG = "UpdateService";
    @Override
    public void onResponses(AVersionService service, String response) {
        Log.i(TAG,response);
        service.showVersionDialog("http://www.hdggwh.com/hdwh.apk","检查更新","请点击确定更新！");
    }
}
