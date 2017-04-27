function ExportUser() {
	var url = "/" + path + "/export/exportUserExcel.do";
	var fileName = "千锋员工信息.xls";
	url = url + "?fileName=" + fileName;
	var orgId = "";
	if ($("#orgId").val() != null && $("#orgId").val() != "") {
		orgId = $("#orgId").val();
		url = url + "&orgId=" + orgId;
	}
	if ($("#userChName").val() != null && $("#userChName").val() != "") {
		var userChName = $("#userChName").val();
		url = url + "&userChName=" + userChName;
	}
	location.href = url;
}