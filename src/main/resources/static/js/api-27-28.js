/**
 * Created by lenovo on 2019/8/26.
 */
$(function(){
    var json=new Array;
    var json1={
        k_id : 2,
        k_name: "数据库",
        question_list: [{
            type: "multi",
            score: 20,
            description:"描述测试1",
            content: "多选题测试3-1",
            must_or_not: 0,
            category_content: "一、数据库基础",
            option_list: [{
                "id": 0,
                "option": "选项一内容"
            }, {
                "id": 1,
                "option": "选项二内容"
            }],

            answer_list: [0, 1]
        },{
            type: "multi",
            score: 20,
            content: "多选题测试4-1",
            description:"描述测试1",
            must_or_not: 0,
            category_content: "一、数据库基础",
            option_list: [{
                "id": 0,
                "option": "选项一内容"
            }, {
                "id": 1,
                "option": "选项二内容"
            }, {
                "id": 2,
                "option": "选项三内容"
            }],

            answer_list: ["1", 1]
        }]
    };

    var json2={
        k_id : 3,
        k_name: "计算机",
        question_list: [{
            type: "single",
            score: 20,
            content: "单选题测试",
            description:"描述测试1",
            must_or_not: 0,
            category_content: "一、数据库基础",
            option_list: [{
                "id": 0,
                "option": "选项一内容"
            }, {
                "id": 1,
                "option": "选项二内容"
            }, {
                "id": 2,
                "option": "选项三内容"
            }, {
                "id": 3,
                "option": "选项四内容"
            }],

            answer_list: ["1"]
        },{
            type: "subjective",
            score: 20,
            content: "简答题测试",
            description:"描述测试1",
            must_or_not: 1,
            category_content: "二、计算机网络",
            option_list: [],
            answer_list: ["参考答案测试"]
        }]
    };

    json[0]=json1;
    json[1]=json2;

    $("#api-27").click(function() {
        $.ajax({
            url: "keypoint/questions",
            type: "post",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(json),
            success: function (data) {
                console.log(data);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            },
        });

    });

    var json3={
        k_id : 3
    };

    $("#api-28").click(function() {
        $.ajax({
            url: "keypoint/questionlist",
            type: "post",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(json3),
            success: function (data) {
                console.log(data);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            },
        });

    });
});
