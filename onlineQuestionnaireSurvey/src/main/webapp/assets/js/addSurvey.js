var qesInformation = [];
const PLACEHOLDER_STRING = '请输入选项, 新增请点加';
const CLASS_NAME = 'anw-input';

function $(id) {
  return document.getElementById(id);
}

function render() {
  var questionList = $('question-list');
  var divWarper = document.createElement('div');
  var curQuestion = qesInformation[qesInformation.length - 1];
  var stemH3 = document.createElement('h3');
  var fragment = document.createDocumentFragment('div');

  // 设置题
  stemH3.innerHTML = qesInformation.length + '. ' + curQuestion.stem;

  // 设置选项
  for (let key in curQuestion.options) {
    let p = document.createElement('p');  
    p.innerHTML = key + '. ' + curQuestion.options[key];
    fragment.appendChild(p);
  }

  divWarper.className = 'question';
  divWarper.appendChild(stemH3);
  divWarper.appendChild(fragment);

  questionList.appendChild(divWarper);
}

function createMask() {
  var mask = document.createElement('div');
  var scrollHeight = document.documentElement.scrollHeight || document.body.scrollHeight;
  var clientWidth = document.documentElement.offsetWidth || document.body.offsetWidth;

  mask.style.width = clientWidth + 'px';
  mask.style.height = scrollHeight + 'px';
  mask.className = 'mask';

  showQuestionBox();
  document.body.appendChild(mask);
}

function showQuestionBox() {
  var questionBox = $('add-box');
  questionBox.style.display = 'block';
}

function hideQuestionBox() {
  var questionBox = $('add-box');
  questionBox.style.display = 'none';
}

function handleCancel() {
  // 1. 清空所有数据
  var questionH = $('ques-h');
  var inputList = $('input-list');
  var message = $('message');

  while (inputList.childElementCount > 1) {
    inputList.removeChild(inputList.lastElementChild);
  }

  inputList.firstElementChild.value = '';
  questionH.value = '';

  // 2. 隐藏
  hideQuestionBox();
  message.style.display = 'none';
  
  // 3. 删除层
  var body = $('body');
  body.removeChild(body.lastElementChild);
}

function handleOk() {

  // 1. 验证所有数据不能为空
  var questionH = $('ques-h');
  var inputList = $('input-list');
  var message = $('message');
  var length = inputList.childElementCount;
  var index = 0;
  var nowChild = inputList.firstElementChild;
  var letters = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'];
  
  if (questionH.value.trim().length < 1) {
    message.style.display = 'block';
    return;
  }

  while (index < length) {
    if (nowChild.value.trim().length < 1) {
      message.style.display = 'block';
      return;
    }
    index++;
    nowChild = nowChild.nextElementSibling;
  }

  index = 0;
  nowChild = inputList.firstElementChild;

  // 将一道题保存起来
  var question = {};         
  var questionOptions = {};

  while (index < length) {
    questionOptions[letters[index]] = nowChild.value.trim();  
    nowChild = nowChild.nextElementSibling;
    index++;
  }
  question.stem = questionH.value.trim();
  question.options = questionOptions;
  qesInformation.push(question);

  handleCancel();
  render();
}

function handleAdd() {
  var input = document.createElement('input');
  var inputList = $('input-list');

  input.placeholder = PLACEHOLDER_STRING;
  input.className = CLASS_NAME;

  inputList.appendChild(input);
}

function handleMinus() {
  var inputList = $('input-list');
  if (inputList.childElementCount > 1) {
    inputList.removeChild(inputList.lastElementChild);
  }
}

function handleSubmit() {
  var qesTitle = $('an-name');
  var qesProfix = $('an-des'); 
  var startTime = $('an-begin-date');
  var endTime = $('an-end-date');

  var isPublic = $('isPublic'); // 拿到select对象
  var index = isPublic.selectedIndex; // 拿到下标
  var value = isPublic.options[index].value; // 拿到value

  var message = $('reg-mess');
  var startTimeToSting;
  var endTimeToString;

  message.innerHTML = '不能为空!';

  if (qesTitle.value.trim().length < 1) {
    message.style.display = 'block';
    return;
  }

  if (qesProfix.value.trim().length < 1) {
    message.style.display = 'block';
    return;
  }

  if (startTime.value.trim().length < 1) {
    message.style.display = 'block';
    return;
  }

  if (endTime.value.trim().length < 1) {
    message.style.display = 'block';
    return;
  }

  // 验证时间
  startTimeToSting = startTime.value.replace(/(\d{4})-(\d{2})-(\d{2})/, '$1$2$3'); 
  endTimeToSting = endTime.value.replace(/(\d{4})-(\d{2})-(\d{2})/, '$1$2$3'); 

  if (parseInt(startTimeToSting) > parseInt(endTimeToSting)) {
    message.innerHTML = '结束时间必须大于开始时间!';
    message.style.display = 'block';
    return;
  }

  if (qesInformation.length < 1) {
    message.innerHTML = '请添加题目!';
    message.style.display = 'block';
    return;
  }

  sendAjax('../put/questionnaire/private', {
    method: 'post',
    callback: function(data) {
      data = toJson(data);
      if (data.success) {
        window.location.href = data.data.url;
      } else {
        message.innerHTML = data.error;
        message.style.display = 'block';
        return;
      }
    },
    data: {
      qesTitle: qesTitle.value.trim(),
      isPublic: value,
      startTime: startTime.value,
      endTime: endTime.value,
      qesInformation: qesInformation,
      qesProfix: qesProfix.value.trim(),
    }
  });

}

function handleFocus(event) {
  var target = event.target;
  var message = $('message');
  var regMessage = $('reg-mess');

  if (target.nodeName.toUpperCase() === 'INPUT') {
    message.style.display = 'none';
    regMessage.style.display = 'none';
  }
}
  

/*
 * DOM对象
 *
 */
var addQuestionBtn = $('add-question');
var okBtn = $('ok-btn');
var cancelBtn = $('cancel-btn');
var addBtn = $('add-btn');
var minusBtn = $('minus-btn');
var submitSurveyBtn = $('submit-survey');
var body = $('body');

/*
 * 事件绑定 
 *
 */
function init() {
  addQuestionBtn.addEventListener('click', createMask);
  okBtn.addEventListener('click', handleOk);
  cancelBtn.addEventListener('click', handleCancel);
  addBtn.addEventListener('click', handleAdd);
  minusBtn.addEventListener('click', handleMinus);
  submitSurveyBtn.addEventListener('click', handleSubmit);
  body.addEventListener('click', handleFocus); 
}

init();
