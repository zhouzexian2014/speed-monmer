package com.speed.module.organize.entity;

import com.speed.mutual.webjpa.bean.IdEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * <p>
 * 角色权限关系表
 * </p>
 *
 * @author Joey
 * @since 2022-03-23
 */
@Data
@ApiModel("角色权限关系表")
@Entity
@Table(
        name = "rel_role_permission",
        indexes = {
                @Index(name = "idx_RelRolePermission_rp",columnList = "roleId,permissionId",unique = true)
        }
)
@org.hibernate.annotations.Table(appliesTo = "rel_role_permission",comment="角色权限关系表")
public class RelRolePermission extends IdEntity {

    @Column(nullable = false,columnDefinition = "varchar(64) comment '角色id'")
    @ApiModelProperty(value = "角色id")
    private String roleId;

    @Column(nullable = false,columnDefinition = "varchar(64) comment '权限id'")
    @ApiModelProperty(value = "权限id")
    private String permissionId;


}
