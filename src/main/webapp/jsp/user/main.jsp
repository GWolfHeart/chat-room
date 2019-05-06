<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>聊天室</title>
    <link rel="stylesheet" type="text/css" href="../../css/main.css">
    <script type="text/javascript" src="../../js/jquery-3.3.1.js"></script>
    <script type="text/javascript">

        window.onload = function () {

            $.ajax({

                url : "getUser.do",
                success : function (data) {

                    if (data.success == "true") {
                        var htmlStr = "<span id='append-username'>" + data.user +"</span>";
                        $("#username").html(htmlStr);
                        refreshRoomInfo();
                        refreshConversation();
                    } else {
                        alert("你还没登录呢，快去登录吧")
                        window.location.href = "../login.jsp";
                    }

                }

            });

        };

        function refreshConversation() {

            $.ajax({

                type:"get",
                url:"refreshConversation.do",
                success:function (data) {
                    var str = "";
                    var conversations = data.conversations;
                    $.each(conversations,function (index,conversation) {

                        str = str +"("+ conversation.time +") "+ conversation.username
                            +" : "+ conversation.message +"\n";

                    });
                    $("#text-area").val(str);
                    $("#text-area").scrollTop(1000000);

                },
                dataType:'json'

            });

        }

        function refreshRoomInfo() {
            $.ajax({

                type:"get",
                url:"queryUsersOnline.do",
                success:function (data) {

                    var usersNum = data.usersNum;
                    var users = data.users;
                    var userAdded = $("#userAdded");
                    userAdded.html("");
                    $("#userNums").html("在线人数： " + usersNum + "人");
                    $.each(users, function (index, user) {
                        userAdded.append("<li class=\"users\"><span class=\"users-name\">"
                            + user.username + "</span></li>");
                    });

                },
                dataType:'json'

            });
        }

        $(function () {



            setInterval(refreshConversation,1000);

            setInterval(refreshRoomInfo,3000)

            function sendMessage() {

                if ($.trim($("#send-area").val()).length > 0){
                    $.get(
                        "sendMessage.do?message="+$("#send-area").val(),
                        function success(data) {
                            var str = $("#text-area").val() + data.user + " : " + data.message +"\n";
                            $("#text-area").val(str);
                            $("#text-area").scrollTop(1000000);
                            $("#send-area").val("");
                        },
                        'json'
                    );
                }

            }

            $("#button-send").click(function () {
                sendMessage();
            });

            $("#send-area").keydown(function () {

                /**
                 * 回车事件 发送消息
                 */
                if(event.keyCode == 13){
                    sendMessage();
                }

            })

            $("#button-logout").click(function () {

                $.ajax({

                    url:"logout.do",
                    success : function () {

                        alert("已安全退出")
                        window.location.href = "../login.jsp";

                    }

                });

            })

        })

    </script>
</head>
<body>
    <div class="main">
        <div class="logo">
            <div id="logo">

            </div>
            <div class="user-info">
                <div id="username">

                </div>
                <button id="button-logout">退出</button>
            </div>
        </div>
        <div id="logo_bottom" class="dividing-line">

        </div>
        <div class="room_info">
            <div class="countUsersOnline" id="countUsersOnline">
                <span id="userNums"></span>
            </div>
            <div class="usersOnline" id="usersOnline">
                <ul class="user-list" id="userAdded">
                    <%--<li class="users"><span class="users-name">小明</span></li>--%>
                </ul>
            </div>
        </div>
        <div id="info-right" class="dividing-line">

        </div>
        <div class="conversation-area">
            <textarea class="text-area" id="text-area" readonly="readonly"></textarea>
        </div>
        <div id="conversation-bottom" class="dividing-line">

        </div>
        <div class="message-area">
            <div class="message-text">
                <textarea class="text-area" id="send-area"></textarea>
            </div>
            <div class="button-area">
                <button id="button-send">发送</button>
            </div>
        </div>

    </div>
</body>
</html>
