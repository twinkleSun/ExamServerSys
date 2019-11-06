package com.examsys.service.Impl;

import com.examsys.dao.ExamGroupMapper;
import com.examsys.dao.ExamMapper;
import com.examsys.model.Exam;
import com.examsys.model.ExamGroup;
import com.examsys.model.entity.ResponseEntity;
import com.examsys.util.error.ErrorMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Repository
public class ExamServiceImpl{

    @Autowired
    ExamMapper examMapper;
    @Autowired
    ExamGroupMapper examGroupMapper;


    /**
     * 新建/修改考试
     * @param map
     * @return
     */
    public ResponseEntity addOrUpdateExam(Map<String,Object> map){
        Exam exam=new Exam();
        exam.setExamName(String.valueOf(map.get("exam_name")));

        if (map.get("paper_code") == null || map.get("paper_code") == "") {
            return new ResponseEntity(ErrorMsgEnum.NOT_CHOOSE_PAPER);
        }
        exam.setPaperCode(String.valueOf(map.get("paper_code")));

        SimpleDateFormat dateFormatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        exam.setBeginTime(String.valueOf(map.get("begin_time")));
        exam.setEndTime(String.valueOf(map.get("end_time")));
        exam.setDuration(Long.valueOf(map.get("duration").toString()));

        try{
            Date dtBegin = dateFormatTime.parse(exam.getBeginTime());
            Date dtEnd = dateFormatTime.parse(exam.getEndTime());
            if(dtBegin.getTime() >= dtEnd.getTime()){
                return new ResponseEntity(ErrorMsgEnum.END_EARLY_THAN_BEGIN);
            }
        }catch (Exception e){
            return new ResponseEntity(ErrorMsgEnum.INCORRECT_DATA_FORMAT);
        }

        int examId;
        if(map.get("id") == null ||map.get("id") ==""){
            //新建考试
            exam.setStatus("未开始");
            examMapper.insert(exam);
            examId=exam.getId();
        }else{
            examId = Integer.valueOf(String.valueOf(map.get("id")));
            exam.setId(examId);
            String status = String.valueOf(map.get("status"));

            if(status.equals("未开始")){
                //修改考试
                exam.setStatus(status);
                examMapper.updateByPrimaryKey(exam);
                examGroupMapper.deleteByExamId(examId);
            }else {
                return new ResponseEntity(ErrorMsgEnum.BEGIN_CAN_NOT_UPDATE);
            }

        }

        if(map.get("group_ids") == null || map.get("group_ids") == ""){
            return new ResponseEntity(ErrorMsgEnum.EXAM_HAS_NO_GROUP);
        }else {
            ArrayList<Integer> groupIds=(ArrayList<Integer>)map.get("group_ids");
            //添加考试-组关系
            ResponseEntity responseEntity = addExamGroup(examId,groupIds);
            return responseEntity;
        }

    }


    /**
     * 添加考试-组关系
     * @param examId
     * @param groupIds
     * @return
     */
    public ResponseEntity addExamGroup(int examId,ArrayList<Integer> groupIds){
        for(int i=0;i<groupIds.size();i++){
            ExamGroup examGroup = new ExamGroup();
            examGroup.setExamId(examId);
            examGroup.setGroupId(groupIds.get(i));
            ExamGroup examGroupDB = examGroupMapper.selectByExamGroup(examGroup);
            if(examGroupDB != null){
               //已存在就不添加
            }else{
                examGroupMapper.insert(examGroup);
            }
        }
        return new ResponseEntity(200,"编辑成功");
    }


    /**
     * 管理员手动结束考试
     * @param examId
     * @return
     */
    public ResponseEntity endExam(int examId){
        Exam exam = new Exam();
        exam.setStatus("已结束");
        exam.setId(examId);
        examMapper.updateExamStatus(exam);
        return new ResponseEntity(200,"考试已结束");
    }



    /**
     * 批量删除考试
     * @param map
     * @return
     */
    @Transactional
    public ResponseEntity delExams(Map<String,Object> map){
        List<Integer> examIds = (List<Integer>)map.get("exam_id");
        for(int i=0;i<examIds.size();i++){
            int examId = examIds.get(i);
            Exam exam = examMapper.selectByPrimaryKey(examId);
            if(!exam.getStatus().equals("未开始")){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return new ResponseEntity(ErrorMsgEnum.EXAM_BEGINS_CAN_NOT_DELETE);
            }else{
                examGroupMapper.deleteByExamId(examId);
                examMapper.deleteByPrimaryKey(examId);
            }
        }
        return new ResponseEntity(200,"删除成功");
    }

}
