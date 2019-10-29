package com.examsys.dao;

import com.examsys.model.TestPaperDetail;
import com.examsys.model.entity.TestPaperEntity;
import com.examsys.model.entity.TestPaperListEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TestPaperDetailMapper {

    TestPaperListEntity selectPapers(String paperCode);

    TestPaperListEntity selectStuPaper(@Param("paperCode")String paperCode,
                                       @Param("examId")Integer examId,
                                       @Param("stuId")Integer stuId);

    int deleteByPrimaryKey(Integer id);

    int insert(TestPaperDetail record);

    TestPaperDetail selectByPrimaryKey(Integer id);

    List<TestPaperDetail> selectAll();

    int updateByPrimaryKey(TestPaperDetail record);

    int deleteByPaperCode(String paperCode);
}