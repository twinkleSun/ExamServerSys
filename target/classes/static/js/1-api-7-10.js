/**
 * Created by lenovo on 2019/8/26.
 */
$(function(){


    $("#1-api-7").click(function() {
        var json7={
            group_id: "1",
            student_id: [ 2, 3]
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
            userName:"user",
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



});
