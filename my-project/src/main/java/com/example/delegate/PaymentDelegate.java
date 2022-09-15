package com.example.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 支付活动 - Java代理
 *
 * @author luohq
 * @date 2022-01-31
 */
@Component
@Slf4j
public class PaymentDelegate implements JavaDelegate {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void execute(DelegateExecution execution) {
        /** 获取流程相关信息 */
        String bizKey = execution.getProcessBusinessKey();
        String processInstanceId = execution.getProcessInstanceId();
        log.info("调用支付服务，processInstanceId: {}, bizKey: {}", processInstanceId, bizKey);

//        PaymentProcessVariablesWrapper paymentProcessVariablesWrapper = new PaymentProcessVariablesWrapper(execution.getVariables());
//        log.info("RPC调用支付服务, 流程变量：{}", paymentProcessVariablesWrapper);
//
//        /** 更新业务DB */
//        BizPaymentProcessInfo bizPaymentProcessInfo = BizPaymentProcessInfo.builder()
//                .id(Long.valueOf(bizKey))
//                .paymentResult(PaymentResultEnum.PAY_SUCCESS.getCode())
//                .paymentTime(LocalDateTime.now())
//                .build();
//        log.info("更新支付结果, 参数：{}", bizPaymentProcessInfo);
//        Boolean result = this.bizPaymentProcessInfoService.updateById(bizPaymentProcessInfo);
//        log.info("更新支付结果, 结果：{}", result);
        //抛出异常，则此task执行失败，回退到上一步
        //throw new MsgRuntimeException("支付异常");
    }

}