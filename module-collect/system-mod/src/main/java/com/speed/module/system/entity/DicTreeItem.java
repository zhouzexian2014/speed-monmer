package com.speed.module.system.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.speed.mutual.webjpa.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@ApiModel(value="字典项")
@ColumnWidth(25)
@Entity
@Table(name = "dic_tree_item",
        indexes = {
                @Index(name = "idx_DicTreeItem_idKey",columnList = "treeId,itemKey",unique = true),
                @Index(name = "idx_DicTreeItem_idName",columnList = "treeId,itemName",unique = true)
        })
@org.hibernate.annotations.Table(appliesTo = "dic_tree_item",comment="字典项表")
public class DicTreeItem extends BaseEntity {

    @Column(nullable = false,columnDefinition = "varchar(64) comment '分类ID'")
    @ApiModelProperty(value = "分类ID")
    private String treeId;

    @Column(nullable = false,columnDefinition = "varchar(64) comment '项名称'")
    @ApiModelProperty(value = "项名称")
    private String itemName;

    @Column(nullable = false,columnDefinition = "varchar(64) comment '项key'")
    @ApiModelProperty(value = "项key")
    private String itemKey;

    @Column(columnDefinition = "varchar(128) default '' comment '扩展1'")
    @ApiModelProperty(value = "扩展1")
    private String extend1;

    @ApiModelProperty(value = "扩展2")
    @Column(columnDefinition = "varchar(64) default '' comment '扩展2'")
    private String extend2;

    @ApiModelProperty(value = "扩展3")
    @Column(columnDefinition = "varchar(64) default '' comment '扩展3'")
    private String extend3;

}
