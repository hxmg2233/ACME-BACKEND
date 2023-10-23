package com.payroll_requirements.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.payroll_requirements.entity.Administrator;

public interface AdministratorService extends IService<Administrator> {
    public Administrator getByUserName(Administrator administrator);
}
