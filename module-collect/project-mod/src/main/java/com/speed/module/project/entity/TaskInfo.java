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
@Table(name = "task_info",
        indexes = {
                @Index(name = "idx_TaskInfo_no",columnList = "taskNo",unique = true)
        }
)
@org.hibernate.annotations.Table(appliesTo = "task_info",comment="任务信息")
@ColumnWidth(25)
@ApiModel(value="任务信息")
public class TaskInfo extends BaseEntity {

    @Column(nullable = false,columnDefinition = "varchar(64) comment '项目ID'")
    @ApiModelProperty(value = "项目ID")
    private String projectId;

    @Column(nullable = false,columnDefinition = "varchar(64) comment '父级ID'")
    @ApiModelProperty(value = "父级ID")
    private String pid;

    @Column(nullable = false,columnDefinition = "varchar(128) comment '任务编号'")
    @ApiModelProperty(value = "任务编号")
    private String taskNo;

    @Column(nullable = false,columnDefinition = "varchar(128) comment '任务标题'")
    @ApiModelProperty(value = "任务标题")
    private String taskTitle;

    @Column(nullable = true,columnDefinition = "varchar(1024) comment '任务内容'")
    @ApiModelProperty(value = "任务内容")
    private String taskContent;

    @Column(nullable = true,columnDefinition = "int(3) comment '任务进度'")
    @ApiModelProperty(value = "任务进度")
    private Integer taskProgress;

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

    @Column(nullable = false,columnDefinition = "int(2) comment '任务状态'")
    @ApiModelProperty(value = "任务状态")
    private Integer taskStatus;
}
