package com.speed.module.system.entity;

import com.speed.mutual.webjpa.bean.IdEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * <p>
 * 日志追踪表
 * </p>
 *
 * @author Joey
 * @since 2022-03-23
 */
@Data
@ApiModel(value="日志追踪表")
@Entity
@Table(name = "trace_log")
@org.hibernate.annotations.Table(appliesTo = "trace_log",comment="日志追踪表")
public class TraceLog extends IdEntity {

    @Column(nullable = false,columnDefinition = "varchar(64) comment '跟踪ID'")
    @ApiModelProperty(value = "跟踪ID")
    private String traceId;

    @Column(columnDefinition = "bit(1) DEFAULT b'1' comment '是否成功'")
    @ApiModelProperty(value = "是否成功")
    private Boolean success=true;

    @Column(columnDefinition = "varchar(128) comment '名称'")
    @ApiModelProperty(value = "名称")
    private String name;

    @Column(columnDefinition = "varchar(128) comment '访问地址'")
    @ApiModelProperty(value = "访问地址")
    private String url;

    @Column(columnDefinition = "varchar(128) comment '类名'")
    @ApiModelProperty(value = "类名")
    private String className;

    @Column(columnDefinition = "varchar(64) comment '方法名'")
    @ApiModelProperty(value = "方法名")
    private String methodName;

    @Column(columnDefinition = "varchar(1024) comment '参数'")
    @ApiModelProperty(value = "参数")
    private String paramContent;

    @Column(columnDefinition = "varchar(2048) DEFAULT '' comment '结果'")
    @ApiModelProperty(value = "结果")
    private String resultContent;

    @Column(columnDefinition = "varchar(2048) DEFAULT '' comment '异常'")
    @ApiModelProperty(value = "异常")
    private String exception;

    @Column(columnDefinition = "datetime comment '开始时间'")
    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;

    @Column(columnDefinition = "int(11) DEFAULT 0 comment '持续时长'")
    @ApiModelProperty(value = "持续时长")
    private Long duration;

    @Column(columnDefinition = "varchar(64) comment '访问ip'")
    @ApiModelProperty(value = "访问ip")
    private String ip;

    @Column(columnDefinition = "varchar(64) comment '创建人id'")
    @ApiModelProperty(value = "创建人id")
    protected String createBy;


}
