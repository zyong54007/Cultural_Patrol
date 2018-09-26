package com.zhjy.cultural.services.patrol.ui.setup.checkversion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.zhjy.cultural.services.patrol.CheckVersionPresenter;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.base.BaseActivity;
import com.zhjy.cultural.services.patrol.network.Contant;
import com.zhjy.cultural.services.patrol.network.EasySubscriber;
import com.zhjy.cultural.services.patrol.network.GRetrofit;
import com.zhjy.cultural.services.patrol.network.GemService;
import com.zhjy.cultural.services.patrol.network.URLs;
import com.zhjy.cultural.services.patrol.ui.MainActivity;
import com.zhjy.cultural.services.patrol.util.SPUtils;
import com.zhjy.cultural.services.patrol.util.ToastUtil;

public class CheckVersionActivity extends BaseActivity<CheckVersionPresenter.CheckVersionUI, CheckVersionPresenter> implements CheckVersionPresenter.CheckVersionUI {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_check_version);
//
//
//    }

    @Override
    protected CheckVersionPresenter.CheckVersionUI createUI() {
        return this;
    }

    @Override
    protected CheckVersionPresenter createPresenter() {
        return new CheckVersionPresenter();
    }

    @Override
    protected void initViews() {
//        String userid = SPUtils.get("userid", "");
//        Log.i("TAG", "=============" + userid);
//        String jsessionid = AppContext.getJsessionid();
//        Log.i("TAG", "=============" + jsessionid);
////        http://wwgl.hdggwh.com/wwgl/wwInfo/listInterface;jsessionid=D83B7A97624AEEA9DD2467A4505B62C1?wwType=0&pager.offset=0
//        String url = URLs.BASE_URL + "wwInfo/listInterface;jsessionid=" + AppContext.getJsessionid() + "?wwType=0&pager.offset=0";
//        Log.i("TAG", "=========ul====" + url);
//        new GRetrofit()
//                .request(GemService.class)
//                .Wwlist(url)
//                .compose(GRetrofit.observeOnMainThread(getUI()))
//                .subscribe(b -> {
//                            int pageSize = b.getData().getPageSize();
//
//                            Log.i("TAG", "====" + pageSize);
//
//                        }
//
//
//                );


    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_check_version;
    }
}
