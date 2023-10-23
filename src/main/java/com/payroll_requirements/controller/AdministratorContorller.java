package com.payroll_requirements.controller;

import com.payroll_requirements.common.BaseContext;
import com.payroll_requirements.common.ReturnData;
import com.payroll_requirements.entity.Administrator;
import com.payroll_requirements.entity.Employee;
import com.payroll_requirements.mapper.AdministratorMapper;
import com.payroll_requirements.service.AdministratorService;
import com.payroll_requirements.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrator")
@Slf4j
public class AdministratorContorller {

    @Autowired
    private AdministratorService administratorService;
    @PostMapping("/login")
    public ReturnData<String> login(HttpServletRequest request, @RequestBody Administrator administrator){
        log.info("管理员尝试登录，用户名:{}，密码:{}", administrator.getUsername(), administrator.getPassword());

        Administrator checklogin = administratorService.getByUserName(administrator);
        if (checklogin == null){
            return ReturnData.error("用户不存在");
        }
        String password = DigestUtils.md5DigestAsHex(administrator.getPassword().getBytes());
        if (!password.equals(checklogin.getPassword())){
            return ReturnData.error("密码错误");
        }

        request.getSession().setAttribute("administrator", checklogin.getId());
        BaseContext.setCurrentId(checklogin.getId());
        return ReturnData.success("管理员登录成功",null);
    }
    @PostMapping("/logout")
    public ReturnData<String> logout(HttpServletRequest request){
        //清理Session中保存的当前登录员工的id
        request.getSession().removeAttribute("administrator");
        return ReturnData.success("退出成功",null);
    }
}
