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
@Entity
@Table(name = "dic_tree_item", schema = "monomer_db", catalog = "")
@ApiModel(value="字典项")
@ColumnWidth(25)
public class DicTreeItem extends BaseEntity {
    @ApiModelProperty(value = "分类id")
    @Basic
    @Column(name = "tree_id", nullable = false, length = 64)
    private String treeId;
    @ApiModelProperty(value = "项名称")
    @Basic
    @Column(name = "item_name", nullable = false, length = 64)
    private String itemName;
    @ApiModelProperty(value = "项编号")
    @Basic
    @Column(name = "item_no",nullable = false, length = 64)
    private String itemNo;
    @ApiModelProperty(value = "扩展1")
    @Basic
    @Column(name = "extend1", length = 64)
    private String extend1;
    @ApiModelProperty(value = "扩展2")
    @Basic
    @Column(name = "extend2", length = 64)
    private String extend2;
    @ApiModelProperty(value = "扩展3")
    @Basic
    @Column(name = "extend3", length = 64)
    private String extend3;

}
