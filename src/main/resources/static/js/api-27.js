$(function(){


    var arr1=new Array();
    arr1[0]="多选题测试";
    arr1[1]="简答题测试";

    var arr2=new Array();
    arr2[0]="multi";


    var arr3=new Array();
    arr3[0]="计算机网络";
    arr3[1]="数据库";

    var str={
        ques_name_filter: ["测试"],
        ques_type_filter: ["judge"],
        ques_knowledge_filter: ["数据库"]
    };
    $("#api-27-2").click(function() {

        $.ajax({
            url: "/question/filter",
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

    // $("#api-35").click(function() {
    //
    //     var str={
    //         exam_id:2
    //     }
    //     $.ajax({
    //         url: "/paper/student",
    //         type: "post",
    //         traditional: true,
    //         contentType: "application/json; charset=UTF-8",
    //         dataType: "json",
    //         data:JSON.stringify(str),
    //         success: function (data) {
    //             console.log(data);
    //         },
    //         error: function (XMLHttpRequest, textStatus, errorThrown) {
    //             // console.log(XMLHttpRequest.status);
    //             // console.log(XMLHttpRequest.readyState);
    //             // console.log(textStatus);
    //         },
    //     });
    //
    // });

});
