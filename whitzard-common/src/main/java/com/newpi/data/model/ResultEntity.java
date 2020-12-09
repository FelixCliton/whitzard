package com.newpi.data.model;

import com.newpi.data.enums.ResultCode;
import lombok.Getter;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/11/23 10:37 AM
 * @desc:
 */
@Getter
public class ResultEntity<T> {

    private Header header = new Header(ResultCode.SUCCESS);

    private T data;

    public ResultEntity(ResultCode resultCode, T data) {
        this.header = new Header(resultCode);
        this.data = data;
    }

    public ResultEntity(Header header, T data) {
        this.header = header;
        this.data = data;
    }

    public ResultEntity() {
    }

    public ResultEntity(T data) {
        if (data instanceof ResultCode) {
            ResultCode code = (ResultCode) data;
            this.header = new Header(code.getCode(), code.getMessage());
        } else {
            this.data = data;
        }
    }
}
