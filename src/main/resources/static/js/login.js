$(function(){
	$('#login').click(function(){
		var username=$('#username').val();
		var password=$('#password').val();
		$.ajax({
			url:'/getuser',
			type:"post",
			data:{
				username:username,
				password:password
			},
			success:function(data){
				//alert(data.userList[0].username);
				//alert(data.success);
				if(data.success){
					
					if(data.admin){
						if(data.url==null){
							window.location.href='/admin';
						}else{
							window.location.href=data.url;
						}
					}else{
						if(data.url==null){
							window.location.href='/index';
						}else{
							window.location.href=data.url;
						}
					}
				}else{
					alert('用户名或密码错误！');
				}
			}
		});
	});
});