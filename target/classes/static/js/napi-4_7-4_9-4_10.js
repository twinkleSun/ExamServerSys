$(function(){
    var str={
        exam_id: 3
    };

    $("#api-4_7").click(function() {

        $.ajax({
            url: "/spi/subanswers",
            type: "post",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(str),
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


    $("#api-4_9").click(function() {

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


    var str={
        exam_id: 3
    };
    $("#api-4_10").click(function() {

        $.ajax({
            url: "/exam/techend",
            type: "post",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(str),
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
