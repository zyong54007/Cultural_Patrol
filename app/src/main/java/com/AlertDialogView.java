package com;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.zhjy.cultural.services.patrol.R;


/**
 * Created by jialg on 2017/7/25.
 */

public class AlertDialogView {
    private Context context;

    public AlertDialogView(Context context) {
        this.context = context;
    }

    ;

    public void alertHitInfo(final String status, String toastshow) {
        final AlertDialog alertDialog = new AlertDialog.Builder(context, R.style.My_Theme_Dialog_Alert).create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable());
        window.setContentView(R.layout.dialog_hints_info);
        TextView tv_title = (TextView) window.findViewById(R.id.tv_dialog_title);
        //tv_title.setText(" 提示 ");
        TextView tv_message = (TextView) window.findViewById(R.id.tv_dialog_message);
        tv_message.setText(toastshow);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if (alertDialog != null && alertDialog.isShowing()) {
                    alertDialog.dismiss();
                    if (null != status && status.equals("1")) {
                        ((Activity) context).finish();
                    }
                }
            }
        }, 1000);
    }

    public void alertFinish(final String status, String toastshow) {
        final AlertDialog alertDialog = new AlertDialog.Builder(context, R.style.My_Theme_Dialog_Alert).create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable());
        window.setContentView(R.layout.dialog_cancle_info);
        TextView tv_title = (TextView) window.findViewById(R.id.tv_dialog_title);
        tv_title.setText("提示");
        TextView tv_message = (TextView) window.findViewById(R.id.tv_dialog_message);
        tv_message.setText(toastshow);
        tv_message.setTextColor(0xff434343);
        Button cancleBtn = (Button) window.findViewById(R.id.cancle_btn);
        cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        Button okBtn = (Button) window.findViewById(R.id.ok_btn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                if (null != status && status.equals("1")) {
                    ((Activity) context).finish();
                }
            }
        });
    }

    public void alertPhoneInfo(final String status, final String toastshow) {
        final AlertDialog alertDialog = new AlertDialog.Builder(context, R.style.My_Theme_Dialog_Alert).create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable());
        window.setContentView(R.layout.dialog_phone_info);
        TextView tv_message = (TextView) window.findViewById(R.id.tv_dialog_message);
        tv_message.setText(toastshow);
        tv_message.setTextColor(0xff434343);
        Button cancleBtn = (Button) window.findViewById(R.id.cancle_btn);
        cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        Button okBtn = (Button) window.findViewById(R.id.ok_btn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("tel:" + toastshow);
                Intent intent = new Intent(Intent.ACTION_CALL, uri);
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    context.startActivity(intent);
                }
                alertDialog.dismiss();
                if (null != status && status.equals("1")) {
                    ((Activity) context).finish();
                }
            }
        });
    }
}
