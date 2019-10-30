package com.examsys.controller;

import com.examsys.model.entity.ResponseEntity;
import com.examsys.service.Impl.GroupUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


/**
 * Created by twinkleStar on 2019/9/3.
 */

@RestController
@RequestMapping("/upi")
public class GroupUserController {

    @Autowired
    GroupUserServiceImpl groupUserService;

    /**
     * 获取所有组及其成员列表
     * @return
     */
    @GetMapping(value = "/groupuser/all")
    public ResponseEntity getGroupAndUserList() {
        ResponseEntity responseEntity = groupUserService.getAllGroupAndUserList();
        return responseEntity;
    }


    /**
     * 获取所有考生和其所属的组的列表
     * @return
     */
    @GetMapping(value = "/usergroup/all")
    public ResponseEntity getUserGroup() {
        ResponseEntity responseEntity = groupUserService.getAllUserAndGroupList();
        return responseEntity;
    }


    /**
     * 根据组ID获取所有学生
     * @param map
     * @return
     */
    @PostMapping("/groupuser/gid")
    public ResponseEntity getGroupUserByGid(@RequestBody Map<String,Integer> map) {
        ResponseEntity responseEntity = groupUserService.getGroupUserByGid(map.get("id"));
        return responseEntity;
    }


    /**
     * 向某个组添加学生,添加失败则回滚
     * @param map
     * @return
     */
    @PostMapping("/groupuser")
    @Transactional
    public ResponseEntity addNewStudent(@RequestBody Map<String,Object> map) {
        ResponseEntity responseEntity = groupUserService.addNewGroupUser(map);
        if(responseEntity.getStatus() != 200){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return responseEntity;
    }


    /**
     * 修改考生信息和组
     * @param map
     * @return
     */
    @PostMapping("/usergroup/relation")
    @Transactional
    public ResponseEntity updateRelation(@RequestBody Map<String,Object> map) {
        ResponseEntity responseEntity = groupUserService.updateUserWithRelation(map);
        if(responseEntity.getStatus()!=200){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return responseEntity;
    }


    @DeleteMapping("/usergroup/del")
    public ResponseEntity deleteRelation(@RequestBody Map<String,Object> map) {
        ResponseEntity responseEntity = groupUserService.selectOfNoStart(map);
        return responseEntity;
    }

}
