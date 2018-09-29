package com.jaagro.crm.api.dto.request.truck;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author baiyiran
 */
@Data
@Accessors(chain = true)
public class ListTruckTeamCriteriaDto implements Serializable {
    private Integer pageNum;
    private Integer pageSize;
    private String keywords;
    private Integer teamType;
    private Integer teamStatus;
}
