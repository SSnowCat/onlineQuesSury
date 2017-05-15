function $(id) {
	return document.getElementById(id);
}

function createXMLHttpRequest() {
	var request;
	try {
		request = new XMLHttpRequest(); /*用以模拟的HTTP GET和POST*/
	} catch(tryMS) {
		// IE6及以下
		try {
			request = new ActiveXObject("Msxml2.XMLHTTP"); /*高版本支持*/
		} catch(otherMS) {
			try {
				request = new ActiveXObject("Microsoft.XMLHTTP"); /*低版本 msxml2.6以下使用*/
			} catch(failed) {
				request = null;
			}
		} finally {
			if(request == null) {
				alert("Error initializing XMLHttpRequest");
			}
		}	
	}
	return request;	
}

function sendAjax(url, opts) {
	var xhr = createXMLHttpRequest();	
	var data;
	opts.data = JSON.stringify(opts.data);
	opts.data = JSON.stringify(opts.data);
	if(opts.method.toLowerCase() == 'get') {
		xhr.open(opts.method, url);
		xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhr.send(null);
	} 

	if(opts.method.toLowerCase() == 'post') {
		
		xhr.open(opts.method, url, true);
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.send(opts.data);
	}

	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4) {
			if( xhr.status == 200) {
				data = xhr.responseText;
				opts.callback(data);
			}
		} else {
		}
	};
}

function toJson(str) {
	return JSON.parse(str);
}

/*
 * 验证码点击切换
 *
 */
function changeSig(node) {
  var date = new Date();
  node.src = "../code/" + date.getTime();
}

var Validation = function() {
};

/*
 * 验证用户名
 *
 * 规则: 只能是数字，字母，下划线，且长度要大于3
 */
Validation.prototype.checkUsername = function(val) {
  var pattern = new RegExp('\\w{3,}');
  return pattern.test(val);
};

/*
 * 验证密码
 *
 * 规则: 只能是数字，字母，下划线，且长度在6～15中间
 */
Validation.prototype.checkPwd = function(val) {
  var pattern = new RegExp('\\w{6,15}');
  return pattern.test(val);
};

/*
 * 验证密码与确认密码
 * 
 */
Validation.prototype.checkSamePwd = function(val1, val2) {
  var isRight = val1 == val2 ? true : false;
  return isRight;
};

Validation.prototype.checkPhone = function(val) {
	var reg = new RegExp("1[3|4|5|6|7|8|9][0-9]{9}$");
  return reg.test(val);
};

// 验证邮箱格式
function validateEmail(email) {
	var reg = new RegExp("^(\\w-*\\.*)+@(\\w-?)+(\\.\\w{2,})+$");
	return reg.test(email);
}


// 验证中文
function validateChinese(words) {
	var reg = new RegExp("^\s*[\u4e00-\u9fa5]{1,10}\s*$");
	if(reg.test(words)) {
		return true;
	} else {
		return false;
	}
}
