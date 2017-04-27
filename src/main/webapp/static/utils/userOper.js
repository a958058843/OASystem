function addUser() {
	var htmlStr = initHtml();
	$.layer({
		type : 1,
		title : '人员新增',
		area : [ 'auto', 'auto' ],
		page : {
			html : htmlStr
		// 弹出的内容（页面）
		}
	});
	initData();// 初始化日期

	// 初始化省份 地市 区县
	// 所有的省份的area_parent_id = 1
	var cascadeArea = new CascadeArea(null, null, null, "provinceId1",
			"cityId", "countryId");
}
function saveOrUpdateUser() {
	var param = new Object();
	var userId = $("#userId").val();// 有值 修改 没值新增
	param.userId = userId;// set
	param.orgId = $("#orgId1").val();
	param.userChName = $("#userChName").val();
	param.userId = $("#userId").val();
	param.orgId = $("#orgId1").val();
	param.userChName = $("#userChName1").val();
	param.userSex = /* $("radio[name='userSex']:selected").val(); */$(
			"input[name='userSex']:checked").val();
	param.userBirthday = $("#year").val() + "-" + $("#month").val() + "-"
			+ $("#day").val();
	param.mobilePhone = $("#mobilePhone1").val();
	param.email = $("#email").val();
	param.userName = $("#userName").val();
	param.userPassword = $("#userPassword").val();
	param.userName = $("#userName").val();
	param.provinceId = $("#provinceId1").val();// 省份id select 的value值
	param.provinceName = $("#provinceId1").find("option:selected").text();// 省份name
	// select
	// option中间额文本
	param.cityId = $("#cityId").val();
	param.cityName = $("#cityId").find("option:selected").text();// 标签中间的文本
	param.countryId = $("#countryId").val();
	param.contryName = $("#countryId").find("option:selected").text();
	var url = "/" + path + "/user/";
	var desc = "新增";
	if (userId) {
		desc = "修改";
		url = url + "update.do";
	} else {
		url = url + "add.do";
	}
	$.ajax({
		type : "post",
		dataType : "json",
		url : url,
		data : param,
		success : function(responseData) {
			if (responseData.isSuccess == "true") {
				var alters = layer.alert(desc + "成功", 1, function() {
					layer.closeAll();
					location.relaod();
				});
			}
		}
	});
}
function update(userId) {
	var user;
	$.ajax({
		type : "post",
		dataType : "json",
		url : "/" + path + "/user/toUpdate.do",
		data : {
			userId : userId
		},
		success : function(responseData) {
			alert(responseData);
			user = responseData; 
			//user =  JSON.parse(responseData);
			var htmlStr = initHtml();
			$.layer({
				type : 1,
				title : '人员编辑',
				area : [ 'auto', 'auto' ],
				page : {
					html : htmlStr
				// 弹出的内容（页面）
				}
			});
			initData();
			// 初始化省份 地市 区县
			// 所有的省份的area_parent_id = 1
			var cascadeArea = new CascadeArea(null, null, null, "provinceId1",
					"cityId", "countryId");
			// 把ajax请求回来的数据填充到修改的html页面的输入框
			alert(responseData.userId);
			$("#userId").val(user.userId);
			$("#orgId1").val(user.orgId);
			$("#userChName1").val(user.userChName);
			var userSex = user.userSex;
			var $radio = $("input[type='radio']");

			for (var i = 0; i < $radio.size(); i++) {
				// jquery对象转为dom对象
				if ($radio[i].value == userSex) {
					$radio[i].checked = true;
				}
			}
			// alert(user.userBirthday);
			$("#mobilePhone1").val(user.mobilePhone);
			$("#email").val(user.email);
			$("#userName").val(user.userName);
			$("#userPassword").val(user.userPassword);
			$("#provinceId1").val(user.provinceId);// 省份id select 的value值
			$("#provinceId1").text(user.provinceName);// 省份name select
			// option中间额文本
			$("#cityId").val(user.cityId);
			$("#cityId").text(user.provinceName);// 标签中间的文本
			$("#countryId").val(user.countryId);
			// param.countryName = $("#countryId:selected").val();
		}
	});
}
/**
 * 删除
 * 
 * @returns
 */
function deleteUser(userId) {
	$.ajax({
		type : "post",
		dataType : "json",
		url : "/" + path + "/user/delete.do",
		data : {
			userId : userId
		},
		success : function(responseData) {
			if (responseData.isSuccess == "true") {
				var alerts = layer.alert("删除成功", 1, function() {
					layer.closeAll();
					location.reload();// 重新加载
				});
			}
		}
	});

}
function initData() {
	var year = $("#year");
	for (var i = 1930; i <= 2017; i++) {
		year.append("<option value='" + i + "'>" + i + "</option>");
	}

	var month = $("#month");
	for (var i = 1; i <= 12; i++) {
		month.append("<option value='" + i + "'>" + i + "</option>");
	}

	var day = $("#day");
	for (var i = 1; i <= 31; i++) {
		day.append("<option value='" + i + "'>" + i + "</option>");
	}
}
function initHtml() {
	var htmlStr = '';
	htmlStr += '<div style="width:600px;" >';
	htmlStr += '<div style="display:block; padding-bottom:20px;" align="center"  >';
	htmlStr += '<table border="0" >';
	htmlStr += '<tr style="display:none">';
	htmlStr += '<td colspan="2">';
	htmlStr += '<span>用户ID:</span>';
	htmlStr += '<input type="text"  id="userId" style="width:240px;">';
	htmlStr += '</td>';
	htmlStr += '</tr>';
	htmlStr += '<tr>';
	htmlStr += '<td>';
	htmlStr += '所属组织：';
	htmlStr += '<input type="text" id="orgName1"  style="width:110px;" readonly="readonly" >';
	htmlStr += '<input type="hidden" id="orgId1">';
	htmlStr += '<button  onclick="addOrgTreeLayer();">选择</button>';
	htmlStr += '</td>';
	htmlStr += '<td>';
	htmlStr += '<span>姓名:</span>';
	htmlStr += '<input type="text"  id="userChName1" style="width:240px;">';
	htmlStr += '</td>';
	htmlStr += '</tr>';

	htmlStr += '<tr>';
	htmlStr += '<td>';
	htmlStr += '性别：<input type="radio" name="userSex"  value="1">男';
	htmlStr += '<input type="radio" name="userSex"   value="2">女';
	htmlStr += '<input type="radio" name="userSex"   value="3">保密';
	htmlStr += '</td>';
	htmlStr += '<td>';
	htmlStr += '生日： <select id="year"  name="year"></select>年';
	htmlStr += '<select id="month" name="month"></select>月';
	htmlStr += '<select id="day"  name="day"></select>日';
	htmlStr += '</td>';
	htmlStr += '</tr>';
	htmlStr += '<tr>';
	htmlStr += '<td>';
	htmlStr += '<span>电话:</span>';
	htmlStr += '<input type="text"  id="mobilePhone1" style="width:240px;">';
	htmlStr += '</td>';
	htmlStr += '<td>';
	htmlStr += '<span>邮件:</span>';
	htmlStr += '<input type="text"  id="email" style="width:240px;">';
	htmlStr += '</td>';
	htmlStr += '</tr>';

	htmlStr += '<tr>';
	htmlStr += '<td>';
	htmlStr += '<span>用户名:</span>';
	htmlStr += '<input type="text"  id="userName" style="width:240px;">';
	htmlStr += '</td>';
	htmlStr += '<td>';
	htmlStr += '<span>密码:</span>';
	htmlStr += '<input type="text"  id="userPassword" style="width:240px;">';
	htmlStr += '</td>';
	htmlStr += '</tr>';
	htmlStr += '<tr>';
	htmlStr += '<td>';
	htmlStr += '<span>省份:</span>';
	htmlStr += '<select id="provinceId1" style="width:240px;">';
	htmlStr += '</select>';
	htmlStr += '</td>';
	htmlStr += '<td>';
	htmlStr += '<span>地市:</span>';
	htmlStr += '<select id="cityId" style="width:240px;">';
	htmlStr += '</select>';
	htmlStr += '</td>';
	htmlStr += '</tr>';

	htmlStr += '<tr>';
	htmlStr += '<td>';
	htmlStr += '<span>区县:</span>';
	htmlStr += '<select id="countryId" style="width:240px;">';
	htmlStr += '</select>';
	htmlStr += '</td>';
	htmlStr += '<td>';
	htmlStr += '</td>';
	htmlStr += '</tr>';
	htmlStr += '<tr id="saveUserTr"><td colspan="2" align="center">';
	htmlStr += '<input id="saveButton" name="" type="button"  onclick="javascript:saveOrUpdateUser();" value="保存"  />';
	htmlStr += '</td></tr>';
	htmlStr += '</table>';
	htmlStr += '</div>';
	htmlStr += '</div>';

	return htmlStr;
}