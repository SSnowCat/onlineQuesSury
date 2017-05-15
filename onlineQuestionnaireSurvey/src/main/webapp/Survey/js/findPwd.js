/*
 * @author: 余正峰;
 * @date: startTime:2017-05-06; 
 *
 */

/*
 * DOM对象
 * 
 */

var findPwdMess = $('findpwd-mess');
var username = $('username');
var phone = $('phone');
var pwd = $('pwd');
var confirmPwd = $('confirm-pwd');
var sig = $('sig');
var sigImg = $('sig-img');
var findPwdBtn = $('findpwd-btn');


/*
 * 表单验证
 *
 */
function checkForm() {

	var inputArr = [username, phone, pwd, confirmPwd, sig];
	var isEmpty = inputArr.every(function(item, index, array) {
		if(item.value.trim().length != 0) {
			return true;
		}
	});

	if(!isEmpty) {
		findPwdMess.innerHTML = NOT_EMPTY;
		findPwdMess.style.display = 'block';
		return;
	}

	if(pwd.value.trim() != confirmPwd.value.trim()) {
		findPwdMess.innerHTML = NOT_SAME;
		findPwdMess.style.display = 'block';
		return;
	}

	/*
	 * 当javascript验证数据通过后，后端验证数据
	 *
	 */
	sendAjax('../get/user', {
		method: 'post',
		callback: function(data) {
			data = toJson(data);
			if (data.success) {
				window.location.href = data.data.url;
			} else {
				findPwdMess.innerHTML = data.error;
				findPwdMess.style.display = 'block';
				changeSig(sigImg);
				return;
			} 
		},
		data: {
			username: username.value.trim(),
			userPhone: phone.value.trim(),
			password: pwd.value.trim(),
			rePassword: confirmPwd.value.trim(),
			identify: sig.value.trim(),
		}
	});
} 

/*
 * 隐藏错误提示
 *
 */
function hideErrorMsg() {
	regMess.style.display = 'none';
}

function hideErrMsg() {
	findPwdMess.style.display = 'none';
}

/*
 * 初始化，添加监听事件
 *
 */
function init() {
	changeSig(sigImg);
	username.addEventListener('focus', hideErrMsg);
	phone.addEventListener('focus', hideErrMsg);
	pwd.addEventListener('focus', hideErrMsg);
	confirmPwd.addEventListener('focus', hideErrMsg);
	sig.addEventListener('focus', hideErrMsg);

	findPwdBtn.addEventListener('click', checkForm);
	sigImg.addEventListener('click', function() {
		changeSig(sigImg);  
	});
}

init();
