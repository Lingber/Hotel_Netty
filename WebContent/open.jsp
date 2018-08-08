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

	<%--搭建显示页面，--%>
	<div class="container">
		<%--标题--%>
		<div class="row">
			<div class="col-md-12">
				<h1>房间开门记录</h1>
			</div>
		</div>
		<%--按钮--%>
		<div class="row">
		
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
							<th>房间号</th>
							<th>手机号</th>
							<th>开门时间</th>
							<th></th>
						</tr>
				  	<colgroup>
				  		<col width='5%'></col> <!--	checkbox -->
				  		<col width='30%'></col><!-- 房间号 -->
					    <col width='30%'></col><!-- 手机号 -->
					    <col width='30%'></col><!-- 开房时间 -->
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
			url : "/Hotel_Server/open/getall",
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
							var roomnumberTD = $("<td></td>").append(item.roomnumber);
							var phone_NumbTD = $("<td></td>").append(item.phone_Numb);
							var openDoorTimeTD = $("<td></td>").append(item.openDoorTime);
							
							$("<tr></tr>").append(checkboxTd).append(
									roomnumberTD).append(phone_NumbTD).append(openDoorTimeTD).appendTo(
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
			url : "/Hotel_Server/open/get_by_key",
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
	
	</script>

</body>
</html>