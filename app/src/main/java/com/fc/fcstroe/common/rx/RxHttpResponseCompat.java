package com.fc.fcstroe.common.rx;

import android.util.Log;

import com.fc.fcstroe.common.exception.ApiException;
import com.fc.fcstroe.data.bean.AppInfo;
import com.fc.fcstroe.data.bean.BaseBean;
import com.fc.fcstroe.data.bean.PageBean;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static io.reactivex.Observable.create;

/**
 * 数据转化  切换线程
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */
// TODO: 2017-08-21  
public class RxHttpResponseCompat {
    private static final String TAG = "RxHttpResponseCompat";

//    public static <S> ObservableTransformer<BaseBean, BaseBean> compatResult() {
    public static <T> ObservableTransformer<BaseBean<T>, T> compatResult() {
        return new ObservableTransformer<BaseBean<T>, T>() {
            @Override
            public ObservableSource apply(@NonNull Observable<BaseBean<T>> upstream) {


                return upstream.flatMap(new Function<BaseBean<T>, ObservableSource<T>>() {

                    @Override
                    public ObservableSource<T> apply(@NonNull final BaseBean<T> tBaseBean) throws Exception {

                        if (tBaseBean.isSuccess()) {

                            return Observable.create(new ObservableOnSubscribe<T>() {
                                @Override
                                public void subscribe(@NonNull ObservableEmitter<T> e) throws Exception {
                                    try {
                                        e.onNext((T) tBaseBean.getData());
                                        e.onComplete();
                                    } catch (Exception e1) {
                                        Log.e(TAG, "subscribe: "+e);
                                        e.onError(e1);
                                    }
                                }
                            });

                        } else {
                            return Observable.error(new ApiException(tBaseBean.getStatus(), tBaseBean.getMessage()));
                        }

                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


}
