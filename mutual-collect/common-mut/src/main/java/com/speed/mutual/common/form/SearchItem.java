package com.speed.mutual.common.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="Mybatis检索项信息")
public class SearchItem {
    @ApiModelProperty(value = "字段名称")
    private String field;
    @ApiModelProperty(value = "字段值")
    private String value;
    @ApiModelProperty(value = "检索类型，默认为like",example = "like")
    private String compare;
}
