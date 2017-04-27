var getArea = new GetArea();
// 初始化下拉列表
function CascadeArea(provinceId, cityId, countyId, eprovinceId, ecityId,
		ecountyId) {
	getArea.getProvinces("1", provinceId, eprovinceId, ecityId);

	$("#" + ecityId).click(function() {
		var pGeoId = $("#" + eprovinceId + "  option:selected").val();
		if (!pGeoId) {
			alert("请选择省份！");
			return;
		}
	});
	$("#" + ecountyId).click(function() {
		var pGeoId = $("#" + eprovinceId + "  option:selected").val();
		if (!pGeoId) {
			alert("请选择省份！");
			return;
		}
		var cGeoId = $("#" + ecityId + "  option:selected").val();
		if (!cGeoId) {
			alert("请选择地市！");
			return;
		}
	});
	// 省份下拉框志改变，清空地市、区县数据
	// js onChange
	$("#" + eprovinceId).change(function() {
		$("#" + ecityId + " option").remove();
		$("#" + ecountyId + " option").remove();
		// 查询当前省份对应的市
		var pId = $("#" + eprovinceId).val();// 省份id的值 14000
		// 预加载地市
		getArea.getCity(pId, null, ecityId);
		// 初始化区县
		getArea.getCounty(null, null, ecountyId);
	});
	// 地市下拉框值改变，清空区县数据
	$("#" + ecityId).change(function() {
		$("#" + ecountyId + " option").remove();
		// 查询下一级区域
		var cId = $("#" + ecityId).val();
		// 预加载区县
		getArea.getCounty(cId, null, ecountyId);
	});
}
function GetArea() {
	// 初始化省级区域 1
	this.getProvinces = function(chId, provinceId, eprovinceId, ecityId) {
		// 初始化省级区域
		var len = $("#" + eprovinceId + "  option").length;
		if (len < 1) {

			var areaId = chId;
			$.getJSON("/" + path + "/getAreaList.do", {
				areaParentId : "1"
			}, function(data) {
				var pObj = $("#" + eprovinceId);
				pObj.append("<option value=''>请选择:</option>");
				$.each(data, function(keyi, items) {
					pObj.append("<option value='" + items.areaId + "'>"
							+ items.areaName + "</option>");
				});
				// 初始化下拉框
				if (provinceId) {
					$("#provinceId1").val(provinceId);
					// 预加载地市
					// getArea.getCity(provinceId,null,ecityId);
				}
			});// getJson
		}
	}
	this.getCity = function(provinceId, cityId, ecityId, ecountyId) {
		var cObj = $("#" + ecityId);
		var len = $("#" + ecityId + "  option").length;
		// 判断下拉列表是否有值
		if (len < 1) {
			cObj.append("<option value=''>请选择:</option>");
		}
		if (provinceId) {
			if (len <= 1) {

				$.getJSON("/" + path + "/getAreaList.do", {
					areaParentId : provinceId
				},// 140000
				function(data) {
					$.each(data, function(keyi, items) {
						cObj.append("<option value='" + items.areaId + "'>"
								+ items.areaName + "</option>");
					});
					if (cityId) {
						$("#" + ecityId).val(cityId);
						// 预加载区县
						getArea.getCounty(cityId, null, ecountyId);
					}
				});// getJson
			}
		}
	}
	this.getCounty = function(cityId, countyId, ecountyId) {
		var coObj = $("#" + ecountyId);
		var len = $("#" + ecountyId + "  option").length;
		if (len < 1) {
			coObj.append("<option value=''>请选择:</option>");
		}
		if (cityId) {
			if (len <= 1) {
				$.getJSON("/" + path + "/getAreaList.do", {
					areaParentId : cityId
				}, function(data) {
					$.each(data, function(keyi, items) {
						coObj.append("<option value='" + items.areaId + "'>"
								+ items.areaName + "</option>");
					});
					if (countyId) {
						$("#" + ecountyId).val(countyId);
					}
				});// getJson
			}
		}
	}
}