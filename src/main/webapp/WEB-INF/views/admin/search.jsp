<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="chartRevenueRecentDays" class="shadow" style="height: 370px; width: 100%; border-radius: 10px; overflow: hidden;"></div>
<script>
	//Chart column Revenue Recent Days
	var dps = [[]]
	var chartRevenueRecentDays = new CanvasJS.Chart("chartRevenueRecentDays", {
		theme: "light2", //"light1", "dark1", "dark2"
		animationEnabled: true,
		title: {
			text: "Doanh thu ${model.days} ngày gần đây",
			fontFamily: 'Nunito'
		},
		axisX: {
			valueFormatString: "DD-MM"
		},
		axisY: {
			title: "(VNĐ)",
		},
		data: [{
			type: "column",
			xValueType: "dateTime",
			xValueFormatString: "DD-MM",
			yValueFormatString: "#,##0 VNĐ",
			dataPoints: dps[0]
		}]
	});
	 
	var xValue;
	var yValue;
	
	<c:forEach items="${model.chartRevenueRecentDaysDataList}" var="dataPoints" varStatus="loop">	
		<c:forEach items="${dataPoints}" var="dataPoint">
			xValue = parseInt("${dataPoint.x}");
			yValue = parseFloat("${dataPoint.y}");
			label = "${dataPoint.label}";
			indexLabel = "${dataPoint.indexLabel}";
			dps[parseInt("${loop.index}")].push({
				x : xValue,
				y : yValue
			});	
		</c:forEach>	
	</c:forEach> 
	 
	chartRevenueRecentDays.render();	
</script>
<!-- NHớ bỏ cancvs -->