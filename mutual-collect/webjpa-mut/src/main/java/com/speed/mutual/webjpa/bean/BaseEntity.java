package com.speed.mutual.webjpa.bean;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.speed.mutual.common.constant.BaseConstant;
import com.speed.mutual.common.utils.LocalDateTimeConverter;
import com.speed.mutual.webjpa.utils.DbDefinition;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.LocalDateTime;

/**
 * Jpa实体基类-创建信息-更新信息
 * @author joey
 */
//@Entity
//@Table(name = "表名",
//      indexes = {
//                @Index(name = "索引",columnList = "workId,memberId",unique = true)
//        })
//@org.hibernate.annotations.Table(appliesTo = "test",comment="我会有表注释的哟...")
//@Column(nullable = true,columnDefinition = "varchar(64)  comment '字符串'")
//@Column(nullable = true,columnDefinition = "varchar(64) default '' comment '字符串'")
//@Column(nullable = true,columnDefinition = "datetime comment '时间'")
//@Column(nullable = false,columnDefinition = "bit(1) default b'0' comment '布尔'")
//@Column(nullable = true,columnDefinition = "int(11) default 0 comment '整数'")
//@Column(nullable = true,columnDefinition = "double(11,2) default 0.0 comment '小数'")
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity extends IdEntity {

    @Column(columnDefinition = "int(11) default 1 comment '排序编号'")
    @ExcelProperty(value = "排序编号")
    @ApiModelProperty(value = "排序编号")
    private Integer orderNo = 1;

    @Column(nullable = false,columnDefinition = "bit(1) default b'0' comment '是否禁用'")
    @ApiModelProperty(value = "是否禁用")
    private Boolean canceled = false;

    @CreatedDate
    @ExcelProperty(value = "创建时间",converter = LocalDateTimeConverter.class)
    @JsonFormat(locale = "zh", pattern = BaseConstant.ENTITY_DATE_FORMAT)
    @Column(columnDefinition = "datetime comment '创建时间'")
    @ApiModelProperty(value = "创建时间")
    protected LocalDateTime createTime;

    @LastModifiedDate
    @ExcelProperty(value = "更新时间",converter = LocalDateTimeConverter.class)
    @JsonFormat(locale = "zh", pattern = BaseConstant.ENTITY_DATE_FORMAT)
    @Column(columnDefinition = "datetime comment '更新时间'")
    @ApiModelProperty(value = "更新时间")
    protected LocalDateTime updateTime;

    @CreatedBy
    @Column(columnDefinition = "varchar(64) default '' comment '创建人ID'")
    @ApiModelProperty(value = "创建人ID")
    protected String createBy;

    @ExcelIgnore
    @LastModifiedBy
    @Column(columnDefinition = "varchar(64) default '' comment '更新人ID'")
    @ApiModelProperty(value = "更新人ID")
    protected String updateBy;

    @Version
    @ExcelIgnore
    @ApiModelProperty(value = "版本号")
    @Column(columnDefinition = "int(11) default 0 comment '版本号'")
    private int versionNo = 0;

    @ExcelIgnore
    @ApiModelProperty(value = "租户ID")
    @Column(columnDefinition = "varchar(64) default '' comment '租户ID'")
    protected String tenantId;

}
