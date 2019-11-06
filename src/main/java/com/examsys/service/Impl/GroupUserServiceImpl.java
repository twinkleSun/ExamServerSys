package com.examsys.service.Impl;

import com.examsys.dao.GroupUserMapper;
import com.examsys.dao.UserMapper;
import com.examsys.model.GroupUser;
import com.examsys.model.User;
import com.examsys.model.entity.GroupUserEntity;
import com.examsys.model.entity.ResponseEntity;
import com.examsys.model.entity.UserGroupEntity;
import com.examsys.util.error.ErrorMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
    UserMapper userMapper;

    /**
     * 获取所有组和组内成员信息
     * @return
     */
    public ResponseEntity getAllGroupAndUserList(){
        List<GroupUserEntity> groupAndUserList = groupUserMapper.selectGroupUser();
        if(groupAndUserList == null || groupAndUserList.size() == 0){
            return new ResponseEntity(ErrorMsgEnum.NO_GROUP_NO_USER);
        }else {
            return new ResponseEntity(200,"查询成功",groupAndUserList);
        }
    }


    /**
     * 获取考生和其所属的组列表
     * @return
     */
    public ResponseEntity getAllUserAndGroupList(){
        List<UserGroupEntity> userAndGroupList = groupUserMapper.selectUserGroup();
        if(userAndGroupList == null || userAndGroupList.size() == 0){
            return new ResponseEntity(ErrorMsgEnum.NO_USER_NO_GROUP);
        }else {
            return new ResponseEntity(200,"查询成功",userAndGroupList);
        }
    }


    /**
     * 根据组ID获取所有学生
     * @param groupId
     * @return
     */
    public ResponseEntity  getGroupUserByGid(int groupId){
        GroupUserEntity groupUserInfo = groupUserMapper.selectGroupUserByGid(groupId);
        if(groupUserInfo == null){
            //一般不会报这个错，组信息存在
            return new ResponseEntity(ErrorMsgEnum.NO_GROUP_NO_USER);
        } else {
            return new ResponseEntity(200,"查询成功",groupUserInfo);
        }

    }

    /**
     * 向某个组添加学生
     * @param map
     * @return
     */
    @Transactional
    public ResponseEntity updateGroupUser(Map<String,Object> map){

        int groupId=Integer.parseInt(String.valueOf(map.get("group_id")));
        ArrayList<Integer> stuObj=(ArrayList<Integer>)map.get("student_id");

        GroupUser groupUser=new GroupUser();
        groupUser.setGroupId(groupId);

        //删除所有关系
        groupUserMapper.deleteByGroupId(groupId);

        for(int i=0; i<stuObj.size(); i++){
            groupUser.setStudentId(stuObj.get(i));
            int res = groupUserMapper.insert(groupUser);
            if(res<0){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return new ResponseEntity(ErrorMsgEnum.DATABASE_ERROR);
            }
        }
        return new ResponseEntity(200,"组内添加学生成功");
    }

    /**
     * 修改单个考生，包括该考生所属的组
     * @param map
     * @return
     */
    @Transactional
    public ResponseEntity updateUserWithRelation(Map<String,Object> map){

        User userFront =new User();
        userFront.setName((String)map.get("userName"));
        userFront.setPassword((String)map.get("password"));
        userFront.setRole((String)map.get("userType"));
        if (String.valueOf(map.get("id")) == null || String.valueOf(map.get("id")) == "" || String.valueOf(map.get("id")).equals("0")) {

            //不存在则添加
            User userDB  = userMapper.selectByUser(userFront);
            if (userDB == null) {
                int tmp = userMapper.insert(userFront);
                if(tmp<0){
                    return new ResponseEntity(ErrorMsgEnum.DATABASE_ERROR);
                }
            }else{
                return new ResponseEntity(ErrorMsgEnum.USER_ALREADY_EXIST);
            }

        }else{
            //存在则修改
            userFront.setId(Integer.parseInt(String.valueOf(map.get("id"))));
            User userDB  = userMapper.selectByUser(userFront);

            if (userDB != null && userDB.getId() != userFront.getId()) {
                return new ResponseEntity(ErrorMsgEnum.USER_ALREADY_EXIST_SAME);
            }else{
                int tmp = userMapper.updateByPrimaryKey(userFront);
                if(tmp < 0) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return new ResponseEntity(ErrorMsgEnum.DATABASE_ERROR);
                }
            }
        }

        int userId = userFront.getId();
        //直接全部删除是不是不太好？
        //todo:或者设置下ID索引
        groupUserMapper.deleteByUserId(userId);

        List<Map<String,Object>> groupList= (List<Map<String, Object>>)map.get("group_list");

        //更新用户的组关系
        if (groupList != null && groupList.size() !=0 ) {
            for (int j=0;j<groupList.size();j++){
                Map<String,Object> groupUser = groupList.get(j);
                int groupId = Integer.valueOf(groupUser.get("group_id").toString());
                GroupUser gu = new GroupUser();
                gu.setStudentId(userId);
                gu.setGroupId(groupId);
                int tmp = groupUserMapper.insert(gu);
                if(tmp < 0) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return new ResponseEntity(ErrorMsgEnum.DATABASE_ERROR);
                }
            }
        }

        return new ResponseEntity(200,"更新用户信息成功");
    }

    /**
     * 组删除若干学生
     * @param map
     * @return
     */
    @Transactional
    public ResponseEntity groupDelStudents(Map<String,Object> map){
        int groupId = Integer.valueOf(map.get("group_id").toString());
        ArrayList<Integer> stuIds = (ArrayList<Integer>)map.get("student_id");

        List<GroupUser> groupUserDB = groupUserMapper.selectOfNoStart(groupId);
        if(groupUserDB == null || groupUserDB.size() == 0){
            //进行delete
            for(int i=0;i<stuIds.size();i++){
                int res = groupUserMapper.delete(groupId,stuIds.get(i));
                if(res<0){
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return new ResponseEntity(ErrorMsgEnum.DATABASE_ERROR);
                }
            }
            return new ResponseEntity(200,"删除成功");
        }else{
            return new ResponseEntity(ErrorMsgEnum.GROUP_ASSISTANT_EXAM_BEGIN);
        }
    }


}
