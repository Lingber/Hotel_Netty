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
<title>用户信息管理</title>
	<script type="text/javascript" src="./static/js/jquery-3.0.0.min.js"></script>
	<link href="./static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="./static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<link href="${APP_PATH }/static/css/main.css" rel="stylesheet">
    <link href="./static/time/bootstrap-datetimepicker.min.css" rel="stylesheet" />
    <script src="./static/time/moment-with-locales.js"></script>
    <script src="./static/time/bootstrap-datetimepicker.min.js"></script>
    <script src="./static/time/bootstrap-datetimepicker.zh-CN.js"></script>
</head>
<body>
<header id="header">
	<div class="container">
	    <div class="row">
	        <div class="col-sm-12 overflow">
	            <div class="social-icons pull-right">
	                <ul class="nav nav-pills">
	                    <li><a href="#"><i class="fa fa-twitter"></i>Git</a></li>
	                    <li><a href="#"><i class="glyphicon glyphicon-envelope"></i></a></li>
	                </ul>
	            </div>
	        </div>
	    </div>
	</div>        
    <div class="navbar navbar-inverse" role="banner">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">
                    <h1><img src="${APP_PATH }/static/media/logo.png" alt="logo" height="80"></h1>
                </a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li class="active"><a href="#">房间信息管理</a></li>
                    <li class="#"><a href="Home.jsp">用户信息管理</a></li>
                </ul>
            </div>

        </div>
    </div>
</header>


<a class='input-group date' id='datetimepicker1' style="float: left; left: 320px;">
                <input type='text' class="form-control" id='nowdate' style="width: 150px; height: 30px;" />
                <span class="input-group-addon" style="float: left; width: 50px; height: 30px;">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </a>


	
	<!-- js脚本开始 -->
	<script type="text/javascript">
	//设置日期时间控件
	function Datetime() {
	    $('#datetimepicker1').datetimepicker({
	        language: 'zh-CN',//显示中文
	        format: 'yyyy-mm-dd',//显示格式
	        minView: "month",//设置只显示到月份
	        initialDate: new Date(),
	        autoclose: true,//选中自动关闭
	        todayBtn: true,//显示今日按钮
	        locale: moment.locale('zh-cn')
	    });
	    //默认获取当前日期
	    var today = new Date();
	    var nowdate = (today.getFullYear()) + "-" + (today.getMonth() + 1) + "-" + today.getDate();
	    //对日期格式进行处理
	    var date = new Date(nowdate);
	    var mon = date.getMonth() + 1;
	    var day = date.getDate();
	    var mydate = date.getFullYear() + "-" + (mon < 10 ? "0" + mon : mon) + "-" + (day < 10 ? "0" + day : day);
	    document.getElementById("nowdate").value = mydate;
	}
	</script>

</body>
</html>