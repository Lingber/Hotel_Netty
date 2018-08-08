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
	<link rel="icon" href="./static/media/images/favicon.ico" type="image/ico" />
	
	
	<title>智能胶囊旅馆! | </title>
	<!-- jQuery -->
	    <script type="text/javascript" src="./static/js/jquery-3.0.0.min.js"></script>
    <!-- Bootstrap -->
    <!-- <link href="../vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet"> -->
        <link href="./static/home/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <!-- 侧面导航栏的图标、按键 -->
    <!-- <link href="./static/home/font-awesome.min.css" rel="stylesheet"> -->
        <link href="./static/home/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- Custom Theme Style -->
    <!-- 颜色之类的在里面 -->
        <link href="./static/home/custom.min.css" rel="stylesheet">

  </head>
  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
              <a href="index.html" class="site_title"><i class="fa fa-paw"></i> <span>智能胶囊旅馆!</span></a>
            </div>

            <div class="clearfix"></div>

            <!-- 顶头头像图标menu profile quick info -->
            <div class="profile clearfix">
              <div class="profile_pic" id="userinfo4">
              </div>
              <div class="profile_info" id="userinfo1">
                <span>欢迎您,</span>
              </div>
            </div>
            <!-- /顶头头像图标menu profile quick info -->

            <br />

            <!-- 侧面导航sidebar menu -->
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">
                <h3>General</h3>
                <ul class="nav side-menu">
                  <li><a><i class="fa fa-home"></i> Home <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="index.html">Dashboard</a></li>
                      <li><a href="index2.html">Dashboard2</a></li>
                      <li><a href="index3.html">Dashboard3</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-edit"></i> Forms <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="form.html">General Form</a></li>
                      <li><a href="form_advanced.html">Advanced Components</a></li>
                      <li><a href="form_validation.html">Form Validation</a></li>
                      <li><a href="form_wizards.html">Form Wizard</a></li>
                      <li><a href="form_upload.html">Form Upload</a></li>
                      <li><a href="form_buttons.html">Form Buttons</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-table"></i> Tables <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="tables.html">Tables</a></li>
                      <li><a href="tables_dynamic.html">Table Dynamic</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-bar-chart-o"></i> Data Presentation <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="chartjs.html">Chart JS</a></li>
                      <li><a href="chartjs2.html">Chart JS2</a></li>
                      <li><a href="morisjs.html">Moris JS</a></li>
                      <li><a href="echarts.html">ECharts</a></li>
                      <li><a href="other_charts.html">Other Charts</a></li>
                    </ul>
                  </li>
                </ul>
              </div>
            </div>
            <!-- /侧面导航sidebar menu -->

            <!-- /menu footer buttons -->
            <div class="sidebar-footer hidden-small">
              <a data-toggle="tooltip" data-placement="top" title="Settings">
                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="FullScreen">
                <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="Lock">
                <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="Logout" href="login.html">
                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
              </a>
            </div>
            <!-- /menu footer buttons -->
          </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">
          <div class="nav_menu">
            <nav>
              <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
              </div>

              <ul class="nav navbar-nav navbar-right">
                <li class="">
                  <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                    <div id="userinfo3">
                    </div>
                    <span class=" fa fa-angle-down"></span>
                  </a>
                  <ul class="dropdown-menu dropdown-usermenu pull-right">
                    <li><a href="javascript:;"> Profile</a></li>
                    <li>
                      <a href="javascript:;">
                        <span class="badge bg-red pull-right">50%</span>
                        <span>Settings</span>
                      </a>
                    </li>
                    <li><a href="javascript:;">Help</a></li>
                    <li><a href="#" onclick="outlogin();"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
                  </ul>
                </li>
              </ul>
            </nav>
          </div>
        </div>
        <!-- /top navigation -->


        <!-- 内容部分开始 -->

        <!-- page content -->
        <div class="right_col" role="main">
          <!-- top tiles -->
          <div class="row tile_count">
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
							<br>
							<br>
							<!-- 大标题开始 -->
					<div id="userinfo2"></div>
							<!-- 大标题结束 -->
							<br>
							<br>
							<br>
							<br>
							<!-- 选择列表开始 -->
					<div class="list-group">
					  <a href="upload.jsp" class="list-group-item">实名认定</a>
					  <a href="situOfout.jsp" class="list-group-item">舱外实况</a>
					  <a href="#" class="list-group-item">使用记录</a>
					  <a href="#" class="list-group-item">我的红包</a>
					</div>
							<!-- 选择列表结束 -->
							<br>
							<br>
							<br>
							<br>
							<br>
							<!-- 连接按钮开始 -->
			 		<button class="btn btn-large btn-success btn-block" id="ConnEquip_Button" type="button">开启设备</button>
			 				<!-- 连接按钮结束 -->
					</div>
							<br>
							<br>
							<br>
							<br>
							<br>							
							<br>
							<br>
							<br>
							<br>
							<br>
							<br>
							<br>							
							<br>
							<br>
							<br>
							<br>
				</div>
			</div>
          </div>
        </div>
        <!-- /page content -->
        
       
        <!-- 进度条模拟框开始 -->
<!-- 		<div class="modal fade" tabindex="-1" role="dialog" id="progressbar_Modal" aria-labelledby="gridSystemModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="gridSystemModalLabel">设备连接中</h4>
		      </div>
		      进度条
			<div class="progress">
			  <div class="progress-bar" role="progressbar" id="barFill" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;">
			  </div>
			</div>
		      
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		        <button type="button" class="btn btn-primary" disabled="disabled" id="successConn_Button">连接成功</button>
		      </div>
		    </div>
		  </div>
		</div> -->
			<!-- 进度条模拟框开始结束 -->
	 		
			
		<!-- 内容部分结束 -->

      </div>
    </div>
    
 <script>
    //开门
    $("#ConnEquip_Button").click(function () {
    	var code00 ="00";
    	var code01 ="01";
    	var code02 ="02";
    	var data = "open";
        //发送Ajax请求
        $.ajax({
            url:"/Hotel_Server/netty/sendMsg",
            type:"POST",
            data: "function="+data,
            success:function (result) {
                if(result.msg_code == code01){
                	alert("开锁成功");
                	self.location='time.html'; 
                }else {
                		alert("设备不可用！");
                		self.location='userLogin.jsp'; 
                	}
                }
            });
    });

	$(function() {
		to_page(1)
	});
	function to_page(pn) {
		$.ajax({
			url : "/Hotel_Server/user/getuserinfo",
			data : "",
			type : "GET",
			success : function(result) {
 	        	$("#userinfo1").html("<h2>"+result.userName+"</h2>");
 	        	$("#userinfo2").html("<h3 class='text-center'>"+"Hi,"+result.userName+"</h3>");
 	        	$("#userinfo3").html("<img src='"+result.userImage_URL+"' alt=''>");
 	        	$("#userinfo4").html("<img src='"+result.userImage_URL+"' alt='' class='img-circle profile_img'>");
			}
		});
	}    
   
	//注销时间
 	function outlogin(){
 	    //发送Ajax请求
 	    $.ajax({
 	        url:"/Hotel_Server/login/outlogin",
 	        type:"POST",
 	        data: "",
 	        success:function (result) {
 	        	$("#humidity").html("<span>"+result.humidity+"</span>");
 	        	$("#co_concentration").html("<span>"+result.co_concentration+"</span>");
 	        	$("#fog_concentration").html("<span>"+result.fog_concentration+"</span>");
 	        	$("#action_count").html("<span>"+result.action_count+"</span>");
 	    }
 	});
    }
	</script> 

    <!-- Bootstrap -->
   <!--  <script type="text/javascript" src="./static/js/Home.js"></script>	 -->
    <!-- <script src="../vendors/bootstrap/dist/js/bootstrap.min.js"></script> -->
	<script type="text/javascript" src="./static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <!-- Custom Theme Scripts -->
  	<script type="text/javascript" src="./static/home/custom.min.js"></script>
  </body> 
</html>


