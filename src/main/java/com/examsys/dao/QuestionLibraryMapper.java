package com.examsys.dao;

import com.examsys.model.QuestionLibrary;
import com.examsys.model.entity.QuesKnowNameEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface QuestionLibraryMapper {

    int insert(QuestionLibrary questionLibrary);


    QuestionLibrary selectByQuestion(QuestionLibrary questionLibrary);

    List<QuestionLibrary> selectAll();

    List<QuesKnowNameEntity> selectAllWithKnowledgeName();

    List<QuestionLibrary> selectQuesByKnow(Integer k_id);

    QuestionLibrary getId(QuestionLibrary questionLibrary);

    List<QuesKnowNameEntity> selectByFilter(@Param("content")String content,
                                         @Param("type")String type,
                                         @Param("kname")String kname);

    int updateByPK(QuestionLibrary record);

//    int deleteByPrimaryKey(Integer id);
//
//
//
//    Questioninfo selectByPrimaryKey(Integer id);
//

//

}