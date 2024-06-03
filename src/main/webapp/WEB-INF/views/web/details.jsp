<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@page import="com.hotel.util.SecurityUtils"%>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<!-- Thông báo  -->
<script>
function showAlert(notification) {
    Swal.fire({
        icon: "error", // Changed to "warning" for better context
        title: "Thông báo",
        text: notification,
        timer: 3000,
        showConfirmButton: false // Automatically close after 3 seconds
    });
}
</script>

<style>
.i-con{
	font-size:18px;
	paddinng-right:5px;
}
	<c:forEach begin="1" end="4" var="item">
        .icon-${item}:hover > .image-hover-${item} {
            display: block !important;
        }
    </c:forEach>
</style>
<c:if test="${notAvailable!=null }">
	<script>
		showAlert("Hiện loại phòng này đã hết phòng trống!");
	</script>
</c:if>

<c:if test="${errorDate!=null }">
	<script>
		showAlert("Hiện không hỗ trợ thuê 1 ngày!");
	</script>
</c:if>

<c:if test="${late!=null }">
	<script>
		showAlert("Phải đặt phòng từ ngày hiện tại trở lên!");
	</script>
</c:if>
<c:if test="${lateDateNow!=null }">
	<script>
		showAlert("Ngày đặt phải từ ngày hiện tại trở lên!");
	</script>
</c:if>
<section class="room-details-section spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-8">
				<div class="room-details-item">
					<img src="<c:url value='${typeroom.image}'/>" style="width:100%"
						alt="${typeroom.name}" />
					<div class="rd-text">
						<div class="rd-title">
							<h3>${typeroom.name}</h3>
							<div class="rdt-right pt-2">
								<div class="rating">
									<i class="icon_star"></i> <i class="icon_star"></i> <i
										class="icon_star"></i> <i class="icon_star"></i> <i
										class="icon_star-half_alt"></i>
								</div>

							</div>
						</div>
						<hr>
						<h2>${typeroom.priceFormat}
							VNĐ<span>/Đêm</span>
						</h2>
						<table>
							<tbody>
								<tr>
									<td class="r-o">Không gian:</td>
									<%-- <td>${room.space} m<sub>2</sub></td> --%>
									<td>${typeroom.space}m<sup>2</sup></td>
								</tr>
								<tr>
									<td class="r-o">Sức chứa:</td>
									<td>${typeroom.quantity}người</td>
								</tr>
								<tr>
									<td class="r-o">Bed:</td>
									<td>King Beds</td>
								</tr>
								<tr>
									<td class="r-o">Services:</td>
									<td>Wifi, Television, Bathroom,...</td>
								</tr>
							</tbody>
						</table>
						<hr>
						<!-- Xuống dòng khi gặp ' ' -->
						<p style="white-space: pre-line;">${typeroom.description}</p>
						<hr>
						<div class="d-flex flex-nowrap justify-content-around">
							<div class="text-center">
								<img style="margin-bottom:20px" src="https://cdn6.agoda.net/images/property/highlights/location.svg"/>
								<p style = "white-space:wrap;width:100px;text-align:center">Địa điểm được yêu thích</p>
							</div>
							<div class="text-center">
								<img style="margin-bottom:20px" src="https://cdn6.agoda.net/images/property/highlights/medal.svg"/>
								<p style = "white-space:wrap;width:100px">Top đánh giá tốt</p>
							</div>
							<div class="text-center">
								<img style="margin-bottom:20px" src="https://cdn6.agoda.net/images/property/highlights/spray.svg"/>
								<p style = "white-space:wrap;width:100px">Vệ sinh sạch sẽ</p>
							</div>
							<div class="text-center">
								<img style="margin-bottom:20px" src="https://cdn6.agoda.net/images/property/highlights/family-love-1.svg"/>
								<p style = "white-space:wrap;width:100px">Được các cặp đôi yêu thích</p>
							</div>
							<div class="text-center">
								<img style="margin-bottom:20px" src="https://cdn6.agoda.net/images/property/highlights/location.svg"/>
								<p style = "white-space:wrap;width:120px">Vị trí gần trung tâm thành phố</p>
							</div>
						</div>
						
					</div>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="room-booking ">
					<h3 style="color:#dfa974">Đặt phòng cho bạn</h3>
					<form action='<c:url value="/chi-tiet" />' method="get">
						<input type="hidden" value="${typeroom.id }" name="id" />
						<div class="check-date">
							<label for="date-in">Thời gian:</label> <input type="text"
								class="form-control" name="dateRange" value="${dateRange }" />
							<i class="icon_calendar"></i>
						</div>

						<div class="select-option">
							<label for="guest">Khuyến Mãi:</label> 
							<select id="guest" name="promotionId">
								<option value="0" class="mx-auto">---- Chọn khuyến mãi ----</option>
								<c:choose>
									<c:when test="${not empty promotions}">
										<c:forEach items="${promotions}" var="item">
											<option value="${item.id}">${item.name}</option>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<option value="-1">Không có khuyến mãi nào</option>
									</c:otherwise>
								</c:choose>
							</select>
						</div>
						<%-- <div class="select-option">
							<label for="room">Loại phòng:</label> <select id="room">
								<c:forEach items="${typerooms}" var="item">
									<option value="${item.id}">${item.name}</option>
								</c:forEach>
							</select>
						</div> --%>
						<c:if test="${user!=null }">
							<button type="submit">Đặt phòng</button>
						</c:if>
						<c:if test="${user==null }">
							<a href="<c:url value='/dang-nhap'/>" style="color:#fff;background:#dfa974; border:1px #dfa974 solid;padding:12px; width:100%" class="rounded btn"> Vui lòng đăng nhập để đặt phòng</a>
						</c:if>
					</form>
					<hr>
					
					<div class="col pt-3">
						<h5 style="font-weight:bold">Địa điểm gần khách sạn</h5>
						<div class="row d-flex justify-content-between pt-4 icon-1">
							<strong><i class="i-con fa-solid fa-shop pr-2"></i> Siêu thị: <i class="fa-regular fa-image position-relative " style="color:gray;font-size:13px"></i></strong>
							<img src="https://media.vneconomy.vn/images/upload/2024/03/26/sieu-thi2.jpg" class="position-absolute d-none image-hover-1" alt="Hover Image" style="top: -120px;left:20px;border-radius:10px;height:180px;object-fit:cover;width:250px">	
							<p>320m</p>
						</div>
						<div class="row d-flex justify-content-between icon-2">
							<strong><i class="i-con fa-solid fa-square-parking pt-1 pl-1 pr-2"></i> Công viên: <i class="fa-regular fa-image position-relative " style="color:gray;font-size:13px"></i></strong>
								<img src="https://static.vinwonders.com/production/cong-vien-1.jpg" class="position-absolute d-none image-hover-2" alt="Hover Image" style="top: -120px;left:20px;border-radius:10px;height:180px;object-fit:cover;width:250px">	
							<p>1km</p>
						</div>
						<div class="row d-flex justify-content-between icon-3">
							<strong><i class="i-con fa-solid fa-umbrella-beach pr-2"></i> Biển: <i class="fa-regular fa-image position-relative " style="color:gray;font-size:13px"></i></strong>
							<img src="https://kenh14cdn.com/thumb_w/650/2016/top-hawaiian-beaches-kaunaoa-jpg-rend-tccom-1280-960-1468482390326.jpeg" class="position-absolute d-none image-hover-3" alt="Hover Image" style="top: -120px;left:20px;border-radius:10px;height:180px;object-fit:cover;width:250px">	
							<p>2km</p>
						</div>
						<div class="row d-flex justify-content-between icon-4">
							<strong><i class="i-con fa-solid fa-house-chimney pr-2"></i> Bảo tàng điêu khắc: <i class="fa-regular fa-image position-relative " style="color:gray;font-size:13px"></i></strong>
							<img src="https://bazaarvietnam.vn/wp-content/uploads/2022/08/Harpers-Bazaar-bao-tang-museum-Trung-Quoc_02.jpg" class="position-absolute d-none image-hover-4" alt="Hover Image" style="top: -120px;left:20px;border-radius:10px;height:180px;object-fit:cover;width:250px">	
							<p>2km</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<hr>
		<!-- Hiển thị feedback theo loại phòng -->
		<form action="<c:url value='/chi-tiet/phan-hoi'/>"
		id="formSearch" method="post" data-container="#searchResult">
		    <input type="hidden" name="page" value="" id="page"/>
		    <input type="hidden" name="limit" value="" id="limit"/>
		    <div id="searchResult"></div>
		</form>
	</div>
	
</section>
<script>
	document.getElementById("page").value = ${model.page};
	document.getElementById("limit").value = ${model.limit};
	function resetPageAndSubmit() {
        // Đặt giá trị của phần tử có id là "page" và "limit" về ${model.page} và ${model.limit} tương ứng
        document.getElementById("page").value = 1;
        document.getElementById("limit").value = ${model.limit};

        // Gửi biểu mẫu tìm kiếm khi thay đổi lựa chọn
        $("#formSearch").submit();
    }
	
	  $(document).ready(function () {
	        $("#formSearch").submit(function (e) {
	            e.preventDefault();
	            doSearch(this, 1);
	        });
	        // Initially load search results
	        doSearch("#formSearch", ${model.page});
	    });

	  //nào có input onChange or onCLick mới dùng
    function doSearch(formSearch, page) {
        var action = $(formSearch).prop("action");
        var method = $(formSearch).prop("method");
        var container = $(formSearch).data("container");

        // Include page information in search data
        var searchData = $(formSearch).serializeArray();


        $.ajax({
            url: action,
            type: method,
            data: searchData,
            error: function () {
                alert("Your request is not valid!");
            },
            success: function (data) {
                $('#searchResult').html(data);
            }
        });
    }
</script>



