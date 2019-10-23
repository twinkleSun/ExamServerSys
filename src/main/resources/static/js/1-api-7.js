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









});
