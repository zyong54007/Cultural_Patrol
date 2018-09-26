package com.zhjy.cultural.services.patrol.util;

import android.widget.Toast;

import com.zhjy.cultural.services.patrol.app.AppContext;

public class ToastUtil {
    public static void toastLong(int msgRes) {
        //toastLong(GEMApplication.Companion.get_instane().getString(msgRes));
        showToast(msgRes);
    }

    public static void toastLong(String msg) {
        //  Toast.makeText(GEMApplication.Companion.get_instane(), msg, Toast.LENGTH_SHORT).show();
        showToastMsg(msg);
    }

    public static Toast mToast = null;

    public static void showToast(int msgRes) {
        if (mToast == null) {
            mToast = Toast.makeText(AppContext.getInstance(), AppContext.getInstance().getString(msgRes), Toast.LENGTH_SHORT);
        } else {
            mToast.setText(AppContext.getInstance().getString(msgRes));
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void showToastMsg(String msgRes) {
        if (mToast == null) {
            mToast = Toast.makeText(AppContext.getInstance(), msgRes, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msgRes);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    /**
     * 关闭Toast
     */
    public static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}

