package com.speed.module.system.entity;

import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.speed.mutual.webjpa.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;

/**
 * <p>
 * 文件信息
 * </p>
 *
 * @author Joey
 * @since 2022-03-23
 */
@Data
@Entity
@ApiModel(value="文件信息")
@ColumnWidth(25)
public class FileInfo extends BaseEntity {

    @ApiModelProperty(value = "文件类型")
    private String contentType;

    @ApiModelProperty(value = "扩展名")
    private String extension;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "文件路径")
    private String filePath;

    @ApiModelProperty(value = "文件大小")
    private Long length;

    @ApiModelProperty(value = "关联标识")
    private String relationKey;
    //0=其他
    @ApiModelProperty(value = "关联类型")
    private Integer relationType;

    @ApiModelProperty(value = "是否需要授权")
    private Boolean hadAuthorization;


}
