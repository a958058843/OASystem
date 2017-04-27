<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Full Layout - jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="/${proPath}/static/jquery-easyui-1.5/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/${proPath}/static/jquery-easyui-1.5/themes/icon.css">
	<!-- <link rel="stylesheet" type="text/css" href="/${proPath}/static/jquery-easyui-1.5/demo.css"> -->
	<script type="text/javascript" src="/${proPath}/static/jquery-easyui-1.5/jquery.min.js"></script>
	<script type="text/javascript" src="/${proPath}/static/jquery-easyui-1.5/jquery.easyui.min.js"></script>
	<!--树形菜单需要的-->
	 <link rel="stylesheet" href="/${proPath}/static/zTree_v3-master/css/demo.css" type="text/css">
	<link rel="stylesheet" href="/${proPath}/static/zTree_v3-master/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="/${proPath}/static/zTree_v3-master/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="/${proPath}/static/zTree_v3-master/js/jquery.ztree.core.js"></script>
	<!-- 树形菜单   -->
	<SCRIPT type="text/javascript">
	   <!--  如果支持js  那么解析为js  如果不支持相当于是注释-->
		
		
		var setting = {
				data: {
					simpleData: {
						enable: true
					}
				}
			};
		var zNodes =[
			<#list  menuList as menu>
		     	{ id:1, pId:0, name:'${menu.menuName}', url:'${menu.menuPath}', target:"right"},
			</#list>	
		];

		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
		 
	</SCRIPT>

</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">张氏集团</div>
	<div data-options="region:'west',split:true,title:'权限菜单'" style="width:150px;padding:10px;">
		<!-- 树形菜单 -->
		<div class="content_wrap">
			<div class="zTreeDemoBackground left">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
		</div>	
		
	</div>
	<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div>
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
	<div data-options="region:'center',title:'Center'">
		<!--   点击json数据配置的超链接  数据展示到中间 -->	
		<iframe name="right" width="100%" height="100%">
		
		</iframe>
	</div>
</body>
</html>