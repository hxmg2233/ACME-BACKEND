package com.payroll_requirements;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@ServletComponentScan //自动扫描Servlet（控制器）、Filter（过滤器）、Listener（监听器），并注册
@EnableTransactionManagement
public class PayrollRequirementsApplication {

    public static void main(String[] args) {SpringApplication.run(PayrollRequirementsApplication.class, args);}

}
