package com.zhjy.cultural.services.patrol.routing;

import android.content.Intent;
import android.util.Log;

import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.bean.InSpecListEntity;
import com.zhjy.cultural.services.patrol.bean.SearchRoutingEntity;
import com.zhjy.cultural.services.patrol.mvp.FragmentPresenter;
import com.zhjy.cultural.services.patrol.mvp.GEMUI;
import com.zhjy.cultural.services.patrol.mvp.MVPActivity;
import com.zhjy.cultural.services.patrol.network.GRetrofit;
import com.zhjy.cultural.services.patrol.network.GemService;
import com.zhjy.cultural.services.patrol.ui.LoginActivity;

import rx.Observer;

public class InspectionRecordPresenter extends FragmentPresenter<InspectionRecordPresenter.InspectionRecordUI> {
    private int page = 0;

    public interface InspectionRecordUI extends GEMUI {

    }

    @Override
    public void onUIReady(MVPActivity activity, InspectionRecordUI ui) {
        super.onUIReady(activity, ui);
        ReqestDate();
    }

    private void ReqestDate() {
        int pagesize = page * 10;
//        http://wwgl.hdggwh.com/wwgl/record/loadAppList;jsessionid=DA1D656E74860E382B53C2B0535B39F1?recordType=r&title=&flag=1&pager.offset=10
        String url = "record/loadAppList;jsessionid=" + AppContext.getJsessionid() + "?recordType=r&title=&flag=1&pager.offset=" + pagesize;
        Log.i("TAG", "url巡检记录==================" + url);
        new GRetrofit().request(GemService.class).getInspeclist(url)
                .compose(GRetrofit.observeOnMainThread(getUI()))
                .subscribe(new Observer<InSpecListEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        getActivity().startActivity(intent);
                        getActivity().finish();
                    }

                    @Override
                    public void onNext(InSpecListEntity inSpecListEntity) {

                    }
                });
    }
}
