package com.yang.test;

import com.yang.entity.Tag;
import com.yang.entity.User;
import com.yang.mapper.UserMapper;
import com.yang.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author yanglh
 * @ className:
 * @ description:
 * @ create 2021-03-22 11:31
 **/
public class MybatisInterfaceTestCase {
    private SqlSession sqlSession;
    @Before
    public void init() {
        sqlSession = MyBatisUtil.getSqlSession();
    }
    @After
    public void close() {
        sqlSession.close();
    }
    @Test
    public void add() {
        //  创建接口的实现类
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User("aaa","shanghai","222");
        int rows = userMapper.add(user);
        System.out.println("rows--->" + rows);
        System.out.println("主键--->" + user.getId());
        sqlSession.commit();
    }
    @Test
    public void update() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findById(12);
        user.setCountryId(10);
        int row = userMapper.update(user);
        System.out.println(row);
        sqlSession.commit();
    }
    @Test
    public void findOne() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findById(12);
        System.out.println("查找到user: " + user);
    }
    @Test
    public void findAll() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.findAll();
        for (User user : userList) {
            System.out.println("查询全部：" + user);
        }
    }
    @Test
    public void delete() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.delete(10);
        sqlSession.commit();
    }
    @Test
    public void page() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.page(0,3);
        for (User user : userList) {
            System.out.println(user);
        }

    }
    @Test
    public void find() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.find(13);
        System.out.println(user);
        System.out.println("姓名：" + user.getUserName() + ";国家：" + user.getCountry().getCountryName());
    }
    @Test
    public void findByIdWithTag() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findByIdWithTag(1);
        System.out.println("user :" + user);
        List<Tag> tagList = user.getTagList();
        for (Tag tag : tagList) {
            System.out.println(tag);
        }

    }

    @Test
    public void batchSaveUser() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user1 = new User("AA","河南","121");
        User user2 = new User("BB","河南","121");
        User user3 = new User("CC","河南","121");
        List<User> userList = Arrays.asList(user1,user2,user3);
        int num = userMapper.batchSaveUser(userList);
        sqlSession.commit();
        System.out.println("受影响行数：" + num);
    }
}
