package com.examsys.dao;

import com.examsys.model.TestPaperDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface TestPaperDetailMapper {

    List<TestPaperDetail> selectPapers();



    int deleteByPrimaryKey(Integer id);

    int insert(TestPaperDetail record);

    TestPaperDetail selectByPrimaryKey(Integer id);

    List<TestPaperDetail> selectAll();

    int updateByPrimaryKey(TestPaperDetail record);
}