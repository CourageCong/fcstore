package com.fc.fcstroe.ui.fragment.super_fragment;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fc.fcstroe.R;
import com.fc.fcstroe.common.constant.Constant;
import com.fc.fcstroe.data.bean.AppInfo;
import com.fc.fcstroe.data.bean.PageBean;
import com.fc.fcstroe.presenter.AppInfoPresenter;
import com.fc.fcstroe.presenter.contract.IAppInfoConract;
import com.fc.fcstroe.ui.activity.AppDetailActivity;
import com.fc.fcstroe.ui.adapter.AppInfoAdapter;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/10/8 0008.
 */

public abstract class BaseAppInfoFragment extends ProgressFragment<AppInfoPresenter> implements IAppInfoConract.AppInfoView,BaseQuickAdapter.RequestLoadMoreListener{

    @BindView(R.id.recyleAppInfo)
    RecyclerView mRecyleAppInfo;

    private AppInfoAdapter appAdapter;


    protected int page = 0;

    @Override
    public void onEmptyViewClick() {
        presenter.requestData(setType(), page,0,0);
    }

    @Override
    public void init() {

        initRecyclerView();
        presenter.requestData(setType(), page,0,0);

    }

    /**
     * 设置网络数据对应的 -00--AppInfoPresenter.type
     * */
    public abstract int setType();

    protected void initRecyclerView() {
        mRecyleAppInfo.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyleAppInfo.setItemAnimator(new DefaultItemAnimator());
        appAdapter = buildAdapter();
        appAdapter.setOnLoadMoreListener(this,mRecyleAppInfo);//设置上拉加载更多
        mRecyleAppInfo.setAdapter(appAdapter);
        //item点击事件
        appAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mApplication.setView(view);

                AppInfo appInfo = (AppInfo) adapter.getItem(position);

                Intent intent = new Intent(getActivity(), AppDetailActivity.class);
                intent.putExtra(Constant.APP_INFO_DETAIL, appInfo);
                startActivity(intent);
            }
        });
    }

    public abstract AppInfoAdapter buildAdapter();

    @Override
    public int setLayoutId() {
        return R.layout.template_recyclerview;
    }




    @Override
    public void showResult(PageBean<AppInfo> info) {
        appAdapter.addData(info.getDatas());

        if(info.isHasMore()){}{
            page++;
        }

        appAdapter.setEnableLoadMore(info.isHasMore());
    }

    @Override
    public void onLoadMoreComplete() {
        appAdapter.loadMoreComplete();//加载更多完成
    }

    @Override
    public void showError(String msg) {
        super.showError(msg);
        Toast.makeText(mContext, "服务器开小差了~" + msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onLoadMoreRequested() {
        presenter.requestData(setType(), page,0,0);
    }
}
