package com.examsys.dao;

import com.examsys.model.Exam;
import com.examsys.model.entity.ExamEntity;
import com.examsys.model.entity.StuPointList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

@Mapper
@Repository
public interface ExamMapper {

    HashSet<StuPointList> selectExamByUserId(Integer stuId);

    int insert(Exam exam);

    int updateExamStatus(Exam exam);

    List<Exam> selectAll();

    List<ExamEntity> selectWithPaper();

    List<Exam> selectByPaperCode(String paperCode);

    Exam selectByPrimaryKey(Integer id);

   int deleteByPrimaryKey(Integer id);

   int updateByPrimaryKey(Exam record);


}