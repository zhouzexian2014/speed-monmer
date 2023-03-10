package com.speed.module.project.entity;

import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.speed.mutual.webjpa.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "task_member",
        indexes = {
                @Index(name = "idx_TaskMember_user",columnList = "taskId,memberId",unique = true)
        }
)
@org.hibernate.annotations.Table(appliesTo = "task_member",comment="任务成员")
@ColumnWidth(25)
@ApiModel(value="任务成员")
public class TaskMember extends BaseEntity {

    @Column(nullable = false,columnDefinition = "varchar(64) comment '项目ID'")
    @ApiModelProperty(value = "项目ID")
    private String projectId;

    @Column(nullable = false,columnDefinition = "varchar(64) comment '任务ID'")
    @ApiModelProperty(value = "任务ID")
    private String taskId;

    @Column(nullable = false,columnDefinition = "varchar(64) comment '成员ID'")
    @ApiModelProperty(value = "成员ID")
    private String memberId;

    @Column(nullable = false,columnDefinition = "varchar(64) comment '成员名称'")
    @ApiModelProperty(value = "成员名称")
    private String memberName;

    @Column(nullable = true,columnDefinition = "varchar(64) default '' comment '职责'")
    @ApiModelProperty(value = "职责")
    private String duty;
}
