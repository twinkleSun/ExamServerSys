package com.examsys.dao;

import com.examsys.model.QuestionLibrary;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface QuestionLibraryMapper {

    int insert(QuestionLibrary questionLibrary);

    QuestionLibrary selectByQuestion(QuestionLibrary questionLibrary);

    List<QuestionLibrary> selectAll();



//    int deleteByPrimaryKey(Integer id);
//
//
//
//    Questioninfo selectByPrimaryKey(Integer id);
//

//
//    int updateByPrimaryKey(Questioninfo record);
}