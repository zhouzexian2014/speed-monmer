package com.speed.module.organize.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.speed.mutual.webjpa.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * <p>
 * 角色信息
 * </p>
 *
 * @author Joey
 * @since 2022-03-23
 */
@Data
@ColumnWidth(25)
@ApiModel("角色信息")
@Entity
@Table(
        name = "role",
        indexes = {
                @Index(name = "idx_role_key",columnList = "roleKey",unique = true),
                @Index(name = "idx_role_name",columnList = "roleName",unique = true)
        }
)
@org.hibernate.annotations.Table(appliesTo = "role",comment="角色信息")
public class Role extends BaseEntity {

    @Column(nullable = false,columnDefinition = "varchar(128) comment '角色名称'")
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @Column(nullable = false,columnDefinition = "varchar(64) comment '角色Key'")
    @ApiModelProperty(value = "角色Key")
    private String roleKey;

    @Column(nullable = false,columnDefinition = "int(2) DEFAULT 0 comment '角色类型'")
    @ApiModelProperty(value = "角色类型")
    private Integer roleType;

    @ApiModelProperty(value = "备注")
    @Column(columnDefinition = "varchar(128) DEFAULT '' comment '备注'")
    private String remarks;

    @Column(columnDefinition = "int(11) DEFAULT 1 comment '排序编号'")
    @ExcelProperty(value = "排序编号")
    @ApiModelProperty(value = "排序编号")
    private Integer orderNo;

    @Column(nullable = false,columnDefinition = "bit(1) DEFAULT b'0' comment '是否禁用'")
    @ApiModelProperty(value = "是否禁用")
    private boolean hadCancel;

}
