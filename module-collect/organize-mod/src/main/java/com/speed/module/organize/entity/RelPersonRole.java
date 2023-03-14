package com.speed.module.organize.entity;

import com.speed.mutual.webjpa.bean.IdEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * <p>
 * 用户角色关系表
 * </p>
 *
 * @author Joey
 * @since 2022-03-23
 */
@Data
@ApiModel("用户角色关系表")
@Entity
@Table(
        name = "rel_person_role",
        indexes = {
                @Index(name = "idx_RelPersonRole_pr",columnList = "personId,roleId",unique = true)
        }
)
@org.hibernate.annotations.Table(appliesTo = "rel_person_role",comment="用户角色关系表")
public class RelPersonRole extends IdEntity {

    @Column(nullable = false,columnDefinition = "varchar(64) comment '用户id'")
    @ApiModelProperty(value = "用户id")
    private String personId;

    @Column(nullable = false,columnDefinition = "varchar(64) comment '角色id'")
    @ApiModelProperty(value = "角色id")
    private String roleId;


}
