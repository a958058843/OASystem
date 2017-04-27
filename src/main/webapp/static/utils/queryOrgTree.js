//查询属性菜单
function QueryOrgTreeLayer() {
	var htmlStr = "";
	htmlStr += '<div style="width:300px;height:200px" >';
	htmlStr += '<div style="display:block; padding-bottom:5px;" align="center"  >';
	htmlStr += '<div><table><tr><td><input type="hidden" id="tmp_orgPath" width="50px"/><input type="text" id="tmp_orgName" width="50px"><button onclick="SetQueryOrgTreeParam();">确定</button></td></tr></table></div>';
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
	QueryOrgTreeObj();
}
function QueryOrgTreeObj() {
	var setting = {
		data : {
			simpleData : {
				enable : true
			}
		},
		async : {
			enable : true,
			url : "/" + path + "/tree/orgSubList.do",
			autoParam : [ 'id', 'name' ],
			dataType : "json",
			type : "get",
		},
		callback : {
			onClick : ClickQueryOrgTreeNodeFunc
		},
	};
	var zNodes = [];

	var zTreeObj = $.fn.zTree.init($("#QueryOrgTree"), setting, zNodes);
}
function ClickQueryOrgTreeNodeFunc(event, treeId, treeNode, clickFlag) {
	$("#tmp_orgPath").val(treeNode.id);
	$("#tmp_orgName").val(treeNode.name);
}
function SetQueryOrgTreeParam() {
	$("#orgId").val($("#tmp_orgPath").val());
	$("#orgName").val($("#tmp_orgName").val());
	layer.closeAll();
}
function CancelQueryOrgTreeParentOrgId() {
	$("#qry_orgId").val('');
	$("#qry_orgName").val('');
}
