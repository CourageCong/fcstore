package com.fc.fcstroe.ui.fragment;

import android.app.ProgressDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.fc.fcstroe.R;
import com.fc.fcstroe.dagger.component.ApComponent;
import com.fc.fcstroe.dagger.component.DaggerRecommendCoponent;
import com.fc.fcstroe.dagger.module.RecommendModule;
import com.fc.fcstroe.data.bean.AppInfo;
import com.fc.fcstroe.data.bean.IndexBean;
import com.fc.fcstroe.data.net.ApiServer;
import com.fc.fcstroe.presenter.RecommendPresenter;
import com.fc.fcstroe.presenter.contract.IAppInfoConract;
import com.fc.fcstroe.ui.adapter.FirstpageAdapter;
import com.fc.fcstroe.ui.adapter.IndexMultiAdapter;
import com.fc.fcstroe.ui.fragment.super_fragment.ProgressFragment;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class RecommendFragment extends ProgressFragment<RecommendPresenter> implements IAppInfoConract.View {


    @Inject
    ProgressDialog mProgressDialog;

    @Inject
    ApiServer mApiServer;


    private static final String TAG = "MainActivity";
    @BindView(R.id.recyleAppInfo)
    RecyclerView mRecyleAppInfo;


    @Override
    public int setLayoutId() {
        return R.layout.template_recyclerview;
    }

    @Override
    public void setComponent(ApComponent component) {
        //dagger注入

        DaggerRecommendCoponent.builder()
                .apComponent(component)
                .recommendModule(new RecommendModule(this))
                .build()
                .inject(this);

        //DaggerRecommendCoponent.create(); //简略写法
    }

    @Override
    public void init() {
        //利用rxPermission动态请求
//        presenter.requestTestData();
        initNewRecyclerView();
        presenter.requestDatas();// TODO: 2017/10/8 0008
        //先请求权限
        presenter.requestPermission();

        //尝试请求用来获取json串
       /* mApiServer.getFistPage().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.e(TAG, "onResponse: "+response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });*/
    }

    @Override
    public void onEmptyViewClick() {
        presenter.requestDatas();
    }

    private void initRecycleView(List<AppInfo> infos) {
        FirstpageAdapter adapter = new FirstpageAdapter(mContext, infos);

        mRecyleAppInfo.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyleAppInfo.setItemAnimator(new DefaultItemAnimator());
        mRecyleAppInfo.setAdapter(adapter);


    }

    @Override
    public void showNoData() {
        Toast.makeText(mContext, "暂时无数据", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String msg) {
        super.showError(msg);
        Toast.makeText(mContext, "服务器开小差了" + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showResult(List<AppInfo> infos) {
        initRecycleView(infos);
    }

    @Override
    public void showNewResult(IndexBean indexBean) {

        adapter.setData(indexBean);
        mRecyleAppInfo.setAdapter(adapter);
    }

    IndexMultiAdapter adapter;



    private void initNewRecyclerView() {

        adapter = new IndexMultiAdapter(getActivity());

        mRecyleAppInfo.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyleAppInfo.setItemAnimator(new DefaultItemAnimator());


    }

    @Override
    public void onRequestPermissionSuccess() {
        presenter.requestDatas();
    }

    @Override
    public void onRequestPermissionError() {
        Toast.makeText(mContext, "您拒绝授权", Toast.LENGTH_SHORT).show();
    }

   /* @Override
    public void showLoading() {
        mProgressDialog.show();
    }

    @Override
    public void dismissLoading() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }*/
}
