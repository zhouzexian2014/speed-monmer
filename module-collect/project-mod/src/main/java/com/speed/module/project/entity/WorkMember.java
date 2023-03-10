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
@Table(name = "work_member",
        indexes = {
                @Index(name = "idx_WorkMember_user",columnList = "workId,memberId",unique = true)
        }
)
@org.hibernate.annotations.Table(appliesTo = "work_member",comment="工序成员")
@ColumnWidth(25)
@ApiModel(value="工序成员")
public class WorkMember extends BaseEntity {

    @Column(nullable = false,columnDefinition = "varchar(64) comment '项目ID'")
    @ApiModelProperty(value = "项目ID")
    private String projectId;

    @Column(nullable = false,columnDefinition = "varchar(64) comment '任务ID'")
    @ApiModelProperty(value = "任务ID")
    private String taskId;

    @Column(nullable = false,columnDefinition = "varchar(64) comment '工序ID'")
    @ApiModelProperty(value = "工序ID")
    private String workId;

    @Column(nullable = false,columnDefinition = "varchar(64) comment '成员ID'")
    @ApiModelProperty(value = "成员ID")
    private String memberId;

    @Column(nullable = false,columnDefinition = "varchar(64) comment '成员名称'")
    @ApiModelProperty(value = "成员名称")
    private String memberName;

    @Column(nullable = true,columnDefinition = "varchar(64) comment '职责'")
    @ApiModelProperty(value = "职责")
    private String duty;

    @Column(nullable = false,columnDefinition = "bit(1) comment '是否注销'")
    @ApiModelProperty(value = "是否注销")
    private boolean hadCancel;
}
