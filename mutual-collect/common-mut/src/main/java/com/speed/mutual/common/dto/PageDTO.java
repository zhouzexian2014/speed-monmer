package com.speed.mutual.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页封装
 * @param <T>
 */
@Data
@ApiModel(value="分页")
public class PageDTO<T,V> implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "列表")
    private List<T> records;
    @ApiModelProperty(value = "总数")
    private V total;
    @ApiModelProperty(value = "总数")
    private V pages;


}
