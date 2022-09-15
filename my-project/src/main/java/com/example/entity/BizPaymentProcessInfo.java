package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 支付流程 - 业务数据
 *
 * @author luohq
 * @since 2022-01-30
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BizPaymentProcessInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 业务主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 流程实例ID
     */
    private String processInstanceId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品金额
     */
    private BigDecimal productPrice;

    /**
     * 商品折扣
     */
    private BigDecimal productDiscount;

    /**
     * 商品折扣后金额
     */
    private BigDecimal productDiscountPrice;

    /**
     * 用户是否同意付款(0:未确认,1:同意, 2:不同意)
     */
    private Integer approvalResult;
    /**
     * 用户确认时间
     */
    private LocalDateTime approvalTime;

    /**
     * 支付用户ID
     */
    private String paymentAssignee;
    /**
     * 支付结果（0:未支付,1:成功, 2:失败）
     */
    private Integer paymentResult;

    /**
     * 用户支付时间
     */
    private LocalDateTime paymentTime;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;


}
