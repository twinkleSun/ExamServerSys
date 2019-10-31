package com.examsys.controller;

import com.examsys.model.entity.ResponseEntity;
import com.examsys.service.Impl.UserServiceImpl;
import com.examsys.util.ExcelTemplateUtil;
import com.examsys.util.OtherUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by twinkleStar on 2019/8/26.
 */

@RestController
@RequestMapping("/upi/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    ExcelTemplateUtil excelUtil;

    @Autowired
    OtherUtil otherUtil;


    /**
     * 用户登陆
     * 参数：user_name,password
     * @param map
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity userLogin(@RequestBody Map<String,String> map) {
        ResponseEntity responseEntity = userService.userLogin(map.get("user_name"), map.get("password"));
        return responseEntity;
    }


    /**
     * 通过上传模板excel的方式批量添加用户
     * @param multipartFiles
     * @return
     */
    @PostMapping(value = "/batch")
    @Transactional
    public ResponseEntity addNewStudent(
            @RequestParam( value="files[]",required=false)MultipartFile[] multipartFiles)throws IllegalStateException, IOException{
        MultipartFile file=multipartFiles[0];
        ResponseEntity responseEntity=userService.addNewUsers(file);
        return responseEntity;
    }


    /**
     * 下载批量导入用户的excel模板
     * @param response
     * @throws Exception
     */
    @GetMapping("/template")
    public void userTemplate(HttpServletResponse response) throws Exception {
        String fileName = "添加用户模板.xls";
        HSSFWorkbook wb=excelUtil.addUsersTemplate(); //调用excelUtil生成excel
        try {
            otherUtil.setResponseHeader(response, fileName,wb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * todo:删除若干考生
     * @param map
     * @return
     */
    @DeleteMapping("/multi")
    @Transactional
    public ResponseEntity deleteUsers(@RequestBody Map<String,Object> map) {
        ResponseEntity responseEntity = userService.deleteUsers(map);
        return responseEntity;
    }

}
