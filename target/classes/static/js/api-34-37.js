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

});
