package com.examsys.dao;

import com.examsys.model.TestPaper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TestPaperMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TestPaper testPaper);

    TestPaper selectByPrimaryKey(Integer id);

    List<TestPaper> selectAll();

    int updateByPrimaryKey(TestPaper record);

    TestPaper selectByPaperCode(String paperCode);

    List<TestPaper> selectByAdminId(Integer adminId);

    int updateByPaperCode(TestPaper record);

}