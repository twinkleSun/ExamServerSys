/**
 * Created by lenovo on 2019/8/26.
 */
$(function(){

    $("#4-api-1").click(function() {
        var json1={
            id:9,
            exam_name:"考试测试",
            paper_code:"20191008193539",
            begin_time:"2019-10-10 19:35:39",
            end_time:"2019-10-10 21:35:39",
            duration:"00:02:00",
            status:"未开始",
            group_ids:[1,2]
        };
        $.ajax({
            url: "/exam/new",
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


    $("#4-api-2").click(function() {
        var json2={
            exam_id:1,
            group_ids:[3,4]
        };
        $.ajax({
            url: "/exam/group",
            type: "post",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(json2),
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


    $("#4-api-3").click(function() {

        var str3={
            user_id:2
        };
        $.ajax({
            url: "/epi/user/examlist",
            type: "post",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(str3),
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

    $("#4-api-5").click(function() {


        $.ajax({
            url: "/epi/admin/examlist",
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



    $("#4-api-4").click(function() {

        var str4={
            exam_id:2
        };
        $.ajax({
            url: "/paper/student",
            type: "post",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(str4),
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


    $("#4-api-6").click(function() {
        var str6={
            paper_code: "20191008193539",
            exam_id:3,
            stu_id:10
        };
        $.ajax({
            url: "/spi/stupaper",
            type: "post",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(str6),
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
