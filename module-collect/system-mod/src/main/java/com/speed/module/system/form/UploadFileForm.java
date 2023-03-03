package com.speed.module.system.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("文件上传form")
public class UploadFileForm {
    @ApiModelProperty(value = "关联标识")
    private String relationKey;
    @ApiModelProperty(value = "关联类型0=其他1=规程文件")
    private Integer relationType;
}
