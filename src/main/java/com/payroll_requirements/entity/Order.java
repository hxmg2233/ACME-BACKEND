package com.payroll_requirements.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2023/10/21 13:17
 */
@Data
@TableName(value = "all_order")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;//主键，通过自定义注解使用雪花算法生成
    private String contact_person;//客户联系人
    private String biling_address;//联系地址
    private String product_name;//产品名字
    private Long price;//总价格，以分为单位
    private Date create_date;//订单时间
    private Long employee_id;//经手人

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
