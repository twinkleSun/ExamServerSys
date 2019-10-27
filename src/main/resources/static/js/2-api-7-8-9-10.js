/**
 * Created by lenovo on 2019/8/26.
 */
$(function(){


    $("#2-api-7").click(function() {
        var str7={
            ques_name_filter: ["测试"],
            ques_type_filter: ["judge"],
            ques_knowledge_filter: ["数据库"]
        };
        $.ajax({
            url: "/question/filter",
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


    $("#2-api-8").click(function() {

        var json8={
            id:24,
            type: "judge",
            content: "判断题测试1027",
            description:"描述测试1",
            option_list: [{
                "id": 0,
                "content": "选项一内容"
            }, {
                "id": 1,
                "content": "选项二内容"
            }],
            answer_list: [{"id":0, "content":"选项一内容"}],
            knowledge_list: ["数据库1","数据库2"]
        };


        var json8_2={
            id:25,
            type: "subjective",
            content: "简答题测试1027",
            description:"描述测试1",
            option_list: [],
            answer_list: [""],
            knowledge_list: ["数据库1"]
        };

        $.ajax({
            url: "/question/single",
            type: "post",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(json8_2),
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


    $("#2-api-9").click(function() {

        var json9={
            id:7,
            name: "数据库4",
            description:"数据库",
            level: 2,
            parent_id:2
        };

        $.ajax({
            url: "/keypoint/single",
            type: "post",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(json9),
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

    $("#2-api-10").click(function() {

        var json9={
            id:[2]
        };

        $.ajax({
            url: "/keypoint/del",
            type: "post",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(json9),
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
