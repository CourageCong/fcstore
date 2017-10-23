package com.fc.fcstroe.data.net;

import com.fc.fcstroe.data.bean.AppInfo;
import com.fc.fcstroe.data.bean.BaseBean;
import com.fc.fcstroe.data.bean.Category;
import com.fc.fcstroe.data.bean.IndexBean;
import com.fc.fcstroe.data.bean.LoginBean;
import com.fc.fcstroe.data.bean.PageBean;
import com.fc.fcstroe.data.bean.requestbean.LoginRequestBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public interface ApiServer {

    String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";

    String IMAGE_URL = "http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";

    @GET("featured")
    Observable<PageBean<AppInfo>> getApps(@Query("p")String json);

    //http://112.124.22.238:8081/course_api/cniaoplay/index?p={%22publicParams%22:{%22resolution%22:%22720*1280%22,%22sdk%22:%2223%22,%22la%22:%22zh%22,%22densityScaleFactor%22:%222.0%22,%22os%22:%22V8.1.5.0.MALCNDI%22,%22model%22:%22Redmi%203S%22}}
    //首页
    @GET("index")
    Observable<BaseBean<IndexBean>> getFist();
//    Call<ResponseBody> getFistPage();

    //排行榜
    @GET("toplist")
    Observable<BaseBean<PageBean<AppInfo>>> getRankList(@Query("page") int page);

    //游戏game
    @GET("game")
    Observable<BaseBean<PageBean<AppInfo>>> getGameList(@Query("page") int page);

//    @Headers("Content-Type: application/json")
//    @POST("login")
//    Call<ResponseBody> login(@Body RequestBody requestBean);
    //登录
    @POST("login")
    Observable<BaseBean<LoginBean>> login(@Body LoginRequestBean requestBean);
//    Call<ResponseBody> login(@Body LoginRequestBean requestBean);

    //分类
    @GET("category")
    Observable<BaseBean<List<Category>>> getCategories();

    //分类详细列表
    @GET("category/{type}/{categoryId}")
    Observable<BaseBean<PageBean<AppInfo>>> getategorieDetial(@Path("type") String type, @Path("categoryId") int categoryid, @Query("page") int page);

    //app详情页
    @GET("app/{id}")
    Observable<BaseBean<AppInfo>> getAppDetail(@Path("id") int id);
}
