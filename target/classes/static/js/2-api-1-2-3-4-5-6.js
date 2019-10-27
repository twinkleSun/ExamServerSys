/**
 * Created by lenovo on 2019/8/26.
 */
$(function(){

    var json1=new Array;
    var json1_1={
         type: "multi",
         score: 20,
         description:"描述测试1",
         content: "多选题测试3-1",
         must_or_not: 0,
         category_content: "一、数据库基础",
         option_list: [{
                "id": 0,
                "content": "选项一内容"
            }, {
                "id": 1,
                "content": "选项二内容"
            }],

        answer_list: [{"id":0, "content":"选项一内容"},{"id":1, "content":"选项二内容"}]
    };
    var json1_2={
        type: "multi",
        score: 20,
        content: "多选题测试4-1",
        description:"描述测试1",
        must_or_not: 0,
        category_content: "一、数据库基础",
        option_list: [{
            "id": 0,
            "content": "选项一内容"
        }, {
            "id": 1,
            "content": "选项二内容"
        }, {
            "id": 2,
            "content": "选项三内容"
        }],

        answer_list: [{"id":0, "content":"选项一内容"},{"id":1, "content":"选项二内容"}]
    };
    var json1_3={
        type: "single",
        score: 20,
        content: "单选题测试",
        description:"描述测试1",
        must_or_not: 0,
        category_content: "一、数据库基础",
        option_list: [{
            "id": 0,
            "content": "选项一内容"
        }, {
            "id": 1,
            "content": "选项二内容"
        }, {
            "id": 2,
            "content": "选项三内容"
        }, {
            "id": 3,
            "content": "选项四内容"
        }],

        answer_list: [{"id":1, "content":"选项一内容"}]
    };
    var json1_4={
        type: "subjective",
        // score: 20,
        content: "简答题测试",
        description:"描述测试1",
        // must_or_not: 1,
        // category_content: "二、计算机网络",
        option_list: [],
        answer_list: ["参考答案测试"]
    };
    var json1_5={
        type: "judge",
        // score: 20,
        content: "判断题测试2",
        description:"描述测试1",
        // must_or_not: 1,
        // category_content: "二、计算机网络",
        option_list: [{
            "id": 0,
            "content": "选项一内容"
        }, {
            "id": 1,
            "content": "选项二内容"
        }],
        answer_list: [{"id":0, "content":"选项一内容"}]
    };
    json1[0]=json1_1;
    json1[1]=json1_2;
    json1[2]=json1_3;
    json1[3]=json1_4;
    json1[4]=json1_5;

    $("#2-api-1").click(function() {
        $.ajax({
            url: "/question/multi",
            type: "post",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(json1),
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


    $("#2-api-2").click(function() {
        $.ajax({
            url: "/question/all",
            type: "get",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
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


    var json3=new Array;
    var json3_1={
        name: "数据库2",
        description:"数据库",
        level: 2,
        parent_id:2

    };
    var json3_2={
        name: "计算机1",
        description:"计算机",
        level: 1,
        parent_id:0
    };
    json3[0]=json3_1;
    json3[1]=json3_2;
    $("#2-api-3").click(function() {
        $.ajax({
            url: "/keypoint/multi",
            type: "post",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(json3),
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

    $("#2-api-4").click(function() {
        $.ajax({
            url: "/keypoint/all",
            type: "get",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
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

    var json5=new Array;
    var json5_1={
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
    var json5_2={
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
    json5[0]=json5_1;
    json5[1]=json5_2;

    $("#2-api-5").click(function() {
        $.ajax({
            url: "keypoint/questions",
            type: "post",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(json5),
            success: function (data) {
                console.log(data);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            },
        });

    });


    $("#2-api-6").click(function() {
        var json6={
            k_id : 3
        };
        $.ajax({
            url: "keypoint/questionlist",
            type: "post",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(json6),
            success: function (data) {
                console.log(data);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            },
        });

    });




});
