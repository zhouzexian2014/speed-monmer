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
        name = "person"
)
@org.hibernate.annotations.Table(appliesTo = "person",comment="用户信息")
public class Person extends BaseEntity {

    @Column(nullable = false,columnDefinition = "varchar(128) comment '分类名称'")
    @ExcelProperty(value = "昵称")
    @ApiModelProperty(value = "昵称")
    private String nickname;

    @Column(columnDefinition = "varchar(64) default '' comment '分类名称'")
    @ExcelProperty(value = "手机号")
    @ApiModelProperty(value = "手机号")
    private String phone;

    @Column(columnDefinition = "varchar(128) default '' comment '邮箱'")
    @ExcelProperty(value = "邮箱")
    @ApiModelProperty(value = "邮箱")
    private String email;

    @Column(columnDefinition = "varchar(64) default '' comment '组织id'")
    @ApiModelProperty(value = "主组织id")
    private String companyId;

    @Column(columnDefinition = "varchar(64) default '' comment '部门id'")
    @ApiModelProperty(value = "主部门id")
    private String deptId;

}
