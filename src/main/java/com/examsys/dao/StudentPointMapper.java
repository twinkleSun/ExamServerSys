package com.examsys.dao;

import com.examsys.model.StudentPoint;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentPointMapper {

    StudentPoint selectByIds(StudentPoint studentPoint);
    int deleteByPrimaryKey(Integer id);

    int insert(StudentPoint record);

    StudentPoint selectByPrimaryKey(Integer id);

    List<StudentPoint> selectAll();

    int updateByPrimaryKey(StudentPoint record);

    int updateSubStatus(StudentPoint studentPoint);

    int updateByPK(StudentPoint studentPoint);
}