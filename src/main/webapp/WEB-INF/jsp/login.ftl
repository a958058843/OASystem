
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Validate Form on Submit - jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="/${proPath }/static/jquery-easyui-1.5/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/${proPath }/static/jquery-easyui-1.5/themes/icon.css">
	<script type="text/javascript" src="/${proPath }/static/jquery-easyui-1.5/jquery.min.js"></script>
	<script type="text/javascript" src="/${proPath }/static/jquery-easyui-1.5/jquery.easyui.min.js"></script>
</head>
<body>
	<div align="center">
	<h2>张氏集团OA系统</h2>
	
	<div class="easyui-panel" title="登录" style="width:35%;vertical-align: middle;" align="center">
		<form id="ff" action="/${proPath }/doLogin.do" class="easyui-form" method="post">
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="name" style="width:100%" data-options="label:'用户名:',required:true">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" type="password" name="password" style="width:100%" data-options="label:'密码:',required:true,validType:'password'">
			</div>
			
		</form>
		<div style="text-align:center;padding:5px 0">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">Submit</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">Clear</a>
		</div>
	</div>
	</div>
	<script>
		
		function submitForm(){
			/* $('#ff').form('submit',{
				onSubmit:function(){
					return true;
				}
			}); */
			//$("#ff").submit();
			document.getElementById("ff").submit();
		}
		function clearForm(){
			$('#ff').form('clear');
		}
	</script>
</body>
</html>