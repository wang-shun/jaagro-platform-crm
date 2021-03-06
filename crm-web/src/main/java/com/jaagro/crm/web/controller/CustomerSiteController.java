package com.jaagro.crm.web.controller;

import com.jaagro.crm.api.dto.request.customer.CreateCustomerSiteDto;
import com.jaagro.crm.api.dto.request.customer.ListSiteCriteriaDto;
import com.jaagro.crm.api.dto.request.customer.ShowSiteDto;
import com.jaagro.crm.api.dto.request.customer.UpdateCustomerSiteDto;
import com.jaagro.crm.api.dto.response.selectValue.ReturnSelectSiteDto;
import com.jaagro.crm.api.service.CustomerSiteService;
import com.jaagro.crm.biz.mapper.CustomerMapperExt;
import com.jaagro.crm.biz.mapper.CustomerSiteMapperExt;
import com.jaagro.utils.BaseResponse;
import com.jaagro.utils.ResponseStatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 客户地址管理
 *
 * @author baiyiran
 */
@RestController
@Api(value = "customer", description = "客户地址管理", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerSiteController {

    @Autowired
    private CustomerMapperExt customerMapper;
    @Autowired
    private CustomerSiteService siteService;
    @Autowired
    private CustomerSiteMapperExt siteMapper;

    @ApiOperation(value = "新增单个地址")
    @PostMapping("/site")
    public BaseResponse insertCustomer(@RequestBody CreateCustomerSiteDto siteDto) {
        if (siteDto.getCustomerId() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "客户id:[customerId]不能为空");
        }
        if (this.customerMapper.selectByPrimaryKey(siteDto.getCustomerId()) == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "客户id:[customerId]不存在");
        }
        if (siteDto.getSiteType() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "地址类型:[siteType]不能为空");
        }
        if (siteDto.getSiteName() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "地址名称:[siteName]不能为空");
        }
        return BaseResponse.service(siteService.createSite(siteDto));
    }

    @ApiOperation("删除地址[逻辑]")
    @DeleteMapping("/deleteSiteById/{id}")
    public BaseResponse deleteById(@PathVariable Integer id) {
        if (this.siteMapper.selectByPrimaryKey(id) == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "查询不到相应数据");
        }
        return BaseResponse.service(this.siteService.disableSite(id));
    }

    @ApiOperation("修改单个地址")
    @PutMapping("/site")
    public BaseResponse updateSite(@RequestBody UpdateCustomerSiteDto siteDto) {
        if (this.siteMapper.selectByPrimaryKey(siteDto.getId()) == null) {
            return BaseResponse.idNull("地址id:[id]不能为空");
        }
        if (siteDto.getSiteName() == null) {
            if (this.siteMapper.getSiteDto(siteDto) != null) {
                return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "地址名称:[siteName]已存在");
            }
        }
        return BaseResponse.service(siteService.updateSite(siteDto));
    }

    @ApiOperation("查询单个地址")
    @GetMapping("/site/{id}")
    public BaseResponse getById(@PathVariable Integer id) {
        if (this.siteMapper.selectByPrimaryKey(id) == null) {
            return BaseResponse.queryDataEmpty();
        }
        return BaseResponse.successInstance(this.siteMapper.selectByPrimaryKey(id));
    }

    @ApiOperation("分页查询地址")
    @PostMapping("/listSiteByCriteria")
    public BaseResponse listByCriteria(@RequestBody ListSiteCriteriaDto criteriaDto) {
        return BaseResponse.service(this.siteService.listByCriteria(criteriaDto));
    }

    @ApiOperation("根据客户查询全部收发货地址")
    @PostMapping("/listSiteForSelect")
    public BaseResponse getById(@RequestBody ListSiteCriteriaDto criteriaDto) {
        if (this.customerMapper.selectByPrimaryKey(criteriaDto.getCustomerId()) == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "客户不存在");
        }
        List<ReturnSelectSiteDto> siteDtoList = siteService.listSiteForSelectByCustomerId(criteriaDto);
        return BaseResponse.successInstance(siteDtoList);
    }

    @Ignore
    @GetMapping("/getShowSite/{id}")
    public ShowSiteDto getShowSiteById(@PathVariable("id") Integer id) {
        return siteService.getShowSiteById(id);
    }

    @Ignore
    @ApiOperation("提供给feign:根据地址id数组获得地址名称")
    @PostMapping("/listSiteNameByIds")
    public List<String> listSiteNameByIds(@RequestBody List<Integer> siteIds) {
        return siteService.listSiteNameByIds(siteIds);
    }

    @ApiOperation("更新装卸货地的经纬度")
    @PostMapping("/updateAllCustomerSite")
    public BaseResponse updateAllCustomerSite() {
        return BaseResponse.successInstance(siteService.updateAllCustomerSite());
    }
}
