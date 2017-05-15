/*
 * @author: 余正峰;
 * @date: startTime:2017-05-06; 
 *
 */



/*
 * DOM对象
 * 
 */
var A = $('A');
var B = $('B');
var goPhoneBtn = $('go-phone');
var goPwdBtn = $('go-pwd');

var newPwdA = $('new-pwdA');
var newConfirmPwdA = $('new-confirm-pwdA');
var sigA = $('sigA');
var sigImgA = $('sig-imgA');
var ensureA = $('ensureA');
var messA = $('reg-messA');

var phoneB = $('phoneB');
var sigB = $('sigB');
var sigImgB = $('sig-imgB');
var ensureB = $('ensureB');
var messB = $('reg-messB');

/*
 * 修改密码验证
 *
 */
function changePwdForm() {

  var inputArr = [newPwdA, newConfirmPwdA, sigA];
  var isEmpty = inputArr.every(function(item, index, array) {
    if(array[index].value.trim().length != 0) {
      return true;
    }
  });

  if(!isEmpty) {
    messA.innerHTML = NOT_EMPTY;
    messA.style.display = 'block';
    return;
  }

  if(newPwdA.value.trim() != newConfirmPwdA.value.trim()) {
    messA.innerHTML = NOT_SAME;
    messA.style.display = 'block';
    return;
  }

  sendAjax('../patch/user/private', {
    method: 'post',
    callback: function(data) {
      data = toJson(data);
      if (data.success) {
        window.location.href = data.data.url;
      } else{
    	changeSig(sigImgA);
        messA.innerHTML = data.error;
        messA.style.display = 'block';
        return;
      }
    },
    data: {
      newPassword: newPwdA.value.trim(),
      reNewPassword: newConfirmPwdA.value.trim(),
      identify: sigA.value.trim(),
    },
  });
} 


/*
 * 修改手机号验证
 *
 * 如果有错误，就返回一个字符串。
 * 如果没有任何错，不返回任何一个数据。
 */
function changePhoneForm() {

  var inputArr = [phoneB, sigB];
  var isEmpty = inputArr.every(function(item, index, array) {
    if(array[index].value.trim().length != 0) {
      return true;
    }
  });

  if(!isEmpty) {
    messB.innerHTML = NOT_EMPTY;
    messB.style.display = 'block';
    return;
  }

  // 直接后端验证, 前端不用验证
  sendAjax('../phone/patch/user/private', {
    method: 'post',
    callback: function(data) {
      if (data.success) {
        window.location.href = data.data.url;
      } else {
    	changeSig(sigImgB);
        messB.innerHTML = data;
        messB.style.display = 'block';
        return;
      }
    },
    data: {
      userPhone: phoneB.value.trim(),
      identify: sigB.value.trim(),
    },
  });
}

function hideErrMsg() {
  messA.style.display = 'none';
  messB.style.display = 'none';
}

/*
 * 初始化，添加监听事件
 *
 */
function init() {
  B.style.display = 'none';
  changeSig(sigImgA);
  changeSig(sigImgB);

  newPwdA.addEventListener('focus', hideErrMsg);
  newConfirmPwdA.addEventListener('focus', hideErrMsg);
  sigA.addEventListener('focus', hideErrMsg);

  phoneB.addEventListener('focus', hideErrMsg);
  sigB.addEventListener('focus', hideErrMsg);

  goPhoneBtn.addEventListener('click', function() {
    A.style.display = 'none';
    B.style.display = 'block';
  });

  goPwdBtn.addEventListener('click', function() {
    B.style.display = 'none';
    A.style.display = 'block';
  });

  ensureA.addEventListener('click', function() {
    changePwdForm();
  });

  ensureB.addEventListener('click', function() {
    changePhoneForm();
  });

  sigImgA.addEventListener('click', function() {
	changeSig(sigImgA);  
  });
  
  sigImgB.addEventListener('click', function() {
	changeSig(sigImgB);  
  });
}

init();
