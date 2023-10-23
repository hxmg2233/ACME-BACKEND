package com.payroll_requirements.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.payroll_requirements.common.BaseContext;
import com.payroll_requirements.common.ReturnData;
import com.payroll_requirements.entity.Administrator;
import com.payroll_requirements.entity.Employee;
import com.payroll_requirements.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Random;


/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2023/10/20 22:01
 */
@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeContorller {

    @Autowired
    private EmployeeService employeeService;

    /**
     * @description 员工登录
     * @param request
     * @param employee
     * @return com.payroll_requirements.common.ReturnData<java.lang.String>
     * @author Administrator
     * @date 2023/10/20 22:59
    */
    @PostMapping("/login")
    public ReturnData<Integer> login(HttpServletRequest request, @RequestBody Employee employee){
        log.info("员工尝试登录，用户名:{}，密码:{}", employee.getUsername(), employee.getPassword());

        Employee checklogin = employeeService.getByUsername(employee.getUsername());
        if (checklogin == null){
            return ReturnData.error("用户不存在");
        }
        String password = DigestUtils.md5DigestAsHex(employee.getPassword().getBytes());
        if (!password.equals(checklogin.getPassword())){
            return ReturnData.error("密码错误");
        }
        request.getSession().setAttribute("employee", checklogin.getId());
        BaseContext.setCurrentId(checklogin.getId());


        return ReturnData.success("员工登录成功",checklogin.getEmployee_type());
    }

    /**
     * @description 新增员工，由管理员完成
     * @param employee
     * @return com.payroll_requirements.common.ReturnData<java.lang.String>
     * @author Administrator
     * @date 2023/10/20 22:08
    */
    @PostMapping("/change")
    public ReturnData<Employee> save(@RequestBody Employee employee){
        log.info("新增员工，员工信息：{}",employee.toString());

        //设置用户名
        Random random = new Random();
        int i1 = 0;
        while (true){
            i1 = random.nextInt(89999999) + 10000000; //生成一个8位的int值
            Employee check = employeeService.getByUsername(Integer.valueOf(i1).toString());
            if (check == null) break;
        }
        employee.setUsername(Integer.valueOf(i1).toString());
        //设置初始密码123456，需要进行md5加密处理
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        //设置最大工时
        employee.setMax_working_hour(8);
        //设置假期天数
        employee.setVacation(15);
        //设置工资单位为分
        employee.setSalary(employee.getSalary() * 100);
        //设置支付方式
        employee.setPayment(0);

        employeeService.save(employee);
        log.info("新增员工，员工信息：{}",employee.toString());
        return ReturnData.success("新增员工成功",employee);
    }

    /**
     * @description 根据员工id改信息，由管理员完成
     * @param employee
     * @return com.payroll_requirements.common.ReturnData<java.lang.String>
     * @author Administrator
     * @date 2023/10/20 22:53
    */
    @PutMapping("/change")
    public ReturnData<String> update(@RequestBody Employee employee){
        log.info(employee.toString());
        Employee byUsername = employeeService.getByUsername(employee.getUsername());
        employee.setId(byUsername.getId());
        if (employee.getPassword() != null){
            String temp = employee.getPassword();
            employee.setPassword(DigestUtils.md5DigestAsHex(temp.getBytes()));
        }
        employeeService.updateById(employee);
        return ReturnData.success("员工信息修改成功", null);
    }
    /**
     * @description 根据id删除员工，由管理员完成
     * @param employee
     * @return com.payroll_requirements.common.ReturnData<java.lang.String>
     * @author Administrator
     * @date 2023/10/20 22:56 
    */
    @DeleteMapping("/change")
    public ReturnData<String> delete(@RequestBody Employee employee){
        log.info("即将删除:{}", employee.toString());
        Employee byUsername = employeeService.getByUsername(employee.getUsername());
        employee.setId(byUsername.getId());
        employeeService.removeById(employee);
        return ReturnData.success("员工信息删除成功", null);
    }
    

    /**
     * @description 由用户名查询员工信息
     * @param username
     * @return com.payroll_requirements.common.ReturnData<com.payroll_requirements.entity.Employee>
     * @author Administrator
     * @date 2023/10/20 22:07
     */
    @GetMapping("/username")
    public ReturnData<Employee> getByUsername(@RequestParam("username") String username){
        log.info("根据id查询员工信息...");
        Employee employee = employeeService.getByUsername(username);
        if(employee != null){
            return ReturnData.success("成功", employee);
        }
        return ReturnData.error("没有查询到对应员工信息");
    }
    /**
     * @description 由id查询员工信息
     * @param id
     * @return com.payroll_requirements.common.ReturnData<com.payroll_requirements.entity.Employee>
     * @author Administrator
     * @date 2023/10/20 22:07
    */
    @GetMapping("/id/{id}")
    public ReturnData<Employee> getById(@PathVariable Long id){
        log.info("根据id查询员工信息...");
        Employee employee = employeeService.getById(id);
        if(employee != null){
            return ReturnData.success("成功", employee);
        }
        return ReturnData.error("没有查询到对应员工信息");
    }
    /**
     * @description 员工信息的分页查询，大概是用不上的
     * @param page
     * @param pageSize
     * @return com.payroll_requirements.common.ReturnData<com.baomidou.mybatisplus.extension.plugins.pagination.Page>
     * @author Administrator
     * @date 2023/10/20 22:19
    */
    @GetMapping("/page")
    public ReturnData<Page> page(int page,int pageSize){
        log.info("page = {},pageSize = {}" ,page,pageSize);
        //执行查询
        Page pageInfo = employeeService.getPage(page, pageSize);
        return ReturnData.success("成功", pageInfo);
    }
}
