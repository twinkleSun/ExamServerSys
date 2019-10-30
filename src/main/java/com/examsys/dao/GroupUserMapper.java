package com.examsys.dao;

import com.examsys.model.GroupUser;
import com.examsys.model.entity.GroupUserEntity;
import com.examsys.model.entity.UserGroupEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface GroupUserMapper {

    List<GroupUserEntity> selectGroupUser();

    List<GroupUserEntity> selectStudent(Integer exam_id);

    List<UserGroupEntity> selectUserGroup();

    List<GroupUser> selectByGroupId(Integer group_id);

    GroupUserEntity selectGroupUserByGid(Integer groupId);

    int insert(GroupUser groupUser);

    int delete(@Param("group_id")Integer group_id, @Param("student_id")Integer student_id);

    List<GroupUser> selectByUserId(Integer user_id);

    int deleteByUserId(Integer student_id);

    int deleteByGroupId(Integer group_id);

    int insertUsertoGroup(GroupUser gu);

    List<GroupUser> selectOfNoStart(int groupId);

    GroupUser selectByGroupUser(GroupUser groupUser);




}