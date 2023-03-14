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
 * 部门信息
 * </p>
 *
 * @author Joey
 * @since 2022-09-18
 */
@Data
@ColumnWidth(25)
@ApiModel("部门信息")
@Entity
@Table(name = "department",
        indexes = {
                @Index(name = "idx_Department_no",columnList = "departmentNo",unique = true),
                @Index(name = "idx_Department_pd",columnList = "pid,departmentName",unique = true)
        }
)
@org.hibernate.annotations.Table(appliesTo = "department",comment="部门信息")
public class Department extends BaseEntity {

    @Column(nullable = false,columnDefinition = "varchar(64) comment '组织id'")
    @ApiModelProperty(value = "组织id")
    private String companyId;

    @Column(nullable = false,columnDefinition = "varchar(64) DEFAULT '0' comment '父id'")
    @ApiModelProperty(value = "父id")
    private String pid;

    @Column(nullable = false,columnDefinition = "varchar(128) comment '部门名称'")
    @ApiModelProperty(value = "部门名称")
    private String departmentName;

    @Column(nullable = false,columnDefinition = "varchar(64) comment '部门编号'")
    @ApiModelProperty(value = "部门编号")
    private String departmentNo;

    @ApiModelProperty(value = "备注")
    @Column(columnDefinition = "varchar(128) default '' comment '备注'")
    private String remarks;

    @Column(nullable = false,columnDefinition = "int(2) default 0 comment '部门类型'")
    @ApiModelProperty(value = "部门类型")
    private Integer deptType;

}
