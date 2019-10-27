package com.examsys.dao;

import com.examsys.model.Knowledge;
import com.examsys.model.entity.Keypoint;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface KnowledgeMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Knowledge record);

    Knowledge selectByPrimaryKey(Integer id);

    List<Knowledge> selectAll();

    int updateByPrimaryKey(Knowledge record);

    Knowledge selectByKnowledge(Knowledge knowledge);

    List<Keypoint> selectAllKeyPoint();
}