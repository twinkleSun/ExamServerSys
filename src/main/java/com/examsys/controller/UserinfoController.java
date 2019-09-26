package com.examsys.controller;

import com.examsys.model.entity.ResponseEntity;
import com.examsys.service.IUserinfoService;
import com.examsys.util.ExcelTemplateUtil;
import com.examsys.util.OtherUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by twinkleStar on 2019/8/26.
 */

@RestController
@RequestMapping("/upi/user")
public class UserinfoController {

    @Autowired
    IUserinfoService iUserinfoService;

    @Autowired
    ExcelTemplateUtil excelUtil;
    @Autowired
    OtherUtil otherUtil;

    /**
     * 用户登陆
     * 参数：userName,password
     * @param map
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity userLogin(@RequestBody Map<String,String> map) {
        ResponseEntity responseEntity = iUserinfoService.selectUser(map.get("user_name"), map.get("password"));
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
        ResponseEntity responseEntity=iUserinfoService.addNewUsers(file);
        return responseEntity;
    }

    /**
     * 下载批量导入用户的excel模板
     * @param response
     * @throws Exception
     */
    @PostMapping("/template")
    public void userTemplate(HttpServletResponse response) throws Exception {
        String fileName = "添加用户模板.xls";
        HSSFWorkbook wb=excelUtil.addUsersTemplate(); //调用excelUtil生成excel
        try {
            otherUtil.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    /**
//     * 发送响应流方法
//     * @param response
//     * @param fileName
//     */
//    public void setResponseHeader(HttpServletResponse response, String fileName) {
//        try {
//            try {
//                fileName = new String(fileName.getBytes(),"ISO8859-1");
//            } catch (UnsupportedEncodingException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            response.setContentType("application/octet-stream;charset=ISO8859-1");
//            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
//            response.addHeader("Pargam", "no-cache");
//            response.addHeader("Cache-Control", "no-cache");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

    @DeleteMapping("/multi")
    public ResponseEntity deleteUsers(@RequestBody Map<String,Object> map) {
        ResponseEntity responseEntity = iUserinfoService.deleteUsers(map);
        return responseEntity;
    }

}
