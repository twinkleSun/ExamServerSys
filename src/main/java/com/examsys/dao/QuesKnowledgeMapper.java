package com.examsys.dao;

import com.examsys.model.QuesKnowledge;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuesKnowledgeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuesKnowledge record);

    int insertNotExist(QuesKnowledge record);

    QuesKnowledge selectByPrimaryKey(Integer id);

    List<QuesKnowledge> selectAll();

    int updateByPrimaryKey(QuesKnowledge record);
}