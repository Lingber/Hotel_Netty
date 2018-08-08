		/*在JS文件里获取项目的根目录开始*/
		var pathName = window.document.location.pathname;
		var APP_PATH=pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
		/*在JS文件里获取项目的根目录结束*/
		
		/********为设备连接按钮增加事件关联*****************/
		$("#ConnEquip_Button").click(function(){
			//弹出模态框
		　　　$("#progressbar_Modal").modal({ //利用js创建模态框
			backdrop : "static"
			});
				//加载进度条
			      run();
			    //连接设备
		　});
		
		/************进度条*********/
		function run(){
			var fill=document.getElementById('barFill');
			fill.style.width=parseInt(fill.style.width) + 1 + "%";
			if(fill.style.width == "100%"){  
				var button=document.getElementById('successConn_Button');
					button.disabled=false;
					return;
			    } 
			    var timeout=window.setTimeout("run()",20); 
		}

		/*********设备控制页面按钮*******/
		$("#successConn_Button").click(function(){
			$("#progressbar_Modal").modal("hide");
/*			var nowtime=getNowFormatDate();
			alert("即将执行页面跳转操作,当前时间："+nowtime);*/
			/*self.location='EuipContrl.jsp'; */
		　});
		
		/*********获取当前时间*********/
		function getNowFormatDate() {
		    var date = new Date();
		    var seperator1 = "-";
		    var seperator2 = ":";
		    var month = date.getMonth() + 1;
		    var strDate = date.getDate();
		    if (month >= 1 && month <= 9) {
		        month = "0" + month;
		    }
		    if (strDate >= 0 && strDate <= 9) {
		        strDate = "0" + strDate;
		    }
		    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
		            + " " + date.getHours() + seperator2 + date.getMinutes()
		            + seperator2 + date.getSeconds();
		    return currentdate;
		}