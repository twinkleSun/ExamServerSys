package com.examsys.ExamServer;

import com.alibaba.fastjson.JSONObject;
import com.examsys.dao.UserMapper;
import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExamsysApplicationTests {

    @Autowired
    UserMapper userinfoMapper;

    @Test
    public void contextLoads() {
//
//        try{
//            Exam exam=new Exam();
//
//            String obj="{\"qid\": 167, \"title\": \"微软为其认为热帖热我\", \"option\": [{\"id\": 0, \"content\": \"sefnlkan.kjsafnsl;;sda\"}, {\"id\": 1, \"content\": \"士大夫但是广泛大使馆的\"}, {\"id\": 2, \"content\": \"的撒发生发射点仿盛大\"}, {\"id\": 3, \"content\": \"萨芬士大夫大是大非的\"}], \"myAnswer\": \"1|3\", \"correctAnswer\": \"1|3\"}";
//            Answerinfo answerinfo=new Answerinfo();
//            answerinfo.setAnswer(obj);
//            JSONObject quesJObj = new JSONObject(answerinfo.getAnswer());
//
//            String quesTitle = quesJObj.getString("title");
//            int qid=quesJObj.getInt("qid");
//
//            JSONArray options = quesJObj.getJSONArray("option");
//
//
//
//            System.out.println(quesTitle);
//            System.out.println(qid);
//           System.out.println(options);
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//

//        Date now = new Date();
//        SimpleDateFormat dateFormatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        dateFormatTime.format(now);
//        try{
//            Date date=dateFormatTime.parse("2019-11-04 21:00:00");//将String字符串类型转换为date时间类型
//            Long zero=date.getTime();//获取date的值
//            System.out.println("zero="+zero);
//            Date date2 = new Date();
//
//            date2.getTime();
//            System.out.println("date2="+date2.getTime());
//        }catch (Exception w){
//
//        }
        String test = "{0:我才是的}####{1:343242}####{2：测卡}";

        //todo:如果是客观题，一定含有####,否则报错
        String[] arr = test.split("####");
        //todo:选项不可以超过10个,否则报错

        List<Map<String,Object>> mapList =new ArrayList<>();
        for(int i=0;i<arr.length;i++){
            Map<String,Object> map =new HashMap<>();
//            JSONObject jsonObject = JSONObject.parseObject(arr[i]);
            System.out.println(arr[i]);
            //todo:判断截取的是否equals（0，1，2，，，9）
            int index = Integer.valueOf(arr[i].substring(1,2));
            System.out.println("index="+index);
            //todo:判断最后一个字符是否为}
            String content = arr[i].substring(3,arr[i].length()-1);
            System.out.println("content="+content);
            map.put("id",index);
            map.put("content",content);
            mapList.add(map);
        }

        String t = JSONObject.toJSONString(mapList);
        System.out.println(t);



    }

}
