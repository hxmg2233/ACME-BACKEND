package com.payroll_requirements.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Administrator implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String username;
    private String password;
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
