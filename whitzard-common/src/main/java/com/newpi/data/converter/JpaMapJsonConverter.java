package com.newpi.data.converter;

import com.alibaba.fastjson.JSONObject;

import javax.persistence.AttributeConverter;
import java.util.Map;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/11/22 11:32 PM
 * @desc:
 */
public class JpaMapJsonConverter implements AttributeConverter<Map, String> {

    @Override
    public String convertToDatabaseColumn(Map attribute) {
        return JSONObject.toJSONString(attribute);
    }

    @Override
    public Map convertToEntityAttribute(String dbData) {
        return JSONObject.parseObject(dbData, Map.class);
    }
}
