package com.payroll_requirements;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@Slf4j
@SpringBootTest
class PayrollRequirementsApplicationTests {




    @Test
    void basejava(){

    }

    @Test
    void contextLoads() throws IOException {
/*        //1.构建MyBatis
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2.获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取Mapper代理
        AdministratorMapper administratorMapper = sqlSession.getMapper(AdministratorMapper.class);
        List<Administrator> administrators = administratorMapper.selectAllAdministrator();

        System.out.println(administrators);
        //4.释放资源
        sqlSession.close();*/
    }

    @Test
    void test1() throws IOException {
/*        //1.构建MyBatis
*//*        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);*//*

        //1.通过工具类获得session
        SqlSession sqlSession = SqlSessionUtils.getSession();
        //3.获取Mapper代理
        AdministratorMapper administratorMapper = sqlSession.getMapper(AdministratorMapper.class);
        List<Administrator> administrators = administratorMapper.selectAllAdministrator();

        log.info(administrators.toString());
        //4.释放资源
        sqlSession.close();*/
    }
}
