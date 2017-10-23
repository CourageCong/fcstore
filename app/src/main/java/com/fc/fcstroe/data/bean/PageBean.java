package com.fc.fcstroe.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 用来处理需要分页的数据
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class PageBean<T>{

    /**
     * hasMore : false
     * datas : []
     * status : 1
     * message : success
     */

    private boolean hasMore;
//    private int status;
//    private String message;
    private List<T> datas;

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
}
