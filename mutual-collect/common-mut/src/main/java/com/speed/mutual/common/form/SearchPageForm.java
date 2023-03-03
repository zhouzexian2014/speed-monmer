package com.speed.mutual.common.form;

import com.speed.mutual.common.dto.SortFieldDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value="查询分页form")
public class SearchPageForm extends PageForm{
    @ApiModelProperty(value = "排序方式desc/asc")
    private List<SortFieldDTO> sortOrders;
    @ApiModelProperty(value = "and检索项列表")
    private List<SearchItem> searchAndItemList;
//    @ApiModelProperty(value = "or检索项列表")
//    private List<SearchItem> searchOrItemList;
//    @ApiModelProperty(value = "true=查询页数和总数,false=不查询页数和总数",example = "true")
//    private Boolean hadSearchCount = true;
//    @ApiModelProperty(value = "true=字段驼峰转下划线,false=不处理",example = "true")
//    private Boolean hadHumpToLine = true;
//    @ApiModelProperty(value = "排序字段")
//    private String sortField;

}
