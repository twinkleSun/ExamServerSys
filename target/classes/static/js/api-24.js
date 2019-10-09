/**
 * Created by lenovo on 2019/8/26.
 */
$(function(){

    $("#api-24").click(function() {

        var str={
            paper_code:"20191008193539"
        }
        $.ajax({
            url: "/paper/single",
            type: "post",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(str),
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
