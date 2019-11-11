/**
 * Created by lenovo on 2019/8/26.
 */
$(function(){

    $("#4-api-7").click(function() {
        var str7 = {
            exam_id: 3,
            paper_code: 20191008193539,
            student_id: 1,
            end_flag:0,
            paper_status: [
                {
                    id: 6,
                    content: "多选题测试3-1",
                    type: "multi",
                    description: "描述测试1",
                    total_point: 10,
                    mustOrNot: 0,
                    stamp:1,
                    student_answer: [{id:0, content:"选项一内容"}]
                },
                {
                    id: 4,
                    content: "简答题2",
                    type: "multi",
                    description: "简答题2追加描述",
                    total_point: 20,
                    mustOrNot: 0,
                    stamp:0,
                    student_answer: "学生1填写的答案2"
                }
            ]
        };
        $.ajax({
            url: "/spi/do",
            type: "post",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(str7),
            success: function (data) {
                console.log(data);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                // console.log(XMLHttpRequest.status);
                // console.log(XMLHttpRequest.readyState);
                // console.log(textStatus);
            },
        });

    });


    $("#4-api-8").click(function() {

        var str8={
            exam_id: 3
        };
        $.ajax({
            url: "/spi/subanswers",
            type: "post",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(str8),
            success: function (data) {
                console.log(data);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                // console.log(XMLHttpRequest.status);
                // console.log(XMLHttpRequest.readyState);
                // console.log(textStatus);
            },
        });

    });


    $("#4-api-9").click(function() {
        var techsub={
            exam_id:3,
            paper_code: 20191008193539,
            student_answers_detail: [{
                student_id: 1,
                student_name: "郭靖与",
                paper_status: {
                    objective_grade: 60,
                    subjective_answers: [{
                        id: 6,
                        content: "简答题1",
                        description: "简答题1追加描述",
                        total_point:10,
                        point: 6,
                        answer: "参考答案1",
                        student_answer: "学生1填写的答案"
                    },
                        {
                            id: 4,
                            content: "简答题2",
                            description: "简答题2追加描述",
                            total_point:10,
                            point: 7,
                            answer: "参考答案2",
                            student_answer: "学生1填写的答案"
                        }]
                }
            },
                {
                    student_id: 2,
                    student_name: "王涛",
                    paper_status: {
                        objective_grade: 66,
                        subjective_answers: [{
                            id: 6,
                            content: "简答题1",
                            description: "简答题1追加描述",
                            point: 4,
                            total_point:10,
                            answer: "参考答案1",
                            student_answer: "学生2填写的答案"
                        },
                            {
                                id: 4,
                                content: "简答题2",
                                description: "简答题2追加描述",
                                point: 5,
                                total_point:10,
                                answer: "参考答案2",
                                student_answer: "学生2填写的答案"
                            }]
                    }
                }]
        };
        $.ajax({
            url: "/spi/techsub",
            type: "post",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(techsub),
            success: function (data) {
                console.log(data);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                // console.log(XMLHttpRequest.status);
                // console.log(XMLHttpRequest.readyState);
                // console.log(textStatus);
            },
        });

    });


    $("#4-api-10").click(function() {
        var str10={
            exam_id: 3
        };
        $.ajax({
            url: "/exam/techend",
            type: "post",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(str10),
            success: function (data) {
                console.log(data);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                // console.log(XMLHttpRequest.status);
                // console.log(XMLHttpRequest.readyState);
                // console.log(textStatus);
            },
        });

    });
});
