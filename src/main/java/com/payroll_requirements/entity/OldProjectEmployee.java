package com.payroll_requirements.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2023/10/21 21:42
 */
@Data
@TableName(value = "old_project_employee")
public class OldProjectEmployee implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;//主键，通过自定义注解使用雪花算法生成
    private Long project_id;//项目id
    private Long employee_id;//参与员工id
    private Integer total_work_hour;//参与员工总工时
    private String name;
}
