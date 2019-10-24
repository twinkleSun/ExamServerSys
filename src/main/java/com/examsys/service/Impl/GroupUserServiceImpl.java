package com.examsys.service.Impl;

import com.examsys.dao.GroupUserMapper;
import com.examsys.dao.UserMapper;
import com.examsys.model.GroupUser;
import com.examsys.model.User;
import com.examsys.model.entity.GroupUserEntity;
import com.examsys.model.entity.ResponseEntity;
import com.examsys.model.entity.UserGroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by twinkleStar on 2019/9/3.
 */
@Service
@Repository
public class GroupUserServiceImpl{

    @Autowired
    GroupUserMapper groupUserMapper;

    @Autowired
    UserMapper userinfoMapper;

    /**
     * 获取组+成员信息
     * @return
     */
    public ResponseEntity getGroupUser(){
        List<GroupUserEntity> groupUserList=groupUserMapper.selectGroupUser();
        ResponseEntity responseEntity=new ResponseEntity();
        if(groupUserList==null || groupUserList.size()==0){
            responseEntity.setStatus(-1);
            responseEntity.setMsg("不存在任何组和成员信息");
        }else {
            responseEntity.setStatus(200);
            responseEntity.setMsg("查询成功");
            responseEntity.setData(groupUserList);
        }
        return responseEntity;
    }



    /**
     * 获取成员+组信息
     * @return
     */
    public ResponseEntity getUserGroup(){
        List<UserGroupEntity> userGroupList=groupUserMapper.selectUserGroup();
        ResponseEntity responseEntity=new ResponseEntity();
        if(userGroupList==null || userGroupList.size()==0){
            responseEntity.setStatus(-1);
            responseEntity.setMsg("不存在任何成员和组信息");
        }else {
            responseEntity.setStatus(200);
            responseEntity.setMsg("查询成功");
            responseEntity.setData(userGroupList);
        }

        return responseEntity;
    }

    /**
     * 根据组ID获取所有学生
     * @param groupId
     * @return
     */
    public ResponseEntity  getGroupUserByGid(int groupId){
        GroupUserEntity groupUserInfo=groupUserMapper.selectGroupUserByGid(groupId);
        ResponseEntity responseEntity=new ResponseEntity();
        if(groupUserInfo==null){
            responseEntity.setStatus(-1);
            responseEntity.setMsg("组不存在");
        } else {
            responseEntity.setStatus(200);
            responseEntity.setMsg("查询成功");
            responseEntity.setData(groupUserInfo);
        }

        return responseEntity;
    }

    /**
     * 向某个组添加学生
     * todo:没检查
     * @param map
     * @return
     */
    public ResponseEntity addNewGroupUser(Map<String,Object> map){
        ResponseEntity responseEntity=new ResponseEntity();

        int groupId=Integer.parseInt(String.valueOf(map.get("group_id")));

        ArrayList<Integer> stuObj=(ArrayList<Integer>)map.get("student_id");
        int length = stuObj.size();

        int[] studentIds=new int[length];

        GroupUser groupUser=new GroupUser();
        groupUser.setGroupId(groupId);

        int flag=0;

        String resStr="未添加成功的groupId为"+groupId+",未添加成功的studentID见data返回值";
        ArrayList<Integer> resId=new ArrayList<Integer>();
        for(int i=0;i<length;i++){
            studentIds[i]=stuObj.get(i);
            groupUser.setStudentId(studentIds[i]);
            int res=groupUserMapper.insert(groupUser);
            if(res<0){
                flag++;
                resId.add(studentIds[1]);
            }
        }

        if(flag==0){
            responseEntity.setStatus(200);
            responseEntity.setMsg("添加成功");
        }else{
            responseEntity.setMsg(resStr);
            responseEntity.setStatus(-1);
            responseEntity.setData(resId);
        }

        return responseEntity;
    }

    /**
     * todo:该方法有待修改
     * @param map
     * @return
     */
    public ResponseEntity updateUserGroupRelation(Map<String,Object> map){
        ResponseEntity responseEntity=new ResponseEntity();
        int userId=Integer.parseInt(String.valueOf(map.get("id")));

        User userinfo =new User();
        userinfo.setId(userId);
        userinfo.setName((String) map.get("userName"));
        userinfo.setPassword((String) map.get("password"));
        userinfo.setRole((String) map.get("userType"));

        int res = 0;
        if(userId != 0){
            res = userinfoMapper.updateByPrimaryKey(userinfo);
        } else {
            res = userinfoMapper.insert(userinfo);
        }

        if(res <= 0) {
            responseEntity.setMsg("更新失败");
            responseEntity.setStatus(-1);
            return responseEntity;
        }

        userId = userinfo.getId();
        int respp = groupUserMapper.deleteByUserId(userId);

        if(respp <= 0) {
            responseEntity.setMsg("更新失败");
            responseEntity.setStatus(-1);
            return responseEntity;
        }

        ArrayList<Map<String,Object>> groupList= (ArrayList<Map<String, Object>>) map.get("group_list");

        int flag = 0;
        if (groupList != null && groupList.size() !=0 ) {
            for (int j=0;j<groupList.size();j++){
                int groupId = (int) groupList.get(j).get("group_id");
                int resp = groupUserMapper.insertUsertoGroup(groupId,userId);
                if(resp < 0) {
                    flag++;
                }
            }
        }

        //todo:没做校验
        if(flag==0){
            responseEntity.setStatus(200);
            responseEntity.setMsg("更新成功");
        }else{
            responseEntity.setMsg("更新失败");
            responseEntity.setStatus(-1);
        }
        return responseEntity;
    }
}
