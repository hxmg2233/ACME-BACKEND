package com.payroll_requirements.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.payroll_requirements.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
