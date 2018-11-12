package com.haobin.watermelon_all_summer.app;

/**
 * Created by Wenghaobin
 * on 2018/11/12
 * for
 */
public interface PreferrnceHelper {
    /**
     * Get night mode state
     * @return if is night mode
     */
    boolean getNightModeState();

    /**
     * Set night mode state
     * @param b current night mode state
     */
    void setNightModeState(boolean b);
}
