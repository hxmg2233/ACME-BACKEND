package com.payroll_requirements.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2023/10/16 14:33
 */
@Data
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;//主键，通过自定义注解使用雪花算法生成
    private String username;//用户名，保证唯一
    private String password;//密码，默认为123456
    private String name;//人名
    private Integer employee_type;//员工类型，决定付款方式，0：小时工，1：固定薪资，2：佣金
    private String mailing_address;//通信地址
    private String phone;//手机号
    private Integer payment;
    private String bank;
    private String bank_id;
    private String social_security_number;//社会安全号码，只有9位数字
    private Integer standard_tax_deduction;//标准税收减免
    private Integer other_tax_deduction;//其他税收减免
    private Integer salary;//时薪，薪资，以分为单位
    private Integer commission_rate;//佣金率，百分比，0-100
    private Integer max_working_hour;//最大工时
    private Integer vacation;//假期时长

    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime create_time;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime update_time;
    //创建人
    @TableField(fill = FieldFill.INSERT)
    private Long create_user;
    //修改人
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long update_user;
}
