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
 * 用户信息
 * </p>
 *
 * @author Joey
 * @since 2022-03-23
 */
@Data
@ApiModel("用户信息")
@ColumnWidth(25)
@Entity
@Table(
        name = "person",
        indexes = {
                @Index(name = "idx_Person_name",columnList = "username",unique = true)
        }
)
@org.hibernate.annotations.Table(appliesTo = "person",comment="用户信息")
public class Person extends BaseEntity {

    @Column(nullable = false,columnDefinition = "varchar(128) comment '分类名称'")
    @ExcelProperty(value = "昵称")
    @ApiModelProperty(value = "昵称")
    private String nickname;

    @Column(columnDefinition = "varchar(64) DEFAULT '' comment '分类名称'")
    @ExcelProperty(value = "手机号")
    @ApiModelProperty(value = "手机号")
    private String phone;

    @Column(columnDefinition = "varchar(128) DEFAULT '' comment '邮箱'")
    @ExcelProperty(value = "邮箱")
    @ApiModelProperty(value = "邮箱")
    private String email;

    @Column(nullable = false,columnDefinition = "varchar(64) comment '账号'")
    @ExcelProperty(value = "帐号")
    @ApiModelProperty(value = "帐号")
    private String username;

    @Column(nullable = false,columnDefinition = "varchar(128) comment '密码'")
    @ExcelProperty(value = "密码")
    @ApiModelProperty(value = "密码")
    private String password;

    @Column(columnDefinition = "varchar(64) DEFAULT '' comment '组织id'")
    @ApiModelProperty(value = "主组织id")
    private String companyId;

    @Column(columnDefinition = "varchar(64) DEFAULT '' comment '部门id'")
    @ApiModelProperty(value = "主部门id")
    private String deptId;

    @Column(nullable = false,columnDefinition = "bit(1) DEFAULT b'0' comment '是否禁用'")
    @ApiModelProperty(value = "是否禁用")
    private boolean hadCancel;

}
