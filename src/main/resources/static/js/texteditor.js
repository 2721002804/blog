$(function(){
	$('#submit').click(function(){
		var title=$('#title').val();
		var content=$('#content').val();
		if(title.length==0){
			title=content.substr(0,5);
		}
		$.ajax({
			url:'/saveblog',
			type:"post",
			data:{
				title:title,
				content:content
			},
			success:function(data){
				//alert(data.userList[0].username);
				if(data.success){
					window.location.href='/personal';
				}else{
					alert('发布失败！');
				}
			}
		});
	});
});