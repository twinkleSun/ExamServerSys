package com.examsys.controller;

import com.examsys.model.entity.ResponseEntity;
import com.examsys.service.IGroupUserService;
import com.examsys.service.IUserinfoService;
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
    IGroupUserService iGroupUserService;

    /**
     * 获取所有组及其成员列表
     * todo:后期加上admin权限，仅可查看有权限的组（比如自己创建的）
     * @return
     */
    @GetMapping(value = "/groupuser/all")
    @ResponseBody
    public ResponseEntity getGroupUser() {
        ResponseEntity responseEntity = iGroupUserService.getGroupUserInfo();
        return responseEntity;
    }

    @PostMapping("/groupuser")
    public ResponseEntity addNew(@RequestBody Map<String,Object> map) {
        ResponseEntity responseEntity = iGroupUserService.addNewGroupUser(map);
        return responseEntity;
    }

    @PostMapping("/groupuser/gid")
    public ResponseEntity getGroupUserByGid(@RequestBody Map<String,Integer> map) {
        ResponseEntity responseEntity = iGroupUserService.getGroupUserByGid(map.get("id"));
        return responseEntity;
    }

    @PostMapping("/usergroup/relation")
    public ResponseEntity updateRelation(@RequestBody Map<String,Object> map) {
        ResponseEntity responseEntity = iGroupUserService.updateUserGroupRelation(map);
        return responseEntity;
    }

    @GetMapping(value = "/usergroup/all")
    @ResponseBody
    public ResponseEntity getUserGroup() {
        ResponseEntity responseEntity = iGroupUserService.getUserGroupInfo();
        return responseEntity;
    }

}
