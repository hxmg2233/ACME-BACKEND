package com.payroll_requirements.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.payroll_requirements.entity.OldProjectEmployee;

import java.util.List;

public interface OldProjectEmployeeService extends IService<OldProjectEmployee> {

    public OldProjectEmployee getByProjectId(Long projectid, Long employeeid);

    public List<OldProjectEmployee> getByEmployeeId(Long employeeid);
}
