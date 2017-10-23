package com.fc.fcstroe.common.util;

import android.app.Activity;
/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class PermissionUtil {




    public static void readPhonestate(Activity activity){



       /* requestPermisson(activity,Manifest.permission.READ_PHONE_STATE).subscribe(new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Boolean aBoolean) {

            }
        });*/

//        RxPermissions rxPermissions = new RxPermissions(activity);
//        rxPermissions.request(Manifest.permission.READ_PHONE_STATE)
//                .subscribe(new Subscriber<Boolean>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(Boolean aBoolean) {
//
//                    }
//                });

    }


    /*public static Observable<Boolean> requestPermisson(Activity activity, String permission){


        RxPermissions rxPermissions = new RxPermissions(activity);


        return rxPermissions.request(permission);
    }

    public static Observable.Transformer<Object, Boolean> ensure(Activity activity, String permission){

        RxPermissions rxPermissions = new RxPermissions(activity);

       return  rxPermissions.ensure(permission);

    }*/



}
