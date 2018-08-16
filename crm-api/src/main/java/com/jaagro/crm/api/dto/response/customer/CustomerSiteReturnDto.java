package com.jaagro.crm.api.dto.response.customer;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liqiangping
 */
@Data
@Accessors(chain = true)
public class CustomerSiteReturnDto implements Serializable {
    /**
     * 客户发货ID
     */
    private Long id;

    /**
     * 地址类型
     1-装货点，2-卸货点
     */
    private Integer siteType;

    /**
     * 外键关联客户ID
     ( References customer)
     */
    private Long customerId;

    /**
     * 装货地名称
     */
    private String siteName;

    /**
     * 系统状态
     */
    private Integer siteStatus;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区
     */
    private String county;

    /**
     * 详细地址
     */
    private String detailedAddress;

    /**
     * 纬度
     */
    private BigDecimal latitude;

    /**
     * 经度
     */
    private BigDecimal longitude;

    /**
     * 装货要求
     */
    private String shipmentRequire;

    /**
     * 备注消息
     */
    private String notes;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 创建人(References: user)
     */
    private Long createUserId;

    /**
     * 修改人(References: user)
     */
    private Long modifyUserId;
}