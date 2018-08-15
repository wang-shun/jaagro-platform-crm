package com.jaagro.crm.web.controller;

import com.jaagro.crm.api.dto.request.PriceCriteriaDto;
import com.jaagro.crm.api.service.CalculatePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import utils.ResponseStatusCode;
import utils.ServiceResult;

import java.util.Map;

/**
 * @author tony
 */
@RestController
public class CalculatePriceController {

    @Autowired
    private CalculatePriceService calculatePriceService;

    @PostMapping("/calculatePrice")
    public Map<String, Object> calculatePrice(@RequestBody PriceCriteriaDto dto){

        //全部需要改成从数据库中验证
        if(StringUtils.isEmpty(dto.getContractId())){
            return ServiceResult.error(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "请选择计费合同编号");
        }
        if(StringUtils.isEmpty(dto.getPricingType())){
            return ServiceResult.error(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "请选择计价方式");
        }
        if(StringUtils.isEmpty(dto.getProductType())){
            return ServiceResult.error(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "请选择货物类型");
        }

        return calculatePriceService.calculatePrice(dto);
    }
}
