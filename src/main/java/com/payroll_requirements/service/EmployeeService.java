package com.payroll_requirements.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.payroll_requirements.entity.Employee;

public interface EmployeeService extends IService<Employee> {

    public Page getPage(int page,int pageSize);

    public Employee getByUsername(String username);
}
