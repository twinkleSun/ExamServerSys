package com.examsys.dao;

import com.examsys.model.StudentPointDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentPointDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudentPointDetail record);

    StudentPointDetail selectByPrimaryKey(Integer id);

    List<StudentPointDetail> selectAll();

    int updateByPrimaryKey(StudentPointDetail record);
}