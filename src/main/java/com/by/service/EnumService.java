package com.by.service;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class EnumService {
    //ProductStatus
    @SneakyThrows
    public JSONArray select(String enumClassName) {
        //通过反射获取到当前枚举类
        JSONArray array = new JSONArray();
        Class<?> enumClass = Class.forName("com.by.enums." + enumClassName);
        Object[] enumConstants = enumClass.getEnumConstants();
        for (Object enumConstant : enumConstants) {
            Object code = ReflectUtil.getFieldValue(enumConstant, "code");
            Object message = ReflectUtil.getFieldValue(enumConstant, "message");
            JSONObject object= new JSONObject();
            object.put("code",code);
            object.put("message",message);
            array.add(object);
        }
        return array;
    }
}
