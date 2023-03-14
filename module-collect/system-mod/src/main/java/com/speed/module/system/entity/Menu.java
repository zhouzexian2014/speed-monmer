package com.speed.module.system.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.speed.mutual.webjpa.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
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
@Table(name = "menu",
        indexes = {
            @Index(name = "idx_menu_key",columnList = "menuKey",unique = true),
                @Index(name = "idx_menu_pidName",columnList = "pid,menuName",unique = true)
        }
)
@org.hibernate.annotations.Table(appliesTo = "menu",comment="菜单信息表")
public class Menu extends BaseEntity {

    @Column(nullable = false,columnDefinition = "varchar(64) DEFAULT '0' comment '父ID'")
    @ApiModelProperty(value = "父ID")
    private String pid;

    @Column(nullable = false,columnDefinition = "varchar(64) comment '菜单名称'")
    @ExcelProperty(value = "菜单名称")
    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @Column(columnDefinition = "varchar(64) comment '菜单key'")
    @ExcelProperty(value = "菜单key")
    @ApiModelProperty(value = "菜单key")
    private String menuKey;

    @Column(columnDefinition = "varchar(128) DEFAULT '' comment '扩展项1'")
    @ApiModelProperty(value = "扩展项1")
    private String extend1;

    @Column(columnDefinition = "varchar(128) DEFAULT '' comment '扩展项2'")
    @ApiModelProperty(value = "扩展项2")
    private String extend2;

    @Column(columnDefinition = "varchar(128) DEFAULT '' comment '扩展项3'")
    @ApiModelProperty(value = "扩展项3")
    private String extend3;

    @Column(columnDefinition = "int(11) DEFAULT 1 comment '排序编号'")
    @ExcelProperty(value = "排序编号")
    @ApiModelProperty(value = "排序编号")
    private Integer orderNo;

    @Column(nullable = false,columnDefinition = "bit(1) DEFAULT b'0' comment '是否禁用'")
    @ApiModelProperty(value = "是否禁用")
    private boolean hadCancel;


}
