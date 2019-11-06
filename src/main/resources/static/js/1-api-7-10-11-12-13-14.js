/**
 * Created by lenovo on 2019/8/26.
 */
$(function(){


    $("#1-api-7").click(function() {
        var json7={
            group_id: 1,
            student_id: [5, 9]
        };
        $.ajax({
            url: "/upi/groupuser",
            type: "post",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(json7),
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

    $("#1-api-10").click(function() {
        var json10={
            id:"",
            userName:"user1031",
            password:"password2",
            userType:"student",
            group_list:[{
                group_id:2,
                group_name:"group1"
            }, {
                group_id:5,
                group_name:"group2"
            }]
        };
        $.ajax({
            url: "/upi/usergroup/relation",
            type: "post",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(json10),
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




    $("#1-api-11").click(function() {
        var json11={
            id: [2,3]
        };
        $.ajax({
            url: "/upi/user/multi",
            type: "delete",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(json11),
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

    $("#1-api-12").click(function() {
        var json12={
            group_id:[4]
        };
        $.ajax({
            url: "/upi/group/del",
            type: "delete",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(json12),
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



    $("#1-api-13").click(function() {
        var json13={
            group_id:5,
            student_id:[13,15]
        };
        $.ajax({
            url: "/upi/usergroup/del",
            type: "delete",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(json13),
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



    $("#1-api-14").click(function() {
        var json14={
            group_id:2,
            group_name:"测试1030"
        };
        $.ajax({
            url: "/upi/group/copy",
            type: "post",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(json14),
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
