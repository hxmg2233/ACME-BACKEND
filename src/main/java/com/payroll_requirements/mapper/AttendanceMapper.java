package com.payroll_requirements.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.payroll_requirements.entity.Attendance;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttendanceMapper extends BaseMapper<Attendance> {
}
