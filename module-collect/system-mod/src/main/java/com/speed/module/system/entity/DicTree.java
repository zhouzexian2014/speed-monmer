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
@Table(name = "dic_tree", schema = "monomer_db", catalog = "")
@ApiModel(value="字典分类")
@ColumnWidth(25)
public class DicTree extends BaseEntity {
    @ApiModelProperty(value = "父级id")
    @Column(name = "pid", nullable = false, length = 64)
    private String pid;
    @ApiModelProperty(value = "分类名称")
    @Basic
    @Column(name = "tree_name", nullable = false, length = 64)
    private String treeName;
    @ApiModelProperty(value = "分类key")
    @Basic
    @Column(name = "tree_key", nullable = false, length = 64)
    private String treeKey;
}
