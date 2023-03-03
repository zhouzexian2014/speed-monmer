package com.speed.mutual.common.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页form
 * @author joey
 */
@Data
@ApiModel(value="分页form")
public class PageForm extends BaseForm {
    @ApiModelProperty(value = "当前页，从1开始",example = "1")
    private Integer pageNo=1;
    @ApiModelProperty(value = "每页数量",example = "10")
    private Integer pageSize=10;

}
