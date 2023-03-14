package com.speed.module.project.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.speed.mutual.common.constant.BaseConstant;
import com.speed.mutual.common.utils.LocalDateTimeConverter;
import com.speed.mutual.webjpa.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "project_info",
        indexes = {
                @Index(name = "idx_ProjectInfo_name",columnList = "projectName",unique = true),
                @Index(name = "idx_ProjectInfo_no",columnList = "projectNo",unique = true)
        }
)
@org.hibernate.annotations.Table(appliesTo = "project_info",comment="项目信息")
@ColumnWidth(25)
@ApiModel(value="项目信息")
public class ProjectInfo extends BaseEntity {

    @Column(nullable = false,columnDefinition = "varchar(256) comment '项目名称'")
    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @Column(nullable = false,columnDefinition = "varchar(64) default '' comment '项目编号'")
    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @Column(columnDefinition = "varchar(1024) default '' comment '项目描述'")
    @ApiModelProperty(value = "项目描述")
    private String projectDesc;

    @Column(columnDefinition = "varchar(64) default '' comment '项目负责人'")
    @ApiModelProperty(value = "项目负责人")
    private String projectLeader;

    @Column(columnDefinition = "int(3) default 0 comment '项目进度'")
    @ApiModelProperty(value = "项目进度")
    private Integer projectProgress;

    @ExcelProperty(value = "计划开始时间",converter = LocalDateTimeConverter.class)
    @JsonFormat(locale = "zh", pattern = BaseConstant.ENTITY_DATE_FORMAT)
    @Column(nullable = true,columnDefinition = "datetime comment '计划开始时间'")
    @ApiModelProperty(value = "计划开始时间")
    protected LocalDateTime planStartTime;

    @ExcelProperty(value = "计划结束时间",converter = LocalDateTimeConverter.class)
    @JsonFormat(locale = "zh", pattern = BaseConstant.ENTITY_DATE_FORMAT)
    @Column(nullable = true,columnDefinition = "datetime comment '计划结束时间'")
    @ApiModelProperty(value = "计划结束时间")
    protected LocalDateTime planEndTime;

    @ExcelProperty(value = "实际开始时间",converter = LocalDateTimeConverter.class)
    @JsonFormat(locale = "zh", pattern = BaseConstant.ENTITY_DATE_FORMAT)
    @Column(nullable = true,columnDefinition = "datetime comment '实际开始时间'")
    @ApiModelProperty(value = "实际开始时间")
    protected LocalDateTime actualStartTime;

    @ExcelProperty(value = "实际结束时间",converter = LocalDateTimeConverter.class)
    @JsonFormat(locale = "zh", pattern = BaseConstant.ENTITY_DATE_FORMAT)
    @Column(nullable = true,columnDefinition = "datetime comment '实际结束时间'")
    @ApiModelProperty(value = "实际结束时间")
    protected LocalDateTime actualEndTime;

    @Column(nullable = false,columnDefinition = "int(2) default 0 comment '项目状态'")
    @ApiModelProperty(value = "项目状态")
    private Integer projectStatus;

    @Column(nullable = false,columnDefinition = "bit(1) default b'0' comment '是否模板'")
    @ApiModelProperty(value = "是否模板")
    private boolean hadTemp;
}
