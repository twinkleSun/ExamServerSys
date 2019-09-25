package com.examsys.dao;

import com.examsys.model.GroupUser;
import com.examsys.model.entity.GroupUserInfo;
import com.examsys.model.entity.UserGroupInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface GroupUserMapper {

    List<GroupUser> selectAll();

    /**
     * 获取组+成员信息
     * @return
     */
    List<GroupUserInfo> selectGroupUserInfo();


    GroupUserInfo selectGroupUserByGid(Integer groupId);

    /**
     * 插入组内成员
     * @param record
     * @return
     */
    int insert(GroupUser record);

    int delete(@Param("group_id")Integer group_id, @Param("user_id")Integer user_id);

    List<GroupUser> selectByUserId(Integer user_id);

    int deleteByUserId(Integer user_id);

    List<GroupUser> selectByGroupId(Integer group_id);

    int deleteByGroupId(Integer group_id);

    List<UserGroupInfo> selectUserGroupInfo();

    int insertUsertoGroup(GroupUser groupUser);
}