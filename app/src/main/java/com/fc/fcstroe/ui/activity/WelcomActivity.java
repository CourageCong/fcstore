package com.fc.fcstroe.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.eftimoff.androipathview.PathView;
import com.fc.fcstroe.R;
import com.fc.fcstroe.common.util.ACache;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomActivity extends AppCompatActivity {


    @BindView(R.id.pathView)
    PathView mPathView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        startSvg();
    }

    public void startSvg(){
        mPathView.getPathAnimator()
                .delay(100)
                .duration(2000)
                .listenerEnd(new PathView.AnimatorBuilder.ListenerEnd() {
                    @Override
                    public void onAnimationEnd() {
                        jump();

                    }
                })
                .interpolator(new AccelerateDecelerateInterpolator())
                /*.listenerStart(new AnimationListenerStart())
                .listenerEnd(new AnimationListenerEnd())
                .interpolator(new AccelerateDecelerateInterpolator())*/
                .start();


    }

    private void jump() {
        ACache mCache = ACache.get(this);
        String start = mCache.getAsString(getString(R.string.key_first_start));

        if (null == start) {
            startActivity(new Intent(this, GuideActivity.class));
        }else{
            startActivity(new Intent(WelcomActivity.this, HomeActivity.class));

        }

        this.finish();

    }
}
