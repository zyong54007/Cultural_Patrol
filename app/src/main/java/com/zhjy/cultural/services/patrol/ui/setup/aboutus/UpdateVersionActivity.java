package com.zhjy.cultural.services.patrol.ui.setup.aboutus;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.allenliu.versionchecklib.core.AllenChecker;
import com.allenliu.versionchecklib.core.VersionParams;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.databinding.ActivityUpdateVersionBinding;

public class UpdateVersionActivity  extends AppCompatActivity {

    public static UpdateVersionActivity updateVersionActivity;

    ActivityUpdateVersionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_version);
        updateVersionActivity = this;
    }


    public void updateVersion(View view){
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

        AllenChecker.startVersionCheck(getApplication(), builder.build());

    }

    public void cancleUpdate(View view){
        AllenChecker.cancelMission();
    }
}
