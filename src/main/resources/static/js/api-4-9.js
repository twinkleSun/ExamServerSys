/**
 * Created by lenovo on 2019/8/26.
 */
$(function(){

    $("#api-4").click(function() {

        $.ajax({
            url: "/upi/groupuser/all",
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

    $("#api-5").click(function() {

        $.ajax({
            url: "/upi/usergroup/all",
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


    var json9={
        id:2
    };

    $("#api-9").click(function() {
        $.ajax({
            url: "/upi/groupuser/gid",
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
