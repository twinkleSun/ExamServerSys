package com.examsys.controller;

import com.examsys.model.entity.ResponseEntity;
import com.examsys.service.Impl.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by twinkleStar on 2019/9/4.
 */
@RestController
@RequestMapping("/upi/group")
public class GroupController {

    @Autowired
    GroupServiceImpl groupService;


    /**
     * 添加单个组
     * @param map
     * @return
     */
    @PostMapping("/single")
    public ResponseEntity addSingleGroup(@RequestBody Map<String,String> map) {
        ResponseEntity responseEntity=groupService.addSingleGroup(map.get("groupName"));
        return responseEntity;
    }


    /**
     * 删除若干组
     * @param map
     * @return
     */
    @DeleteMapping("/del")
    @Transactional
    public ResponseEntity deleteGroups(@RequestBody Map<String,Object> map) {
        ResponseEntity responseEntity = groupService.deleteGroups(map);
        if(responseEntity.getStatus() != 200){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return responseEntity;
    }


    /**
     * 根据学生ID获取所在组
     * @param map
     * @return
     */
    @PostMapping("/uid")
    public ResponseEntity getGroupByUserId(@RequestBody Map<String,Integer> map) {
        ResponseEntity responseEntity = groupService.getGroupByUserId(map.get("id"));
        return responseEntity;
    }


    /**
     * 复制组
     * @param map
     * @return
     */
    @PostMapping("/copy")
    public ResponseEntity copyGroup(@RequestBody Map<String,Object> map) {
        ResponseEntity responseEntity=groupService.copyGroup(map);
        return responseEntity;
    }


}
