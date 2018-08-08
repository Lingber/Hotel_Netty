<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <!-- 获取当前项目路径，以/开始，不以/结尾 -->
<%
  pageContext.setAttribute("APP_PATH",request.getContextPath());
 %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>用户登录 </title>
	<script type="text/javascript" src="static/js/jquery-3.0.0.min.js"></script>
	
    <!-- Bootstrap -->
    <link href="static/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="static/css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="static/css/nprogress/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="static/css/animate.css/animate.min.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="static/css/build/css/custom.min.css" rel="stylesheet">

    
  </head>

  <body class="login">
    <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>

      <div class="login_wrapper">
        <div class="animate form login_form">
          <section class="login_content">
            <!-- <form name="login" action="login/loginCheckUp" method="post"> -->
            <form id="login_form" name="login">
              <h1>用户登录</h1>
              <div>
                <input type="text" name="phone_Numb" class="form-control" placeholder="手机号" required="" />
              </div>
              <div>
                <input type="text" name="roomnumber" class="form-control" placeholder="房间号" required="" />
              </div>
              <div>
                <input type="text" name=overTime class="form-control" placeholder="最迟退房时间" required="" />
              </div>
              <div>
              <button type="button" class="btn btn-default submit" id="login_btn">写 </button>
                <!-- <input class="btn btn-default submit" type="submit" value="登录"> -->
              </div>

              <div class="clearfix"></div>
            </form>
           	<button type="button" class="btn btn-default" id="close_btn">退出写模式</button>
          </section>
        </div>
     </div>
   </div>
     <script>
    //登录
    $("#login_btn").click(function () {
    	var code05="05";
		var code03="03";
		var code04="04";
    	//发送Ajax请求
        $.ajax({
            url:"/Hotel_Server/socket/waitClient",
            type:"POST",
            data: $("#login_form").serialize(),
            success:function (result) {
                if(result.msg_code == code05){
                	alert("写入成功");
                	self.location='#'; 
                }else {
                	if(result.msg_code == code03){
                		alert("没有开启服务器");
                		self.location='write.jsp'; 
                	}else{
                		alert("终端未连接！");
                		self.location='write.jsp'; 
                	}
                }
            }
        });
    });
	
    $("#close_btn").click(function () {
    	var code06="06";
		var code03="03";
		var code04="04";
    	//发送Ajax请求
        $.ajax({
            url:"/Hotel_Server/socket/closeScoket",
            type:"POST",
            data: "",
            success:function (result) {
                if(result.msg_code == code06){
                	alert("关闭成功！");
                	self.location='#'; 
                }else {
                	if(result.msg_code == code03){
                		alert("没有开启服务器");
                		self.location='write.jsp'; 
                	}else{
                		alert("终端未连接！");
                		self.location='write.jsp'; 
                	}
                }
            }
        });
    });
    
	</script> 
	
  </body>
</html>
