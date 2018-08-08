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
    <link href="./static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="./static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
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
              <div class="profile_pic">
                <img src="./static/media/images/img.jpg" alt="..." class="img-circle profile_img">
              </div>
              <div class="profile_info">
                <span>欢迎您,</span>
                <h2>Lingber</h2>
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
                    <img src="./static/media/images/img.jpg" alt="">Lingber
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
                    <li><a href="login.html"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
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
						<h3 class="text-center">
							上传身份证件照片
						</h3>
							<!-- 大标题结束 -->
							<br>
							<br>
							<br>
							<br>
							<!-- 选择列表开始 -->
							<div align="center">
							<form action="#" method="post" enctype="multipart/form-data">
							       <input class="btn btn-xlarge btn-clean-one" id="pictureFile" type="file" onchange="setSpanCon()" name="pictureFile" style="opacity: 0">
							       <span class="btn btn-primary" id="showFilename" onclick="setFilename()">请选择身份证件正面照片</span>
							       <script type="text/javascript">
							           function setFilename(){
							               var pictureFile = document.getElementById("pictureFile");
							               pictureFile.click();
							           }
							           function setSpanCon(){
							               var pictureFile = document.getElementById("pictureFile");
							               var FullFilename = pictureFile.value;
							               var aFilename = FullFilename.split('\\');
							               var filename = aFilename[aFilename.length-1];
							               var spanFilename = document.getElementById("showFilename");
							               spanFilename.innerHTML = filename;
							           }
							       </script>
							       <br>
							       <br>
							       <br>
							       <br>
							       <br>
							       <!-- <input class="btn btn-xlarge btn-clean-one" type="submit" id="emp_save_btn" value="提交"/> -->
							        <input class="btn btn-xlarge btn-clean-one" id="emp_save_btn" value="提交"/>
							</form>
							</div>
							<!-- 选择列表结束 -->
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
<!--  关门提示模拟框-->
<div class="modal fade" tabindex="-1" role="dialog" id="uploadModal">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" style="color:#000000 ">提交结果：</h4>
      </div>
      <div class="modal-body">
        <h6 style="color:#000000 " align="center">提交完成</h6>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!--  关门提示模拟框-->
		<!-- 内容部分结束 -->
      </div>
    </div>
    
 <script>
 //提交
    $("#emp_save_btn").click(function () {
          $("#uploadModal").modal({
              backdrop:"static"
          });
    });
		
	</script> 

    <!-- Bootstrap -->
<!--     <script type="text/javascript" src="./static/js/Home.js"></script>	 -->
    <!-- <script src="../vendors/bootstrap/dist/js/bootstrap.min.js"></script> -->
<!-- 	<script type="text/javascript" src="./static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script> -->
    <!-- Custom Theme Scripts -->
  	<script type="text/javascript" src="./static/home/custom.min.js"></script>
  </body> 
</html>


