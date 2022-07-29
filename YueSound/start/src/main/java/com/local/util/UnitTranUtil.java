package com.local.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hhs
 * @create 2022-07-01 11:42
 */
public class UnitTranUtil {
    // bip--u9计量单位转换规则
    public static Map<String,String> getUnitMap(){
        Map<String,String> map=new HashMap<>();
        map.put("L005","L005");//码(长度)
        map.put("L006","L006");//千米(长度)
        map.put("L007","L007");//米(长度)
        map.put("A001","A001");//平方米(面积)
        map.put("01","01");//张(数量)
        map.put("N001","N001");//张(数量)
        map.put("F040","F040");//个(财务)
        map.put("0101","0101");//令(500张/令)
        map.put("0102","0102");//张(500张/令)
        map.put("0201","0201");//公斤(1000KG/T)
        map.put("0202","0202");//吨(1000KG/T)
        map.put("0301","0301");//包(12包/箱)
        map.put("0302","0302");//箱(12包/箱)
        map.put("Day","Day");//天
        map.put("Hour","Hour");//小时
        map.put("Minute","Minute");//分
        map.put("Second","Second");//秒
        return map;
    }

}
