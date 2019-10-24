/**
 * Created by lenovo on 2019/8/26.
 */
$(function(){


    $("#4-api-11").click(function() {
        var str7={
            exam_id:3
        };
        $.ajax({
            url: "/spi/startobj",
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

});
