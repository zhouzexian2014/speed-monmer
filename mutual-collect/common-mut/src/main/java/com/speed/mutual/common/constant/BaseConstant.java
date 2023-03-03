package com.speed.mutual.common.constant;

/**
 * 基础常量
 * @author joey
 */
public class BaseConstant {

    //时间格式化
    public static  final String DATE_FORMAT1 = "yyyy/MM/dd";
    public static final String DATE_FORMAT2 = "yyyy/MM/dd HH:mm:ss";
    public static final String DATE_FORMAT3="yyyy-MM-dd";
    public static final String DATE_FORMAT4="yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT5="yyyyMMddHHmmss";
    public static final String ENTITY_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String ENTITY_DATE_FORMAT2 = "uuuu/MM/dd HH:mm:ss";
    //字符串分割
    public static final char SPITS_CHAR1 = ',';
    public static final String SPITS_STR1=",";
    public static final String SPITS_STR2="/";
    public static final String SPITS_STR3="_";
    public static final String SPITS_STR4=".";
    public static final String SPITS_STR5="%";
    public static final String STR_EMPTY="";
    //操作类型 新增删除修改
    public static final String OPT_CREATE="create";
    public static final String OPT_UPDATE="update";
    public static final String OPT_REMOVE="remove";
    //字段翻译类型
    public static final String DIC_TYPE_D="dic";
    public static final String DIC_TYPE_PSN="psn";
    public static final String DIC_TYPE_GROUP="group";
    //
    public static final int INT_0=0;
    public static final int INT_1=1;
    public static final int INT_2=2;
    public static final String STR_0="0";
    public static final String STR_1="1";
    public static final String TRUE_STR = "true";
    public static final String FALSE_STR = "false";
    //分页
    public static final long MAX_PAGE_SIZE=999;
    public static final long INIT_PAGE_NO=1;
    public static final long INIT_PAGE_SIZE=10;
    //分页
    public static final int MAX_PAGE_SIZE_ES=999;
    public static final int INIT_PAGE_NO_ES=0;
    public static final int INIT_PAGE_SIZE_ES=10;
    //授权
    public static final String HEADER_TOKEN="Authorization";
    public static final String TOKEN_BEARER="Bearer";
    public static final String SUPER_ADMIN="SUPER_ADMIN";
    public static final String GROUP="GROUP";
    public static final String ROLE="ROLE";
    public static final String TRACT_ID="tractId";
    public static final String AUTH_PWD = "auth_pwd_";
    //拦截类型
    public static String FILTER_TYPE_PRE="pre";
    public static String FILTER_TYPE_ERROE="error";
    //流程
    public static final String FLOW_EVENT_TYPE_TASK = "TASK_";
    public static final String FLOW_EVENT_TYPE_PROCESS = "PROCESS_";
    public static final String FLOW_HANDLE_BEAN_NAME = "FlowableEventHandleImpl";

}
