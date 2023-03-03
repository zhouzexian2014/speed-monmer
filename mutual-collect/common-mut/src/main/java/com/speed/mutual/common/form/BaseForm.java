package com.speed.mutual.common.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 基础form
 * @author joey
 */
@Data
@ApiModel(value="基础form")
public class BaseForm implements Serializable {
    private static final long serialVersionUID = 1L;
}
