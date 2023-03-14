package com.speed.mutual.webjpa.utils;

/**
 * 自动生成表字段属性-兼容多数据库扩展
 */
public class DbDefinition {
    private static String DB_TYPE="mysql";
    private static String DB_MYSQL="mysql";


    /** 整型 */
    public static String numInt(String desc,int size,int def){
        String columnDefinition = "";
        if(DB_MYSQL.equals(DB_TYPE)){
            //int(11) default 0 comment '整数'
            columnDefinition=" int("+size+") default "+def+" comment '"+desc+"' ";
        }
        return columnDefinition;
    }
    /** 整型 size=11 */
    public static String numInt(String desc,int def){
        return numInt(desc,11,def);
    }
    /** 整型  size=11 def=1 */
    public static String numInt1(String desc){
        return numInt(desc,1);
    }
    /** 整型  size=11 def=0 */
    public static String numInt0(String desc){
        return numInt(desc,0);
    }
    /** 小数 */
    public static String numDouble(String desc,int size1,int size2,double def){
        String columnDefinition = "";
        if(DB_MYSQL.equals(DB_TYPE)){
            //double(11,2) default 0.0 comment '小数'
            columnDefinition=" double("+size1+","+size2+") default "+def+" comment '"+desc+"' ";
        }
        return columnDefinition;
    }
    /** 小数 size=11,2 def=0.0 */
    public static String numDouble(String desc){
        return numDouble(desc,11,2,0.0);
    }
    /** 字符串 */
    public static String varchar2(String desc,int size,String def){
        String columnDefinition = "";
        if(DB_MYSQL.equals(DB_TYPE)){
            //varchar(64) default '' comment '字符串'
            columnDefinition=" varchar("+size+") default '"+def+"' comment '"+desc+"' ";
        }
        return columnDefinition;
    }
    /** 字符串 def="" */
    public static String varchar2(String desc,int size){
        return varchar2(desc,size,"");
    }
    /** 字符串 size=64 def="" */
    public static String varchar2(String desc){
        return varchar2(desc,64,"");
    }
    /** 时间 */
    public static String datetime(String desc){
        String columnDefinition = "";
        if(DB_MYSQL.equals(DB_TYPE)){
            //datetime comment '时间'
            columnDefinition=" datetime comment '"+desc+"' ";
        }
        return columnDefinition;
    }
    /** 布尔 */
    public static String bit(String desc,int def){
        String columnDefinition = "";
        if(DB_MYSQL.equals(DB_TYPE)){
            //bit(1) default b'0' comment '布尔'
            columnDefinition=" bit(1) default b'"+def+"' comment '"+desc+"' ";
        }
        return columnDefinition;
    }
    /** 布尔 def=0 */
    public static String bit0(String desc){
        return bit(desc,0);
    }
    /** 布尔 def=1 */
    public static String bit1(String desc){
        return bit(desc,1);
    }
}
