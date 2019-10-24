package com.examsys.controller;

import com.examsys.model.entity.ResponseEntity;
import com.examsys.service.Impl.GroupUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    @ResponseBody
    public ResponseEntity getGroupUser() {
        ResponseEntity responseEntity = groupUserService.getGroupUser();
        return responseEntity;
    }

    /**
     * 获取所有考生和其所属的组的列表
     * @return
     */
    @GetMapping(value = "/usergroup/all")
    @ResponseBody
    public ResponseEntity getUserGroup() {
        ResponseEntity responseEntity = groupUserService.getUserGroup();
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
     * 向某个组添加学生
     * @param map
     * @return
     */
    @PostMapping("/groupuser")
    public ResponseEntity addNewStudent(@RequestBody Map<String,Object> map) {
        ResponseEntity responseEntity = groupUserService.addNewGroupUser(map);
        return responseEntity;
    }



    /**
     * 修改考生信息和组
     * @param map
     * @return
     */
    @PostMapping("/usergroup/relation")
    public ResponseEntity updateRelation(@RequestBody Map<String,Object> map) {
        ResponseEntity responseEntity = groupUserService.updateUserGroupRelation(map);
        return responseEntity;
    }

}
