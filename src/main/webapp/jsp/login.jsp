<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <link rel="stylesheet" type="text/css" href="../css/login.css">
    <script type="text/javascript" src="../js/jquery-3.3.1.js"></script>

    <script type="text/javascript">
        
        $(function () {

            $("#userLogin").click(function () {

                doLogin();

            });

            $(".login-box").keydown(function () {
                if(event.keyCode == 13){
                    doLogin();
                }
            })

            function doLogin() {
                $.ajax({
                    type:"post",
                    url:"login.do",
                    data:"username="+$("#username").val()+"&password="+$("#password").val(),
                    dataType:"json",
                    success:function (data) {

                        if(data.success == 1){
                            window.location.href = "user/main.jsp";
                        }else{
                            alert("用户名或密码错误，请重新登录");
                        }

                    }
                })
            }

            $("#button2Register").click(function () {

                window.location.href = "register.jsp";

            })

        })

    </script>

</head>
<body>

    <div class="login-box">
        <div class="logo">
            <span class="title">聊天室登录界面</span>
        </div>
        <div class="user_info">
            <div class="username">
                <div class="frame-left">

                </div>
                <div class="user_logo">
                    <img src="../images/img_user.png">
                </div>
                <div class="user_text">
                    <input type="text" class="text_style" id="username" autocomplete="off">
                </div>

            </div>
            <div class="password">
                <div class="frame-left">

                </div>
                <div class="user_logo">
                    <img src="../images/img_password.png">
                </div>
                <div class="user_text">
                    <input type="password" class="text_style" id="password">
                </div>
            </div>
            <div class="login_button">
                <button class="login-submit" id="userLogin">登录</button>
            </div>
            <div class="goRegister">
                <button class="button2Register" id="button2Register">去注册</button>
            </div>
        </div>
    </div>

</body>
</html>
