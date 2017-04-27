//发送邮件的弹框
	function sendEmail(email){//收件人   email
		
		var htmlStr = initHtml1();
		$.layer({
				type : 1,
				title : '发送邮件',
				area : [ 'auto', 'auto' ],
				page : {
					html : htmlStr   //弹出的内容（页面）
				}
		   });
		$('#receiverAddress').val(email);
		
	}

	function send() {
		var receiverAddress=$("#receiverAddress").val();
		if(!receiverAddress){
			var alerts=layer.alert("收件人不能为空",5,function(){
				layer.close(alerts);
			});
		}
		var title=$("#title").val();
		if(!title){
			var alerts=layer.alert("主题不能为空",5,function(){
				layer.close(alerts);
			});
		}
		var content=$("#content").val();
		if(!content){
			var alerts=layer.alert("内容不能为空",5,function(){
				layer.close(alerts);
			});
		}
		var params=new Object();
		params.receiverAddress=receiverAddress;
		params.title=title;
		params.content=content;
		
		$.ajax({
			url:"/"+path+"/user/sendMail.do",
			type:"post",
			data:params,
			success:function(msg){
				var obj= jQuery.parseJSON(msg);
				var isSuccess=obj.isSuccess;
				alert(isSuccess);
				if(true==isSuccess){
					var alerts=layer.alert("发送成功",1,function(){
						layer.close(alerts);
						location.reload();
					});
				}else{
					var alerts=layer.alert("发送失败",5,function(){
						layer.close(alerts);
					});
				}
			}
		});
	}
	 function initHtml1(){//初始化html   发送邮件的html页面
			var htmlStr = "";
			htmlStr += '<div style="width:600px;" >';
			htmlStr += '<div style="display:block; padding-bottom:20px;" align="center"  >';
			htmlStr += '<table border="0" >';
			htmlStr += '<tr>';
			htmlStr += '<td colspan="2">';
			htmlStr += '<span>收件人:</span>';
			htmlStr += '<input type="text"  id="receiverAddress" readonly="readonly" style="width:480px;">';
			htmlStr += '</td>';
			htmlStr += '</tr>';
			htmlStr += '<tr>';
			htmlStr += '<td colspan="2">';
			htmlStr += '<span>标题:</span>';
			htmlStr += '<input type="text"  id="title"  style="width:480px;">';
			htmlStr += '</td>';
			htmlStr += '</tr>';
			htmlStr += '<tr>';
			htmlStr += '<td colspan="2">';
			htmlStr += '<span>内容:</span>';
			htmlStr += '<textarea type="text" rows="4" id="content" style="width:480px;"></textarea>';	 
			htmlStr += '</td>';
			htmlStr += '</tr>';
			htmlStr += '<tr><td colspan="2" align="center">';
			htmlStr += '<input  name="" type="button"  onclick="javascript:send();" value="发送"  />';		
			htmlStr += '</td></tr>';
			htmlStr += '</table>';
			htmlStr += '</div>';
			htmlStr += '</div>';
			return htmlStr;
		} 