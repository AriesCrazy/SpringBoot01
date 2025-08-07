package com.by.dao;

import com.by.entity.User;
import com.by.entity.UserQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    User login(String tel, String password);
    User loginById(Integer id);
    User check(String tel);
    int insert(User user);

    List<User> select(UserQuery query);

    int updateInfo(Integer id, String nickName, String tel, String email,String password );

    int deleteUserRoles(Integer id);
    int insertUserRoles(Integer id, List<Integer> roleids);

    int delete1(Integer id);
    int delete2(Integer id);
}
