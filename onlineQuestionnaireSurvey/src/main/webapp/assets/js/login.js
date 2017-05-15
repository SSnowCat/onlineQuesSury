var login = function(){
	var uname = $('.username').val();
	var pwd = $('.password').val();
	var ident = $('.identify').val();
	if(uname == '') {
		$('.error').fadeOut('fast', function(){
			$(this).css('top', '27px');
		});
		$('.error').fadeIn('fast', function(){
			$(this).parent().find('.username').focus();
		});
		return;
	}
	if(pwd == '') {
		$('.error').fadeOut('fast', function(){
			$(this).css('top', '96px');
		});
		$('.error').fadeIn('fast', function(){
			$(this).parent().find('.password').focus();
		});
		return;
	}
	if(ident== ''){
		$('.error').fadeOut('fast', function(){
			$(this).css('top', '165px');
		});
		$('.error').fadeIn('fast', function(){
			$(this).parent().find('.identify').focus();
		});
		return;	
	}
	$.ajax({
		type:'post',
		url:'./session/user',
		contentType: "application/json;charset=utf-8",
		dataType:'json',
		data:JSON.stringify(getData(uname,pwd,ident)),
		async:true,
		success:function(data){
			if(data.success){
				location.href=data.data.url;
			}else{
				alert(data.error);
			}
		},
		error:function(jqXHR){
			alert(jqXHR.status);
		}
	});
	
};

$(function(){
	$('.username,.password,.identify').keyup(function(){
		$(this).parent().find('.error').fadeOut('fast');
	});	
})

function getData(uname,pwd,ident){
	var data = {
		username:uname,
		password:pwd,
		identify:ident
	}
    return JSON.stringify(data);
}

function getImg(){
	var Image1 = document.getElementById("img");
	if (Image1 != null) {
		Image1.src = 'code/'+new Date().getSeconds();
		Image1.style.display="block";
	}
}
function change(){
	var Image1 = document.getElementById("img");
	if (Image1 != null) {
		Image1.src = Image1.src + "?";
	}
}

