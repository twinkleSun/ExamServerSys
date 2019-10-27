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

//    int deleteByPrimaryKey(Integer id);
//

//
//    Examinfo selectByPrimaryKey(Integer id);
//
//    List<Examinfo> selectAll();
//
//    int updateByPrimaryKey(Examinfo record);


}