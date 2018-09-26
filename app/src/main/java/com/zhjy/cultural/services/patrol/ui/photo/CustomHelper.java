package com.zhjy.cultural.services.patrol.ui.photo;

import android.net.Uri;
import android.os.Environment;
import android.view.View;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.model.TakePhotoOptions;

import java.io.File;

import javax.inject.Inject;


/**
 * Created by jialg on 2018/1/11.
 */

public class CustomHelper {

    @Inject
    CustomHelper(){
    }

    public void takePhotoCall(View view,TakePhoto takePhoto) {
        File file=new File(Environment.getExternalStorageDirectory(), "/temp/"+System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        Uri imageUri = Uri.fromFile(file);
        //configCompress(takePhoto);
        takePhoto.onEnableCompress(null,false);
        configTakePhotoOption(takePhoto);
        takePhoto.onPickFromCapture(imageUri);
    }

    public void takeImagesCall(View view,TakePhoto takePhoto){
        configTakePhotoOption(takePhoto);
        takePhoto.onPickMultiple(4);
        //takePhoto.onPickFromGallery();

    }

    private void configTakePhotoOption(TakePhoto takePhoto){
        TakePhotoOptions.Builder builder=new TakePhotoOptions.Builder();
        builder.setWithOwnGallery(true);
        builder.setCorrectImage(true);
        takePhoto.setTakePhotoOptions(builder.create());

    }
}
