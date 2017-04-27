$(function() {
	var mybarCharts = echarts.init(document.getElementById("barCharts"));
	var option={
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'cross',
		            crossStyle: {
		                color: '#999'
		            }
		        }
		    },
		    toolbox: {
		        feature: {
		            dataView: {show: true, readOnly: false},
		            magicType: {show: true, type: ['line', 'bar']},
		            restore: {show: true},
		            saveAsImage: {show: true}
		        }
		    },
		    legend: {
		        data:['男','女','保密']
		    },
		    xAxis: [
		        {
		            type: 'category',
		            data: [],//x显示的文字
		            axisPointer: {
		                type: 'shadow'
		            }
		        }
		    ],
		    yAxis: [  //数组
		        {
		            type: 'value',
		            name: '人数',
		            min: 0,
		            max: 10,
		            interval: 2,  //interval  增量多少
		            axisLabel: {
		                formatter: '{value} 个'
		            }
		        }
		    ],
		    series: [
		        {
		            name:'男',
		            type:'bar',
		            data:[]
		        },
		        {
		            name:'女',
		            type:'bar',
		            data:[]
		        },
		        {
		            name:'保密',
		            type:'bar',
		            data:[]
		        }
		    ]
		};
	 //使用ajax 异步请求服务端的数据 
	 $.getJSON('/OASystem/user/selectBarProvice.do',{},
			 function(responseData){
		 if(responseData.isSuccess==true){
			 option.xAxis[0].data = responseData.provinceNameList;
			 option.series[0].data = responseData.boyList;
			 option.series[1].data = responseData.girlList;
			 option.series[2].data = responseData.secrecyList;
			 mybarCharts.setOption(option);

		 }
	 });
});