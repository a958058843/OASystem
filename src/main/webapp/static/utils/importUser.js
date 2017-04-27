function ImportUser() {
	var htmlStr = "";
	htmlStr += '<div style="width:600px;" >';
	htmlStr += '<div style="display:block; padding-bottom:20px;" align="center" >';
	htmlStr += '<form  id = "userExcelFileFormId" action="/'+ path+ '/importExcel/uploadExcel.do" enctype="multipart/form-data" method="post" >';
	htmlStr += '<input type="file" id="userExcelFile" name="userExcelFile"  onchange="ValidateFileType()" >';
	htmlStr += ' <input type="submit"  value="导入"  />  ';
	htmlStr += '</form>';
	htmlStr += '</table>';
	htmlStr += '</div>';
	htmlStr += '</div>';
	$.layer({
		type : 1,
		title : "导入用户",
		area : [ 'auto', 'auto' ],
		page : {
			html : htmlStr
		}
	});
	submitFuc();
}
function submitFuc() {
	var options={
			beforeSubmit:ShowRequest,
			success:ShowResponse,
			resetForm:true,
			dataType:'json',
	};
	
	$('#userExcelFileFormId').submit(function() {
		var file = document.getElementById("userExcelFile");// file
		if(!file.value){
			alert("请选Excel文档！");
			return false;// 阻止表单默认行为
	}
		
		$(this).ajaxSubmit(options);
		return false;
	});
}
function ShowResponse(responseText, statusText){
	if(responseText.success==true){
		alert('导入成功');
		window.location ="/"+path+"/user/userMana.do";//刷新  查询最新的数据
	}
}
function ValidateFileType() {
	var array = new Array(); // 定义一数组
	var excelName = $("#userExcelFile").val();
	array = excelName.split('.');
	//var file = document.getElementById("userExcelFile");
	var suffix = array[array.length - 1];
	//xls/xlsx/doc
	if (suffix != 'xlsx' && suffix != 'xls') {
		alert("你选择的不是Excel文档,请选Excel文档！");
		var file = document.getElementById("userExcelFile");
		file.value = "";
	}
}
function  ShowRequest(){}
