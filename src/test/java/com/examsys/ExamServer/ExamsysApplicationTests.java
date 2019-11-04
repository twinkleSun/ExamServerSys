package com.examsys.ExamServer;

import com.examsys.dao.UserMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

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

        Date now = new Date();
        SimpleDateFormat dateFormatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        dateFormatTime.format(now);
        try{
            Date date=dateFormatTime.parse("2019-11-04 21:00:00");//将String字符串类型转换为date时间类型
            Long zero=date.getTime();//获取date的值
            System.out.println("zero="+zero);
            Date date2 = new Date();

            date2.getTime();
            System.out.println("date2="+date2.getTime());
        }catch (Exception w){

        }



    }

}
