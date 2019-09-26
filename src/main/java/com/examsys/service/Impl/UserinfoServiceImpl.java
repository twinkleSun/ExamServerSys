package com.examsys.service.Impl;

import com.examsys.dao.GroupUserMapper;
import com.examsys.dao.UserinfoMapper;
import com.examsys.model.GroupUser;
import com.examsys.model.Userinfo;
import com.examsys.model.entity.ResponseEntity;
import com.examsys.service.IUserinfoService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.ws.spi.WebServiceFeatureAnnotation;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by twinkleStar on 2019/8/26.
 */
@Repository
public class UserinfoServiceImpl implements IUserinfoService {

    @Autowired
    UserinfoMapper userinfoMapper;
    @Autowired
    GroupUserMapper groupUserMapper;

    /**
     * 通过模板批量导入用户
     * @param multipartFile
     * @return
     */
    public ResponseEntity addNewUsers(MultipartFile multipartFile)throws IllegalStateException, IOException {

        ResponseEntity responseEntity=new ResponseEntity();
        InputStream inputStream = multipartFile.getInputStream();

        try{
            Workbook wb = WorkbookFactory.create(inputStream);
            Sheet sheet = wb.getSheetAt(0);
            //获取最大行
            int maxRowNum = sheet.getPhysicalNumberOfRows();

            String[] name=new String[maxRowNum-3];
            int[] password=new int[maxRowNum-3];

            //从第四行开始解析，前三行为提示信息
            for(int i=3;i<maxRowNum;i++){
                Row row=sheet.getRow(i);
                Cell nameCell=row.getCell(0);
                Cell passwordCell= row.getCell(1);

                if(nameCell.getCellType()==Cell.CELL_TYPE_STRING){
                    name[i-3]=nameCell.getStringCellValue();
                }else{
                    responseEntity.setStatus(-1);
                    responseEntity.setMsg("姓名列不得为数字");
                    return responseEntity;
                }

                if(passwordCell.getCellType()==Cell.CELL_TYPE_NUMERIC){
                    password[i-3]=(int)passwordCell.getNumericCellValue();
                }else{
                    responseEntity.setStatus(-1);
                    responseEntity.setMsg("身份证号列必须为数字");
                    return responseEntity;
                }
            }

            //进行插入
            int res=0;
            for(int i=0;i<name.length;i++){
                Userinfo userinfo=new Userinfo();
                userinfo.setUserType("student");
                userinfo.setUserName(name[i]);
                userinfo.setPassword(password[i]+"");
                Userinfo user=userinfoMapper.selectByUsername(userinfo.getUserName());
                if(user!=null){
                    responseEntity.setStatus(-1);
                    responseEntity.setMsg("该用户已存在");
                    return responseEntity;
                }else{
                    res=userinfoMapper.insert(userinfo);

                    if(res<0){
                        responseEntity.setStatus(-1);
                        responseEntity.setMsg("添加失败");
                        return responseEntity;
                    }else{
                        responseEntity.setStatus(200);
                        responseEntity.setMsg("添加成功");
                    }
                }
            }

        }catch (Exception e) {

        }

        return responseEntity;
    }

    /**
     * 查询用户--用户登陆
     * @param username
     * @param password
     * @return
     */
    public ResponseEntity selectUser(String username,String password){
        Userinfo userinfo=userinfoMapper.selectByUsername(username);
        ResponseEntity responseEntity=new ResponseEntity();
        if(userinfo==null){
            responseEntity.setStatus(-1);
            responseEntity.setMsg("该用户不存在");
        }else if(!password.equals(userinfo.getPassword())){
            responseEntity.setStatus(-1);
            responseEntity.setMsg("用户名或密码错误");
        }else{
            responseEntity.setStatus(200);
            responseEntity.setMsg("查询成功");
            responseEntity.setData(userinfo);
        }
        return responseEntity;
    }


    public ResponseEntity deleteUsers(Map<String,Object> map){
        ArrayList<Integer> userIds=(ArrayList<Integer>)map.get("id");

        for(int i=0;i<userIds.size();i++){
            List<GroupUser> groupUsers=groupUserMapper.selectByUserId(userIds.get(i));
            if (groupUsers==null ||groupUsers.size()==0){

                //todo:加上试卷后需要改动
            }else {
                int delRelation=groupUserMapper.deleteByUserId(userIds.get(i));
            }

            int delRes=userinfoMapper.deleteByPrimaryKey(userIds.get(i));
        }

        ResponseEntity responseEntity=new ResponseEntity();
        responseEntity.setStatus(200);
        responseEntity.setMsg("删除成功");
        return responseEntity;
    }






}
