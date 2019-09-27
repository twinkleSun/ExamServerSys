package com.examsys.service;

import com.examsys.model.Groupinfo;
import com.examsys.model.entity.ResponseEntity;

public interface IExamInfoService {
    ResponseEntity checkExamByStudent(int userId);
}
