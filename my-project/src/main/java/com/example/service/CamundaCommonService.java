package com.example.service;


import com.example.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.history.HistoricTaskInstanceQuery;
import org.camunda.bpm.engine.history.HistoricVariableInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.VariableInstance;
import org.camunda.bpm.engine.runtime.VariableInstanceQuery;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Camunda通用服务类
 *
 * @author luohq
 * @version 1.0.0
 * @date 2022-01-25 13:49
 */
@Slf4j
public class CamundaCommonService {

    @Resource
    private ProcessEngine processEngine;
    @Resource
    private RuntimeService runtimeService;
    @Resource
    private TaskService taskService;
    @Resource
    private HistoryService historyService;


    /** ================================================================================== */
    /** ========================== 流程相关（RuntimeService）=============================== */
    /** ================================================================================== */

    /**
     * 启动流程实例
     *
     * @param processDefinitionKey
     * @param businessKey
     * @param processVariables
     * @return
     */
    public ProcessInstance startProcessInstance(String processDefinitionKey, String businessKey, Object processVariables) {
        log.debug("start process instance, processDefinitionKey: {}, businessKey={}, processVariables: {}", processDefinitionKey, businessKey, processVariables);
        ProcessInstance processInstance = this.runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, JsonUtils.toMap(processVariables));
        log.debug("start process instance success, processInstanceId: {}", processInstance.getId());
        return processInstance;
    }


    /**
     * 启动流程实例
     *
     * @param processDefinitionKey
     * @param businessKey
     * @return
     */
    public ProcessInstance startProcessInstance(String processDefinitionKey, String businessKey) {
        log.debug("start process instance, processDefinitionKey: {}, businessKey={}", processDefinitionKey, businessKey);
        ProcessInstance processInstance = this.runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey);
        log.debug("start process instance success, processInstanceId: {}", processInstance.getId());
        return processInstance;
    }




}
