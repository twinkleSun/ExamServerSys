package com.examsys.dao;

import com.examsys.model.ExamGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ExamGroupMapper {

    ExamGroup selectByExamGroup(ExamGroup examGroup);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamGroup record);

    ExamGroup selectByPrimaryKey(Integer id);

    List<ExamGroup> selectAll();

    int updateByPrimaryKey(ExamGroup record);

    int deleteByExamId(Integer examId);

    List<ExamGroup> selectByGroupId(Integer groupId);

    List<ExamGroup> judgeStudentExist(@Param("examId")Integer examId,
                                      @Param("studentId")Integer studentId);

}