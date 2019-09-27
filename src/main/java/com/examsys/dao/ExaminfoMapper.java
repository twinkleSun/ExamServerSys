package com.examsys.dao;

import com.examsys.model.Examinfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ExaminfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Examinfo record);

    Examinfo selectByPrimaryKey(Integer id);

    List<Examinfo> selectAll();

    int updateByPrimaryKey(Examinfo record);

    List<Examinfo> selectExamById(Integer id);
}