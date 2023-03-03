package com.speed.mutual.webjpa.listener;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.speed.mutual.webjpa.bean.IdEntity;
import com.speed.mutual.webjpa.dao.BaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 公共Excel导入处理
 * @param <T>
 */
public class JpaExcelListener<T extends IdEntity> extends AnalysisEventListener<T> {
    private static final Logger logger = LoggerFactory.getLogger(JpaExcelListener.class);
    private BaseRepository<T,String> service;
    private List<T> updateList = new ArrayList<>();
    private List<T> addList = new ArrayList<>();
    private Integer maxBatchCount = 25;

    public JpaExcelListener(BaseRepository<T,String> service) {
        this.service = service;
    }

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        if(t!=null){
            if(StrUtil.isNotBlank(t.getId())&&service.getById(t.getId())!=null){
                updateList.add(t);
            }else {
                t.setId(IdUtil.getSnowflake(1, 1).nextIdStr());
                addList.add(t);
            }
        }
        if(updateList.size()>=maxBatchCount){
            service.saveAllAndFlush(updateList);
            updateList.clear();
        }
        if(addList.size()>=maxBatchCount){
            service.saveAllAndFlush(addList);
            addList.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        if(updateList.size()>0){
            service.saveAllAndFlush(updateList);
        }
        if(addList.size()>0){
            service.saveAllAndFlush(addList);
        }
    }
}
