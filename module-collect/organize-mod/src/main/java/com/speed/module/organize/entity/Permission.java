package com.speed.module.organize.entity;

import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.speed.mutual.webjpa.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * <p>
 * 权限信息
 * </p>
 *
 * @author Joey
 * @since 2022-03-23
 */
@Data
@ColumnWidth(25)
@ApiModel("权限信息")
@Entity
@Table(
        name = "permission",
        indexes = {
                @Index(name = "idx_Permission_name",columnList = "permissionName",unique = true),
                @Index(name = "idx_Permission_key",columnList = "permissionKey",unique = true)
        }
)
@org.hibernate.annotations.Table(appliesTo = "permission",comment="权限信息")
public class Permission extends BaseEntity {

    @Column(nullable = false,columnDefinition = "varchar(64) comment '权限名称'")
    @ApiModelProperty(value = "权限名称")
    private String permissionName;

    @Column(nullable = false,columnDefinition = "varchar(64) comment '权限key'")
    @ApiModelProperty(value = "权限Key")
    private String permissionKey;

    @Column(nullable = false,columnDefinition = "int(2) DEFAULT 0 comment '权限类型'")
    @ApiModelProperty(value = "权限类型")
    private Integer permissionType;

}
