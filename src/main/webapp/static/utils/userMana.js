// Jquery入口

var pageSize = 10;
var totalCount = 0;

$(document).ready(function() {
	loadUserReturnPageData(0);
	$("#queryButton").bind("click",function(){
		loadUserReturnPageData(0);
	})
})
function loadUserReturnPageData(startPage){
	$.ajax({
		type:"GET",
		url:"/"+path+"/user/userList.do",
		dataType:"Text",
		data:{
			startIndex:startPage*pageSize,
			pageSize:pageSize,
			orgId:$("#orgId").val(),
			userChName:$("#userChName").val(),
			mobilePhone:$("#mobilePhone").val(),
			provinceId:$("#provinceId").val(),
			
		},
		success:function(data){
			//alert(data);
			$("#userListContainer").empty();
			$("#userListContainer").html(data);
			loadReturnPagingNumber(startPage);
		},error:function(){
			alert("请求失败!");
		}
	});
}
/*
 * 加载页面参数的值
 * */
function loadReturnPagingNumber(startPage){
	//ajax
	$.ajax({
		type:"GET",
		url:"/"+path+"/user/getNumberList.do",
		dataType:"text",
		data:{
			total:totalCount, // total-  第一个ajax获取回来的
			pageSize:pageSize,
			startIndex:startPage*pageSize
		},//json
		success:function(responseData){
			//alert(responseData);
			$("#numberListContainer").empty();
			$("#numberListContainer").html(responseData);
		},
		error:function(){
			alert("ajax fail");
		}	
	});
}
