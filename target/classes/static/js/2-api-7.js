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

});
