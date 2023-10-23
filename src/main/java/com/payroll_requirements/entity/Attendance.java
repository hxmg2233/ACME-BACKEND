package com.payroll_requirements.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2023/10/21 13:39
 */
@Data
public class Attendance implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private Long employee_id;
    private Integer working_hour;
    private Date work_year_mouth_day;
}
