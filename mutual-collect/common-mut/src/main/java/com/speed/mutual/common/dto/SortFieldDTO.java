package com.speed.mutual.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("排序对象")
public class SortFieldDTO {
    @ApiModelProperty("排序字段")
    private String fieldName;
    @ApiModelProperty("排序方向ASC/DESC")
    private String direction;
}
