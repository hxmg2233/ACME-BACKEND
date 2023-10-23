package com.payroll_requirements.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.payroll_requirements.entity.Attendance;
import com.payroll_requirements.entity.Order;
import com.payroll_requirements.mapper.OrderMapper;
import com.payroll_requirements.service.OrderService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2023/10/21 13:23
 */
@Service
public class OrderServiceImpl
        extends ServiceImpl<OrderMapper, Order>
        implements OrderService {
    @Override
    public List<Order> getAllOrder(Long userid, Date starttime, Date endtime) {
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Order::getEmployee_id, userid);
        wrapper.ge(Order::getCreate_date, starttime);
        wrapper.le(Order::getCreate_date, endtime);
        return this.list(wrapper);
    }
}
