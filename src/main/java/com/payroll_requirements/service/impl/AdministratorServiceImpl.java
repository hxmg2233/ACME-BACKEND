package com.payroll_requirements.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.payroll_requirements.entity.Administrator;
import com.payroll_requirements.mapper.AdministratorMapper;
import com.payroll_requirements.service.AdministratorService;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2023/10/20 21:17
 */
@Service
public class AdministratorServiceImpl
        extends ServiceImpl<AdministratorMapper, Administrator>
        implements AdministratorService {
    /**
     * @description 通过用户名查找
     * @param administrator
     * @return com.payroll_requirements.entity.Administrator
     * @author Administrator
     * @date 2023/10/20 21:15
     */
    @Override
    public Administrator getByUserName(Administrator administrator){
        LambdaQueryWrapper<Administrator> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Administrator::getUsername, administrator.getUsername());
        return getOne(wrapper);
    }
}
