<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ page import="javax.servlet.http.*" %>
<c:url value="/phong" var="timPhong"/>
<style>
	.alert {
	  padding: 15px;
	  background-color: #4fc841;
	  color: white; position: fixed; top: 0; right: 25%; left:25%; margin-top:10px;
	  font-size:15px;
	}
	
	.closebtn {
	  margin-left: 15px;
	  color: white;
	  font-weight: bold;
	  float: right;
	  font-size: 22px;
	  line-height: 20px;
	  cursor: pointer;
	  transition: 0.3s;
	  padding-top:5px;
	}
	
	.closebtn:hover {
	  color: black;
	}
	::-webkit-scrollbar {
	  width: 0px; /* Đặt chiều rộng thanh cuộn */
	}
</style>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<!-- Chạy thông báo thanh toán thành công -->
<script>
    function showAlert(notification) {
    	Swal.fire({
    		  icon: "success",
    		  title: "Thông báo",
    		  text: notification,
    		  timer: 3000,
    		}).then((result) => {
            if (result.isConfirmed) {
                // Nếu người dùng xác nhận, điều hướng tới href của thẻ <a>
                window.location.href = document.getElementById('cancel-link').href;
            } else if (result.isDenied) {
                Swal.fire("Changes are not saved", "", "info");
            }
        });
        setTimeout(() => {
    	    
    	}, 3000);
    }
</script>
<%
    // Lấy giá trị của tham số "message" từ URL
    String message = request.getParameter("message");

    // Kiểm tra xem tham số "message" có tồn tại không
    if (message != null) {
        %>
        <script>
        	showAlert("Bạn đã thanh toán thành công!");
	    </script>
    <%}%>

<!-- Chạy thông báo đặt phòng thành công -->

<!-- Include SweetAlert2 JS -->


<%
    // Lấy giá trị của tham số "message" từ URL
    String success = request.getParameter("success");

    // Kiểm tra xem tham số "message" có tồn tại không
    if (success != null) {
%>
    <script>
        showAlert("Bạn đã đặt phòng thành công!");
    </script>
<%
    }
%>
<!-- Nội dung -->

<section class="hero-section">

	<div class="container">
		<div class="row">
			<div class="col-lg-6">
				<div class="hero-text">
					<h1>Khách sạn Bình Minh</h1>
					<p>Trang web đặt phòng khách sạn hàng đầu,
						bao gồm các đề xuất cho du lịch quốc tế và để tìm phòng khách sạn
						giá rẻ.</p>
					
				</div>
			</div>
			<%-- <div class="col-xl-4 col-lg-5 offset-xl-2 offset-lg-1">
				<div class="booking-form">
					<h3>Tìm phòng</h3>
					<form action="${timPhong }" method="get">
					    <div class="check-date">
					        <label for="date-in">Thời gian:</label> 
					        <input type="text" class="form-control" name="dateRange" value="${dateRange }" />
					        <i class="icon_calendar"></i>
					    </div>

						<div class="select-option">
							<label for="guest">Số người:</label> 
							<div class="wrapper" >
								<select name="countUser" onfocus='this.size=3;' onblur='this.size=1;' onchange='this.size=1; this.blur();'>
									<c:forEach begin="1" end="6" var="item">
										<option value="${item }">${item } người</option>
									</c:forEach>
									
								</select>
							</div>
						</div>
						<div class="select-option">
							
						</div>
						<button type="submit">Kiểm tra</button>
					</form>
				</div>
			</div> --%>
		</div>
	</div>
	<div class="hero-slider owl-carousel">
		<div class="hs-item set-bg"
			data-setbg="<c:url value = '/template/web/img/hero/hero-1.jpg'/>"></div>
		<div class="hs-item set-bg"
			data-setbg="<c:url value = '/template/web/img/hero/hero-2.jpg'/>"></div>
		<div class="hs-item set-bg"
			data-setbg="<c:url value = '/template/web/img/hero/hero-3.jpg'/>"></div>
	</div>
</section>
<!-- Hero Section End -->

<!-- About Us Section Begin -->
<section class="aboutus-section spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-6">
				<div class="about-text">
					<div class="section-title">
						<span>Về chúng tôi</span>
						<h2>
							Huế <br />Bình Minh Hotel
						</h2>
					</div>
					<p class="f-para">Đặt phòng ngay hôm nay để có khuyến mãi hấp đẫn.</p>
					<p class="s-para">Hãy đến với chúng tôi để có một trải nghiệm tuyệt vời.</p>
				
				</div>
			</div>
			<div class="col-lg-6">
				<div class="about-pic">
					<div class="row">
						<div class="col-sm-6">
							<img src="<c:url value = '/template/web/img/about/about-1.jpg'/>"
								alt="" />
						</div>
						<div class="col-sm-6">
							<img
								src="<c:url value = '/template/web/img/about/about-p2.jpg'/>"
								alt="" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- About Us Section End -->

<!-- Services Section End -->
<section class="services-section spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="section-title">
					<span>Dịch vụ</span>
					<h2>Khám phá dịch vụ của chúng tôi</h2>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-4 col-sm-6">
				<div class="service-item">
					<i class="flaticon-036-parking"></i>
					<h4>Kế Hoạch Du Lịch</h4>
					<p>Chúng tôi có các tour du lịch giúp các bạn có trải nghiệm
						trọn vẹn và tuyệt vời mà không cần phải suy nghĩ quá nhiều.</p>
				</div>
			</div>
			<div class="col-lg-4 col-sm-6">
				<div class="service-item">
					<i class="flaticon-033-dinner"></i>
					<h4>Ẩm thực</h4>
					<p>Với những món ăn được lên ý tưởng bời những đầu bếp tay nghề
						lâu năm của chúng tôi, mang đến những trải nghiệm độc đáo và mới
						lạ.</p>
				</div>
			</div>
			<div class="col-lg-4 col-sm-6">
				<div class="service-item">
					<i class="flaticon-026-bed"></i>
					<h4>Vui chơi</h4>
					<p>Chúng tôi có nhiều sự kiện hoạt động giữu du khách, đảm bảo
						mang đến cho quy khách một thời gian vui vẻ.</p>
				</div>
			</div>
			<div class="col-lg-4 col-sm-6">
				<div class="service-item">
					<i class="flaticon-024-towel"></i>
					<h4>Giặt ủi</h4>
					<p>đảm bảo quần áo của bạn luôn sạch sẽ và sẵn sàng cho bất kỳ
						sự kiện nào.</p>
				</div>
			</div>
			<div class="col-lg-4 col-sm-6">
				<div class="service-item">
					<i class="flaticon-044-clock-1"></i>
					<h4>thuê tài xế</h4>
					<p>Giúp di chuyển một cách an toàn và tiện lợi, không bó buộc
						về mặt thời gian, tự do khám phá theo ước muốn của bạn</p>
				</div>
			</div>
			<div class="col-lg-4 col-sm-6">
				<div class="service-item">
					<i class="flaticon-012-cocktail"></i>
					<h4>Quầy bar</h4>
					<p>Chúng tôi cung cấp đồ uống phong phú và hấp dẫn, giúp bạn
						thư giãn và thưởng thức những khoảnh khắc thú vị tại khách sạn của
						chúng tôi.</p>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- Services Section End -->
<div class="row d-flex justify-content-end mr-3 " style="margin-top:-50px">
	<a style="color:#333; padding:20px; font-size:23px" href='<c:url value="/phong"/>' ><i class="fa-solid fa-up-right-from-square"></i> Hiển thị thêm</a>
</div>
<!-- Home Room Section Begin -->
<section class="hp-room-section">
	<div class="container-fluid">
		<div class="hp-room-items">
			<div class="d-flex flex-row "  id="scrollContainer" style="overflow-x: auto !important; 
                      overflow-y: hidden;
                      white-space: nowrap;
                      scroll-snap-type: x mandatory;">
				<c:forEach items="${ typerooms}" var="item"> 
					<div class="col-lg-3 col-md-6">
						<div class="hp-room-item set-bg"
							data-setbg="<c:url value = '${item.getImage() }'/>">
							<div class="hr-text" style="margin-bottom:-10px">
								<h3 style="white-space: wrap;height:80px">${item.getName() }</h3>
								<h2>
									${item.getPriceFormat()} VNĐ<span>/Đêm</span>
								</h2>
								<table>
									<tbody>
										<tr>
											<td class="r-o">Không gian:</td>
											<td>${item.getSpace() } m<sup>2</sup></td>
										</tr>
										<tr>
											<td class="r-o">Sức chứa:</td>
											<td>${item.getQuantity() } người</td>
										</tr>
										
										<tr>
											<td class="r-o">Dịch vụ:</td>
											<td>Wifi, TV, Phòng tắm,...</td>
										</tr>
									</tbody>
								</table>
								<a href="<c:url value='/chi-tiet?id=${item.getId() }'/>" class="primary-btn " >Thông tin chi tiết</a>
							</div>
						</div>
					</div>
				</c:forEach>
					
			</div>
		</div>
	</div>
</section>
<!-- Home Room Section End -->

<!-- Testimonial Section Begin -->
<section class="testimonial-section spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="section-title">
					<span>Nhận xét</span>
					<h2>Khách hàng nói gì ?</h2>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-8 offset-lg-2">
				<div class="testimonial-slider owl-carousel">
					<div class="ts-item">
						<p>Sau khi dự án xây dựng kéo dài lâu hơn dự kiến, chồng tôi,
							con gái và tôi cần một nơi ở trong vài đêm. Là cư dân Chicago,
							chúng tôi biết rất nhiều về thành phố, khu phố của chúng tôi và
							các lựa chọn nhà ở có sẵn và hoàn toàn thích kỳ nghỉ của chúng
							tôi tại Bình Minh Hotel.</p>
						<div class="ti-author">
							<div class="rating">
								<i class="icon_star"></i> <i class="icon_star"></i> <i
									class="icon_star"></i> <i class="icon_star"></i> <i
									class="icon_star-half_alt"></i>
							</div>
							<h5>- Nguyễn Bảo Ngọc</h5>
						</div>
						<img src="img/testimonial-logo.png" alt="">
					</div>
					<div class="ts-item">
						<p>Sau khi một dự án xây dựng kéo dài hơn dự kiến, chồng tôi,
							con gái và tôi cần một nơi lưu trú trong vài đêm. Là cư dân
							Đà lạt, chúng tôi biết rất nhiều về thành phố, khu phố của chúng
							tôi và các lựa chọn nhà ở có sẵn và thực sự thích kỳ nghỉ của
							chúng tôi tại Bình Minh Hotel.</p>
						<div class="ti-author">
							<div class="rating">
								<i class="icon_star"></i> <i class="icon_star"></i> <i
									class="icon_star"></i> <i class="icon_star"></i> <i
									class="icon_star-half_alt"></i>
							</div>
							<h5>- Quý Hoài Bảo</h5>
						</div>
						<img src="img/testimonial-logo.png" alt="">
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- Testimonial Section End -->

<!-- Blog Section Begin -->
<section class="blog-section spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="section-title">
					<span>Hotel News</span>
					<h2>Our Blog & Event</h2>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-4">
				<div class="blog-item set-bg"
					data-setbg="<c:url value = '/template/web/img/blog/blog-1.jpg'/>">
					<div class="bi-text">
						<span class="b-tag">Du lịch</span>
						<h4>
							<a href="#">Biển Lăng Cô</a>
						</h4>
						<div class="b-time">
							<i class="icon_clock_alt"></i> 15th April, 2019
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="blog-item set-bg"
					data-setbg="<c:url value = '/template/web/img/blog/blog-2.jpg'/>">

					<div class="bi-text">
						<span class="b-tag">Cắm trại</span>
						<h4>
							<a href="#">Chinh phục trekking Tà Năng – Phan Dũng</a>
						</h4>
						<div class="b-time">
							<i class="icon_clock_alt"></i> 15th April, 2019
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="blog-item set-bg"
					data-setbg="<c:url value = '/template/web/img/blog/blog-3.jpg'/>">
					<div class="bi-text">
						<span class="b-tag">Event</span>
						<h4>
							<a href="#">Leo núi</a>
						</h4>
						<div class="b-time">
							<i class="icon_clock_alt"></i> 21th April, 2019
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-8">
				<div class="blog-item small-size set-bg"
					data-setbg="<c:url value = '/template/web/img/blog/blog-wide.jpg'/>">

					<div class="bi-text">
						<span class="b-tag">Event</span>
						<h4>
							<a href="#">Chuyến đi đến Huế, một thành phố của Việt Nam</a>
						</h4>
						<div class="b-time">
							<i class="icon_clock_alt"></i> 08th April, 2019
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="blog-item small-size set-bg"
					data-setbg="<c:url value = '/template/web/img/blog/blog-10.jpg'/>">
					<div class="bi-text">
						<span class="b-tag">Travel</span>
						<h4>
							<a href="#">Du lịch đến Đà Nẵng</a>
						</h4>
						<div class="b-time">
							<i class="icon_clock_alt"></i> 12th April, 2019
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- <div class="alert text-center" >
		  <span class="closebtn" ><i class="fa-solid fa-xmark"></i></span> 
		  <strong>Success !</strong> Bạn đã thanh toán thành công vui lòng chờ ít phút.
		</div> -->
<script>
	const alert = document.querySelector(".alert");
	const ex = document.querySelector(".closebtn");
	ex.addEventListener('click',function(){
		this.parentElement.style.display='none';
		alert.style.transition = 'opacity 1s ease';
	    alert.style.opacity = '0';
	    setTimeout(() => {
	        alert.style.display = 'none';
	    }, 1000);
	});
	setTimeout(() => {
	    // Thêm hiệu ứng làm mờ
	    alert.style.transition = 'opacity 1s ease';
	    alert.style.opacity = '0';

	    // Ẩn phần thông báo sau khi hoàn thành hiệu ứng làm mờ
	    setTimeout(() => {
	        alert.style.display = 'none';
	    }, 1000); // Thời gian delay tương ứng với thời gian của hiệu ứng làm mờ
	}, 3000);
</script>