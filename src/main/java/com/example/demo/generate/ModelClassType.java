package com.example.demo.generate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author 35716 <a href="xiaopeng.miao@1hai.cn">Contact me.</a>
 * @version 1.0
 * @since 2020/08/19 17:06
 * desc : 获取对应关系
 */
public enum ModelClassType {
    object("object","Object"),
    string("string","String"),
    integer("int32","Integer"),
    integer1("integer","Integer"),
    Long("int64","Long"),
    //list 需要特殊处理 加上类型
    array("array","List"),
    Boolean("boolean","Boolean"),
    number("number","BigDecimal");
    private String sName;
    private String stringClassName;

    ModelClassType(String sName,String stringClassName) {
        this.sName=sName;
        this.stringClassName = stringClassName;
    }
    public static String getClassName(String stringName) {
         Optional<ModelClassType> first = Arrays.stream(values()).filter(item -> item.sName.equals(stringName)).findFirst();
        if (first.isPresent()){
            return first.get().stringClassName;
        }else if(stringName==null){
            return string.stringClassName;
        }
        return stringName;
    }

    public static boolean isBodyType(String stringName){
        List<ModelClassType> modelClassTypes = Arrays.asList(values());
        for (ModelClassType modelClassType : modelClassTypes) {
            if(stringName.equals(modelClassType.sName)){
                   if(modelClassType.stringClassName.equals("List") || modelClassType.stringClassName.equals("Object")){
                       return true;
                   }else {
                       continue;
                   }
            }
        }
        return false;
    }

    public static boolean isArray(String target){
        return array.sName.equals(target);
    }
    public static boolean isList(String target){
        return array.stringClassName.equals(target);
    }
}
