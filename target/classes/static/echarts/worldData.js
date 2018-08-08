var totalRecord, currentPage;
	//页面加载完成后，直接去发送一个Ajax请求，要到分页数据
	$(function() {
		to_page(1)
	});
	function to_page(pn) {
		$.ajax({
			url : "/Rec_Job/word",
			data : "pn=" + pn,
			type : "GET",
			success : function(result) {
				console.log(result);
				//1.解析并显示员工数据
				build_rough_table(result);
			}
		});
	}


var createRandomItemStyle1 = function (params) {
    var colors = ['#fda67e', '#81cacc', '#cca8ba', "#88cc81", "#82a0c5", '#fddb7e', '#735ba1', '#bda29a', '#6e7074', '#546570', '#c4ccd3'];
    return colors[parseInt(Math.random() * 10)];
}

var createRandomItemStyle2 = function () {
    var colorArr = ['#fda67e', '#81cacc', '#cca8ba', "#88cc81", "#82a0c5", '#fddb7e', '#735ba1', '#bda29a', '#6e7074', '#546570', '#c4ccd3'];
    var flag = parseInt(Math.random() * 10);
    return {
        normal: {
            fontFamily: '微软雅黑',
            color:colorArr[flag]
        }
    };
}
function _setWordCloud (cloudData) {
    var option = {
        series: [
            {
                type: 'wordCloud',
                shape: 'ellipse',
                gridSize: 8,
                textStyle: {
                    normal: {
                        fontFamily: '微软雅黑',
                        color: function () {
                            var colors = ['#fda67e', '#81cacc', '#cca8ba', "#88cc81", "#82a0c5", '#fddb7e', '#735ba1', '#bda29a', '#6e7074', '#546570', '#c4ccd3'];
                            return colors[parseInt(Math.random() * 10)];
                        }
                    }
                },

                /*注释一：*/
                // textStyle:createRandomItemStyle2(),


                /*注释二：*/
                /*textStyle: {
                normal: {
                        fontFamily: '微软雅黑',
                        color: createRandomItemStyle1()
                    }
                },*/
                data: cloudData
            }
        ]
    };
    return option;
};

//词云图初始化
function initWordCloud(wordCloudData) {
    var option = _setWordCloud(wordCloudData.cloudData);
    var myChart = echarts.init(document.getElementById(wordCloudData.cloudDiv));
    myChart.setOption(option);
}

function build_rough_table(result) {
	var rough = result;
	for(var o in rough){
			rough[o]["name"] = rough[o]["dataWords"];
			rough[o]["value"] = rough[o]["dataFrequency"];
		   delete rough[o]["dataWords"];
		   delete rough[o]["dataFrequency"];
		   delete rough[o]["dataId"];
		}
	var dataCloud = {
		"cloudData":rough,
		"cloudDiv":"worldDiv"
	 };
	initWordCloud(dataCloud);
};


