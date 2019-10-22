/**
 * Created by lenovo on 2019/8/26.
 */
$(function(){

    // var option_list=new Array;
    //
    // option_list[0]="231";
    // option_list[1]="wqeqe";
    //
    // var answer_list=new Array;
    //
    //
    //
    // answer_list[0]="231";
    // answer_list[1]="wqeqe";


    var json=new Array;
    var json1={
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
    };

    var json2={
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
    };

    var json3={
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
    };

    var json4={
        type: "subjective",
        score: 20,
        content: "简答题测试",
        description:"描述测试1",
        must_or_not: 1,
        category_content: "二、计算机网络",
        option_list: [],
        answer_list: ["参考答案测试"]
    };

    var json5={
        type: "judge",
        score: 20,
        content: "判断题测试2",
        description:"描述测试1",
        must_or_not: 1,
        category_content: "二、计算机网络",
        option_list: [{
            "id": 0,
            "option": "选项一内容"
        }, {
            "id": 1,
            "option": "选项二内容"
        }],
        answer_list: [{"id":0, "content":"选项一内容"}]
    };


    json[0]=json1;
    json[1]=json2;
    json[2]=json3;
    json[3]=json4;
    json[4]=json5;

    var map={
        title:"测试名称",
        description:"描述",
        question_list:json,
        user_id:1
    };


    $("#api-18").click(function() {
        $.ajax({
            url: "/paper/new",
            type: "post",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(map),
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


    $("#api-19").click(function() {
        $.ajax({
            url: "/question/multi",
            type: "post",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(json),
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
