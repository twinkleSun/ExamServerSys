package com.examsys.dao;

import com.examsys.model.TestPaper;
import com.examsys.model.entity.TestPaperAdminEntity;
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

    List<TestPaperAdminEntity> selectAllWithAdmin();

    int updateByPrimaryKey(TestPaper record);

    TestPaper selectByPaperCode(String paperCode);

    List<TestPaper> selectByAdminId(Integer adminId);

    int updateByPaperCode(TestPaper record);

    int deleteByPaperCode(String paperCode);

}