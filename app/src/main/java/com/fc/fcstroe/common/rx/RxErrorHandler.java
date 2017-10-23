package com.fc.fcstroe.common.rx;

import android.content.Context;
import android.widget.Toast;


import com.fc.fcstroe.common.exception.ApiException;
import com.fc.fcstroe.common.exception.BaseException;
import com.fc.fcstroe.common.exception.ErrorMessageFactory;
import com.google.gson.JsonParseException;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava2.HttpException;


/**
 * 封装异常
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class RxErrorHandler {


    private Context mContext;

    public RxErrorHandler(Context context){

        this.mContext = context;
    }

    public BaseException handleError(Throwable e){

        BaseException exception = new BaseException();

        if(e instanceof ApiException){

            exception.setCode(((ApiException)e).getCode());

        }
        else if (e instanceof JsonParseException){
            exception.setCode(BaseException.JSON_ERROR);
        }
        else  if(e instanceof HttpException){

            exception.setCode(((HttpException)e).code());
        }
        else  if(e instanceof SocketTimeoutException){

            exception.setCode(BaseException.SOCKET_TIMEOUT_ERROR);
        }
        else if(e instanceof SocketException){

            exception.setCode(BaseException.SOCKET_ERROR);
        }
        else {

            exception.setCode(BaseException.UNKNOWN_ERROR);

        }

        exception.setDisplayMessage(ErrorMessageFactory.create(mContext,exception.getCode()));

        return  exception;
    }

    public void  showErrorMessage(BaseException e){


        Toast.makeText(mContext,e.getDisplayMessage(),Toast.LENGTH_LONG).show();

    }
}
