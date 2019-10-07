package com.examsys.service.Impl;

import com.examsys.dao.GroupUserMapper;
import com.examsys.dao.GroupMapper;
import com.examsys.model.Group;
import com.examsys.model.GroupUser;
import com.examsys.model.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by twinkleStar on 2019/9/4.
 */

@Repository
public class GroupServiceImpl{

    @Autowired
    GroupMapper groupMapper;
    @Autowired
    GroupUserMapper groupUserMapper;

    /**
     * 添加单个组
     * @param group
     * @return
     */
    public ResponseEntity addSingleGroup(Group group){
        ResponseEntity responseEntity=new ResponseEntity();
        Group infoAlready=groupMapper.selectByName(group.getName());
        if(infoAlready!=null){
            responseEntity.setStatus(-1);
            responseEntity.setMsg("组已存在,组名不可重复");
            return responseEntity;
        }else{
            int res=groupMapper.insert(group);

            if(res<0){
                throw new RuntimeException("添加组失败");
//                responseEntity.setStatus(-1);
//                responseEntity.setMsg("添加失败");
            }else{
                responseEntity.setStatus(200);
                responseEntity.setMsg("添加组成功");
                responseEntity.setData(group);
            }

            return responseEntity;
        }

    }

    /**
     * 删除单个组
     * todo:暂不可用
     * @param map
     * @return
     */
    public ResponseEntity deleteSingleGroup(Map<String,Integer> map){
        int groupId=map.get("id");
        List<GroupUser> groupUsers=groupUserMapper.selectByGroupId(groupId);
        if (groupUsers==null ||groupUsers.size()==0){

            //todo:加上试卷后需要改动
        }else {
            int delRelation=groupUserMapper.deleteByGroupId(groupId);
        }
        int delRes=groupMapper.deleteByPrimaryKey(groupId);
        ResponseEntity responseEntity=new ResponseEntity();
        responseEntity.setStatus(200);
        responseEntity.setMsg("删除成功");
        return responseEntity;
    }

    /**
     * 根据学生ID获取所在组
     * @param userId
     * @return
     */
    public ResponseEntity getGroupByUserId(int userId){
        ResponseEntity responseEntity=new ResponseEntity();
        List<Group> groupList=groupMapper.selectByUserId(userId);
        if (groupList != null || groupList.size()!=0) {
            responseEntity.setStatus(200);
            responseEntity.setMsg("查询成功");
            responseEntity.setData(groupList);
        }else{
            responseEntity.setStatus(-1);
            responseEntity.setMsg("该用户不属于任何一个组");
        }

        return responseEntity;
    }

}
