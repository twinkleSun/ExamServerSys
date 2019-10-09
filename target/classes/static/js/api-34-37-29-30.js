/**
 * Created by lenovo on 2019/8/26.
 */
$(function(){

    $("#api-34").click(function() {

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


    var json={
        admin_id:1
    };

    $("#api-37").click(function() {

        $.ajax({
            url: "/paper/adminpaper",
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


    var json29={
        exam_name:"考试测试",
        paper_code:"20191008193539",
        begin_time:"2019-10-10 19:35:39",
        end_time:"2019-10-10 21:35:39",
        duration:"00:02:00",
        group_ids:[1,2]
    };



    $("#api-29").click(function() {

        $.ajax({
            url: "/exam/new",
            type: "post",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(json29),
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


    var json30={
        exam_id:1,
        group_ids:[3,4]
    };

    $("#api-30").click(function() {

        $.ajax({
            url: "/exam/group",
            type: "post",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(json30),
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
