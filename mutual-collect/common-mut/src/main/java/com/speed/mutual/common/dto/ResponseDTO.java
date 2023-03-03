package com.speed.mutual.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 响应封装
 */
@Data
@ApiModel(value="响应")
public class ResponseDTO<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "是否成功")
    private Boolean success = true;
    @ApiModelProperty(value = "状态码")
    private Integer code=200;
    @ApiModelProperty(value = "提示语")
    private String msg="操作成功";
    @ApiModelProperty(value = "结果")
    private T data;
}
