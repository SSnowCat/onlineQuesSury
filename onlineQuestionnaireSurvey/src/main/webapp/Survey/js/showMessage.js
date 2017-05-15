/*
 * 将url改掉即可
 *
 * 请求数据
 *
 */
sendAjax('../'+getArg()[0]+'/chart', {
  method: 'get',
  callback: function(data) {

    data = toJson(data);
    if(data.success){
    	const comAnData = data.data.comAnalysis;
    	const resData = data.data.conAnalysis;
    	
    	comAn(comAnData);
    	resAn(resData);    	
    }else{
    	alert(data.error);
    }
  },
  data: {
  },
});

function getArg(){
	 var q = location.search.substring(1);
	 var qs = q.split('&');
	 var argStr = [];
	 if(qs){
		 for(var i=0;i<qs.length;i++){
			 argStr[i] = qs[i].split('=')[1];
		 }
	 }
	 return argStr;
}

function comAn(data) {
  // 详情分析x坐标数组
  var allItems = [];

  // 详情分析y坐标数组
  var allItemsVal = [];

  var index = 0;
  var showAll = $('all');

  for(var key in data) {
    allItems[index] = key;
    allItemsVal[index] = data[key];
    index++;
  } 

  drawGra(showAll, allItems, allItemsVal);
}


/*
 * 先将id一样的数据全部存在一个数组里
 * 如果id改变，就渲染数组并清空和更新数组
 *
 */
function resAn(data) {
  var initId = data[0].id;
  var allItems = [];
  var allItemsVal = [];
  var objectArr = [];
  var obj = {};
  var index = 0; // 第几题
  
  for(var i = 0, j = 0;i < data.length;i++, j++) {

    obj = new Object();
    obj.value = data[i].number;  
    obj.name = data[i].option;

    if(data[i].id == initId) {
      objectArr[j] = obj;
    } else {

      // 渲染一个饼图
      newPie(objectArr, index);

      // 改变id
      initId = data[i].id;
      
      objectArr.length = 0; // 清空数组
      j = 0;
      objectArr[j] = obj; // 更新数组
      index++;
    }
  }
  newPie(objectArr, index);
}


// 画饼图
function newPie(objectArr, index) {
  // 1. 创建一个div
  var node = document.createElement('div');
  var title = document.createElement('h3');

  // 设置样式
  node.style.width = 400 + 'px';
  node.style.height = 300 + 'px';
  node.style.float = 'left';
  title.innerHTML = '第' + (index + 1) + '题';

  // 2. 将div添加到DOM树里
  var showItems = document.getElementsByClassName('show-item')[0];

  // 3. 渲染
  var showAll = echarts.init(node);
  var option = {
    title: {
      text: '详情分析',
      show: true
    },
    tooltip: {
      show: true,
      trigger: 'item',
      formatter: "{a}<br/>{b} : {c} ({d}%)"
    },
    legend: {
      data: ['A', 'B', 'C', 'D']
    },
    series: [{
      name: '选择的人数:',
      type: 'pie',
      radius: '55%',
      data: objectArr 
    }],
  }
  showAll.setOption(option);
  showItems.appendChild(title);
  showItems.appendChild(node);
}

// 画柱状图
function drawGra(node, xData, yData) {
  var showAll = echarts.init(node);
  var option = {
    title: {
      text: '综合分析',
      show: true
    },
    tooltip: {},
    legend: {
      data: ['数量']
    },
    xAxis: {
      data: xData
    },
    yAxis: {},
    series: [{
      name: '数量',
      type: 'bar',
      data: yData
    }],
  }
  showAll.setOption(option);
};
