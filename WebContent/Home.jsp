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
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="./static/media/images/favicon.ico" type="image/ico" />
<title>用户信息管理</title>
	<script type="text/javascript" src="./static/js/jquery-3.0.0.min.js"></script>
	<link href="./static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="./static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<link href="${APP_PATH }/static/css/main.css" rel="stylesheet">
	<link href="./time/bootstrap-datetimepicker.min.css" rel="stylesheet"/>
    <script src="./time/moment-with-locales.js"></script>
    <script src="./time/bootstrap-datetimepicker.min.js"></script>
    <script src="./time/bootstrap-datetimepicker.zh-CN.js"></script>
    <!-- Font Awesome -->
    <!-- 侧面导航栏的图标、按键 -->
    <!-- <link href="./static/home/font-awesome.min.css" rel="stylesheet"> -->
        <link href="./static/home/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- Custom Theme Style -->
    <!-- 颜色之类的在里面 -->
        <link href="./static/home/custom.min.css" rel="stylesheet">
</head>
<body>
  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
              <a href="index.html" class="site_title"><i class="fa fa-paw"></i> <span>后台管理!</span></a>
            </div>

            <div class="clearfix"></div>

            <!-- 顶头头像图标menu profile quick info -->
            <div class="profile clearfix">
              <div class="profile_pic">
                <img src="./static/media/images/img.jpg" alt="..." class="img-circle profile_img">
              </div>
              <div class="profile_info">
                <span>欢迎您,</span>
              </div>
            </div>
            <!-- /顶头头像图标menu profile quick info -->
            <br/>

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
                    <!-- <img src="./static/media/images/img.jpg" alt="">Lingber -->
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

	<%--搭建显示页面，--%>
	<div class="container">
		<%--标题--%>
		<div class="row">
			<div class="col-md-12">
				<h1>入住用户信息</h1>
			</div>
		</div>
		<%--按钮--%>
		<div class="row">
		
			<div class="col-md-4">
	            <button class="btn btn-primary" id="emp_add_modal_btn">新增</button>
	        </div>
		
			<div class="col-md-4 col-md-offset-8">
				<!-- <button class="btn btn-primary" id="emp_add_modal_btn">查看生成词云</button> -->
				   <div class="row">
					    <div class="input-group">
					      <input type="text" name="phone_Numb_search" class="form-control" placeholder="Search for...">
					      <span class="input-group-btn">
					        <button class="btn btn-default" type="button" onclick="get_job_by_key()">Go!</button>
					      </span>
					    </div><!-- /input-group -->
					</div><!-- /.row -->
			</div>
		</div>
		<%--表格--%>
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" id="rough_table">
					<thead>
						<tr>
							<th><input type="checkbox" id="check_all"></th>
							<th>用户姓名</th>
							<th>身份证号</th>
							<th>手机号</th>
							<th>房间号</th>
							<th>开房时间</th>
							<th>最后退房时间</th>
							<th>操作</th>
							<th></th>
						</tr>
				  	<colgroup>
				  		<col width='5%'></col> <!--	checkbox -->
					    <col width='5%'></col> <!-- 用户ID -->
					    <col width='30%'></col><!-- 身份证号 -->
					    <col width='10%'></col><!-- 手机号 -->
					    <col width='10%'></col><!-- 房间号 -->
					    <col width='15%'></col><!-- 开房时间 -->
					    <col width='15%'></col><!-- 最后退房时间 -->
					    <col width='5%'></col><!-- 申请 -->
					    <col width='5%'></col><!-- 申请 -->
				   </colgroup>
					<thead>
					<tbody>

					</tbody>
				</table>
			</div>
		</div>
		<%--分页信息--%>
		<div class="row">
			<%--分页文字信息--%>
			<div class="col-md-4" id="page_info_area"></div>
			<%--分页条--%>
			<div class="col-md-8" id="page_nav_area"></div>
		</div>
	</div>

				</div>
			</div>
          </div>
        </div>
        <!-- /page content -->
       
		<!-- 内容部分结束 -->

      </div>
    </div>



	<%--员工修改模态框--%>
	<div class="modal fade" id="empUpdataModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title">客户入住信息修改</h4>
	            </div>
	            <div class="modal-body">
	                <form class="form-horizontal">
	
	                   <div class="form-group">
	                        <label for="roomnumber_update_input" class="col-sm-2 control-label">房间号</label>
	                        <div class="col-sm-10">
	                            <input type="text" name="roomnumber_update" class="form-control" id="roomnumber_update_input" placeholder="房间号">
	                            <span class="help-block"></span>
	                        </div>
	                    </div>		

<!-- 	                    <div class="form-group">
	                        <label for="overTime_updata_input" class="col-sm-2 control-label">最后退房时间</label>
	                        <div class="col-sm-10">
	                            <input type="text" name="overTime_updata" class="form-control" id="overTime_updata_input" placeholder="最后退房时间">
	                            <span class="help-block"></span>
	                        </div>
	                    </div> -->
	        
				    <div class="form-group">      
				        <label for="overTime_updata_input" class="col-sm-2 control-label">最后退房时间</label>
			            <div class="col-sm-10">
						        <a class='input-group date' id='datetimepicker1' style="float: left; left: 320px;">
					                <input type='text' name="overTime_updata" class="form-control" id='overTime_updata_input' style="width: 150px; height: 30px;" />
					                <span class="input-group-addon" style="float: left; width: 50px; height: 30px;">
					                    <span class="glyphicon glyphicon-calendar"></span>
					                </span>
					            </a>
			            </div>	        
				     </div>             
				 <!--  <div class="form-group">
	                        <label for="phone_Numb_updata_input" class="col-sm-2 control-label">手机号</label>
	                        <div class="col-sm-10">
	                            <input type="text"  class="form-control" id="phone_Numb_updata_input" placeholder="phone_Numb">
	                            <span class="help-block"></span>
	                        </div>
	                    </div> -->		 
	                </form>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" id="emp_updata_btn">更新</button>
	            </div>
	        </div>
	    </div>
	</div>
	
	<!-- 员工添加的模态框 -->
	<div class="modal fade" id="empAndModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title" id="myModalLabel">入住客户添加</h4>
	            </div>
	            <div class="modal-body">
	                <form class="form-horizontal">
					    <div class="form-group">
	                        <label for="userName_add_input" class="col-sm-2 control-label">用户姓名</label>
	                        <div class="col-sm-10">
	                            <input type="text" name="userName" class="form-control" id="userName_add_input" placeholder="用户姓名">
	                            <span class="help-block"></span>
	                        </div>
	                    </div>							
					    <div class="form-group">
	                        <label for="iDNumber_add_input" class="col-sm-2 control-label">身份证号</label>
	                        <div class="col-sm-10">
	                            <input type="text" name="iDNumber" class="form-control" id="iDNumber_add_input" placeholder="身份证号">
	                            <span class="help-block"></span>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label for="roomnumber_add_input" class="col-sm-2 control-label">房间号</label>
	                        <div class="col-sm-10">
	                            <input type="text" name="roomnumber" class="form-control" id="roomnumber_add_input" placeholder="房间号">
	                            <span class="help-block"></span>
	                        </div>
	                    </div>	                    
					    <div class="form-group">
	                        <label for="startTime_add_input" class="col-sm-2 control-label">开房时间</label>
	                        <div class="col-sm-10">
	                            <input type="text" name="startTime" class="form-control" id="startTime_add_input" placeholder="开房时间">
	                            <span class="help-block"></span>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label for="overTime_add_input" class="col-sm-2 control-label">最后退房时间</label>
	                        <div class="col-sm-10">
	                            <input type="text" name="overTime" class="form-control" id="overTime_add_input" placeholder="最后退房时间">
	                            <span class="help-block"></span>
	                        </div>
	                    </div>
	                    
	                    <div class="form-group">
	                        <label for="phone_Numb_add_input" class="col-sm-2 control-label">手机号</label>
	                        <div class="col-sm-10">
	                            <input type="text" name="phone_Numb" class="form-control" id="phone_Numb_add_input" placeholder="手机号">
	                            <span class="help-block"></span>
	                        </div>
	                    </div>		

	                </form>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" id="emp_save_btn">保存</button>
	            </div>
	        </div>
	    </div>
	</div>
	
	
	<!-- js脚本开始 -->
	<script type="text/javascript">
	 console.log("${APP_PATH}");
	var totalRecord, currentPage;
	//页面加载完成后，直接去发送一个Ajax请求，要到分页数据
	$(function() {
		to_page(1)
	});
	function to_page(pn) {
		$.ajax({
			url : "/Hotel_Server/user/getall",
			data : "pn=" + pn,
			type : "GET",
			success : function(result) {
				console.log(result);
				//1.解析并显示员工数据
				build_rough_table(result);
				//2.解析并显示分页信息
				build_page_info(result);
				//3.解析显示分页条数据
				build_page_nav(result);
			}
		});
	}

	function build_rough_table(result) {
		$("#rough_table tbody").empty();/* 清空之前表内所有数据 */
		var rough = result.extend.pageInfo.list;
		$
				.each(
						rough,
						function(index, item) {
							var checkboxTd = $("<td><input type ='checkbox' class='check_item'/></td>");
							//alert(item.userName);
							var usernameTD = $("<td></td>").append(item.userName);
							var iDNumberTD = $("<td></td>").append(item.iDNumber);
							var phone_NumbTD = $("<td></td>").append(item.phone_Numb);
							var roomnumberTD = $("<td></td>").append(item.roomnumber);
							var startTimeTD = $("<td></td>").append(item.startTime);
							var overTimeTD = $("<td></td>").append(item.overTime);
							/* 数据来源在数据详情显示 */
							
							/* 编辑按钮开始 */
							var editBtn = $("<button></button>")
									.addClass("btn btn-success btn-primary edit_btn")
									.append($("<span></span>")
									.addClass("glyphicon glyphicon-pencil"))
									.append("修改");
							editBtn.attr("edit-id", item.phone_Numb);
							//为编辑按钮添加自定义属性，表示当前id
							var ebtnTd = $("<td></td>").append(editBtn);
							/* 编辑按钮结束 */
							
							/* 删除按钮开始 */
							var delBtn = $("<button></button>")
									.addClass("btn btn-danger btn-primary delete_btn")
									.append($("<span></span>")
									.addClass("glyphicon glyphicon-trash"))
									.append("退房");
							//为删除按钮添加自定义属性，表示当前id
							delBtn.attr("del-id", item.roomnumber);
							var dbtnTd = $("<td></td>").append(delBtn);
							/* 删除按钮结束 */
							
							$("<tr></tr>").append(checkboxTd).append(
									usernameTD).append(iDNumberTD).append(phone_NumbTD).append(roomnumberTD)
									.append(startTimeTD).append(overTimeTD)
									.append(ebtnTd).append(dbtnTd).appendTo(
									"#rough_table tbody");
						});
	}
	//解析显示分页信息
	function build_page_info(result) {
		$("#page_info_area").empty();
		$("#page_info_area").append(
				"当前" + result.extend.pageInfo.pageNum + "页,总"
						+ result.extend.pageInfo.pages + "页,总共"
						+ result.extend.pageInfo.total + "条记录");
		totalRecord = result.extend.pageInfo.total;
		currentPage = result.extend.pageInfo.pageNum;
	}
	
	
	//解析显示分页条
	function build_page_nav(result) {
		$("#page_nav_area").empty();
		var ul = $("<ul></ul>").addClass("pagination");
		var firstPageLi = $("<li></li>").append(
				$("<a></a>").append("首页").attr("href", "#"));
		var prePageLi = $("<li></li>").append(
				$("<a></a>").append("&laquo;"));
		if (result.extend.pageInfo.hasPreviousPage == false) {
			firstPageLi.addClass("disabled");
			prePageLi.addClass("disabled")
		} else {
			firstPageLi.click(function() {
				to_page(1);
			});
			prePageLi.click(function() {
				to_page(result.extend.pageInfo.pageNum - 1);
			});
		}
		var nextPageLi = $("<li></li>").append(
				$("<a></a>").append("&raquo;"));
		var lastPageLi = $("<li></li>").append(
				$("<a></a>").append("末页").attr("href", "#"));
		if (result.extend.pageInfo.hasNextPage == false) {
			nextPageLi.addClass("disabled");
			lastPageLi.addClass("disabled");
		} else {
			nextPageLi.click(function() {
				to_page(result.extend.pageInfo.pageNum + 1);
			});
			lastPageLi.click(function() {
				to_page(result.extend.pageInfo.pages);
			});
		}
		//添加首页和前一页
		ul.append(firstPageLi).append(prePageLi);
		//添加1、2、3遍历ul中添加页码
		$.each(result.extend.pageInfo.navigatepageNums, function(index,
				item) {
			var numLi = $("<li></li>").append($("<a></a>").append(item));
			if (result.extend.pageInfo.pageNum == item) {
				numLi.addClass("active")
			}
			numLi.click(function() {
				to_page(item);
			});
			ul.append(numLi);
		});
		//添加末页和下一页
		ul.append(nextPageLi).append(lastPageLi);
		var navEl = $("<nav></nav>").append(ul);
		$("#page_nav_area").append(navEl);
		//navEl.appendTo("#page_nav_area");
	}
	
	
	function get_job_by_key() {
		var phone_Numb = document.getElementsByName("phone_Numb_search")[0].value;
		$.ajax({
			url : "/Hotel_Server/user/get_by_key",
			data : "phone_Numb=" + phone_Numb,
			type : "GET",
			success : function(result) {
				console.log(result);
				//1.解析并显示员工数据
				build_rough_table(result);
				//2.解析并显示分页信息
				build_page_info(result);
				//3.解析显示分页条数据
				build_page_nav(result);
			}
		});
	}
	
	
	/* 添加用户 */
	
    //弹出模态框
    $("#emp_add_modal_btn").click(function () {
        $("#empAndModal").modal({
            backdrop:"static"
        });
    });

    //保存事件
    $("#emp_save_btn").click(function () {
    	var code05="05";
		var code03="03";
		var code04="04";
    	//发送Ajax请求
        $.ajax({
            url:"/Hotel_Server/socket/waitClient",
            type:"POST",
            data: $("#empAndModal form").serialize(),
            success:function (result) {
                if(result.msg_code == code05){
                	alert("写入成功");
                   //员工保存成功，关闭模态框，显示刚才保存的数据
                    $("#empAndModal").modal("hide");
                    to_page(1);
                }else {
                	if(result.msg_code == code03){
                		alert("没有开启服务器");
                	}else{
                		alert("终端未连接！");
                	}
                }
            }
        });
    });
	
    /* 修改用户信息 */
    
    $(document).on("click", ".edit_btn", function () {

        getEmp($(this).attr("edit-id"));
        //吧员工的id传递给更新按钮
        $("#emp_updata_btn").attr("edit-id",$(this).attr("edit-id"));
        //弹出模态框
        $("#empUpdataModal").modal({
            backdrop:"static"
        });
    });
    function getEmp(id) {
        $.ajax({
            url:"/Hotel_Server/user/client_get_by_key",
            type:"GET",
            data: "phone_Numb="+id,
            success:function (result) {
                var empData = result.result[0];
                $("#roomnumber_update_input").val(empData.roomnumber);
                $("#overTime_updata_input").val(empData.overTime);
            }
        });
    }
    
    
    //点击更新 更新员工
    $("#emp_updata_btn").click(function () {
    	var code20 ="20";
        //发送Ajax请求
        $.ajax({
            url:"/Hotel_Server/user/server_updata",
            type:"GET",
            data:$("#empUpdataModal form").serialize(),
            success:function (result) {
                if(result.msg_code == code20){
                    //员工保存成功，关闭模态框，显示刚才保存的数据
                    $("#empUpdataModal").modal("hide");
                    to_page(1);
                }else {
                			 alert("保存错误!");
                }
            }
        });
    });
    
    
    /* 退房 */
    $(document).on("click", ".delete_btn", function () {
        //1.弹出确认对话框
        //得到房间号
        var roomNumber_over = $(this).attr("del-id");
        //确认发送请求
        if(confirm("确认对【" + roomNumber_over + "】执行退房吗？")){
            $.ajax({
                url:"/Hotel_Server/user/user_overUse_room",
                type:"GET",
                data:"roomNumber="+roomNumber_over,
                success:function (result) {
                    to_page(1);
                }
            });
        }
    });
    
    
/*   //设置日期时间控件
    function Datetime() {
        $('#datetimepicker1').datetimepicker({
            language: 'zh-CN',//显示中文
            format: 'yyyyMMddHHmmss',//显示格式
            minView: "second",//设置只显示到月份
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
    }  */ 
  
	</script>
    <!-- Custom Theme Scripts -->
  	<script type="text/javascript" src="./static/home/custom.min.js"></script>
</body>
</html>