package com.examsys.dao;

import com.examsys.model.Group;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface GroupMapper {

    Group selectByName(String groupName);

    int insert(Group group);

    int deleteByPrimaryKey(Integer id);

    List<Group> selectByUserId(Integer userId);

    int updateByPrimaryKey(Group record);





//    Groupinfo selectByPrimaryKey(Integer id);
//
//    List<Groupinfo> selectAll();
//











}