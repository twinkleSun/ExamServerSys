package com.examsys.dao;

import com.examsys.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    User selectByUsername(String username);

    User selectByUser(User record);

    int insert(User record);

    int updateByPrimaryKey(User record);

    int deleteByPrimaryKey(Integer id);

//    Userinfo selectByPrimaryKey(Integer id);
//
//    List<Userinfo> selectAll();
//
//    /**
//     * 设置数据库自增长
//     * @return
//     */
//    int alterUserinfoable();
//
//    List<String> selectTest();
}