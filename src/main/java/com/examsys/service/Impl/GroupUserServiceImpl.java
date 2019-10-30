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

        int userId =0;
        if (String.valueOf(map.get("id")) == null || String.valueOf(map.get("id")) == "") {

        }else{
            userId=Integer.parseInt(String.valueOf(map.get("id")));
        }

        User userinfo =new User();
        userinfo.setId(userId);
        userinfo.setName((String)map.get("userName"));
        userinfo.setPassword((String)map.get("password"));
        userinfo.setRole((String)map.get("userType"));

        int res = 0;
        if(userId != 0){

            User userAlready  = userinfoMapper.selectByUser(userinfo);
            if (userAlready != null) {
                responseEntity.setMsg("数据库中已存在相同的用户名和密码，请重新修改");
                responseEntity.setStatus(-1);
                return responseEntity;
            }else{
                res = userinfoMapper.updateByPrimaryKey(userinfo);

            }

        } else {
            User userAlready  = userinfoMapper.selectByUser(userinfo);
            if (userAlready == null) {
                res = userinfoMapper.insert(userinfo);
            }else{
                responseEntity.setMsg("该用户已存在");
                responseEntity.setStatus(-1);
                return responseEntity;
            }

        }

        if(res < 0) {
            responseEntity.setMsg("更新失败");
            responseEntity.setStatus(-1);
            return responseEntity;
        }

        userId = userinfo.getId();
        int respp = groupUserMapper.deleteByUserId(userId);

        if(respp < 0) {
            responseEntity.setMsg("更新gu失败");
            responseEntity.setStatus(-1);
            return responseEntity;
        }

        List<Map<String,Object>> groupList= (List<Map<String, Object>>)map.get("group_list");


        if (groupList != null && groupList.size() !=0 ) {
            for (int j=0;j<groupList.size();j++){
                Map<String,Object> groupUser = groupList.get(j);

                int groupId = Integer.valueOf(groupUser.get("group_id").toString());
                GroupUser gu = new GroupUser();
                gu.setStudentId(userId);
                gu.setGroupId(groupId);
                int resp = groupUserMapper.insertUsertoGroup(gu);
                if(resp < 0) {
                    throw new RuntimeException("数据库错误");
                }
            }
        }

        responseEntity.setStatus(200);
        responseEntity.setMsg("更新成功");

        return responseEntity;
    }

    public ResponseEntity selectOfNoStart(Map<String,Object> map){
        ResponseEntity responseEntity = new ResponseEntity();
        int groupId = Integer.valueOf(map.get("group_id").toString());
        ArrayList<Integer> stuIds = (ArrayList<Integer>)map.get("student_id");
        List<GroupUser> groupUserList = groupUserMapper.selectOfNoStart(groupId);
        if(groupUserList == null || groupUserList.size() == 0){
            //进行delete

            int flag =200;
            for(int i=0;i<stuIds.size();i++){
                int res = groupUserMapper.delete(groupId,stuIds.get(i));
                if(res<0){
                    flag = -1;
                }
            }
            responseEntity.setStatus(flag);
            responseEntity.setMsg("删除成功");
            //responseEntity.setData(groupUserList);
            return responseEntity;
        }else{
            responseEntity.setStatus(-1);
            responseEntity.setMsg("该组关联的考试已经开始或者结束，组成员不可以修改");

        }
        return responseEntity;
    }


}
