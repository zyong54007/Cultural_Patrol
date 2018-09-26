package com.zhjy.cultural.services.patrol.mine.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.Toast;

import com.allenliu.versionchecklib.core.AllenChecker;
import com.allenliu.versionchecklib.core.VersionParams;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.mvp.FragmentPresenter;
import com.zhjy.cultural.services.patrol.mvp.GEMUI;
import com.zhjy.cultural.services.patrol.mvp.MVPActivity;
import com.zhjy.cultural.services.patrol.network.Contant;
import com.zhjy.cultural.services.patrol.network.GRetrofit;
import com.zhjy.cultural.services.patrol.network.GemService;
import com.zhjy.cultural.services.patrol.ui.setup.aboutus.AboutUsActivity;
import com.zhjy.cultural.services.patrol.ui.setup.aboutus.CustomVersionDialogActivity;
import com.zhjy.cultural.services.patrol.ui.setup.aboutus.UpdateService;
import com.zhjy.cultural.services.patrol.ui.setup.feedback.FeedBackActivity;
import com.zhjy.cultural.services.patrol.ui.setup.resetpass.ResetPassActivity;
import com.zhjy.cultural.services.patrol.ui.treasures.list.TreasuresListActivity;
import com.zhjy.cultural.services.patrol.util.SPUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class SettingPresenter extends FragmentPresenter<SettingPresenter.SettingUI> {
    public interface SettingUI extends GEMUI {


    }


    @Override
    public void onUIReady(MVPActivity activity, SettingUI ui) {

        super.onUIReady(activity, ui);

    }

    public void init() {
        String mobile = SPUtils.get(Contant.USERPHONE, "****");
        String maskNumber = mobile.substring(0, 3) + "****" + mobile.substring(7, mobile.length());

        getUI().finder().textView(R.id.mine_username).setText(SPUtils.get(Contant.USERNAME, "用户"));
        getUI().finder().textView(R.id.mine_user_grade).setText(SPUtils.get(Contant.USERGRADE, "未知"));
        getUI().finder().textView(R.id.mine_user_phone).setText(maskNumber);
    }


    /**
     * 反馈建议
     */
    public void FeedBack() {
        getActivity().startActivity(new Intent(getActivity(), FeedBackActivity.class));
    }

    /**
     * 关于我们
     */
    public void About() {
        getActivity().startActivity(new Intent(getActivity(), AboutUsActivity.class));

    }

    /**
     * 修改密码
     */

    public void EditPass() {
        getActivity().startActivity(new Intent(getActivity(), ResetPassActivity.class));

    }

    /**
     * 我的文物
     */
    public void MineCulturalRelic() {
        getActivity().startActivity(new Intent(getActivity(), TreasuresListActivity.class));

    }

    /**
     * 检测版本
     */
    public void MineTestting() {
        updateVersion();
//        getActivity().startActivity(new Intent(getActivity(), CheckVersionActivity.class));
    }

    public void SingOut() {
//        new GRetrofit().request(GemService.class).downloadfile("http://pic1.win4000.com/wallpaper/a/568cd27741af5.jpg").compose(GRetrofit.observeOnMainThread(getUI())).subscribe(b -> {
//            InputStream inputStream = b.byteStream();
//            try {
//                long totalLength = inputStream.available();
//
//                String path = getSDPath();
//                File file = new File(path, "zpdownload.jpg");
//
//
//                FileOutputStream fos = new FileOutputStream(file);
//                BufferedInputStream bis = new BufferedInputStream(inputStream);
//                byte[] buffer = new byte[1024];
//                int len;
//                while ((len = bis.read(buffer)) != -1) {
//                    fos.write(buffer, 0, len);
//                    //此处进行更新操作
//                    //len即可理解为已下载的字节数
//                    //onLoading(len, totalLength);
//                }
//                fos.flush();
//                fos.close();
//                bis.close();
//                inputStream.close();
//
//
//                String path1 = file.getPath();
//
//                Log.i("TAG", "==================这厮文件的陆军" + path1);
//
//
////                Intent imageFileIntent = getImageFileIntent(file);
////                getActivity().startActivity(imageFileIntent);
////                getActivity().start
//                Intent intent = openFile(path + "/zpdownload.jpg");
//                getActivity().startActivity(intent);

//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        });

        getActivity().finish();
    }

//
//    public Intent openFile(String filePath) {
//
//        Log.i("TAG", "=======文件地址================" + filePath);
//        File file = new File(filePath);
//        if (!file.exists()) {
//            Log.i("TAG", "========返回了空  文件为空");
//            return null;
//
//        }
//        /* 取得扩展名 */
//        String end = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length()).toLowerCase();
//        /* 依扩展名的类型决定MimeType */
//        if (end.equals("jpg") || end.equals("gif") || end.equals("png") ||
//                end.equals("jpeg") || end.equals("bmp")) {
//            Log.i("TAG", "===============文件后缀名" + end);
//            return getImageFileIntent(filePath);
//        }
//
//        return null;
//    }
//
//
//    public Intent getImageFileIntent(String param) {
//        String packageName = getActivity().getApplicationContext().getPackageName();
//        Log.i("TAG", "包名=================" + packageName);
//
//        Intent intent = new Intent("android.intent.action.VIEW");
//        intent.addCategory("android.intent.category.DEFAULT");
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        Uri uri = FileProvider.getUriForFile(getActivity(), getActivity().getApplicationContext().getPackageName() + ".provider", new File(param));
//        intent.setDataAndType(uri, "image/*");
//        return intent;
//    }
//
//
//    public String getSDPath() {
//        File sdDir = null;
//        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);//判断sd卡是否存在
//        if (sdCardExist) {
//            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
//        }
//        return sdDir.toString();
//
//
//    }


    /**
     *
     */
    public void updateVersion() {
        VersionParams.Builder builder = new VersionParams.Builder()
                .setRequestUrl("https://www.baidu.com")
                .setService(UpdateService.class);

        builder.setDownloadAPKPath("/storage/emulated/0/");

        CustomVersionDialogActivity.customVersionDialogIndex = 2;
        builder.setCustomDownloadActivityClass(CustomVersionDialogActivity.class);

        CustomVersionDialogActivity.isCustomDownloading = true;
        builder.setCustomDownloadActivityClass(CustomVersionDialogActivity.class);

        CustomVersionDialogActivity.isForceUpdate = true;
        builder.setCustomDownloadActivityClass(CustomVersionDialogActivity.class);

        builder.setOnlyDownload(false);

        builder.setShowNotification(true);

        builder.setShowDownloadingDialog(true);

        AllenChecker.startVersionCheck(getActivity().getApplication(), builder.build());

    }


}
