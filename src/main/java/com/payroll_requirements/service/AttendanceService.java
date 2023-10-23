package com.payroll_requirements.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.payroll_requirements.entity.Attendance;

import java.sql.Date;
import java.util.List;

public interface AttendanceService extends IService<Attendance> {
    public List<Attendance> getAllWorkTime(Long userid, Date starttime, Date endtime);
}
