package com.newpi.data.exception;

import com.newpi.data.enums.ResultCode;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/11/28 5:02 PM
 * @desc:
 */
//TODO 需要进一步细化异常种类
@Data
@Accessors(chain = true)
public class WhitzardException extends RuntimeException {

    private ResultCode errorCode = ResultCode.ERROR;

    public WhitzardException(ResultCode errorCode) {
        super();
        this.errorCode = errorCode;
    }


    public WhitzardException(ResultCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }

}
