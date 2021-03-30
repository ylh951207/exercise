package com.yang.test;

import com.yang.entity.User;
import com.yang.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author yanglh
 * @ className:
 * @ description:
 * @ create 2021-03-22 11:34
 **/
public class MybatisTestCase {
    /**
     * @Author:zhangyu
     * @param:
     * @Description:查找对象(一个或者对象集合)
     * @Date:20:02 2017/10/23
     */
    @Test
    public void findOne() throws IOException {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
       /* 1.加载配置文件
        Reader reader = Resources.getResourceAsReader("mybatis.xml");
        2.创建SqlSessionFactory
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        3.创建SqlSession
        SqlSession sqlSession = sessionFactory.openSession();*/
        //4.操作数据库
        User user = sqlSession.selectOne("com.kaishengit.mapper.UserMapper.findById",1);
        System.out.println(user);
        //5.关闭session
        sqlSession.close();
    }

    @Test
    public void findAll() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        List<User> userList = sqlSession.selectList("com.kaishengit.mapper.UserMapper.findAll");
        for(User user : userList) {
            System.out.println(user);
        }
        sqlSession.close();
    }
    /**
     * @Author:zhangyu
     * @param:
     * @Description:添加
     * @Date:20:40 2017/10/23
     */
    @Test
    public void add() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        User user = new User("zhangyu","henan","123");
        sqlSession.insert("com.kaishengit.mapper.UserMapper.add",user);
        sqlSession.commit(); //提交事务
        sqlSession.close();
    }

    @Test
    public void update(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        User user = sqlSession.selectOne("com.kaishengit.mapper.UserMapper.findById",2);
        user.setAddress("河南");
        sqlSession.update("com.kaishengit.mapper.UserMapper.update",user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void delete() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        sqlSession.delete("com.kaishengit.mapper.UserMapper.delete",7);
        sqlSession.commit();
        sqlSession.close();
    }
}
