package com.payroll_requirements.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.payroll_requirements.entity.OldProject;
import com.payroll_requirements.mapper.OldProjectMapper;
import com.payroll_requirements.service.OldProjectService;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2023/10/21 21:58
 */
@Service
public class OldProjectServiceImpl
        extends ServiceImpl<OldProjectMapper, OldProject>
        implements OldProjectService {
}
