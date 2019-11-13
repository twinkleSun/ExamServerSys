package com.examsys.dao;

import com.examsys.model.StudentPoint;
import com.examsys.model.entity.StuPointList;
import com.examsys.model.entity.StudentPointAndInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentPointMapper {

    StudentPoint selectByIds(StudentPoint studentPoint);

    int deleteByPrimaryKey(Integer id);

    int insertIds(StudentPoint studentPoint);

    int insert(StudentPoint record);

    StudentPoint selectByPrimaryKey(Integer id);

    List<StudentPoint> selectAll();

    List<StudentPointAndInfoEntity> selectAllByExamId(int examId);

    int updateByPrimaryKey(StudentPoint record);

    int updateSubStatus(StudentPoint studentPoint);

    int updateByPK(StudentPoint studentPoint);

    int updateTotalPoint(StudentPoint studentPoint);

    int deleteByStuId(int stuId);

}