package com.newpi.data.model;

import com.newpi.data.enums.ResultCode;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/11/26 5:25 PM
 * @desc:
 */
@Data
@Accessors(chain = true)
public class Header {

    private int code = ResultCode.SUCCESS.getCode();

    private String message = ResultCode.SUCCESS.getMessage();


    public Header() {
    }

    public Header(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Header(int code) {
        this.code = code;
        this.message = "";
    }

    public Header(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }
}
