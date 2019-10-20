$(function(){
    var str={
        exam_id: 3
    };

    $("#api-4_7").click(function() {

        $.ajax({
            url: "/spi/subanswers",
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
