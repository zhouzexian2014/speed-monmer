package com.speed.mutual.common.utils;
import cn.hutool.core.util.ArrayUtil;
import io.swagger.annotations.ApiModelProperty;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldUtils {

    /**
     * 获取类的属性-包括父类
     * @param c
     * @return
     */
    public static Field[] getFields(Class c){
        Field[] fields = c.getDeclaredFields();
        Class superclass = c.getSuperclass();
        if(superclass!=null && !superclass.getSimpleName().equals("Object")){
            return ArrayUtil.addAll(fields, getFields(superclass));
        }
        return fields;
    }

    /**
     * 构造Bootstrap table 的Columns属性
     * @param c
     * @return
     */
    public static List getColumns(Class c){
        Field[] fields = getFields(c);
        List fieldList = new ArrayList();
        for(Field field : fields){
            Map<String,Object> item = new HashMap<>();
            ApiModelProperty annotation = field.getAnnotation(ApiModelProperty.class);
            //TableField tableField = field.getAnnotation(TableField.class);
            item.put("type", field.getType().getSimpleName());
            if(annotation!=null){
                //item.put("sqlField", tableField==null?DevUtils.humpToLine2(field.getName()):tableField.value());
                item.put("field", field.getName());
                item.put("title", annotation.value());
//                BsField bsField = field.getAnnotation(BsField.class);
//                if(bsField!=null){
//                    item.put("type", bsField.type());
//                    item.put("length", bsField.maxLength());
//                    item.put("ignore", bsField.ignore());
//                }
                fieldList.add(item);
            }
        }
        return fieldList;
    }
}
