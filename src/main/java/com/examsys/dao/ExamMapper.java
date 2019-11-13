package com.examsys.dao;

import com.examsys.model.Exam;
import com.examsys.model.entity.ExamEntity;
import com.examsys.model.entity.StuPointList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ExamMapper {

    List<StuPointList> selectExamByUserId(Integer stuId);

    int insert(Exam exam);

    int updateExamStatus(Exam exam);

    List<Exam> selectAll();

    List<ExamEntity> selectWithPaper();

    List<Exam> selectByPaperCode(String paperCode);

    Exam selectByPrimaryKey(Integer id);

   int deleteByPrimaryKey(Integer id);

   int updateByPrimaryKey(Exam record);


}