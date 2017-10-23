package com.fc.fcstroe.ui.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.fc.fcstroe.R;
import com.fc.fcstroe.common.constant.Constant;
import com.fc.fcstroe.common.util.DensityUtil;
import com.fc.fcstroe.dagger.component.ApComponent;
import com.fc.fcstroe.data.bean.AppInfo;
import com.fc.fcstroe.ui.fragment.AppDetailFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppDetailActivity extends BaseActivity {


    @BindView(R.id.activity_app_detail)
    LinearLayout mActivityAppDetail;
    @BindView(R.id.frame_content)
    FrameLayout mFrameContent;


    private View view;
    private AppInfo info;

    @Override
    public int setLayoutId() {
        return R.layout.activity_app_detail;
    }

    @Override
    public void setComponent(ApComponent component) {

    }

    @Override
    public void init() {

        showTransAnimin();
        getData();
        //// TODO: 2017-10-12 应该在动画结束后加载fragment
        initFragment(info.getId());

    }

    /**
     * 获取数据
     */
    private void getData() {
        Intent intent = getIntent();
        info = (AppInfo) intent.getSerializableExtra(Constant.APP_INFO_DETAIL);
    }


    /**
     * 设置转场动画
     */
    private void showTransAnimin() {
        view = mApplication.getView();

        //从view中获取对应视图的两种方式，第二种可以继续操作
        /*Bitmap bitmap = getViewImageCache(view);

        if (bitmap != null) {
            mImgTxt.setImageBitmap(bitmap);
        }*/

        int location[] = new int[2];
        view.getLocationOnScreen(location);
        int locationLeft = location[0];
        int locationTop = location[1];

        //反射  获取状态栏高度
        int height = DensityUtil.getStatusBarH(this);

        //添加view
        ViewGroup group = (ViewGroup) view.getParent();
        group.removeView(view);
        mFrameContent.addView(view);

        //设置view的位置
        LinearLayout.MarginLayoutParams params = (LinearLayout.LayoutParams) mFrameContent.getLayoutParams();
        params.leftMargin = locationLeft;
        params.topMargin = locationTop - height;
        mFrameContent.setLayoutParams(params);

//        FrameLayout.LayoutParams params1 = (FrameLayout.LayoutParams) mScaleView.getLayoutParams();
//        params1.height = view.getHeight();
//        params1.width = FrameLayout.LayoutParams.MATCH_PARENT;
//        mScaleView.setLayoutParams(params1);

//        openAnim(mScaleView);
    }

    /**
     * 垂直方向放大到全屏
     */
    private void openAnim(View v) {
        float height = DensityUtil.getScreenH(this);
        float sacale = height / view.getHeight();

        ObjectAnimator animator = ObjectAnimator.ofFloat(v, "scaleY", 1.0f, sacale);
        animator.setDuration(2 * 1000);
        animator.start();

    }

    /**
     * 从view中获取对应bitmap
     */
    private Bitmap getViewImageCache(View view) {

        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();

        if (bitmap == null) {
            return null;
        }

        // TODO: 2017-10-12 重新创建的意义
        bitmap = Bitmap.createBitmap(bitmap);

        //销毁缓存
        view.destroyDrawingCache();
        return bitmap;

    }

    private void initFragment(int id) {

        Fragment fragment = new AppDetailFragment(id);
        FragmentManager mManager = getSupportFragmentManager();
        mManager.beginTransaction().replace(R.id.frame_content, fragment).commit();

    }

}
