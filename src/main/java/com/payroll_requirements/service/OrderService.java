package com.payroll_requirements.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.payroll_requirements.entity.Order;

import java.sql.Date;
import java.util.List;

public interface OrderService extends IService<Order> {

    public List<Order> getAllOrder(Long userid, Date starttime, Date endtime);
}
