package com.hneb;

/**
 * Created by Administrator on 2017/10/9.
 */
public enum  ResultEnum {
    //错误代码为4位，前两位为模块代码，后两位为错误代码
    //00：系统级别错误
    UNKONW_ERROR(0,"未知错误"),
    SUCCESS(1,"成功"),

    SYS_MISS_PAGE_QUERY_PARAMETER(000001,"分页参数异常，请确认参数中含有_pageNum和_pageSize且类型为int型"),
    SYS_SPRING_SECURITY(000002,"spring security获取用户查询异常"),

    RECIPE_NO_DELETE_AUTH(001001,"只能删除自己创建的菜单")
    ;
    private String msg;
    private Integer code;

    ResultEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }
}
