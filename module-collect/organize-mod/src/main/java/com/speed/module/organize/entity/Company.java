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
 * 组织信息
 * </p>
 *
 * @author Joey
 * @since 2022-09-18
 */
@Data
@ColumnWidth(25)
@ApiModel("组织信息")
@Entity
@Table(
        name = "company",
        indexes = {
                @Index(name = "idx_Company_name",columnList = "companyName",unique = true),
                @Index(name = "idx_Company_no",columnList = "companyNo",unique = true)
        }
)
@org.hibernate.annotations.Table(appliesTo = "company",comment="组织信息")
public class Company extends BaseEntity {

    @Column(nullable = false,columnDefinition = "varchar(128) comment '组织名称'")
    @ApiModelProperty(value = "组织名称")
    private String companyName;

    @Column(nullable = false,columnDefinition = "varchar(64) comment '组织编号'")
    @ApiModelProperty(value = "组织编号")
    private String companyNo;

    @ApiModelProperty(value = "备注")
    @Column(columnDefinition = "varchar(128) DEFAULT '' comment '备注'")
    private String remarks;

    @Column(columnDefinition = "int(11) DEFAULT 1 comment '排序编号'")
    @ExcelProperty(value = "排序编号")
    @ApiModelProperty(value = "排序编号")
    private Integer orderNo;

    @Column(nullable = false,columnDefinition = "bit(1) DEFAULT b'0' comment '是否禁用'")
    @ApiModelProperty(value = "是否禁用")
    private boolean hadCancel;

}
