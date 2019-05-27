<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script src="${pageContext.request.contextPath}/js/jquery-1.7.2.js" type="text/javascript"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录页面</title>

</head>
<body>
生成的验证码：<img id="changeCaptcha" src="http://127.0.0.1:9001/getCaptchaCode"> <a href="javascript:changeCaptcha()">看不清，换一张</a>
<br>
<br>
用户名：<input id="username" type="text">
密码：  <input id="password" type="password">
验证码：<input id="captchaCode" type="text"> <input type="button" value="提交" onclick="checkCaptcha()">
</body>
<script type="text/javascript">

    //获取验证码图片
    function changeCaptcha(){
        $("#changeCaptcha").attr("src","http://127.0.0.1:9001/getCaptchaCode");
    }
    //验证输入的验证码
    function checkCaptcha(){
        var username = $("#username").val();
        var password = $("#password").val();
        var captchaCode = $("#captchaCode").val();
        $.ajax({
            type:'post',
            async : false,
            url:'http://127.0.0.1:9001/checkCaptchaCode',
            data:{"captchaCode" : captchaCode,
                    "username":username,
                    "password":password
                  },
            success:function(res){
                alert(res);
            }
        });
    }
</script>
</html>
