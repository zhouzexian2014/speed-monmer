package com.speed.mutual.webjpa.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.speed.mutual.webjpa.bean.IdEntity;
import com.speed.mutual.common.dto.SortFieldDTO;
import com.speed.mutual.common.form.SearchItem;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JpaUtils<T> {
    public static final String ID="id";
    public static final String EQ="eq";
    public static final String NE="ne";
    public static final String LIKE="like";
    public static final String LIKE_LEFT="likeLeft";
    public static final String LIKE_RIGHT="likeRight";
    public static final String NOT_LIKE="notLike";
    public static final String GT="gt";
    public static final String LT="lt";
    public static final String GE="ge";
    public static final String LE="le";
    public static final String ASC="asc";
    public static final String DESC="desc";
    public static final String PERC="%";

    /**
     * 描述：排序处理，默认id降序
     * @param orderInfos
     * @return
     */
    public static Sort getSortOrder(List<SortFieldDTO> orderInfos){
        List<Sort.Order> sortOrders = new ArrayList<>();
        if(CollUtil.isNotEmpty(orderInfos)){
            for (SortFieldDTO order : orderInfos) {
                String orderField = order.getFieldName();
                String orderType = order.getDirection();
                if(StrUtil.isNotBlank(orderField)&&StrUtil.isNotBlank(orderType)){
                    Sort.Order sortOrder = new Sort.Order(ASC.equalsIgnoreCase(orderType) ? Sort.Direction.ASC : Sort.Direction.DESC, orderField);
                    sortOrders.add(sortOrder);
                }
            }
        }
        Sort.Order sortOrder = new Sort.Order(Sort.Direction.DESC, ID);
        sortOrders.add(sortOrder);
        return Sort.by(sortOrders);
    }

    /**
     * 构建查询条件
     * @param searchAndItemList
     * @param <T>
     * @return
     */
    public static <T extends IdEntity> Specification<T> specification(List<SearchItem> searchAndItemList) {
        Specification<T> specification = (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if(CollUtil.isNotEmpty(searchAndItemList)){
                for(SearchItem item : searchAndItemList){
                    if(item!=null){
                        String field = item.getField();
                        String value = item.getValue();
                        if(StrUtil.isNotBlank(field)&&value!=null){
                            String compare = item.getCompare();
                            if(EQ.equals(compare)){
                                predicate.getExpressions().add(criteriaBuilder.equal(root.get(field), value ));
                            }if(GE.equals(compare)){
                                predicate.getExpressions().add(criteriaBuilder.greaterThanOrEqualTo(root.get(field), value ));
                            }if(LT.equals(compare)){
                                predicate.getExpressions().add(criteriaBuilder.lessThan(root.get(field), value ));
                            }else {
                                predicate.getExpressions().add(criteriaBuilder.like(root.get(field), PERC + value + PERC));
                            }
                        }
                    }
                }
            }
            return predicate;
        };
        return specification;
    }

    /**
     * 日期查询条件
     * @param rangeTime
     * @param timeStart
     * @param timeEnd
     * @return
     */
    public Specification<T> specification(String rangeTime, LocalDateTime timeStart, LocalDateTime timeEnd) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            // 增加筛选条件
            Predicate predicate = criteriaBuilder.conjunction();
            // 起始日期
            if (null != timeStart) {
                predicate.getExpressions().add(criteriaBuilder.greaterThanOrEqualTo(root.get(rangeTime).as(LocalDateTime.class), timeStart));
            }
            // 结束日期
            if (null != timeEnd) {
                predicate.getExpressions().add(criteriaBuilder.lessThan(root.get(rangeTime).as(LocalDateTime.class), timeEnd));
            }
            return predicate;
        };
    }
}
