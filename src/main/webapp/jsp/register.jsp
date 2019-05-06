<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>register</title>
    <link rel="stylesheet" type="text/css" href="../css/register.css">
    <script type="text/javascript" src="../js/jquery-3.3.1.js"></script>

    <script type="text/javascript">

        $(function () {

            $("#username").blur(function () {

                if($(this).val().length > 0){

                    $.post(
                        "usernameCheck.do",
                        {username:$("#username").val()},
                        function success (data) {
                            if (data.success == 1) {
                                $("#check-username").css("background-color","#7aff44");
                            } else {
                                $("#check-username").css("background-color","#ff4f3b");
                            }
                        },
                        'json'
                    );

                }

            })

            $("#userRegister").click(function () {
                doRegister();
            })

            $(".login-box").keydown(function () {
                if(event.keyCode == 13){
                    doRegister();
                }
            })

            function doRegister() {

                if($("#check-username").css("background-color") == 'rgb(122, 255, 68)' && $("#password").val().length > 0){
                    $.post(
                        "register.do",
                        {
                            username:$("#username").val(),
                            password:$("#password").val()
                        },
                        function success () {
                            alert("注册成功，快去登录吧")
                            window.location.href = "login.jsp";
                        }
                    );
                } else {
                    alert("注册失败，请检查用户名密码")
                }

            }

            $("#button2Login").click(function () {

                window.location.href = "login.jsp";

            })

        })


    </script>

</head>
<body>
<div class="login-box">
    <div class="logo">
        <span class="title">聊天室注册界面</span>
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
                <div class="check-div">
                    <div id="check-username" class="check-info">

                    </div>
                </div>
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
                <div class="check-div">
                    <div id="check-password" class="check-info">

                    </div>
                </div>
            </div>

        </div>
        <div class="login_button">
            <button class="login-submit" id="userRegister">注册</button>
        </div>
        <div class="goLogin">
            <button class="button2Login" id="button2Login">去登录</button>
        </div>
    </div>
</div>
</body>
</html>
