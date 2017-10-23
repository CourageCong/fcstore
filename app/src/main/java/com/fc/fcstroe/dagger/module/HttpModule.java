package com.fc.fcstroe.dagger.module;

import android.app.Activity;
import android.app.Application;
import com.fc.fcstroe.BuildConfig;
import com.fc.fcstroe.common.http.CommonParamsInterceptor;
import com.fc.fcstroe.common.rx.RxErrorHandler;
import com.fc.fcstroe.data.model.AppModel;
import com.fc.fcstroe.data.net.ApiServer;
import com.google.gson.Gson;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */
@Module
public class HttpModule {

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Application application,Gson gson){



        // log用拦截器
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        //okhttplog  https://github.com/ihsanbal/LoggingInterceptor
        LoggingInterceptor interceptor = new LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
                .request("Request")
                .response("Response")
                .addHeader("version", BuildConfig.VERSION_NAME)
//              .logger(new Logger() {
//                  @Override
//                  public void log(int level, String tag, String msg) {
//                      Log.w(tag, msg);
//                  }
//              })
                .build();

        // 开发模式记录整个body，否则只记录基本信息如返回200，http协议版本等
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // 如果使用到HTTPS，我们需要创建SSLSocketFactory，并设置到client
//        SSLSocketFactory sslSocketFactory = null;

        return new OkHttpClient.Builder()
                // HeadInterceptor实现了Interceptor，用来往Request Header添加一些业务相关数据，如APP版本，token信息
//                .addInterceptor(new HeadInterceptor())
                .addInterceptor(new CommonParamsInterceptor(application,gson))
                .addInterceptor(interceptor)
                // 连接超时时间设置
                .connectTimeout(10, TimeUnit.SECONDS)
                // 读取超时时间设置
                .readTimeout(10, TimeUnit.SECONDS)
                .build();


    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient){


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ApiServer.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient);


        return builder.build();

    }

    @Provides
    @Singleton
    public ApiServer provideApiS0erver(Retrofit retrofit){

        return retrofit.create(ApiServer.class);

    }

    @Provides
    @Singleton
    public RxErrorHandler provideRxErrorHandler(Application mApplication){
        return new RxErrorHandler(mApplication);
    }

    @Provides
    @Singleton
    public AppModel provideAppModel(ApiServer apiServer){
        return new AppModel(apiServer);
    }
}
