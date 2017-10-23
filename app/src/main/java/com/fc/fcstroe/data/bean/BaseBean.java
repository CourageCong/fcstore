package com.fc.fcstroe.data.bean;

import java.io.Serializable;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class BaseBean<T> implements Serializable {

    private int status;
    private String message;
    private T data;

    public static final int SUCCESS = 1;

    public boolean isSuccess(){
        return SUCCESS == status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
