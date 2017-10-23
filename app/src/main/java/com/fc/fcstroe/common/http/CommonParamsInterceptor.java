package com.fc.fcstroe.common.http;

import android.content.Context;

import com.fc.fcstroe.common.constant.Constant;
import com.fc.fcstroe.common.util.ACache;
import com.fc.fcstroe.common.util.DensityUtil;
import com.fc.fcstroe.common.util.DeviceUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class CommonParamsInterceptor implements Interceptor {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");// TODO: 2017-08-21

    private Gson mGson;
    private Context mContext;

    public CommonParamsInterceptor(Context context ,Gson gson) {
        this.mGson = gson;
        this.mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        try {
            String method = request.method();

            HashMap<String, Object> commomParamsMap = new HashMap<String, Object>();
// TODO: 2017-10-11
//        commomParamsMap.put(Constant.IMEI, DeviceUtils.getIMEI(mContext));
            commomParamsMap.put(Constant.MODEL,DeviceUtils.getModel());
            commomParamsMap.put(Constant.LANGUAGE,DeviceUtils.getLanguage());
            commomParamsMap.put(Constant.os,DeviceUtils.getBuildVersionIncremental());
            commomParamsMap.put(Constant.RESOLUTION, DensityUtil.getScreenW(mContext)+"*" + DensityUtil.getScreenH(mContext));
            commomParamsMap.put(Constant.SDK,DeviceUtils.getBuildVersionSDK()+"");
            commomParamsMap.put(Constant.DENSITY_SCALE_FACTOR,mContext.getResources().getDisplayMetrics().density+"");

            String token = ACache.get(mContext).getAsString(Constant.TOKEN);
            //每一个api都可以传递token，自己判断是否需要
            commomParamsMap.put(Constant.TOKEN, token == null ? "" : token);

            HashMap<String, Object> rootMap = new HashMap<>();

            if(method.equals("GET")){

                HttpUrl httpUrl= request.url();

                Set<String> paramsNames = httpUrl.queryParameterNames();

                for (String paramName : paramsNames) {

                    //p = {}&page=
                    if(paramName.equals(Constant.PARAM)){
                        String oldParamsJson = httpUrl.queryParameter(Constant.PARAM);
                        if(oldParamsJson != null){
                            HashMap<String,Object> p = mGson.fromJson(oldParamsJson, HashMap.class);//原始参数

                            if (p != null) {
                                for (Map.Entry<String, Object> entry : p.entrySet()) {

                                    rootMap.put(entry.getKey(), entry.getValue());

                                }
                            }
                        }
                    }else{

                        rootMap.put(paramName,httpUrl.queryParameter(paramName));
                    }

                }


                rootMap.put("publicParams", commomParamsMap);//重新组装的参数
                String newParamsJson = mGson.toJson(rootMap); // {"page":0,"publicParams":{"imei":'xxxxx',"sdk":14,.....}}

                String url = httpUrl.toString();

                int index = url.indexOf("?");
                if (index > 0) {
                    url = url.substring(0,index);

                }
                //  http://112.124.22.238:8081/course_api/cniaoplay/featured?p= {"page":0,"publicParams":{"imei":'xxxxx',"sdk":14,.....}}
                url = url + "?" + Constant.PARAM + "=" + newParamsJson;

                request = request.newBuilder().url(url).build();

            } else if (method.equals("POST")) {

                RequestBody body = request.body();

                HashMap<String, Object> rootmap = new HashMap<>();

                if (body instanceof FormBody) {

                    for(int i = 0 ; i < ((FormBody) body).size();i++) {

                        rootmap.put(((FormBody) body).encodedName(i), ((FormBody) body).encodedValue(i));


                    }

                }else{//json

                    Buffer buffer = new Buffer();
                    body.writeTo(buffer);

                    String oldParams = buffer.readUtf8();

                    rootmap = mGson.fromJson(oldParams, HashMap.class);

                    rootmap.put("publicParams",commomParamsMap); // 重新组装
                    String newJsonParams = mGson.toJson(rootmap); // {"page":0,"publicParams":{"imei":'xxxxx',"sdk":14,.....}}


                    request = request.newBuilder().post(RequestBody.create(JSON, newJsonParams)).build();

                }

            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return chain.proceed(request);
    }
}
