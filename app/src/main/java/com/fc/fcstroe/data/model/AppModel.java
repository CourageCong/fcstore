package com.fc.fcstroe.data.model;

import com.fc.fcstroe.data.bean.AppInfo;
import com.fc.fcstroe.data.bean.BaseBean;
import com.fc.fcstroe.data.bean.IndexBean;
import com.fc.fcstroe.data.bean.PageBean;
import com.fc.fcstroe.data.net.ApiServer;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observable;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class AppModel {

    private static final String TAG = "AppModel";

    private ApiServer mApiServer;


    public AppModel(ApiServer mApiServer) {
        this.mApiServer = mApiServer;
    }
    //测试
    public Observable<PageBean<AppInfo>> getApps(){

        JSONObject object = new JSONObject();
        try {
            object.put("page", 0);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = object.toString();

        return mApiServer.getApps(url);//.enqueue(callback)
    }

    /**
     * 获取推荐页数据
     * */
    public Observable<BaseBean<IndexBean>> getRecommendPage(){
        return mApiServer.getFist();
    }

    /**
     * 获取排行页数据  PageBean用来处理需要分页的数据
     * */
    public Observable<BaseBean<PageBean<AppInfo>>> getRank(int page){
        return mApiServer.getRankList(page);
    }

    /**
     * 获取排行页数据  PageBean用来处理需要分页的数据
     * */
    public Observable<BaseBean<PageBean<AppInfo>>> getGame(int page){
        return mApiServer.getGameList(page);
    }

    /**
     * 获取分类详情页数据  PageBean用来处理需要分页的数据
     */
    public Observable<BaseBean<PageBean<AppInfo>>> getategorieDetial(String type, int categoryId, int page) {

        return mApiServer.getategorieDetial(type, categoryId, page);
    }


}
