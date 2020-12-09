package com.newpi.data.converter;

import com.alibaba.fastjson.JSONObject;

import javax.persistence.AttributeConverter;
import java.util.List;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/11/22 11:32 PM
 * @desc:
 */
public class JpaListJsonConverter implements AttributeConverter<List, String> {

    @Override
    public String convertToDatabaseColumn(List attribute) {
        return JSONObject.toJSONString(attribute);
    }

    @Override
    public List convertToEntityAttribute(String dbData) {
        return JSONObject.parseObject(dbData, List.class);
    }
}
