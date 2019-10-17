<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <script src="${request.contextPath}/statics/js/jquery-1.7.2.js" type="text/javascript"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录页面</title>
</head>

<body>
用户名：<input id="username" type="text"> <br/>
密码： <input id="password" type="password"> <br/>
验证码：<input id="captchaCode" type="text"><br/>
生成的验证码：<img id="changeCaptcha" src="http://127.0.0.1:9001/getCaptchaCode"> <a href="javascript:changeCaptcha()">看不清，换一张</a><br/>
<input type="button" value="提交" onclick="checkCaptcha()"> <br/>
</body>


<script type="text/javascript">

    //获取验证码图片
   function changeCaptcha(){
        $("#changeCaptcha").attr("src","http://127.0.0.1:9001/getCaptchaCode");
    }
    //验证输入的验证码
    function checkCaptcha() {
        var username = $("#username").val();
        var password = $("#password").val();
        var captchaCode = $("#captchaCode").val();
        console.log(username + "：" + password + ":" + captchaCode);
        $.ajax({
            type: 'post',
            async: false,
            url: 'http://127.0.0.1:9001/login',
            xhrFields: {
                withCredentials: true
            },
            data: {
                "captchaCode": captchaCode,
                "username": username,
                "password": password
            },
            success: function (res) {
               console.log(res.code+":"+res.msg);
            }
        });
    }
</script>
</html>
