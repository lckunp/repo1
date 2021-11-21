package com.lagou.test;

import com.lagou.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {

    //快速入门测试方法
    @Test
    public void mybatisQuickStart() throws IOException {

        //1.加载核心配置文件
        InputStream input = Resources.getResourceAsStream("sqlMapConfig.xml");

        //2.获取sqlSessionFactory工厂对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(input);

        //3.获取sqlSession会话对象
        SqlSession session = factory.openSession();

        //4.执行sql 参数：statementid ：namespace.id
        List<User> users = session.selectList("UserMapper.findAll");

        //5.遍历打印结果
        for (User user : users) {
            System.out.println(user);
        }

        //6.关闭资源
        session.close();


    }

    @Test
    //测试新增用户
    public void testSave() throws IOException {

        //1.加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        //2.获取工厂对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //3.获取会话对象
        SqlSession sqlSession = factory.openSession(true);

        //4.调用api
        User user = new User();
        user.setUsername("猪八戒");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("高老庄");
        sqlSession.insert("UserMapper.saveUser",user);
        //手动提交事务
        //sqlSession.commit();

        //关闭资源
        sqlSession.close();
    }

    @Test
    //测试更新用户
    public void testUpdate() throws IOException {

        //1.加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        //2.获取工厂对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //3.获取会话对象
        SqlSession sqlSession = factory.openSession();

        //4.调用api
        User user = new User();
        user.setId(4);
        user.setUsername("lucy");
        user.setBirthday(new Date());
        user.setSex("女");
        user.setAddress("花果山");
        sqlSession.update("UserMapper.update",user);
        //手动提交事务
        sqlSession.commit();
        //关闭资源
        sqlSession.close();
    }



    @Test
    //测试删除用户
    public void testDelete() throws IOException {

        //1.加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        //2.获取工厂对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //3.获取会话对象
        SqlSession sqlSession = factory.openSession();

        //4.调用api
        sqlSession.delete("UserMapper.delete",4);
        //手动提交事务
        sqlSession.commit();
        //关闭资源
        sqlSession.close();
    }
}
