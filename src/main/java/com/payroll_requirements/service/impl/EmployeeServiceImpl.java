package com.payroll_requirements.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.payroll_requirements.entity.Administrator;
import com.payroll_requirements.entity.Employee;
import com.payroll_requirements.mapper.EmployeeMapper;
import com.payroll_requirements.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2023/10/20 21:51
 */
@Service
public class EmployeeServiceImpl
        extends ServiceImpl<EmployeeMapper, Employee>
        implements EmployeeService {
    @Override
    public Page getPage(int page, int pageSize) {
        //构造分页构造器
        Page pageInfo = new Page(page,pageSize);
        //构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        //添加排序条件
        queryWrapper.orderByDesc(Employee::getUpdate_time);
        this.page(pageInfo,queryWrapper);
        return pageInfo;
    }

    @Override
    public Employee getByUsername(String username) {
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Employee::getUsername, username);
        return getOne(wrapper);
    }
}
