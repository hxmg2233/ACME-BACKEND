package com.payroll_requirements.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Administrator
 * @version 1.0
 * @description: 自定义元数据对象处理器
 * @date 2023/10/20 20:53
 */
@Component
@Slf4j
public class MyMetaObjectHandle implements MetaObjectHandler {

    /**
     * @description 插入操作，自动填充公共字段
     * @param metaObject
     * @return void
     * @author Administrator
     * @date 2023/10/20 21:04
    */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段自动填充[insert]...");
        log.info(metaObject.toString());
        Long id = Thread.currentThread().getId();
        log.info("线程id为:{}", id);
        log.info("用户id为{}",BaseContext.getCurrentId());

        metaObject.setValue("create_time", LocalDateTime.now());
        metaObject.setValue("update_time", LocalDateTime.now());
        metaObject.setValue("create_user", BaseContext.getCurrentId());
        metaObject.setValue("update_user", BaseContext.getCurrentId());
    }

    /**
     * @description 更新操作，自动填充公共字段
     * @param metaObject
     * @return void
     * @author Administrator
     * @date 2023/10/20 21:04
    */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段自动填充[update]...");
        log.info(metaObject.toString());
        Long id = Thread.currentThread().getId();
        log.info("线程id为:{}", id);

        metaObject.setValue("update_time", LocalDateTime.now());
        metaObject.setValue("update_user", BaseContext.getCurrentId());
    }
}
