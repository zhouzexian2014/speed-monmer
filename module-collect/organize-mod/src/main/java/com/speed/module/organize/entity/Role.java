package com.speed.module.organize.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.speed.mutual.webjpa.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

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

    @Column(nullable = false,columnDefinition = "int(2) default 0 comment '角色类型'")
    @ApiModelProperty(value = "角色类型")
    private Integer roleType;

    @ApiModelProperty(value = "备注")
    @Column(columnDefinition = "varchar(128) default '' comment '备注'")
    private String remarks;

}
