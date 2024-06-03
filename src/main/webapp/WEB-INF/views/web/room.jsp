<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<section class="rooms-section spad">
	<%-- <div class="container">
			<form class="row d-flex justify-content-start align-items-center" action="${timPhong }" method="get">
			    <div class="check-date form-group col-sm-4">
			        <label for="date-in">Thời gian:</label> 
			        <input type="text" class="form-control" name="dateRange" value="2024/05/09 - 2024/05/09" />
			    </div>
					
				<div class="check-date form-group col-sm-4">
					<label class="" for="">Số người:</label> 
					<select class="form-control" name="countUser" onfocus='this.size=3;' onblur='this.size=1;' onchange='this.size=1; this.blur();'>
						<c:forEach begin="1" end="6" var="item">
							<option value="${item }">${item } người</option>
						</c:forEach>
						
					</select>
				</div>
				<div class="select-option" >
					
				</div>
				<button type="submit" style="height:fit-content; padding : 8px 12px 8px 12px; margin:15px 0 0 18px">Kiểm tra</button>
			</form>
	</div> --%>
	<div class="container">
		<div class="row">
			<c:forEach items="${typerooms}" var="item">
				<div class="col-lg-4 col-md-6">
					<div class="room-item" >
						<img style=" width: 380px;height: 250px; object-fit: cover;"  src="<c:url value='${item.image}'/>" alt="" />

						<div class="ri-text">
							<h4 style="height:50px">${item.name }</h4>
							<h3>
								${item.priceFormat} VNĐ<span>/Đêm</span>
							</h3>
							<table>
								<tbody>
									<tr>
										<td class="r-o">Không gian:</td>
										<td>${item.space}m<sup>2</sup></td>
									</tr>
									<tr>
										<td class="r-o">Sức chứa:</td>
										<td><strong>${item.quantity} người</strong></td>
									</tr>
									<tr>
										<td class="r-o">Giường:</td>
										<td>Giường lớn</td>
									</tr>
									<tr>
										<td class="r-o">Dịch vụ:</td>
										<td>Wifi, TV, Phòng tắm,...</td>
									</tr>
								</tbody>
							</table>
							<a href="<c:url value='/chi-tiet?id=${item.id}'/>"
								class="primary-btn">Xem chi tiết</a>

						</div>
					</div>
				</div>
			</c:forEach>


		</div>
	</div>
</section>

