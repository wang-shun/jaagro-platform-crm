package com.jaagro.crm.api.dto.request.socialDriver;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 查询司机注册意向列表参数
 * @author yj
 * @since 2018/12/5
 */
@Data
@Accessors(chain = true)
public class ListDriverRegisterPurposeCriteriaDto implements Serializable{
    /**
     * 起始页
     */
    @NotNull(message = "{pageNum.NotNull}")
    @Min(value = 1,message = "{pageNum.Min}")
    private Integer pageNum;

    /**
     * 每页条数
     */
    @NotNull(message = "{pageSize.NotNull}")
    @Min(value = 1,message = "{pageSize.Min}")
    private Integer pageSize;

    /**
     * 姓名
     */
    private String name;
}
