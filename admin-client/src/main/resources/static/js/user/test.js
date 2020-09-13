console.log("hello world");
/**
 * 初始化user页面
 */
$(function(){
    var session = $("#session").val();
    console.log("当前请求的session为："+session);
});

//改变语言
var onchange = function(){
    var language = $("#language").val();
    if(language==01){
        $("#test").click(function() {
            $.ajax({
                type: "GET",
                url: "/th/user",
                beforeSend: function(request) {
                    request.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.9");
                },
                success: function(result) {
                    alert("success");
                }
            });
        });
    }else{
        $("#test").click(function() {
            $.ajax({
                type: "GET",
                url: "/th/user",
                beforeSend: function(request) {
                    request.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.9");
                },
                success: function(result) {
                    alert("success");
                }
            });
        });
    }
};


