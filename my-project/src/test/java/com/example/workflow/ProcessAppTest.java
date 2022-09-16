package com.example.workflow;

import com.example.entity.BizPaymentProcessInfo;
import com.example.util.JsonUtils;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;


/**
 * Unit test for simple App.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessAppTest {

    @Resource
    private ProcessEngine processEngine;
    @Resource
    private RuntimeService runtimeService;
    @Resource
    private TaskService taskService;
    @Resource
    private HistoryService historyService;

    @Test
    public void hello() {
        System.out.println("hello");
        String businessKey = "111";
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery()
                .variableValueNotEquals("someVar", null)
                .list();
        System.out.println("size: " + list.size());
    }


    @Test
    public void startProcess() {
        BizPaymentProcessInfo bizPaymentProcessInfo = new BizPaymentProcessInfo();
        bizPaymentProcessInfo.setId(1L);
        bizPaymentProcessInfo.setProductName("单板吉他");
        bizPaymentProcessInfo.setProductPrice(new BigDecimal(2100));
//        bizPaymentProcessInfo.setProductPrice(new BigDecimal(900));
        bizPaymentProcessInfo.setPaymentAssignee("5594");
        bizPaymentProcessInfo.setPaymentResult(0);
        bizPaymentProcessInfo.setApprovalResult(0);
        String businessKey = "1";
        ProcessInstance processInstance = this.runtimeService.startProcessInstanceByKey("PaymentProcess", businessKey, JsonUtils.toMap(bizPaymentProcessInfo));
        System.out.println("over");
    }

    @Test
    public void createTaskQuery() throws Exception {
        List<Task> tasks = taskService.createTaskQuery().list();
//        List<Task> tasks = taskService.createTaskQuery().taskAssignee("5594").list();
//        List<Task> tasks = taskService.createTaskQuery().processDefinitionKey("PaymentProcess").list();
//        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroupIn(Lists.list("xngroup")).list();
        Gson gson = new Gson();
        for (Task task : tasks) {
            System.out.println(gson.toJson(task));
        }
    }

    @Test
    public void complete() {
        HashMap<String, Object> map = new HashMap<>();
//        map.put("approvalResult", 1);
        map.put("approvalResult", 2);
//        taskService.complete("d16787c6-34c8-11ed-9bb4-d8bbc1b89571", map);
        taskService.complete("70a681c0-34c7-11ed-8211-d8bbc1b89571", map);
//        taskService.complete("70a681c0-34c7-11ed-8211-d8bbc1b89571", processVariablesMap);
        System.out.println("Over");
    }

    @Test
    public void createHistoricTaskInstanceQuery() {
        List<HistoricTaskInstance> historicTaskInstances = this.historyService.createHistoricTaskInstanceQuery()
                .taskAssignee("5594")
                .processDefinitionKey("PaymentProcess")
//                .taskDefinitionKey("Task_UserConfirmPayment")
//                .finished()
//                .orderByHistoricActivityInstanceStartTime()
                .listPage(0, 10);
        Gson gson = new Gson();
        for (HistoricTaskInstance historicTaskInstance : historicTaskInstances) {
            System.out.println(gson.toJson(historicTaskInstance));
        }

    }
}
