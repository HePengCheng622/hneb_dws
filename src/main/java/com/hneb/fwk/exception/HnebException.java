package com.hneb.fwk.exception;

import com.hneb.ResultEnum;

/**
 * Created by Administrator on 2017/10/9.
 */
public class HnebException extends RuntimeException {
    private int code;

    public HnebException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        super.printStackTrace();
        this.code= resultEnum.getCode();
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {

        return code;
    }
}
