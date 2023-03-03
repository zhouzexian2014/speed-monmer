package com.speed.mutual.webjpa.bean;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.speed.mutual.common.constant.BaseConstant;
import com.speed.mutual.common.utils.LocalDateTimeConverter;
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
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
//@Entity
//@Table(name = "test")
//@org.hibernate.annotations.Table(appliesTo = "test",comment="我会有表注释的哟...")
public class BaseEntity extends IdEntity {

    @CreatedDate
    @ExcelProperty(value = "创建时间",converter = LocalDateTimeConverter.class)
    @JsonFormat(locale = "zh", pattern = BaseConstant.ENTITY_DATE_FORMAT)
    @Column(nullable = true,columnDefinition = "datetime comment '创建时间'")
    @ApiModelProperty(value = "创建时间")
    protected LocalDateTime createTime;

    @LastModifiedDate
    @ExcelProperty(value = "更新时间",converter = LocalDateTimeConverter.class)
    @JsonFormat(locale = "zh", pattern = BaseConstant.ENTITY_DATE_FORMAT)
    @Column(nullable = true,columnDefinition = "datetime comment '更新时间'")
    @ApiModelProperty(value = "更新时间")
    protected LocalDateTime updateTime;

    @CreatedBy
    @Column(nullable = true,columnDefinition = "varchar(64) comment '创建人ID'")
    @ApiModelProperty(value = "创建人ID")
    protected String createBy;

    @ExcelIgnore
    @LastModifiedBy
    @Column(nullable = true,columnDefinition = "varchar(64) comment '更新人ID'")
    @ApiModelProperty(value = "更新人ID")
    protected String updateBy;

    @Version
    @ExcelIgnore
    @ApiModelProperty(value = "版本号")
    @Column(nullable = true,columnDefinition = "int(11) comment '版本号'")
    private int versionCount;

    @ExcelIgnore
    @ApiModelProperty(value = "租户ID")
    @Column(nullable = true,columnDefinition = "varchar(64) comment '租户ID'")
    protected String tenantId;

}
