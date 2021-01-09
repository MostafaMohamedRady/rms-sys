package com.rms.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

public class CommonUtil {

    public static <T> T convertJsonToObject(String content, Class<T> valueType) {
        try {
            if (StringUtils.isEmpty(content)) {
                return null;
            }
            return new ObjectMapper().readValue(content, valueType);
        } catch (Exception e) {
            return null;
        }
    }

    public static String convertObjectToJsonString(Object obj) {
        try {
            if (obj == null) {
                return null;
            }
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
