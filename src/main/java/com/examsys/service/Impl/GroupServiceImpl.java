package com.examsys.service.Impl;

import com.examsys.dao.ExamGroupMapper;
import com.examsys.dao.GroupUserMapper;
import com.examsys.dao.GroupMapper;
import com.examsys.model.ExamGroup;
import com.examsys.model.Group;
import com.examsys.model.GroupUser;
import com.examsys.model.entity.ResponseEntity;
import com.examsys.util.error.ErrorMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Map;

/**
 * Created by twinkleStar on 2019/9/4.
 */

@Repository
@Service
public class GroupServiceImpl{

    @Autowired
    GroupMapper groupMapper;

    @Autowired
    GroupUserMapper groupUserMapper;

    @Autowired
    ExamGroupMapper examGroupMapper;


    /**
     * 添加单个组/修改组名
     * @param map
     * @return
     */
    public ResponseEntity addSingleGroup(Map<String,Object> map){
        String groupName = map.get("groupName").toString();
        int id = (int)map.get("id");
        Group groupDB = groupMapper.selectByName(groupName);
        Group groupFront = new Group(id,groupName);
        if(id == 0){
            if(groupDB!=null){
                return new ResponseEntity(ErrorMsgEnum.GROUP_ALREADY_EXIST);
            }else{
                groupMapper.insert(groupFront);
                return new ResponseEntity(200,"添加成功",groupFront);
            }
        }else{
            if(groupDB != null && groupDB.getId() != id){
                return new ResponseEntity(ErrorMsgEnum.GROUP_ALREADY_EXIST);
            }else{
                groupMapper.updateByPrimaryKey(groupFront);
                return new ResponseEntity(200,"修改成功",groupFront);
            }
        }

    }


    /**
     * 删除若干组
     * @param map
     * @return
     */
    @Transactional
    public ResponseEntity deleteGroups(Map<String,Object> map){
        List<Integer> groupIds = (List<Integer>)map.get("group_id");

        for(int i=0;i<groupIds.size();i++){
            int groupId = groupIds.get(i);
            List<ExamGroup> examGroups = examGroupMapper.selectByGroupId(groupId);
            if(examGroups == null || examGroups.size() == 0){
                //删除组-用户关系
                int tmp1 = groupUserMapper.deleteByGroupId(groupId);
                //删除组
                int tmp2 = groupMapper.deleteByPrimaryKey(groupId);

                if(tmp1 <0 || tmp2<0){
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return new ResponseEntity(ErrorMsgEnum.DATABASE_ERROR);
                }
            }else {
                //组已和考试关联,不可删除
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                String errMsg = "已和考试关联的组ID为"+groupId+"。";
                return new ResponseEntity(ErrorMsgEnum.GROUP_ASSISTANT_WITH_EXAM,errMsg);
            }
        }

        return new ResponseEntity(200,"删除成功");
    }


    /**
     * 根据学生ID获取所在组
     * @param userId
     * @return
     */
    public ResponseEntity getGroupByUserId(int userId){
        List<Group> groupList = groupMapper.selectByUserId(userId);
        if (groupList != null || groupList.size()!=0) {
            return new ResponseEntity(200,"查询成功",groupList);
        }else{
            return new ResponseEntity(ErrorMsgEnum.USER_BELONGS_NO_GROUP);
        }
    }


    /**
     * 复制组
     * @param map
     * @return
     */
    public ResponseEntity copyGroup(Map<String,Object> map){

        Integer groupId = Integer.valueOf(map.get("group_id").toString());
        String groupName = String.valueOf(map.get("group_name"));

        Group groupDB = groupMapper.selectByName(groupName);
        if(groupDB == null ){
            //开始复制
            Group group = new Group();
            group.setName(groupName);
            groupMapper.insert(group);//插入组

            int newGroupId = group.getId();
            List<GroupUser> groupUserList= groupUserMapper.selectByGroupId(groupId);
            for(int i=0;i<groupUserList.size();i++){
                GroupUser groupUser = groupUserList.get(i);
                groupUser.setGroupId(newGroupId);
                groupUserMapper.insert(groupUser);//插入组-学生关系
            }
            return new ResponseEntity(200,"组复制成功");
        }else{
            return new ResponseEntity(ErrorMsgEnum.GROUP_ALREADY_EXIST);
        }
    }

}
