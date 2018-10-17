package com.haobin.watermelon_all_summer.utils;

import android.annotation.SuppressLint;
import android.widget.Toast;

import com.haobin.watermelon_all_summer.app.App;

/**
 * Created by Wenghaobin
 * on 2018/10/16
 * for 单例Toast
 */

public class ToastUtils {

    private static Toast mToast;

    @SuppressLint("ShowToast")
    public static void showToast(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(App.getInstance(), text, Toast.LENGTH_SHORT);
        } else {
            mToast.cancel();
            mToast = Toast.makeText(App.getInstance(), text, Toast.LENGTH_SHORT);
        }
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setText(text);
        mToast.show();
    }

    @SuppressLint("ShowToast")
    public static void showToastLong(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(App.getInstance(), text, Toast.LENGTH_LONG);
        } else {
            mToast.cancel();
            mToast = Toast.makeText(App.getInstance(), text, Toast.LENGTH_LONG);
        }
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.setText(text);
        mToast.show();
    }

}
