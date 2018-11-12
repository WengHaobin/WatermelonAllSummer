package com.haobin.watermelon_all_summer.module.main.view;

import android.animation.Animator;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.haobin.watermelon_all_summer.R;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.router.Router;


/**
 * Created by Wenghaobin
 * on 2018/10/9
 * for 启动页
 */
public class SplashActivity extends XActivity {

    @BindView(R.id.animation_splash)
    LottieAnimationView animationSplash;

    @Override
    public void initData(Bundle savedInstanceState) {
        //开始动画
        startAnimation();

        //动画状态监听，完成了就跳转主页
        animationSplash.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                goToMain();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void goToMain(){
        Router.newIntent(context).to(MainActivity.class).launch();
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public Object newP() {
        return null;
    }

    private void startAnimation() {
        animationSplash.setAnimation("splash.json");
        animationSplash.playAnimation();
    }

    private void cancelAnimation() {
        if (animationSplash != null) {
            animationSplash.cancelAnimation();
        }
    }

    @Override
    protected void onDestroy() {
        cancelAnimation();
        super.onDestroy();
    }
}
