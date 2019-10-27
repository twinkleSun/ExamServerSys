package com.examsys.dao;

import com.examsys.model.Exam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ExamMapper {

    List<Exam> selectExamByUserId(Integer id);

    int insert(Exam exam);

    int updateExamStatus(Exam exam);

    List<Exam> selectAll();

    List<Exam> selectByPaperCode(String paperCode);

    Exam selectByPrimaryKey(Integer id);

   int deleteByPrimaryKey(Integer id);

   int updateByPrimaryKey(Exam record);


}