package com.examsys.model.entity;

import com.examsys.model.QuestionLibrary;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Data
@Setter
@Getter
public class QuesKnowEntity {
    private int k_id;
    private String k_name;
    private List<QuestionLibrary> ques_list;

    public int getK_id() {
        return k_id;
    }

    public void setK_id(int k_id) {
        this.k_id = k_id;
    }

    public String getK_name() {
        return k_name;
    }

    public void setk_name(String k_name) {
        this.k_name = k_name;
    }

        public List<QuestionLibrary> getQues_list() {
        return ques_list;
    }

        public void setQues_list(List<QuestionLibrary> ques_list) {
        this.ques_list = ques_list;
    }
}

