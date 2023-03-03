package com.speed.mutual.webjpa.utils;

import com.speed.mutual.common.dto.PageDTO;
import com.speed.mutual.webjpa.bean.IdEntity;

import java.util.List;

/**
 * @author joey
 */
public class PageUtil {
    public static final long MAX_PAGE_SIZE=999;
    public static final long INIT_PAGE_NO=1;
    public static final long INIT_PAGE_SIZE=10;

    public static <T extends IdEntity> Object getPageVo(Integer pages, Long total, List<T> records) {
        PageDTO pageDto = new PageDTO();
        pageDto.setRecords(records);
        pageDto.setPages(pages);
        pageDto.setTotal(total);
        return pageDto;
    }
    public static <T> Object getPageVo(Object total,List<T> records) {
        PageDTO pageDto = new PageDTO();
        pageDto.setRecords(records);
        pageDto.setTotal(total);
        return pageDto;
    }
    public static <T extends IdEntity> Object getPageVo(List<T> records) {
        PageDTO pageDto = new PageDTO();
        pageDto.setRecords(records);
        pageDto.setTotal(records.size());
        pageDto.setPages(1);
        return pageDto;
    }
}