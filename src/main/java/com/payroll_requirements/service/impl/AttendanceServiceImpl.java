package com.payroll_requirements.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.payroll_requirements.entity.Attendance;
import com.payroll_requirements.entity.OldProjectEmployee;
import com.payroll_requirements.mapper.AttendanceMapper;
import com.payroll_requirements.service.AttendanceService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2023/10/21 13:42
 */
@Service
public class AttendanceServiceImpl
        extends ServiceImpl<AttendanceMapper, Attendance>
        implements AttendanceService {


    @Override
    public List<Attendance> getAllWorkTime(Long userid, Date starttime, Date endtime) {
        LambdaQueryWrapper<Attendance> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Attendance::getEmployee_id, userid);
        wrapper.ge(Attendance::getWork_year_mouth_day, starttime);
        wrapper.le(Attendance::getWork_year_mouth_day, endtime);
        return super.list(wrapper);
    }
}
