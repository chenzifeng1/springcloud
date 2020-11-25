package com.czf.program.programpractice.utils;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Enumeration;

/**
 * @ProjectName: program-practice
 * @Package: com.czf.program.programpractice.utils
 * @ClassName: JsonUtils
 * @Author: czf
 * @Description: 用来处理Json （fastJson）的工具类
 * @Date: 2020/11/20 16:18
 * @Version: 1.0
 */

public class JsonUtils {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    public static <T> T jsonToObject(JSONObject jsonObject,Class<T> clazz){
        try {
            T t = clazz.newInstance();
            for (Field field : clazz.getDeclaredFields()) {
                //拿到属性值并转为属性对应的类型
                field.getType().cast(jsonObject.get(field.getName()));

            }
            return t;
        } catch (InstantiationException e) {
            logger.error("json对象实例化失败",e);
        } catch (IllegalAccessException e) {
            logger.error("json对象转换失败",e);
        }catch (ClassCastException e){

        }

        return null;
    }


    /**
     * 将request参数值转为json
     */
    public static JSONObject request2Json(HttpServletRequest request) {
        JSONObject requestJson = new JSONObject();
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            String[] pv = request.getParameterValues(paramName);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < pv.length; i++) {
                if (pv[i].length() > 0) {
                    if (i > 0) {
                        sb.append(",");
                    }
                    sb.append(pv[i]);
                }
            }
            requestJson.put(paramName, sb.toString());
        }
        return requestJson;
    }

}
