package com.speed.module.organize.form;

import com.speed.mutual.common.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="登录-密码模式from")
public class PwdLoginForm extends BaseForm {
    @ApiModelProperty(value = "账号",example = "admin")
    private String username;
    @ApiModelProperty(value = "密码",example = "***")
    private String password;
}
