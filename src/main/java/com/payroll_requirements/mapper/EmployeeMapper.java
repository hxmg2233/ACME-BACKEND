package com.payroll_requirements.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.payroll_requirements.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
