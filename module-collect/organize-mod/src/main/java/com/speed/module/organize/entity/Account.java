package com.speed.module.organize.entity;

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
 * 用户信息
 * </p>
 *
 * @author Joey
 * @since 2022-03-23
 */
@Data
@ApiModel("账号信息")
@ColumnWidth(25)
@Entity
@Table(
        name = "account",
        indexes = {
                @Index(name = "idx_Account_name",columnList = "username",unique = true),
                @Index(name = "idx_Account_psnId",columnList = "personId",unique = true)
        }
)
@org.hibernate.annotations.Table(appliesTo = "person",comment="账号信息")
public class Account extends BaseEntity {

    @Column(nullable = false,columnDefinition = "varchar(64) comment '用户id'")
    @ApiModelProperty(value = "用户id")
    private String personId;

    @Column(nullable = false,columnDefinition = "varchar(64) comment '账号'")
    @ExcelProperty(value = "帐号")
    @ApiModelProperty(value = "帐号")
    private String username;

    @Column(nullable = false,columnDefinition = "varchar(128) comment '密码'")
    @ExcelProperty(value = "密码")
    @ApiModelProperty(value = "密码")
    private String password;

}
