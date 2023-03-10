package com.speed.module.system.entity;

import com.alibaba.excel.annotation.ExcelProperty;
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
@ApiModel(value="字典分类")
@ColumnWidth(25)
@Table(name = "dic_tree",
        indexes = {
                @Index(name = "idx_DicTree_key",columnList = "treeKey",unique = true)
        })
@org.hibernate.annotations.Table(appliesTo = "dic_tree",comment="字典分类表")
public class DicTree extends BaseEntity {

    @Column(nullable = false,columnDefinition = "varchar(64) comment '父级ID'")
    @ApiModelProperty(value = "父级ID")
    private String pid;

    @Column(nullable = false,columnDefinition = "varchar(64) comment '分类名称'")
    @ApiModelProperty(value = "分类名称")
    private String treeName;

    @Column(nullable = false,columnDefinition = "varchar(64) comment '分类key'")
    @ApiModelProperty(value = "分类key")
    private String treeKey;

    @Column(columnDefinition = "int(11) comment '排序编号'")
    @ExcelProperty(value = "排序编号")
    @ApiModelProperty(value = "排序编号")
    private Integer orderNo;
}
