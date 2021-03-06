package com.jaagro.crm.api.dto.request.truck;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author gavin
 */
@Data
@Accessors(chain = true)
public class QueryTruckDto implements Serializable {
    private Integer pageNum;
    private Integer pageSize;
    private String truckNumber;
    private Integer truckTypeId;
    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区县
     */
    private String county;

}
