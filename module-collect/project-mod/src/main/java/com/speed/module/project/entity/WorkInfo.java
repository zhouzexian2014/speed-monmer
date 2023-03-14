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
@Table(name = "work_member",
        indexes = {
                @Index(name = "idx_WorkInfo_user",columnList = "workId,memberId",unique = true)
        }
)
@org.hibernate.annotations.Table(appliesTo = "work_member",comment="工序信息")
@ColumnWidth(25)
@ApiModel(value="工序信息")
public class WorkInfo extends BaseEntity {

    @Column(nullable = false,columnDefinition = "varchar(64) comment '项目ID'")
    @ApiModelProperty(value = "项目ID")
    private String projectId;

    @Column(nullable = false,columnDefinition = "varchar(64) comment '任务ID'")
    @ApiModelProperty(value = "任务ID")
    private String taskId;

    @Column(nullable = false,columnDefinition = "varchar(1024) default '' comment '工序内容'")
    @ApiModelProperty(value = "工序内容")
    private String workContent;

    @ExcelProperty(value = "完成时间",converter = LocalDateTimeConverter.class)
    @JsonFormat(locale = "zh", pattern = BaseConstant.ENTITY_DATE_FORMAT)
    @Column(nullable = true,columnDefinition = "datetime comment '完成时间'")
    @ApiModelProperty(value = "完成时间")
    protected LocalDateTime finishTime;

    @Column(nullable = false,columnDefinition = "int(2) default 0 comment '工序状态'")
    @ApiModelProperty(value = "工序状态")
    private Integer workStatus;
}
