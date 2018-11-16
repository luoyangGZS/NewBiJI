package com.example.luoyangcomputer.newbiji;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

public class WelcomeActivity extends Activity {
        private Handler handler = new Handler()
        {
            public void handleMessage(Message paramAnonymousMessage)
            {
                if (paramAnonymousMessage.what == 1)
                {
                    WelcomeActivity.this.startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    WelcomeActivity.this.finish();
                }
            }
        };
        private RelativeLayout rl_welcome_root;

    private void showAnimation() {
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(2000);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(2000);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(2000);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        this.rl_welcome_root.startAnimation(animationSet);
    }

    protected void onCreate(Bundle paramBundle)
        {
            super.onCreate(paramBundle);
            setContentView(R.layout.activity_welcome);
            this.rl_welcome_root = ((RelativeLayout)findViewById(R.id.rl_wecome_root));
            //showAnimation();
            this.handler.sendEmptyMessageDelayed(1, 1000L);
        }
    }
