package com.payroll_requirements.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.payroll_requirements.entity.Employee;
import com.payroll_requirements.entity.OldProjectEmployee;
import com.payroll_requirements.mapper.OldProjectEmployeeMapper;
import com.payroll_requirements.service.OldProjectEmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2023/10/21 21:59
 */
@Service
public class OldProjectEmployeeServiceImpl
        extends ServiceImpl<OldProjectEmployeeMapper, OldProjectEmployee>
        implements OldProjectEmployeeService {


    //项目id加员工id
    @Override
    public OldProjectEmployee getByProjectId(Long projectid, Long employeeid) {
        LambdaQueryWrapper<OldProjectEmployee> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OldProjectEmployee::getProject_id, projectid);
        wrapper.eq(OldProjectEmployee::getEmployee_id, employeeid);
        return super.getOne(wrapper);
    }

    @Override
    public List<OldProjectEmployee> getByEmployeeId(Long employeeid) {
        LambdaQueryWrapper<OldProjectEmployee> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OldProjectEmployee::getEmployee_id, employeeid);
        return super.list(wrapper);
    }
}
