package com.jaagro.crm.biz.service.impl;

import com.jaagro.crm.api.constant.ProductType;
import com.jaagro.crm.api.dto.request.contract.CalculatePaymentDto;
import com.jaagro.crm.api.service.CalculatePriceService;
import com.jaagro.crm.biz.mapper.CustomerContractPriceMapperExt;
import com.jaagro.crm.biz.mapper.CustomerContractSectionPriceMapperExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tony
 */

@Service
public class CalculatePriceServiceImpl implements CalculatePriceService {

    @Autowired
    private CustomerContractPriceMapperExt customerContractPriceMapper;
    @Autowired
    private CustomerContractSectionPriceMapperExt customerContractSectionPriceMapper;


    /**
     * 与客户结算的计算
     *
     * @param dtoList
     * @return 结算金额
     */
    @Override
    public List<Map<Integer, BigDecimal>> calculatePaymentFromCustomer(List<CalculatePaymentDto> dtoList) {
        List<Map<Integer, BigDecimal>> returnList = new ArrayList<>();
        for (CalculatePaymentDto calculatePaymentDto : dtoList) {
            Map<Integer, BigDecimal> map = new HashMap<>();
            BigDecimal paymentMoney = new BigDecimal(0.00);
            BigDecimal unitPrice = new BigDecimal(0.00);
            BigDecimal minLoadWeight = new BigDecimal(0.00);
            int compare =0;
            int productType= calculatePaymentDto.getProductType();
            switch (productType) {

                //毛鸡结算
                case 1:
                    //根据合同id、装货地Id,卸货地id获取实际公里数
                    BigDecimal actualMileage = new BigDecimal(40.00);

                    //根据合同id、车型id获取最小载重量
                    minLoadWeight = new BigDecimal(20.00);
                    compare = calculatePaymentDto.getUnloadWeight().compareTo(minLoadWeight);

                    //根据合同id、实际公里数、和运单完成时间获取区间重量单价
                    unitPrice = new BigDecimal(20.00);

                    //结算金额 = 结算重量（吨）✕ 区间重量单价（元/吨）
                    paymentMoney = map.put(calculatePaymentDto.getWaybillId(), paymentMoney);

                    returnList.add(map);
                    break;
                //饲料结算
                case 2:

                    ////根据合同id、装货地Id,卸货地id和车型id获取结算单价(元/吨)
                    unitPrice = new BigDecimal(0.00);


                    //根据合同Id,车型id和运单完成时间获取车型的最小装载量、常用装载量
                    minLoadWeight = new BigDecimal(0.00);
                    BigDecimal normalWeight = new BigDecimal(0.00);

                    compare = calculatePaymentDto.getUnloadWeight().compareTo(minLoadWeight);
                    if (compare == -1) {
                        unitPrice = unitPrice.multiply(normalWeight.divide(minLoadWeight));
                    }
                    //结算金额 = 结算重量（吨）✕ 结算单价（元/吨）
                    paymentMoney = calculatePaymentDto.getUnloadWeight().multiply(unitPrice);
                    map.put(calculatePaymentDto.getWaybillId(), paymentMoney);
                    returnList.add(map);
                    break;

                case 5:

                    map.put(calculatePaymentDto.getWaybillId(), paymentMoney);
                    returnList.add(map);
                    break;

                case 6:

                    map.put(calculatePaymentDto.getWaybillId(), paymentMoney);
                    returnList.add(map);
                    break;

            }

        }
        return returnList;
    }

    /**
     * 运力结算：与司机的结算
     *
     * @param dtoList
     * @return
     */
    @Override
    public List<Map<Integer, BigDecimal>> calculatePaymentToDriver(List<CalculatePaymentDto> dtoList) {
        for (CalculatePaymentDto calculatePaymentDto : dtoList) {

            //饲料结算
            if (calculatePaymentDto.getProductType().equals(ProductType.CHICKEN)) {
                BigDecimal unitPrice = new BigDecimal(0.00);
            }
            //毛鸡结算
            if (calculatePaymentDto.getProductType().equals(ProductType.FODDER)) {
                BigDecimal unitPrice = new BigDecimal(0.00);
            }

            //仔猪/生猪结算：出租车模式
            if (calculatePaymentDto.getProductType().equals(ProductType.PIGLET) || calculatePaymentDto.getProductType().equals(ProductType.LIVE_PIG)) {

            }
        }
        return null;
    }
}
