package com.speed.module.system.entity;

import com.speed.mutual.webjpa.bean.IdEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
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
public class TraceLog extends IdEntity {

    @ApiModelProperty(value = "跟踪id")
    private String traceId;

    @ApiModelProperty(value = "是否成功")
    private Boolean success=true;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "访问地址")
    private String url;

    @ApiModelProperty(value = "类名")
    private String className;

    @ApiModelProperty(value = "方法名")
    private String methodName;

    @ApiModelProperty(value = "参数")
    private String paramContent;

    @ApiModelProperty(value = "结果")
    private String resultContent;

    @ApiModelProperty(value = "异常")
    private String exception;

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "持续时长")
    private Long duration;

    @ApiModelProperty(value = "访问ip")
    private String ip;

    @ApiModelProperty(value = "创建人id")
    protected String createBy;


}
