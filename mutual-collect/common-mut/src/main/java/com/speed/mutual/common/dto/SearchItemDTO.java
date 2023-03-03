package com.speed.mutual.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="字段搜索-item")
public class SearchItemDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "字段名")
    private String field;
    @ApiModelProperty(value = "字段值")
    private String value;
    @ApiModelProperty(value = "连接类型，如like、eq、gt、lt")
    private String compare;
    @ApiModelProperty(value = "字段类型string、date")
    private String fieldType;
}
