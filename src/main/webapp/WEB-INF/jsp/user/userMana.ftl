<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<script type="text/javascript" src="/${proPath}/static/jquery/jquery-1.11.0.js"></script>
<!-- 自己写的JS -->
<script type="text/javascript" src="/${proPath}/static/utils/userMana.js"></script>
<script type="text/javascript" src="/${proPath}/static/utils/sendEmail.js"></script>
<script type="text/javascript" src="/${proPath}/static/utils/queryOrgTree.js"></script>
<script type="text/javascript" src="/${proPath}/static/utils/userOper.js"></script>
<script type="text/javascript" src="/${proPath}/static/utils/getArea.js"></script>
<script type="text/javascript" src="/${proPath}/static/utils/addOrgTree.js"></script>
<!-- 导入导出Excel -->
<script type="text/javascript" src="/${proPath}/static/utils/exportUser.js"></script>
<script type="text/javascript" src="/${proPath}/static/utils/importUser.js"></script>
<!-- jquery.form插件 -->
<script type="text/javascript" src="/${proPath}/static/jquery.form/jquery.form.js"></script>
<!-- layer -->
<script type="text/javascript" src="/${proPath}/static/layer/layer.min.js"></script>
<!--  zTree -->
<link rel="stylesheet" href="/${proPath}/static/zTree_v3-master/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="/${proPath}/static/zTree_v3-master/js/jquery.ztree.core.js"></script>
<body style="background-color: #F0FFF0;">
	<!-- 条件查询的table -->
	<table border="1" width="100%">
		<tr height="100px">
			<td>
				<table border="1" width="100%" height="90px">
					<thead>
						<tr height="30px">
							<td width="30%">组织：<input type="text" id="orgId"
								name="orgId" readonly="readonly"> <input type="hidden">
								<button onclick="QueryOrgTreeLayer()">选择</button>
							</td>
							<td width="30%">名称：<input type="text" id="userChName"
								name="userChName"></td>
							<td width="30%">电话号码：<input type="text" id="mobilePhone"
								name="mobilePhone"></td>
							<td width="30%">户籍： <select id="provinceId"
								name="provinceId" width="100" style="width: 140px;">
							</select></td>
						</tr>
						<tr height="30px">
							<td width="30%"></td>
							<td width="30%"></td>
							<td width="30%" style="text-align: center;">
								<button id="queryButton">查询</button> &nbsp;&nbsp;&nbsp;
								<button id="resetButton">重置</button>
							</td>
						</tr>
						<tr height="30px">
							<td width="100%" colspan="3" style="text-align: left;">
								<button onclick="addUser()">新增</button>
								  <!-- 导入 -->
						     <button  onclick="ImportUser();">导入Excel文件</button>
						     <button  onclick="ExportUser();">导出Excel文件</button>
							</td>
						</tr>
					</thead>
				</table>
			</td>
		</tr>
		<!-- 用户列表
		  把userList.ftl页面数据 展示到当前位置
		 -->
		<tr>
			<td><div id="userListContainer"></div></td>
		</tr>
		<!-- 分页查询的页码 userPageNumber.ftl -->
		<tr height="50px">
			<td><div id="numberListContainer" height="40px"
					style="text-align: center;"></div></td>
		</tr>
	</table>

</body>
<script type="text/javascript">
	var path = '${proPath}';
</script>
</html>