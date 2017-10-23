package com.fc.fcstroe.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fc.fcstroe.R;
import com.fc.fcstroe.common.constant.Constant;
import com.fc.fcstroe.common.imageloader.ImageLoader;
import com.fc.fcstroe.common.util.ACache;
import com.fc.fcstroe.common.util.UT;
import com.fc.fcstroe.dagger.component.ApComponent;
import com.fc.fcstroe.data.bean.LoginBean;
import com.fc.fcstroe.data.bean.User;
import com.fc.fcstroe.ui.adapter.HomePageAdaper;
import com.fc.fcstroe.ui.widget.BannerLayout;

import butterknife.BindView;

import static com.fc.fcstroe.common.constant.Constant.LOGIN_REQUEST;

public class HomeActivity extends BaseActivity {


    @BindView(R.id.activity_home_tab)
    TabLayout mActivityHomeTab;
    @BindView(R.id.activity_home_viewPager)
    ViewPager mActivityHomeViewPager;
    @BindView(R.id.activity_home)
    DrawerLayout mActivityHome;
    @BindView(R.id.navigation_home)
    NavigationView navigation_home;

    private TextView mLoginTextView;
    private ImageView mHeadImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void setComponent(ApComponent component) {

    }

    @Override
    public void init() {

        initPager();
        initDarawer();
        initUser();
    }

    private void initPager() {
        PagerAdapter adapter = new HomePageAdaper(getSupportFragmentManager());
        mActivityHomeViewPager.setAdapter(adapter);
        mActivityHomeViewPager.setOffscreenPageLimit(4);
        mActivityHomeTab.setupWithViewPager(mActivityHomeViewPager);
    }

    public void initDarawer() {
        mLoginTextView = (TextView) navigation_home.getHeaderView(0).findViewById(R.id.txt_login_home_head);
        mHeadImageView = (ImageView) navigation_home.getHeaderView(0).findViewById(R.id.img_home_head);
        navigation_home.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_logout:
                        loginOut();
                        break;
                }
                return true;
            }
        });
    }

    /**
     * 退出登录
     * */
    private void loginOut(){
        ACache aCache = ACache.get(this);
        aCache.put(Constant.TOKEN, "");
        aCache.put(Constant.USER, "");
        initUser();
        UT.show("退出登录");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LOGIN_REQUEST && resultCode == RESULT_OK) {
            LoginBean bean = (LoginBean) data.getSerializableExtra(Constant.USER_RESULT);
            initUserView(bean.getUser());
        }
    }

    private void initUser() {
        Object user = ACache.get(this).getAsObject(Constant.USER);
        if (user == null) {
            mLoginTextView.setText(R.string.unlogin);
            mHeadImageView.setImageResource(R.mipmap.guide_1);
            mLoginTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivityForResult(new Intent(HomeActivity.this, LoginActivity.class), LOGIN_REQUEST);
                }
            });
        } else {
            if (user instanceof User) {
                initUserView((User) user);
            }
        }
    }

    /**
     * 初始化侧滑菜单的用户头像和名字
     */
    private void initUserView(User user) {

        String url = user.getLogo_url();

        if (!TextUtils.isEmpty(url)) {
               /* Glide.with(this)
                        .load(url)
                        .transform(new)

                        .into(mHeadImageView);*/
            ImageLoader.load(url, mHeadImageView);
            mLoginTextView.setText(user.getUsername());
        }
    }
}
