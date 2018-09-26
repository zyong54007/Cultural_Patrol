package com.zhjy.cultural.services.patrol;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhjy.cultural.services.patrol.adapter.NoticeDetailsAdapter;
import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.mvp.ActPresenter;
import com.zhjy.cultural.services.patrol.mvp.GEMUI;
import com.zhjy.cultural.services.patrol.mvp.MVPActivity;
import com.zhjy.cultural.services.patrol.network.Contant;
import com.zhjy.cultural.services.patrol.network.GRetrofit;
import com.zhjy.cultural.services.patrol.network.GemService;
import com.zhjy.cultural.services.patrol.network.NoticeDetails;
import com.zhjy.cultural.services.patrol.network.URLs;
import com.zhjy.cultural.services.patrol.util.DownloadUtil;
import com.zhjy.cultural.services.patrol.util.OpenFileUtils;
import com.zhjy.cultural.services.patrol.util.ToastUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

import rx.Subscriber;

public class NoticeDetailsPresenter extends ActPresenter<NoticeDetailsPresenter.NoticeDetailsUI> {




    public interface NoticeDetailsUI extends GEMUI {
        RecyclerView getrecyclerview();
    }

    @Override
    public void onUIReady(MVPActivity activity, NoticeDetailsUI ui) {
        super.onUIReady(activity, ui);
        int id = getActivity().getIntent().getIntExtra(Contant.ID, 0);
        if (id == 0) {
            ToastUtil.showToastMsg("网络异常,请检查网络");
            return;
        }

        initAdaapter();
        requestnetwork(id);


    }

    private NoticeDetailsAdapter adapter;
    private List<NoticeDetails.Documents> documents;
    private View headview;

    private TextView tv_time, tv_title, tv_content;


    private void initAdaapter() {
        adapter = new NoticeDetailsAdapter(R.layout.item_notice_details, documents);
        getUI().getrecyclerview().setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.openLoadAnimation();   //开启动画
        adapter.isFirstOnly(false);
        headview = LayoutInflater.from(getActivity()).inflate(R.layout.notice_details_top, null);
        tv_time = headview.findViewById(R.id.notice_details_time);
        tv_title = headview.findViewById(R.id.notice_details_title);
        tv_content = headview.findViewById(R.id.notice_details_content);
        adapter.addHeaderView(headview);
        getUI().getrecyclerview().setAdapter(adapter);






        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                switch (view.getId()){
                    case R.id.enclosure_download:   //下载文件
                        String imgPath = documents.get(position).getImgPath();
                        String fileName = documents.get(position).getFileName();
                        String url = URLs.DEBUG_BASE_URL_FILE + imgPath;

                        DownloadUtil.get().download(url, "download", fileName, new DownloadUtil.OnDownloadListener() {
                            @Override
                            public void onDownloadSuccess() {
//                        Utils.showToast(MainActivity.this, "下载完成");
//                                Log.i("TAG", "========下载完成");

                                Message msg = new Message();
                                msg.what = 1;
                                msg.obj = position;
                                handler.sendMessage(msg);
//
                            }

                            @Override
                            public void onDownloading(int progress) {
                            }

                            @Override
                            public void onDownloadFailed() {
//                        Utils.showToast(MainActivity.this, "下载失败");
//                        Log.i("TAG", "=========================下载失败");
                            }
                        });
                        break;

                    case R.id.item_notice_details_open:

                        String fileNamee = documents.get(position).getFileName();
                        String download = null;
                        try {
                            download = isExistDir("Download");
                            OpenFileUtils.openFileByPath(getActivity(), download + "/" + fileNamee);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                }




            }
        });
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int postion = (int) msg.obj;
            try {
                String download = isExistDir("download");
//                Log.i("TAG", "=条目数量=====================" + postion);
                documents.get(postion).setFlag(true);
                ToastUtil.showToastMsg("下载完毕 文件保存到" + download);
                adapter.notifyDataSetChanged();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    };



    private String isExistDir(String saveDir) throws IOException {
        // 下载位置
        File downloadFile = new File(Environment.getExternalStorageDirectory(), saveDir);
        if (!downloadFile.mkdirs()) {
            downloadFile.createNewFile();
        }
        String savePath = downloadFile.getAbsolutePath();
        return savePath;
    }



    private void requestnetwork(int id) {
//        http://wwgl.hdggwh.com/wwgl/notice/loadNoticeDetail;jsessionid=F49F1CCED04029060F218337D4A6C819?id=0
        String url = "notice/loadNoticeDetail;jsessionid=" + AppContext.getJsessionid() + "?id=" + id;
        Log.i("TAG", "详情地址=========================" + url);
        new GRetrofit().request(GemService.class).getnoticedetails(url)
                .compose(GRetrofit.observeOnMainThread(getUI()))
                .subscribe(new Subscriber<NoticeDetails>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(NoticeDetails b) {
                        documents = b.getDocuments();
                        adapter.addData(documents);
                        b.getNotice().getCreateTime();
                        tv_time.setText(b.getNotice().getCreateTime());
                        tv_title.setText(b.getNotice().getTheme());
                        tv_content.setText(b.getNotice().getContent());
                    }
                });
//                .subscribe(b -> {
//                    documents = b.getDocuments();
//                    adapter.addData(documents);
//                    b.getNotice().getCreateTime();
//                    tv_time.setText(b.getNotice().getCreateTime());
//                    tv_title.setText(b.getNotice().getTheme());
//                    tv_content.setText(b.getNotice().getContent());
//                });

    }




}
