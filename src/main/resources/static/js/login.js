/**
 * Created by lenovo on 2019/8/26.
 */
$(function(){
   // window.location.href="/userinfo/template";

    // group_del[1]=8;
    var option_list=new Array;

    option_list[0]="231";
    option_list[1]="wqeqe";

    var answer_list=new Array;
    var test=new Array;


    answer_list[0]="231";
    answer_list[1]="wqeqe";
    //console.log(test1);
    var test1={
        type:"multi",
        score:20,
        content:"jaskdl",
        option_list:option_list,
        answer_list:answer_list
    };

    var test2={
        type:"single",
        score:20,
        content:"jaskdl",
        option_list:option_list,
        answer_list:answer_list
    };
    test[0]=test1;
    test[1]=test2;

    $.ajax({
        url: "/question/new",
        type: "post",
        traditional: true,
        contentType: "application/json; charset=UTF-8",
        dataType: "json",
        data:JSON.stringify(test),
        success: function (data) {
           console.log(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            // console.log(XMLHttpRequest.status);
            // console.log(XMLHttpRequest.readyState);
            // console.log(textStatus);
        },
    });


    //
    // $("#regis-submit").click(function() {
    //     var userName = $("#username").val();
    //     var password = $("#password").val();
    //
    //     var userType=$("#usertype").val();
    //     var user = {
    //         userName: userName,
    //         password: password,
    //         userType:userType,
    //         id:""
    //     };
    //
    //     $.ajax({
    //         url: "/userinfo",
    //         type: "POST",
    //         traditional: true,
    //         contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    //         dataType: "json",
    //         data: user,
    //         success: function (data) {
    //             if(data.status=="200"){
    //                 alert(data.msg);
    //             }else{
    //                 alert(data.msg);
    //             }
    //         },
    //         error: function (XMLHttpRequest, textStatus, errorThrown) {
    //             // console.log(XMLHttpRequest.status);
    //             // console.log(XMLHttpRequest.readyState);
    //             // console.log(textStatus);
    //         },
    //     });
    //
    //
    // });



    // $("#login-submit").click(function() {
    //     var username = $("#username2").val();
    //     var password = $("#password2").val();
    //
    //
    //     var user = {
    //         username: username,
    //         password: password
    //     };
    //
    //     $.ajax({
    //         url: "/userinfo",
    //         type: "GET",
    //         traditional: true,
    //         contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    //         dataType: "json",
    //         data: user,
    //         success: function (data) {
    //             if(data.status=="200"){
    //                 alert(data.msg);
    //             }else{
    //                 alert(data.msg);
    //             }
    //         },
    //         error: function (XMLHttpRequest, textStatus, errorThrown) {
    //             // console.log(XMLHttpRequest.status);
    //             // console.log(XMLHttpRequest.readyState);
    //             // console.log(textStatus);
    //         },
    //     });
    //
    //
    // });



});
