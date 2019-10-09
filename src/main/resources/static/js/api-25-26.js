/**
 * Created by lenovo on 2019/8/26.
 */
$(function(){

    // var option_list=new Array;
    //
    // option_list[0]="231";
    // option_list[1]="wqeqe";
    //
    // var answer_list=new Array;
    //
    //
    //
    // answer_list[0]="231";
    // answer_list[1]="wqeqe";


    var json=new Array;
    var json1={
        name: "数据库2",
        description:"数据库",
        level: 2,
        parent_id:2

    };

    var json2={
        name: "计算机1",
        description:"计算机",
        level: 1,
        parent_id:0
    };

    json[0]=json1;
    json[1]=json2;

    $("#api-25").click(function() {
        $.ajax({
            url: "/keypoint/multi",
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

    $("#api-26").click(function() {

        //var str="[{\"id\":0,\"option\":\"选项一内容\"},{\"id\":1,\"option\":\"选项二内容\"}]";
        //var str2=JSON.parse(str);
        //  console.log(str2);
        // console.log(str2.length);
        // console.log(str2[0]);
        // console.log(str2[0].id);
        $.ajax({
            url: "/keypoint/all",
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

});
