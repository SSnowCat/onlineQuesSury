/*
 * @author: 余正峰;
 * @date: startTime:2017-05-06; 
 *
 */

/*
 * DOM对象
 * 
 */
var regBtn = $('reg-btn');
var username = $('username');
var pwd = $('pwd');
var confirmPwd = $('confirm-pwd');
var phone = $('phone');
var sig = $('sig');
var sigImg = $('reg-sig-img');
var regMess = $('reg-mess');

/*
 * 表单验证
 *
 */
function checkForm() {
  var v = new Validation();
  regMess.innerHTML = '';

  if(!v.checkUsername(username.value.trim())) {
    regMess.innerHTML = USERNAME_ERROR;
    regMess.style.display = 'block';
    return; 
  }

  if(!v.checkPwd(pwd.value.trim())) {
    regMess.innerHTML = PWD_ERROR;
    regMess.style.display = 'block';
    return; 
  }

  if(!v.checkSamePwd(pwd.value.trim(), confirmPwd.value.trim())) {
    regMess.innerHTML = SAME_PWD_ERROR;
    regMess.style.display = 'block';
    return; 
  }

  if(!v.checkPhone(phone.value.trim())) {
    regMess.innerHTML = PHONE_ERROR;
    regMess.style.display = 'block';
    return; 
  }

  /*
   * 当javascript验证数据通过后，后端验证数据
   *
   */
  sendAjax('../put/user', {
    method: 'post',
    callback: function(data) {
      data = toJson(data);
      if(data.success) {
          window.location.href = data.data.url;   
      } else {
        regMess.innerHTML = data.error;
        regMess.style.display = 'block';
        changeSig(sigImg);
        return;
      }
    },
    data: {
      username: username.value.trim(),
      password: pwd.value.trim(),
      userPhone: phone.value.trim(),
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

/*
 * 初始化，添加监听事件
 *
 */
function init() {
  changeSig(sigImg); // 初始化加载验证码
  regBtn.addEventListener('click', checkForm);
  username.addEventListener('focus', hideErrorMsg);
  pwd.addEventListener('focus', hideErrorMsg);
  confirmPwd.addEventListener('focus', hideErrorMsg);
  phone.addEventListener('focus', hideErrorMsg);
  sig.addEventListener('focus', hideErrorMsg);
  sigImg.addEventListener('click', function() {
	changeSig(sigImg);  
  });
}

init();
