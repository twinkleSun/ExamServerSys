package com.examsys.service.Impl;

import com.examsys.dao.GroupUserMapper;
import com.examsys.dao.UserinfoMapper;
import com.examsys.model.GroupUser;
import com.examsys.model.Userinfo;
import com.examsys.model.entity.GroupUserInfo;
import com.examsys.model.entity.ResponseEntity;
import com.examsys.model.entity.UserGroupInfo;
import com.examsys.service.IGroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by twinkleStar on 2019/9/3.
 */
@Repository
public class GroupUserServiceImpl implements IGroupUserService {

    @Autowired
    GroupUserMapper groupUserMapper;

    @Autowired
    UserinfoMapper userinfoMapper;

    /**
     * 获取组+成员信息
     * @return
     */
    public ResponseEntity getGroupUserInfo(){
        List<GroupUserInfo> groupUserInfoList=groupUserMapper.selectGroupUserInfo();
        ResponseEntity responseEntity=new ResponseEntity();
        if(groupUserInfoList==null || groupUserInfoList.size()==0){
            responseEntity.setStatus(-1);
            responseEntity.setMsg("组不存在");
        }else {
            responseEntity.setStatus(200);
            responseEntity.setMsg("查询成功");
            responseEntity.setData(groupUserInfoList);
        }

        return responseEntity;
    }



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
            groupUser.setUserId(studentIds[i]);
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

    public ResponseEntity  getGroupUserByGid(int groupId){
        GroupUserInfo groupUserInfo=groupUserMapper.selectGroupUserByGid(groupId);
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

    public ResponseEntity updateUserGroupRelation(Map<String,Object> map){
        int userId=Integer.parseInt(String.valueOf(map.get("id")));

        Userinfo userinfo =new Userinfo();
        userinfo.setId(userId);
        userinfo.setUserName((String) map.get("userName"));
        userinfo.setPassword((String) map.get("password"));
        userinfo.setUserType((String) map.get("userType"));

        if(userId != 0){
            userinfoMapper.updateByPrimaryKey(userinfo);
        }else {
            userinfoMapper.insert(userinfo);
        }

        userId = userinfo.getId();

        ArrayList<Integer> addIds=(ArrayList<Integer>)map.get("group_add");
        ArrayList<Integer> deleteIds=(ArrayList<Integer>)map.get("group_del");

        for (int j=0;j<deleteIds.size();j++){
            int res=groupUserMapper.delete(deleteIds.get(j),userId);
        }

        for(int i=0;i<addIds.size();i++){
            GroupUser groupUser=new GroupUser();
            groupUser.setGroupId(addIds.get(i));
            groupUser.setUserId(userId);
            int res=groupUserMapper.insertUsertoGroup(groupUser);
        }

        //todo:没做校验
        ResponseEntity responseEntity=new ResponseEntity();
        responseEntity.setStatus(200);
        responseEntity.setMsg("更新成功");
        return responseEntity;
    }


    /**
     * 获取组+成员信息
     * @return
     */
    public ResponseEntity getUserGroupInfo(){
        List<UserGroupInfo> userGroupInfos=groupUserMapper.selectUserGroupInfo();
        ResponseEntity responseEntity=new ResponseEntity();
        if(userGroupInfos==null || userGroupInfos.size()==0){
            responseEntity.setStatus(-1);
            responseEntity.setMsg("组不存在");
        }else {
            responseEntity.setStatus(200);
            responseEntity.setMsg("查询成功");
            responseEntity.setData(userGroupInfos);
        }

        return responseEntity;
    }

}
