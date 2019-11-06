package com.examsys.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.examsys.dao.*;
import com.examsys.model.Exam;
import com.examsys.model.ExamGroup;
import com.examsys.model.StudentPoint;
import com.examsys.model.StudentPointDetail;
import com.examsys.model.entity.*;
import com.examsys.util.error.ErrorMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Repository
public class StudentPointServiceImpl {

    @Autowired
    StudentPointDetailMapper studentPointDetailMapper;
    @Autowired
    StudentPointMapper studentPointMapper;
    @Autowired
    TestPaperDetailMapper testPaperDetailMapper;
    @Autowired
    ExamMapper examMapper;
    @Autowired
    ExamGroupMapper examGroupMapper;


    /**
     * 学生答题
     * @param map
     * @return
     */
    @Transactional
    public ResponseEntity addStuAns(Map<String,Object> map){
        String paper_code = String.valueOf(map.get("paper_code"));
        int exam_id = Integer.valueOf(map.get("exam_id").toString());
        int student_id = Integer.valueOf(map.get("student_id").toString());
        int endFlag =  Integer.valueOf(map.get("end_flag").toString());
        List<Map<String,Object>> paper_status = (List<Map<String,Object>>)map.get("paper_status");

        double def_total_point = 0.0;
        //处理单个题目记录
        for(int i=0;i<paper_status.size();i++){
            Map<String,Object> stuQues = paper_status.get(i);
            int id = Integer.valueOf(stuQues.get("id").toString());//question的ID
            double total_point = Double.valueOf(stuQues.get("total_point").toString());
            String student_answer;
            if (stuQues.get("student_answer") == null || stuQues.get("student_answer") == "") {
                student_answer = JSON.toJSONString("");
            }else{
                student_answer = JSON.toJSONString(stuQues.get("student_answer"));
            }
            int stamp;
            if (stuQues.get("stamp") == null || stuQues.get("stamp") == "") {
                stamp =0;
            }else {
                stamp = (int)stuQues.get("stamp");
            }

            StudentPointDetail studentPointDetail = new StudentPointDetail();
            studentPointDetail.setQuestionId(id);
            studentPointDetail.setStudentId(student_id);
            studentPointDetail.setPaperCode(paper_code);
            studentPointDetail.setExamId(exam_id);
            studentPointDetail.setQuestionStatus(0);
            studentPointDetail.setDefPoint(total_point);
            studentPointDetail.setStudentAnswer(student_answer);
            studentPointDetail.setStamp(stamp);
            def_total_point = def_total_point + total_point;

            StudentPointDetail alreadyStuRecord = studentPointDetailMapper.selectByIds(studentPointDetail);
            if (alreadyStuRecord != null){
                alreadyStuRecord.setStudentAnswer(studentPointDetail.getStudentAnswer());
                alreadyStuRecord.setStamp(studentPointDetail.getStamp());
                studentPointDetailMapper.updateStuAnswer(alreadyStuRecord);
            }else {
                studentPointDetailMapper.insert(studentPointDetail);
            }
        }

        //处理总记录表
        StudentPoint studentPoint = new StudentPoint();
        studentPoint.setStudentId(student_id);
        studentPoint.setPaperTotalPoint(def_total_point);
        studentPoint.setExamId(exam_id);
        studentPoint.setSubjectiveStatus(0);
        studentPoint.setObjectiveStatus(0);
        studentPoint.setPaperCode(paper_code);
        studentPoint.setEndFlag(endFlag);

        StudentPoint alreadyStuRecord = studentPointMapper.selectByIds(studentPoint);
        if(alreadyStuRecord != null){
            alreadyStuRecord.setPaperTotalPoint(studentPoint.getPaperTotalPoint());
            alreadyStuRecord.setEndFlag(studentPoint.getEndFlag());
            studentPointMapper.updateTotalPoint(alreadyStuRecord);
        }else{
            studentPointMapper.insert(studentPoint);
        }
        return new ResponseEntity(200,"提交成功");
    }


    /**
     * 老师获取主观题内容，方便进行判题
     * @param examId
     * @return
     */
    public ResponseEntity getStuAnsOfExam(int examId){
        List<StudentAnswersDetail> studentAnswersDetails = studentPointDetailMapper.selectStudentAnswers(examId);
        return new ResponseEntity(200,"获取成功",studentAnswersDetails);
    }

    /**
     * 老师判主观题
     * @param map
     * @return
     */
    public ResponseEntity addTechSubPoint(Map<String,Object> map){
        String paper_code = String.valueOf(map.get("paper_code"));
        int exam_id = Integer.valueOf(map.get("exam_id").toString());
        List<Map<String,Object>> student_answers_detail = (List<Map<String,Object>>)map.get("student_answers_detail");

        for(int i=0;i<student_answers_detail.size();i++){
            Map<String,Object> stuMap = student_answers_detail.get(i);
            int student_id = Integer.valueOf(stuMap.get("student_id").toString());
            Map<String,Object> paper_status =(Map<String,Object>)stuMap.get("paper_status");
            List<Map<String,Object>> subMap = (List<Map<String,Object>>)paper_status.get("subjective_answers");
            int flag = 1;
            double totalPoint = 0.0;

            for(int j=0;j<subMap.size();j++){
                Map<String,Object> quesMap = subMap.get(j);
                int id = Integer.valueOf(quesMap.get("id").toString());
                double point = Double.valueOf(quesMap.get("point").toString());

                StudentPointDetail studentPointDetail = new StudentPointDetail();
                studentPointDetail.setExamId(exam_id);
                studentPointDetail.setPaperCode(paper_code);
                studentPointDetail.setStudentId(student_id);
                studentPointDetail.setQuestionId(id);

                //-1.0代表这题还没判
                if(point == -1.0){
                    flag = 0;
                    studentPointDetail.setQuestionStatus(0);
                }else{
                    totalPoint = totalPoint + point;
                    studentPointDetail.setQuestionStatus(1);
                    studentPointDetail.setStudentPoint(point);
                }
                //更新单题分数
                studentPointDetailMapper.updateByIds(studentPointDetail);
            }

            if(flag == 1){
                StudentPoint studentPointTmp = new StudentPoint();
                studentPointTmp.setExamId(exam_id);
                studentPointTmp.setStudentId(student_id);
                StudentPoint studentPoint = studentPointMapper.selectByIds(studentPointTmp);
                studentPoint.setSubjectiveStatus(1);
                studentPoint.setSubjectiveGrade(totalPoint);
                if(studentPoint.getObjectiveGrade() == null){
                    studentPoint.setStudentTotalPoint(totalPoint+0.0);
                }else{
                    studentPoint.setStudentTotalPoint(totalPoint+studentPoint.getObjectiveGrade());
                }

                //更新总记录
                studentPointMapper.updateSubStatus(studentPoint);
            }
        }
        return new ResponseEntity(200,"判题成功");
    }


    /**
     * 学生获取考试试题
     * @param map
     * @return
     */
    public ResponseEntity getStuExamPaper(Map<String,Object> map){
        String paperCode = String.valueOf(map.get("paper_code"));
        int stuId = Integer.valueOf(map.get("stu_id").toString());
        int examId = Integer.valueOf(map.get("exam_id").toString());

        List<ExamGroup> examGroupDB = examGroupMapper.judgeStudentExist(examId,stuId);
        if(examGroupDB == null ||examGroupDB.size() == 0){
            return new ResponseEntity(ErrorMsgEnum.STUDENT_NOT_IN_EXAM);
        }

        StudentPoint studentPoint = new StudentPoint();
        studentPoint.setExamId(examId);
        studentPoint.setStudentId(stuId);
        studentPoint.setPaperCode(paperCode);
        StudentPoint studentPointDB = studentPointMapper.selectByIds(studentPoint);
        if( studentPointDB == null || studentPointDB.getEndFlag() == 0){
            Exam exam = examMapper.selectByPrimaryKey(examId);
            if(studentPointDB == null){
                //新建
                Date now = new Date();
                studentPoint.setInTime(String.valueOf(now.getTime()));
                studentPoint.setLeftTime(exam.getDuration());
                studentPoint.setEndFlag(0);
                studentPointMapper.insertIds(studentPoint);
            }else{
                try{
                    SimpleDateFormat dateFormatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date endDate = dateFormatTime.parse(exam.getEndTime());
                    Long endTime = endDate.getTime();

                    Long inTime = Long.valueOf(studentPointDB.getInTime());
                    Long durationTime = exam.getDuration();
                    Date now = new Date();
                    Long nowTime = now.getTime();

                    Long leftTime = Math.min(inTime + durationTime -nowTime,endTime-nowTime);

                    studentPoint.setLeftTime(leftTime);
                }catch (Exception e){

                }
            }
            TestPaperListEntity testPaperList = testPaperDetailMapper.selectStuPaper(paperCode,examId,stuId);
            testPaperList.setLeftTime(studentPoint.getLeftTime());
            return new ResponseEntity(200,"查询成功",testPaperList);
        }else{
            return new ResponseEntity(ErrorMsgEnum.EXAM_END_CANNOT_IN);
        }

    }


    /**
     * 获取学生答卷详情，包括主观题和客观题
     * @param map
     * @return
     */
    public ResponseEntity getPaperStuAnsPoint(Map<String,Object> map){
        int stuId = Integer.valueOf(map.get("stu_id").toString());
        int examId = Integer.valueOf(map.get("exam_id").toString());
        PaperAndStuAnsPointEntity stuPaperAnsPoint = testPaperDetailMapper.selectStuPaperAnsDetail(examId,stuId);
        if(stuPaperAnsPoint == null){
            return new ResponseEntity(ErrorMsgEnum.STUDENT_NOT_TAKE_PART_IN);
        }else{
            return new ResponseEntity(200,"查询成功",stuPaperAnsPoint);
        }

    }


    /**
     * 开始客观题判题
     * @param eId
     * @return
     */
    public ResponseEntity stuObjQuesJudge(int eId){
        List<StuObjJudgeEntity> stuObjJudgeEntityList = studentPointDetailMapper.selectStuObjQues(eId);

        for(int i=0;i<stuObjJudgeEntityList.size();i++){
            StuObjJudgeEntity stuObjJudgeEntity = stuObjJudgeEntityList.get(i);
            int spId = stuObjJudgeEntity.getSpId();
            Double objectiveGrade = 0.0;
//            Double objectiveGrade = stuObjJudgeEntity.getObjectiveGrade();
            Double subjectiveGrade = stuObjJudgeEntity.getSubjectiveGrade();
            List<StuObjQuesEntity> stuQues = stuObjJudgeEntity.getStuQues();

            for(int j=0;j<stuQues.size();j++){
                StuObjQuesEntity stuObjQuesEntity = stuQues.get(j);
                int sdId = stuObjQuesEntity.getSdId();
                Double defPoint = stuObjQuesEntity.getDefPoint();
                String studentAnswer = stuObjQuesEntity.getStudentAnswer();
                Double studentPoint = stuObjQuesEntity.getStudentPoint();
                String defAns = stuObjQuesEntity.getDefAns();
                String quesType = stuObjQuesEntity.getQuesType();
                //开始判题
                if (studentAnswer == null || studentAnswer == ""){
                    studentPoint = 0.0;
                }else{
                    if(quesType.equals("multi")){
                        JSONArray defAnsArr =  JSONObject.parseArray(defAns);
                        JSONArray stuAnsArr =  JSONObject.parseArray(studentAnswer);

                        int defLen = defAnsArr.size();
                        int stuLen = stuAnsArr.size();
                        if(defLen == stuLen){
                            ArrayList<Integer> defArr = new ArrayList<>();
                            for (Object obj : defAnsArr) {
                                JSONObject defObj = (JSONObject)obj;
                                defArr.add(defObj.getInteger("id"));
                            }

                            int tmp = 1;
                            for(Object obj:stuAnsArr){
                                JSONObject stuObj = (JSONObject)obj;
                                int tmpId = stuObj.getInteger("id");
                                if(defArr.contains(tmpId)){
                                }else{
                                    tmp = 0;
                                    studentPoint = 0.0;
                                    break;
                                }
                            }
                            if(tmp == 1){
                                studentPoint = defPoint;
                            }
                        }else{
                            studentPoint = 0.0;
                        }

                    }else{
                        if (studentAnswer.equals(defAns)){
                            studentPoint = defPoint;
                        }else{
                            studentPoint = 0.0;
                        }
                    }
                }

                objectiveGrade = objectiveGrade + studentPoint;
                StudentPointDetail studentPointDetail = new StudentPointDetail();
                studentPointDetail.setQuestionStatus(1);
                studentPointDetail.setStudentPoint(studentPoint);
                studentPointDetail.setId(sdId);
                studentPointDetailMapper.updateByPK(studentPointDetail);
            }

            //更新总记录
            StudentPoint studentPoint = new StudentPoint();
            studentPoint.setObjectiveStatus(1);
            studentPoint.setObjectiveGrade(objectiveGrade);
            studentPoint.setStudentTotalPoint(objectiveGrade+subjectiveGrade);
            studentPoint.setId(spId);
            studentPointMapper.updateByPK(studentPoint);
        }
        return new ResponseEntity(200,"客观题判题结束");
    }


    /**
     * 管理员获取考生成绩表
     * @param examId
     * @return
     */
    public ResponseEntity selectAllByExamId(int examId){
        List<StudentPointAndInfoEntity> studentPointList =  studentPointMapper.selectAllByExamId(examId);
        if(studentPointList == null || studentPointList.size() ==0){
            return new ResponseEntity(ErrorMsgEnum.NO_STUDENT_POINT_INFO);
        }else{
            return new ResponseEntity(200,"查询成功",studentPointList);
        }
    }
}
