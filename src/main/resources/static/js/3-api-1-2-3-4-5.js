/**
 * Created by lenovo on 2019/8/26.
 */
$(function(){


    $("#3-api-1").click(function() {

        var json1=new Array;
        var json1_1={
            id:1,
            type: "multi",
            score: 20,
            description:"描述测试1",
            content: "多选题测试3-1",
            must_or_not: 0,
            category_content: "一、数据库基础",
            option_list: [{
                id: 0,
                content: "选项一内容"
            }, {
                id: 1,
                content: "选项二内容"
            }],

            answer_list: [{id:0, content:"选项一内容"},{id:1, content:"选项一内容"}]
        };
        var json1_2={
            id:2,
            type: "multi",
            score: 20,
            content: "多选题测试4-1",
            description:"描述测试1",
            must_or_not: 0,
            category_content: "一、数据库基础",
            option_list: [{
                id: 0,
                content: "选项一内容"
            }, {
                id: 1,
                content: "选项二内容"
            }, {
                id: 2,
                content: "选项三内容"
            }],

            answer_list: [{id:0, content:"选项一内容"}]
        };
        var json1_3={
            id:3,
            type: "single",
            score: 20,
            content: "单选题测试",
            description:"描述测试1",
            must_or_not: 0,
            category_content: "一、数据库基础",
            option_list: [{
                id: 0,
                content: "选项一内容"
            }, {
                id: 1,
                content: "选项二内容"
            }, {
                id: 2,
                option: "选项三内容"
            }, {
                id: 3,
                content: "选项四内容"
            }],

            answer_list: [{id:1, content:"选项er内容"}]
        };
        var json1_4={
            id:4,
            type: "subjective",
            score: 20,
            content: "简答题测试",
            description:"描述测试1",
            must_or_not: 1,
            category_content: "二、计算机网络",
            option_list: [],
            answer_list: ["参考答案测试"]
        };
        var json1_5={
            id:5,
            type: "judge",
            score: 20,
            content: "判断题测试2",
            description:"描述测试1",
            must_or_not: 1,
            category_content: "二、计算机网络",
            option_list: [{
                id: 0,
                content: "选项一内容"
            }, {
                id: 1,
                content: "选项二内容"
            }],
            answer_list: [{id:0, content:"选项一内容"}]
        };
        json1[0]=json1_1;
        json1[1]=json1_2;
        json1[2]=json1_3;
        json1[3]=json1_4;
        json1[4]=json1_5;

        var map={
            title:"测试名称",
            description:"描述222",
            paper_code:"20191029200827",
            question_list:json1,
            user_id:1
        };

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

    $("#3-api-2").click(function() {

        $.ajax({
            url: "/paper/all",
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


    $("#3-api-3").click(function() {
        var json3={
            paper_code:["20191027173527","20191027173021"]
        };
        $.ajax({
            url: "/paper/del",
            type: "delete",
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

    $("#3-api-4").click(function() {
        var json4={
            admin_id:1
        };
        $.ajax({
            url: "/paper/adminpaper",
            type: "post",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(json4),
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

    $("#3-api-5").click(function() {
        var str5={
            paper_code:"20191008193539"
        };
        $.ajax({
            url: "/paper/single",
            type: "post",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(str5),
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
