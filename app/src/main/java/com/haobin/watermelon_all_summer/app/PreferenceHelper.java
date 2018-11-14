package com.haobin.watermelon_all_summer.app;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Wenghaobin
 * on 2018/11/12
 * for 设置管理
 */
public class PreferenceHelper {
    private final SharedPreferences mPreferences;

    public PreferenceHelper() {
        mPreferences = App.getInstance().getSharedPreferences(Constants.SP, Context.MODE_PRIVATE);
    }

    public boolean getNightModeState() {
        return mPreferences.getBoolean(Constants.NIGHT_MODE_STATE, false);
    }

    public void setNightModeState(boolean b) {
        mPreferences.edit().putBoolean(Constants.NIGHT_MODE_STATE, b).apply();
    }
}
