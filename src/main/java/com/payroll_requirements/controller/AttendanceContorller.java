package com.payroll_requirements.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.payroll_requirements.common.ReturnData;
import com.payroll_requirements.entity.Attendance;
import com.payroll_requirements.entity.Employee;
import com.payroll_requirements.service.AttendanceService;
import com.payroll_requirements.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2023/10/21 13:43
 */
@Slf4j
@RestController
@RequestMapping("/attendance")
public class AttendanceContorller {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/add")
    public ReturnData<String> add_(@RequestParam("username") String username,
                                   @RequestParam("working_hour") Integer work_hour){
        LocalDate date = LocalDate.now();
        Date now = Date.valueOf(date);
        Employee byUsername = employeeService.getByUsername(username);
        List<Attendance> allWorkTime = attendanceService.getAllWorkTime(byUsername.getId(), now, now);
        if (allWorkTime.size() == 0 && work_hour > 0 && work_hour < 24){
            Attendance attendance = new Attendance();
            attendance.setEmployee_id(byUsername.getId());
            attendance.setWorking_hour(work_hour);
            attendance.setWork_year_mouth_day(now);
            attendanceService.save(attendance);
            return ReturnData.success("保存成功", null);
        }
        if (allWorkTime != null){
            return ReturnData.error("今天工时已保存");
        }
        return ReturnData.error("输入工时有误");
    }
}
