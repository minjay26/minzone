$(document).ready(function() {

	var header = $("meta[name='_csrf_header']").attr("content");
	var token = $("meta[name='_csrf']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		try {
			xhr.setRequestHeader(header, token);
		} catch (err) {
			alert(err);
		}
	});
	// 获取JS传递的语言参数
	var utils = new Utils();
	var args = utils.getScriptArgs();

	// 隐藏Loading/注册失败 DIV
	$(".loading").hide();
	$(".login-error").hide();
	registError = $("<label class='error repeated'></label>");

	// 输入框激活焦点、移除焦点
	jQuery.focusblur = function(focusid) {
		var focusblurid = $(focusid);
		var defval = focusblurid.val();
		focusblurid.focus(function() {
			var thisval = $(this).val();
			if (thisval == defval) {
				$(this).val("");
			}
		});
		focusblurid.blur(function() {
			var thisval = $(this).val();
			if (thisval == "") {
				$(this).val(defval);
			}
		});

	};
	/* 下面是调用方法 */
	$.focusblur("#email");

	// 获取表单验证对象[填写验证规则]
	var validate = $("#signupForm").validate({
		focusCleanup : true,
		rules : {
			email : {
				required : true,
				email : true
			},
			password : {
				required : true,
				minlength : 6,
				maxlength : 16
			},
			passwordAgain : {
				required : true,
				equalTo : "#password"
			},
			username : {
				required : true
			},
			trueName : {
				required : true
			},
			gender : {
				required : true,
			},

			phone : {
				digits : true,
				rangelength : [ 11, 11 ]
			},
			age : {
				digits : true,
				range : [ 1, 100 ],
			}

		},
		messages : {
			email : {
				required : "邮箱不能为空",
				email : "邮箱格式不正确"
			},
			password : {
				required : "邮箱不能为空",
				minlength : "密码长度不能低于6位",
				maxlength : "密码最长为16位"
			},
			passwordAgain : {
				required : "确认密码不能为空",
				equalTo : "两次输入密码不一致"
			},
			username : {
				required : "用户名不能为空"
			},
			trueName : {
				required : "真实姓名必填"
			},
			gender : {
				required : "请选择性别",
			},
			phone : {
				required : "请填入手机号",
			},
			phone : {
				digits : "请输入合法的手机号",
				rangelength : "手机位数不正确"
			},
			age : {
				digits : "请输入正确的年龄",
				range : "请输入正确的年龄"
			}

		}
	});

	// 输入框激活焦点、溢出焦点的渐变特效
	if ($("#email").val()) {
		$("#email").prev().fadeOut();
	}
	;
	$("#email").focus(function() {
		$(this).prev().fadeOut();
	});
	$("#email").blur(function() {
		if (!$("#email").val()) {
			$(this).prev().fadeIn();
		}
		;
	});
	if ($("#password").val()) {
		$("#password").prev().fadeOut();
	}
	;
	$("#password").focus(function() {
		$(this).prev().fadeOut();
	});
	$("#password").blur(function() {
		if (!$("#password").val()) {
			$(this).prev().fadeIn();
		}
		;
	});
	if ($("#passwordAgain").val()) {
		$("#passwordAgain").prev().fadeOut();
	}
	;
	$("#passwordAgain").focus(function() {
		$(this).prev().fadeOut();
	});
	$("#passwordAgain").blur(function() {
		if (!$("#passwordAgain").val()) {
			$(this).prev().fadeIn();
		}
		;
	});
	if ($("#username").val()) {
		$("#username").prev().fadeOut();
	}
	;
	$("#username").focus(function() {
		$(this).prev().fadeOut();
	});
	$("#username").blur(function() {
		if (!$("#username").val()) {
			$(this).prev().fadeIn();
		}
		;
	});
	if ($("#trueName").val()) {
		$("#trueName").prev().fadeOut();
	}
	;
	$("#trueName").focus(function() {
		$(this).prev().fadeOut();
	});
	$("#trueName").blur(function() {
		if (!$("#trueName").val()) {
			$(this).prev().fadeIn();
		}
		;
	});
	if ($("#phone").val()) {
		$("#phone").prev().fadeOut();
	}
	;
	$("#phone").focus(function() {
		$(this).prev().fadeOut();
	});
	$("#phone").blur(function() {
		if (!$("#phone").val()) {
			$(this).prev().fadeIn();
		}
		;
	});
	if ($("#address").val()) {
		$("#address").prev().fadeOut();
	}
	;
	$("#address").focus(function() {
		$(this).prev().fadeOut();
	});
	$("#address").blur(function() {
		if (!$("#address").val()) {
			$(this).prev().fadeIn();
		}
		;
	});

	if ($("#age").val()) {
		$("#age").prev().fadeOut();
	}
	;
	$("#age").focus(function() {
		$(this).prev().fadeOut();
	});
	$("#age").blur(function() {
		if (!$("#age").val()) {
			$(this).prev().fadeIn();
		}
		;
	});

	// ajax提交注册信息
	$("#submit").on("click", function() {
		regist(validate);
	});

	$("body").each(function() {
		$(this).keydown(function() {
			if (event.keyCode == 13) {
				regist(validate);
			}
		});
	});

});

function regist(validate) {
	// 校验Email, password，校验如果失败的话不提交
	if (validate.form()) {
		if (document.getElementById('agree').checked == true) {
			$(".login-error").hide();
			$.ajax({
				url : "/user/register",
				type : "post",
				data : $("#signupForm").serialize(),
				success : function(data) {
					alert(data);
					$('.loading').hide();
					location.href = "/user/register_success"
				},
				error : function(XMLHttpRequest, msg) {
					alert(XMLHttpRequest.Status)
				}
			});
		} else {
			// 勾选隐私政策和服务条款
			$(".login-error").show();
			$(".login-error").html("未选中同意无法注册");
		}
	}
}

var Utils = function() {
};

Utils.prototype.getScriptArgs = function() {// 获取多个参数
	var scripts = document.getElementsByTagName("script"),
	// 因为当前dom加载时后面的script标签还未加载，所以最后一个就是当前的script
	script = scripts[scripts.length - 1], src = script.src, reg = /(?:\?|&)(.*?)=(.*?)(?=&|$)/g, temp, res = {};
	while ((temp = reg.exec(src)) != null)
		res[temp[1]] = decodeURIComponent(temp[2]);
	return res;
};
