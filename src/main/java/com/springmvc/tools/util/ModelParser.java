package com.springmvc.tools.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletRequest;

import org.apache.commons.lang.StringUtils;

import com.springmvc.tools.annotation.CardHolderParameterKeyName;

public class ModelParser {
    public static Object parseHttpParametersToServletRequestModel(ServletRequest severletRequest, Object model) {
        try {
            List<Field> fieldArray = getFieldList(model);
            for (Field field : fieldArray) {
                String keyOfParameter = generateNameValueEntryByField(field, model);
                if (keyOfParameter == null) {
                    continue;
                }
                final PropertyDescriptor pd = new java.beans.PropertyDescriptor(field.getName(), model.getClass());
                java.lang.reflect.Method setter = pd.getWriteMethod();
                setter.invoke(model, severletRequest.getParameter(keyOfParameter));
            }
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException();
        }
        return model;
    }

    private static List<Field> getFieldList(Object object) {
        List<Field> fieldArray = new ArrayList<Field>();
        for (Class<?> clazzTemp = object.getClass(); clazzTemp != null; clazzTemp = clazzTemp.getSuperclass()) {
            fieldArray.addAll(Arrays.asList(clazzTemp.getDeclaredFields()));
        }
        return fieldArray;
    }

    private static String generateNameValueEntryByField(Field field, Object object) {
        if (field.isAnnotationPresent(CardHolderParameterKeyName.class)
                && StringUtils.isNotEmpty(field.getAnnotation(CardHolderParameterKeyName.class).value())) {
            return field.getAnnotation(CardHolderParameterKeyName.class).value();
        } else {
            return null;
        }
    }
}
