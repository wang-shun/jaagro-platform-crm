package com.jaagro.crm.biz.service.impl;

import com.jaagro.crm.api.dto.request.truck.CreateTruckTeamContractSectionPriceDto;
import com.jaagro.crm.api.service.TruckTeamContractSectionPriceService;
import com.jaagro.crm.biz.entity.TruckTeamContractSectionPrice;
import com.jaagro.crm.biz.mapper.TruckTeamContractPriceMapperExt;
import com.jaagro.crm.biz.mapper.TruckTeamContractSectionPriceMapperExt;
import com.jaagro.utils.ServiceResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author baiyiran
 */
@Service
public class TruckTeamContractSectionPriceServiceImpl implements TruckTeamContractSectionPriceService {

    @Autowired
    private TruckTeamContractSectionPriceMapperExt sectionPriceMapper;
    @Autowired
    private TruckTeamContractPriceMapperExt priceMapper;

    @Override
    public Map<String, Object> createSectionPrice(CreateTruckTeamContractSectionPriceDto contractSectionPriceDto) {
        TruckTeamContractSectionPrice sectionPrice = new TruckTeamContractSectionPrice();
        BeanUtils.copyProperties(contractSectionPriceDto, sectionPrice);
        this.sectionPriceMapper.insertSelective(sectionPrice);
        return ServiceResult.toResult("新增成功");
    }

    @Override
    public Map<String, Object> disableByPriceId(Integer id) {
        this.sectionPriceMapper.disableByPriceId(id);
        return ServiceResult.toResult("删除成功");
    }

    @Override
    public Map<String, Object> deleteByPriceId(Integer id) {
        this.sectionPriceMapper.deleteByPriceId(id);
        return ServiceResult.toResult("删除成功");
    }
}