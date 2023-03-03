package com.speed.module.system.entity;

import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.speed.mutual.webjpa.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@ApiModel(value="字典项")
@ColumnWidth(25)
@Entity
@Table(name = "dic_tree_item")
@org.hibernate.annotations.Table(appliesTo = "dic_tree_item",comment="字典项表")
public class DicTreeItem extends BaseEntity {

    @Column(nullable = false,columnDefinition = "varchar(64) comment '分类ID'")
    @ApiModelProperty(value = "分类ID")
    private String treeId;

    @Column(nullable = false,columnDefinition = "varchar(64) comment '项名称'")
    @ApiModelProperty(value = "项名称")
    private String itemName;

    @Column(nullable = false,columnDefinition = "varchar(64) comment '项编号'")
    @ApiModelProperty(value = "项编号")
    private String itemNo;

    @Column(columnDefinition = "varchar(64) comment '扩展1'")
    @ApiModelProperty(value = "扩展1")
    private String extend1;

    @ApiModelProperty(value = "扩展2")
    @Column(columnDefinition = "varchar(64) comment '扩展2'")
    private String extend2;

    @ApiModelProperty(value = "扩展3")
    @Column(columnDefinition = "varchar(64) comment '扩展3'")
    private String extend3;

}
