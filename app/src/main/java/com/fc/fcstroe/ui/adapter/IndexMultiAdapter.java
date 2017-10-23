package com.fc.fcstroe.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fc.fcstroe.R;
import com.fc.fcstroe.common.imageloader.ImageLoader;
import com.fc.fcstroe.data.bean.Banner;
import com.fc.fcstroe.data.bean.IndexBean;
import com.fc.fcstroe.ui.widget.BannerLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class IndexMultiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    public static final int TYPE_BANNER = 0;
    public static final int TYPE_ICON = 1;
    public static final int TYPE_APPS = 2;
    public static final int TYPE_GAMES = 3;



    private LayoutInflater mLayoutInflater;

    private IndexBean mIndexBean;

    private Context mContext;

    public IndexMultiAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    public void setData(IndexBean indexBean) {
        this.mIndexBean = indexBean;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_BANNER) {
            return new BannerViewHolder(mLayoutInflater.inflate(R.layout.template_banner, parent, false));
        } else if (viewType == TYPE_ICON) {
            return new NavIconViewHolder(mLayoutInflater.inflate(R.layout.template_nav_icon, parent, false));
        } else if (viewType == TYPE_APPS) {
            // TODO: 2017-08-22 传递null时，会重新测量，不然的话recyclerview的高度无法测量
            return new AppViewHolder(mLayoutInflater.inflate(R.layout.template_recyleview_with_title, null, false),TYPE_APPS);
        } else if (viewType == TYPE_GAMES) {
            return new AppViewHolder(mLayoutInflater.inflate(R.layout.template_recyleview_with_title, null, false),TYPE_GAMES);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //可以通过viewholder的类型判断也可以通过position进行判断

        if (holder instanceof BannerViewHolder) {
            BannerViewHolder holder1 = (BannerViewHolder) holder;

            List<Banner> banners = mIndexBean.getBanners();
//            List<String> urls = new ArrayList<>(banners.size());//固定大小，避免浪费资源
            List<String> urls = new ArrayList<>();

            for (Banner banner : banners) {

                urls.add(banner.getThumbnail());
            }

            holder1.mBanner.setViewUrls(urls);

            holder1.mBanner.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    // TODO: 2017-08-22 banner点击事件
                }
            });

        } else if (holder instanceof NavIconViewHolder) {
            NavIconViewHolder navIconViewHolder = (NavIconViewHolder) holder;
            navIconViewHolder.mLayoutHotApp.setOnClickListener(this);
            navIconViewHolder.mLayoutHotGame.setOnClickListener(this);
            navIconViewHolder.mLayoutHotSubject.setOnClickListener(this);


        } else if (holder instanceof AppViewHolder) {

            AppViewHolder navIconViewHolder = (AppViewHolder) holder;

            AppInfoAdapter appAdapter = AppInfoAdapter.builder()
                    .showBrief(true)
                    .showCategoryName(true)
                    .showPosition(true)
                    .build();
            appAdapter.openLoadAnimation();
            appAdapter.isFirstOnly(false);
            if (navIconViewHolder.type == TYPE_APPS) {
                navIconViewHolder.mText.setText(R.string.hot_apps);
                appAdapter.addData(mIndexBean.getRecommendApps());

            }else{
                navIconViewHolder.mText.setText(R.string.hot_games);
                appAdapter.addData(mIndexBean.getRecommendGames());

            }
            navIconViewHolder.mRecyclerView.setAdapter(appAdapter);

            /*navIconViewHolder.mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
                @Override
                public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                }
            });*/
            appAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Toast.makeText(mContext, "aaaaa", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return 4;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_BANNER;
        } else if (position == 1) {
            return TYPE_ICON;
        } else if (position == 2) {
            return TYPE_APPS;
        } else if (position == 3) {
            return TYPE_GAMES;
        } else {
            return TYPE_GAMES;
        }

    }

    @Override
    public void onClick(View v) {

    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.banner)
        BannerLayout mBanner;

        public BannerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mBanner.setImageLoader(new ImgLoader());
        }
    }

    class NavIconViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.layout_hot_app)
        LinearLayout mLayoutHotApp;
        @BindView(R.id.layout_hot_game)
        LinearLayout mLayoutHotGame;
        @BindView(R.id.layout_hot_subject)
        LinearLayout mLayoutHotSubject;

        public NavIconViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class AppViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text)
        TextView mText;
        @BindView(R.id.recycler_view)
        RecyclerView mRecyclerView;

        int type;

        public AppViewHolder(View itemView,int type) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            this.type = type;

            initRecyclerView();
        }

        private void initRecyclerView() {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext){
                // TODO: 2017-08-22 不允许进行滚动
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
        }


    }



    class ImgLoader implements BannerLayout.ImageLoader {

        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            ImageLoader.load(path, imageView);//加载图片
        }
    }


}
