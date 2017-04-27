function addOrgTreeLayer() {
	var htmlStr = "";
	htmlStr += '<div style="width:300px;height:200px" >';
	htmlStr += '<div style="display:block; padding-bottom:5px;" align="center"  >';
	htmlStr += '<div><table><tr><td><input type="hidden" id="tmp_orgPath" width="50px"/><input type="text" id="tmp_orgName" width="50px"><button onclick="addQueryOrgTreeParam();">确定</button></td></tr></table></div>';
	htmlStr += '<div id="QueryOrgTree" class="ztree"></div>';
	htmlStr += '</div>';
	htmlStr += '</div>';
	$.layer({
		type : 1,
		title : false,
		area : [ 'auto', 'auto' ],
		border : [ 0 ],
		closeBtn : [ 0, true ],
		shift : 'center',
		page : {
			html : htmlStr
		}
	});
	QueryOrgTreeObj();// 树形结构
}
function QueryOrgTreeObj() {
	var setting = {
		data : {
			simpleData : {
				enable : true
			}
		},
		async : {
			enable : true, // 开启异步加载处理
			url : "/" + path + "/tree/orgSubList.do",
			autoParam : [ "id", "name" ], // 异步加载时需要自动提交父节点属性的参 当前节点的节点id 节点名称
			// otherParam:{"otherParam":"zTreeAsyncTest"},//向后台传入其他参数
			dataType : "json",// 默认text
			type : "get"// 默认post
		},
		callback : {
			onClick : ClickQueryOrgTreeNodeFunc
		}
	};
	var zNodes = [];

	var zTreeObj = $.fn.zTree.init($("#QueryOrgTree"), setting, zNodes);
}
function ClickQueryOrgTreeNodeFunc(event, treeId, treeNode, clickFlag) {
	// alert(treeNode+"---"+treeNode.id+"---"+treeNode.name);
	// 执行ajax成功后的回调函数
	// 把用户点击的这个节点的值 --赋值给 输入框
	$("#tmp_orgPath").val(treeNode.id);
	$("#tmp_orgName").val(treeNode.name);

}
function addQueryOrgTreeParam() {
	$("#orgId1").val($("#tmp_orgPath").val());
	$("#orgName1").val($("#tmp_orgName").val());
	var index = layer.index; // 获取当前弹层的索引号
	layer.close(index); // 关闭当前弹层
}
function CancelQueryOrgTreeParentOrgId() {
	$("#qry_orgId").val('');
	$("#qry_orgName").val('');
}
