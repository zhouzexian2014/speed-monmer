package com.speed.module.system.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.speed.mutual.webjpa.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
@Table(name = "menu")
@org.hibernate.annotations.Table(appliesTo = "menu",comment="菜单信息表")
public class Menu extends BaseEntity {

    @Column(nullable = false,columnDefinition = "varchar(64) comment '父ID'")
    @ApiModelProperty(value = "父ID")
    private String pid;

    @Column(nullable = false,columnDefinition = "varchar(64) comment '菜单名称'")
    @ExcelProperty(value = "菜单名称")
    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @Column(columnDefinition = "varchar(64) comment '编号'")
    @ExcelProperty(value = "编号")
    @ApiModelProperty(value = "编号")
    private String menuNo;

    @Column(columnDefinition = "varchar(128) comment '扩展项1'")
    @ApiModelProperty(value = "扩展项1")
    private String extend1;

    @Column(columnDefinition = "varchar(128) comment '扩展项2'")
    @ApiModelProperty(value = "扩展项2")
    private String extend2;

    @Column(columnDefinition = "varchar(128) comment '扩展项3'")
    @ApiModelProperty(value = "扩展项3")
    private String extend3;


}
