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
@Table(name = "project_member",
        indexes = {
                @Index(name = "idx_ProjectMember_user",columnList = "projectId,memberId",unique = true)
        }
)
@org.hibernate.annotations.Table(appliesTo = "project_member",comment="项目成员")
@ColumnWidth(25)
@ApiModel(value="项目成员")
public class ProjectMember extends BaseEntity {

    @Column(nullable = false,columnDefinition = "varchar(64) DEFAULT '' comment '项目ID'")
    @ApiModelProperty(value = "项目ID")
    private String projectId;

    @Column(nullable = false,columnDefinition = "varchar(64)  comment '成员ID'")
    @ApiModelProperty(value = "成员ID")
    private String memberId;

    @Column(nullable = false,columnDefinition = "varchar(64)  comment '成员名称'")
    @ApiModelProperty(value = "成员名称")
    private String memberName;

    @Column(columnDefinition = "varchar(64) DEFAULT '' comment '职责'")
    @ApiModelProperty(value = "职责")
    private String duty;

    @Column(nullable = false,columnDefinition = "bit(1) DEFAULT b'0' comment '是否注销'")
    @ApiModelProperty(value = "是否注销")
    private boolean hadCancel;

}
