/**
 * Created by lenovo on 2019/8/26.
 */
$(function(){

    $("#api-22").click(function() {

        //var str="[{\"id\":0,\"option\":\"选项一内容\"},{\"id\":1,\"option\":\"选项二内容\"}]";
        //var str2=JSON.parse(str);
      //  console.log(str2);
       // console.log(str2.length);
       // console.log(str2[0]);
       // console.log(str2[0].id);
        $.ajax({
            url: "/question/all",
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
