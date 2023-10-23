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
 * @date 2023/10/21 14:07
 */
@Data
@TableName(value = "old_project")
public class OldProject implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;//主键，通过自定义注解使用雪花算法生成
    private String name;//项目名称
}
