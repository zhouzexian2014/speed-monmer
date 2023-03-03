package com.speed.module.system.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.speed.mutual.webjpa.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;

/**
 * <p>
 * 菜单信息
 * </p>
 *
 * @author Joey
 * @since 2022-09-27
 */

@Data
@ApiModel(value="菜单信息")
@ColumnWidth(25)
@Entity
public class Menu extends BaseEntity {

    @ApiModelProperty(value = "父id")
    private String pid;

    @ExcelProperty(value = "菜单名称")
    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ExcelProperty(value = "编号")
    @ApiModelProperty(value = "编号")
    private String menuNo;

    @ApiModelProperty(value = "扩展项1")
    private String extend1;

    @ApiModelProperty(value = "扩展项2")
    private String extend2;

    @ApiModelProperty(value = "扩展项3")
    private String extend3;


}
