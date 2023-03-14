package com.speed.module.organize.entity;

import com.speed.mutual.webjpa.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * <p>
 * 部门用户关系
 * </p>
 *
 * @author Joey
 * @since 2022-09-18
 */
@Data
@ApiModel("部门用户关系表")
@Entity
@Table(
        name = "rel_person_dept",
        indexes = {
                @Index(name = "idx_RelPersonDept_dp",columnList = "deptId,personId",unique = true)
        }
)
@org.hibernate.annotations.Table(appliesTo = "rel_person_dept",comment="部门用户关系表")
public class RelPersonDept extends BaseEntity {

    @Column(nullable = false,columnDefinition = "varchar(64) comment '部门id'")
    @ApiModelProperty(value = "部门id")
    private String deptId;

    @Column(nullable = false,columnDefinition = "varchar(64) comment '用户id'")
    @ApiModelProperty(value = "用户id")
    private String personId;




}
