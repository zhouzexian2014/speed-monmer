package com.speed.module.organize.entity;

import com.speed.mutual.webjpa.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * <p>
 * 部门权限关系表
 * </p>
 *
 * @author Joey
 * @since 2022-09-18
 */
@Data
@ApiModel("部门权限关系表")
@Entity
@Table(
        name = "rel_dept_permission",
        indexes = {
                @Index(name = "idx_RelDeptPermission_dp",columnList = "deptId,permissionId",unique = true)
        }
)
@org.hibernate.annotations.Table(appliesTo = "rel_dept_permission",comment="部门权限关系表")
public class RelDeptPermission extends BaseEntity {

    @Column(nullable = false,columnDefinition = "varchar(64) comment '部门id'")
    @ApiModelProperty(value = "部门id")
    private String deptId;

    @Column(nullable = false,columnDefinition = "varchar(64) comment '权限id'")
    @ApiModelProperty(value = "权限id")
    private String permissionId;




}
