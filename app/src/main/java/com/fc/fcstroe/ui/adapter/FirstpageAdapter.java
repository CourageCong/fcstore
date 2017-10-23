package com.fc.fcstroe.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fc.fcstroe.R;
import com.fc.fcstroe.data.bean.AppInfo;
import com.fc.fcstroe.data.net.ApiServer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 推荐页adapter
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class FirstpageAdapter extends RecyclerView.Adapter<FirstpageAdapter.ViewHolder> {

    private Context mContext;
    private List<AppInfo> mAppInfos;
    private LayoutInflater mLayoutInflater;

    public FirstpageAdapter(Context context, List<AppInfo> infos) {

        this.mContext = context;
        this.mAppInfos = infos;
        mLayoutInflater = LayoutInflater.from(context);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mLayoutInflater.inflate(R.layout.app_info, parent, false);//注意第三个参数

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        AppInfo info = mAppInfos.get(position);

        holder.mAppNameTxt.setText(info.getDisplayName());
        holder.mAppSize.setText(info.getApkSize()/1024/1024+"M");
        Glide.with(mContext).load(ApiServer.IMAGE_URL + info.getIcon()).into(holder.mAppImgSmall);
//        Glide.with(mContext).load("http://mpic.tiankong.com/ecc/3e3/ecc3e349338dbe58603cf270d9cd7c9c/640.jpg?x-oss-process=image/resize,m_lfit,h_600,w_600/watermark,image_cXVhbmppbmcucG5n,t_90,g_ne,x_5,y_5").into(holder.mAppImgSmall);

    }

    @Override
    public int getItemCount() {
        return mAppInfos.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.app_img_small)
        ImageView mAppImgSmall;
        @BindView(R.id.app_download_btn)
        Button mAppDownloadBtn;
        @BindView(R.id.app_name_txt)
        TextView mAppNameTxt;
        @BindView(R.id.app_size)
        TextView mAppSize;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
