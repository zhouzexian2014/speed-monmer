package com.speed.mutual.webjpa.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Jpa实体基类-id
 * @author joey
 */
@MappedSuperclass
@Data
public class IdEntity implements Serializable {
    protected static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(generator = "idGengerator")
    @GenericGenerator(name = "idGengerator", strategy = "com.speed.mutual.webjpa.service.IdGengerator")
    @ExcelProperty(value = "主键")
    @ApiModelProperty(value = "主键")
    protected String id;


}
