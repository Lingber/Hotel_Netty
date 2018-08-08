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
                <input type="text" name="phonenumbLogin" class="form-control" placeholder="手机号" required="" />
              </div>
              <div>
                <input type="password" name="Loginpassword" class="form-control" placeholder="密码" required="" />
              </div>
              <div>
              <button type="button" class="btn btn-default submit" id="login_btn">登录</button>
                <!-- <input class="btn btn-default submit" type="submit" value="登录"> -->
                <a class="reset_pass" href="#">忘记密码？</a>
              </div>

              <div class="clearfix"></div>

              <div class="separator">
                <p class="change_link">没有账户?
                  &nbsp;  &nbsp;  &nbsp; <a href="#signup" class="to_register"> 去注册 </a>
                </p>

                <div class="clearfix"></div>
                <br/>

                <div>
                  <h1><i class="fa fa-paw"></i> 智能胶囊旅馆</h1>
                  <p>©2018  版权所有 @智能胶囊旅馆团队</p>
                </div>
              </div>
            </form>
           
          </section>
        </div>

        <div id="register" class="animate form registration_form">
          <section class="login_content">
            <form id="register_form" name="register">
              <h1>用户注册</h1>
              <div>
                <input type="text" name="username" class="form-control" placeholder="用户昵称" required="" />
              </div>
              <div>
                <input type="text" name="phonenumbRegister" class="form-control" placeholder="手机号码" required="" />
              </div>
              <div>
                <input type="password" name="RegisterpassWord" class="form-control" placeholder="密码" required="" />
              </div>
              <div>
                <input type="text" name="code" class="form-control" placeholder="验证码"/>
              </div>
                                         
              <div>
               <button type="button" class="btn btn-default submit" id="register_btn">注册</button>
               <!--  <input class="btn btn-default submit" type="submit" value="注册"><br> -->
              </div>
			</form>
			<button class="btn btn-default" onclick="getSMScode()">获取验证码</button>
			
              <div class="clearfix"></div>

              <div class="separator">
                <p class="change_link">已经注册 ?
                  <a href="#signin" class="to_register"> 去登陆 </a>
                </p>

                <div class="clearfix"></div>
                <br/>

                <div>
                  <h1><i class="fa fa-paw"></i> 智能胶囊旅馆</h1>
                  <p>©2018  版权所有 @智能胶囊旅馆团队</p>
                </div>
              </div>
            
          </section>
        </div>
      </div>
    </div>
  
     <script>
	function getSMScode(){
		
		var phonenumbRegister = document.getElementsByName("phonenumbRegister")[0].value;
		alert(phonenumbRegister);
		$.ajax({
			url : "/Hotel_Server/register/sms",
			data : "phonenumbRegister="+phonenumbRegister,
			type : "POST",
			success : function(result) {
				alert("发送成功");
			}
		});
	}
	
    //登录
    $("#login_btn").click(function () {
    	var code06 ="06";
    	var code07 ="07";
    	var code08 ="08";
        //发送Ajax请求
        $.ajax({
            url:"/Hotel_Server/login/server_loginCheckUp",
            type:"POST",
            data: $("#login_form").serialize(),
            success:function (result) {
                if(result.msg_code == code06){
                	alert("登录成功");
                	self.location='connect.jsp'; 
                }else {
                	if(result.msg_code == code08){
                		alert("密码错误");
                		self.location='userLogin.jsp'; 
                	}else{
                		alert("账号不存在");
                		self.location='userLogin.jsp'; 
                	}
                }
            }
        });
    });
    
    
    //注册
    $("#register_btn").click(function () {
    	var code04 ="04";
    	var code05 ="05";
        //发送Ajax请求
        $.ajax({
            url:"/Hotel_Server/register/register_request",
            type:"POST",
            data: $("#register_form").serialize(),
            success:function (result) {
                if(result.msg_code == code04){
                	alert("注册成功");
                	self.location='connect.jsp';  
                }else {
                	if(result.msg_code == code05){
                		alert("验证码错误");
                		self.location='userLogin.jsp'; 
                	}else{
                		alert("注册错误");
                		self.location='userLogin.jsp'; 
                	}
                }
            }
        });
    });
	
	
	</script> 
	
  </body>
</html>
