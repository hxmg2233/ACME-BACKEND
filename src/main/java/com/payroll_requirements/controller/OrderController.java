package com.payroll_requirements.controller;

import com.payroll_requirements.common.ReturnData;
import com.payroll_requirements.entity.Employee;
import com.payroll_requirements.entity.Order;
import com.payroll_requirements.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2023/10/21 13:24
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/id")
    public ReturnData<Order> getOneById(@PathParam("id") Long id){
        Order order = orderService.getById(id);
        return ReturnData.success("查询完毕", order);
    }

    @PostMapping("/change")
    public ReturnData<Order> save(@RequestBody Order order){
        log.info("新增订单，订单信息：{}",order.toString());
        orderService.save(order);
        return ReturnData.success("新增订单成功",order);
    }

    @PutMapping("/change")
    public ReturnData<String> update(@RequestBody Order order){
        log.info("更新订单，订单信息：{}", order.toString());
        orderService.updateById(order);
        return ReturnData.success("修改订单成功", null);
    }

    @DeleteMapping("/change")
    public ReturnData<String> delete(@RequestBody Order order){
        log.info("删除订单，订单信息：{}",order.toString());
        orderService.removeById(order);
        return ReturnData.success("删除订单成功", null);
    }
}
