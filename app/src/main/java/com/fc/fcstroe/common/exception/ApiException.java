package com.fc.fcstroe.common.exception;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class ApiException extends BaseException{


    public ApiException(int code, String displayMsg) {
        super(code, displayMsg);
    }
}
