package com.speed.module.system.entity;

import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.speed.mutual.webjpa.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <p>
 * 文件信息
 * </p>
 *
 * @author Joey
 * @since 2022-03-23
 */
@Data
@ApiModel(value="文件信息")
@ColumnWidth(25)
@Entity
@Table(name = "file_info")
@org.hibernate.annotations.Table(appliesTo = "file_info",comment="文件信息表")
public class FileInfo extends BaseEntity {

    @Column(columnDefinition = "varchar(64) comment '文件类型'")
    @ApiModelProperty(value = "文件类型")
    private String contentType;

    @Column(columnDefinition = "varchar(64) comment '扩展名'")
    @ApiModelProperty(value = "扩展名")
    private String extension;

    @Column(nullable = false,columnDefinition = "varchar(256) comment '文件名称'")
    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @Column(columnDefinition = "varchar(256) comment '文件路径'")
    @ApiModelProperty(value = "文件路径")
    private String filePath;

    @Column(columnDefinition = "int(11) comment '文件大小'")
    @ApiModelProperty(value = "文件大小")
    private Long length;

    @Column(columnDefinition = "varchar(64) comment '关联标识'")
    @ApiModelProperty(value = "关联标识")
    private String relationKey;

    @Column(columnDefinition = "int(2) comment '关联类型'")
    @ApiModelProperty(value = "关联类型")
    private Integer relationType;

    @Column(columnDefinition = "bit(1) comment '是否需要授权'")
    @ApiModelProperty(value = "是否需要授权")
    private Boolean hadAuthorization;


}
