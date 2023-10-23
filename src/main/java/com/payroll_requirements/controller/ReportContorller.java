package com.payroll_requirements.controller;

import com.payroll_requirements.common.ReturnData;
import com.payroll_requirements.entity.*;
import com.payroll_requirements.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2023/10/23 9:36
 */
@Slf4j
@RestController
@RequestMapping("/report")
public class ReportContorller {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private OldProjectEmployeeService oldProjectEmployeeService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/type")
    public ReturnData<String> getReport(@RequestParam("type") Long type,
                                         @RequestParam("username") String username,
                                         @RequestParam("start_date") String start_time,
                                         @RequestParam("end_date") String end_time,
                                         @RequestParam("project_id") String project_id
    ){
        log.info("获取{}类报告, 用户名{}, 时间{}-{}", type, username,start_time, end_time);
        Employee byUsername = employeeService.getByUsername(username);
        if (type == 3){ //查假期的
            return ReturnData.success("查询成功", byUsername.getVacation().toString());
        }
        Date start_date, end_date;
        try {
            if (start_time == null){
                start_time = end_time.substring(0, 4) + "-01-01";
            }
            start_date =new Date(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(start_time).getTime());
            end_date =new Date(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(end_time).getTime());
            if (type == 1){ //查总工时
                List<Attendance> allWorkTime = attendanceService.getAllWorkTime(byUsername.getId(), start_date, end_date);
                Integer sumWorkTime = 0;
                for(Attendance i : allWorkTime){
                    sumWorkTime += i.getWorking_hour();
                }
                return ReturnData.success("查询成功", sumWorkTime.toString());
            }
            if (type == 2){ //查项目工时
                OldProjectEmployee project = oldProjectEmployeeService.getByProjectId(Long.valueOf(project_id), byUsername.getId());
                return ReturnData.success("查询成功", project.getTotal_work_hour().toString());
            }
            if (type == 4){ //查工资
                List<Attendance> allWorkTime = attendanceService.getAllWorkTime(byUsername.getId(), start_date, end_date);
                Long sumSalary = 0l;
                if (byUsername.getEmployee_type() == 0){ //小时工
                    for(Attendance i : allWorkTime){
                        if (i.getWorking_hour() <= byUsername.getMax_working_hour()){
                            sumSalary += (long) i.getWorking_hour() * byUsername.getSalary();
                        }else{
                            sumSalary += (long) byUsername.getMax_working_hour() * byUsername.getSalary()
                             + (long) (1.5 * (i.getWorking_hour() - byUsername.getMax_working_hour()) * byUsername.getSalary());
                        }//加班给1.5倍
                    }
                } else if (byUsername.getEmployee_type() == 1 || byUsername.getEmployee_type() == 2){ //计算固定工资
                    int a = getMonthDiff(end_date,start_date);
                    log.info("月份差值为{}", a);
                    sumSalary += a * byUsername.getSalary();
                }
                if(byUsername.getEmployee_type() == 2){ //计算佣金
                    List<Order> allOrder = orderService.getAllOrder(byUsername.getId(), start_date, end_date);
                    for(Order i : allOrder){
                        sumSalary += i.getPrice() * byUsername.getCommission_rate();
                    }
                }
                log.info("工资为{}", sumSalary);
                int index = sumSalary.toString().length() - 2;
                String s = sumSalary.toString().substring(0, index) + '.' + sumSalary.toString().substring(index);
                return ReturnData.success("查询成功", s);
            }



        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/allproject")
    public ReturnData<List<OldProjectEmployee>> getAllProject(@RequestParam("username") String username){
        Employee byUsername = employeeService.getByUsername(username);
        List<OldProjectEmployee> byEmployeeId = oldProjectEmployeeService.getByEmployeeId(byUsername.getId());
        return ReturnData.success("查询成功", byEmployeeId);
    }

    private static int getMonthDiff(Date largeDate, Date smallDate) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(largeDate);
        c2.setTime(smallDate);
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        // 获取年的差值
        int yearInterval = year1 - year2;
        // 获取月数差值
        int monthInterval = month1 - month2;
        int monthsDiff = yearInterval * 12 + monthInterval;
        return monthsDiff;
    }
}
