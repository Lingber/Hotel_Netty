// require('../css/main.css');

$(function() {
	var $start = $('.start');
	var $count = $('.count');
	var $stop = $('.stop');
	var $reset = $('.reset');
	var $continue = $('.continue');
	var $min = $('#minute');
	var $snd = $('#second');
	var $ms = $('#ms');
	var $record = $('.all_record');
	var timer;

	function PubSub() {
		this.handlers = {};
	}

	PubSub.prototype = {
		// 订阅事件
		on: function(eventType, handler) {
			var self = this;
			if (!(eventType in self.handlers)) {
				self.handlers[eventType] = [];
			}
			self.handlers[eventType].push(handler);
			return this;
		},
		// 触发事件(发布事件)
		emit: function(eventType) {
			var self = this;
			var handlerArgs = Array.prototype.slice.call(arguments, 1);
			for (var i = 0; i < self.handlers[eventType].length; i++) {
				self.handlers[eventType][i].apply(self, handlerArgs);
			}
			return self;
		}
	};

	// 订阅消息
	var pubsub = new PubSub();

	pubsub.on('clock', function(data) {
		$ms.html(data);
		if (data >= 10) {
			$ms.html(0);
			$snd.html(doubleShow(parseInt($snd.html(), 10) + 1));
		}
		if (parseInt($snd.html(), 10) > 59 && data == 10) {
			$snd.html(doubleShow(0));
			$min.html(doubleShow(parseInt($min.html(), 10) + 1));
		}
	});

//页面加载后触犯计时
// 	$(window).on("load", function () {
	
// 		startCount();
// })	

$( document ).ready(function() {

	startCount();
});	


	// 发布消息	
	// 控制计时开始
	// $start.click(function() {
	// 	$(this).parent('.oneBtn').css('display', 'none');
	// 	$(this).parent('.oneBtn').siblings('.twoBtn:first').css('display', 'block');
	// 	startCount();
	// });

	// 控制计次
	var $count = $('button.count');
	console.log($count);
	var delTime, thisTime, startTime = 0,
		lastTime = 0,
		n = 0,
		m, s, ms, m1, s1, ms1;
	$count.click(function() {
		n++;
		thisTime = parseInt($('#minute').html()) * 600 + parseInt($('#second').html()) * 10 + parseInt($('#ms').html());
		if (lastTime === 0) {
			delTime = thisTime - startTime;
			lastTime = delTime;
		} else {
			delTime = thisTime - lastTime;
			lastTime = thisTime;
		}
		m = parseInt(thisTime / 600);
		s = parseInt(thisTime % 600 / 10);
		ms = parseInt(thisTime % 600 % 10);

		m1 = parseInt(delTime / 600);
		s1 = parseInt(delTime % 600 / 10);
		ms1 = parseInt(delTime % 600 % 10);

		console.log(delTime);
		addRecord(n, m, s, ms, m1, s1, ms1);
	});

	// 暂停
	$stop.click(function(event) {
		stopCount();
		$(this).parent('.twoBtn').css('display', 'none');
		$(this).parent('.twoBtn').siblings('.twoBtn:last').css('display', 'block');
	});

	// 继续
	$continue.click(function(event) {
		startCount();
		$(this).parent('.twoBtn').css('display', 'none');
		$(this).parent('.twoBtn').siblings('.twoBtn:first').css('display', 'block');
	});

	// 重置
	$reset.click(function(event) {
		document.location.reload();
	});

	// 开始计时函数
	function startCount() {
		clearInterval(timer);

		$(".maskleft").css("animation-play-state", "running");
		$(".maskright").css("animation-play-state", "running");

		timer = setInterval(function() {
			var msNum = parseInt($ms.html(), 10);
			pubsub.emit('clock', Count(msNum));
		}, 100);
	}
	// 暂停计时
	function stopCount() {
		clearInterval(timer);

		$(".maskleft").css("animation-play-state", "paused");
		$(".maskright").css("animation-play-state", "paused");

	}

	// 添加计次
	function addRecord(n, x, y, z, x1, y1, z1) {
		if (n === 0) {
			$record.html('');
		} else {
			var item = '';
			item += '<div class="record"><div class="Nth">第' + n + '次：</div><div class="time"><span>' + doubleShow(x) + '</span>分<span>' + doubleShow(y) + '</span>秒<span>' + z + '</span></div><div class="deltime"><span>+' + doubleShow(x1) + '</span>:<span>' + doubleShow(y1) + '</span>.<span>' + z1 + '</span></div></div>'
			$record.append(item);
		}
	}

	// 两位数显示
	function doubleShow(value) {
		if (value < 10) {
			value = '0' + value;
		}
		return value;
	}

	// 计数函数
	function Count(num) {
		num++;
		return num;
	}
})