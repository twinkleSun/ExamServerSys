/**
 * Created by lenovo on 2019/8/26.
 */
$(function(){

    $("#4-api-7").click(function() {
        var str7 = {
            exam_id: 3,
            paper_code: 20191008193539,
            student_id: 1,
            end_flag:0,
            paper_status: [
                {
                    id: 6,
                    content: "多选题测试3-1",
                    type: "multi",
                    description: "描述测试1",
                    total_point: 10,
                    mustOrNot: 0,
                    stamp:1,
                    student_answer: [{id:0, content:"选项一内容"}]
                },
                {
                    id: 4,
                    content: "简答题2",
                    type: "multi",
                    description: "简答题2追加描述",
                    total_point: 20,
                    mustOrNot: 0,
                    stamp:0,
                    student_answer: "学生1填写的答案2"
                }
            ]
        };
        $.ajax({
            url: "/spi/do",
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


    $("#4-api-8").click(function() {

        var str8={
            exam_id: 22
        };
        $.ajax({
            url: "/spi/subanswers",
            type: "post",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(str8),
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


    $("#4-api-9").click(function() {
        var techsub={
            exam_id:22,
            paper_code: 20191008193539,
            student_answers_detail: [{
                student_id: 10,
                student_name: "郭靖与",
                paper_status: {
                    objective_grade: 60,
                    subjective_answers: [{
                        id: 37,
                        content: "简答题1",
                        description: "简答题1追加描述",
                        total_point:10,
                        point: 6,
                        answer: [{id:0,content:"<h2><strong>管道：</strong></h2><ul><li>它是半双工的（即数据只能在一个方向上流动），具有固定的读端和写端。</li><li>它只能用于具有亲缘关系的进程之间的通信（也是父子进程或者兄弟进程之间）。</li><li>它可以看成是一种特殊的文件，对于它的读写也可以使用普通的read、write 等函数。但是它不是普通的文件，并不属于其他任何文件系统，并且只存在于内存中。</li></ul><h2><strong>消息队列：</strong></h2><ul><li>它是半双工的（即数据只能在一个方向上流动），具有固定的读端和写端。</li><li>它只能用于具有亲缘关系的进程之间的通信（也是父子进程或者兄弟进程之间）。</li><li>它可以看成是一种特殊的文件，对于它的读写也可以使用普通的read、write 等函数。但是它不是普通的文件，并不属于其他任何文件系统，<strong>并且只存在于内存中。</strong></li></ul><h2><strong>共享内存：</strong></h2><ul><li>共享内存是最快的一种 IPC，因为进程是直接对内存进行存取。</li><li>因为多个进程可以同时操作，所以需要进行同步。</li><li>信号量+共享内存通常结合在一起使用，信号量用来同步对共享内存的访问。</li></ul>"}],
                        student_answer: [{id:0,content:"<ol><li>是是是阿萨德</li><li>其味无穷二</li></ol>"}]
                    }]
                }
            },
                {
                    student_id: 11,
                    student_name: "王涛",
                    paper_status: {
                        objective_grade: 66,
                        subjective_answers: [{
                            id: 37,
                            content: "简答题1",
                            description: "简答题1追加描述",
                            point: 4,
                            total_point:10,
                            answer: [{id:0,content:"<h2><strong>管道：</strong></h2><ul><li>它是半双工的（即数据只能在一个方向上流动），具有固定的读端和写端。</li><li>它只能用于具有亲缘关系的进程之间的通信（也是父子进程或者兄弟进程之间）。</li><li>它可以看成是一种特殊的文件，对于它的读写也可以使用普通的read、write 等函数。但是它不是普通的文件，并不属于其他任何文件系统，并且只存在于内存中。</li></ul><h2><strong>消息队列：</strong></h2><ul><li>它是半双工的（即数据只能在一个方向上流动），具有固定的读端和写端。</li><li>它只能用于具有亲缘关系的进程之间的通信（也是父子进程或者兄弟进程之间）。</li><li>它可以看成是一种特殊的文件，对于它的读写也可以使用普通的read、write 等函数。但是它不是普通的文件，并不属于其他任何文件系统，<strong>并且只存在于内存中。</strong></li></ul><h2><strong>共享内存：</strong></h2><ul><li>共享内存是最快的一种 IPC，因为进程是直接对内存进行存取。</li><li>因为多个进程可以同时操作，所以需要进行同步。</li><li>信号量+共享内存通常结合在一起使用，信号量用来同步对共享内存的访问。</li></ul>"}],
                            student_answer: [{id:0,content:"<p>苛基本面经</p><p>鑫森</p>"}]
                        }]
                    }
                }]
        };
        $.ajax({
            url: "/spi/techsub",
            type: "post",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(techsub),
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


    $("#4-api-10").click(function() {
        var str10={
            exam_id: 3
        };
        $.ajax({
            url: "/exam/techend",
            type: "post",
            traditional: true,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(str10),
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
