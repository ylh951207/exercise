package com.yang.mapper;

import com.yang.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yanglh
 * @ className:
 * @ description:
 * @ create 2021-03-22 11:24
 **/
public interface UserMapper {
    int add(User user);
    int update(User user);
    int delete(int id);
    List<User> findAll();
    User findById(int id);
    List<User> page(@Param("offset")int offset, @Param("size")int size);
    User find(int id);

    User findByIdWithTag(int userId);
    int batchSaveUser(@Param("userList") List<User> userList);
}
