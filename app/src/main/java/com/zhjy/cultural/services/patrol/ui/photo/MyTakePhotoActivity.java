package com.zhjy.cultural.services.patrol.ui.photo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.app.InjectHelp;
import com.zhjy.cultural.services.patrol.databinding.ActivityTakePhotoBinding;
import com.zhjy.cultural.services.patrol.ui.base.PhotoBaseActivity;

import java.util.ArrayList;

import javax.inject.Inject;


public class MyTakePhotoActivity  extends PhotoBaseActivity<ActivityTakePhotoBinding> {

    @Inject
    CustomHelper customHelper;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_take_photo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectHelp.appComponent().inject(this);
    }

    public void takePhotos(View view){
        customHelper.takePhotoCall(view,getTakePhoto());
    }

    public void takeImages(View view){
        customHelper.takeImagesCall(view,getTakePhoto());
    }



    @Override
    public void takeCancel() {
        super.takeCancel();
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        showImg(result.getImages());
    }

    private void showImg(ArrayList<TImage> images) {
        Intent intent=new Intent(this, ResultActivity.class);
        intent.putExtra("images",images);
        startActivity(intent);
    }


}
