<%@ page import="com.hotel.constant.SystemConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url value='/phan-hoi' var="addfeedback" />
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<style>
.alert {
	padding: 20px;
	background-color: #4fc841;
	color: white;
	position: fixed;
	top: 0;
	right: 25%;
	left: 25%;
	margin-top: 10px;
	font-size: 20px;
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
	padding-top: 5px;
}

.closebtn:hover {
	color: black;
}
</style>

<%
// Lấy giá trị của tham số "message" từ URL
String message = request.getParameter("success");

// Kiểm tra xem tham số "message" có tồn tại không
if (message != null) {
%>
<div class="alert text-center" style="background: #dfa974">
	<span class="closebtn"><i class="fa-solid fa-xmark"></i></span> Bạn đã
	phản hồi thành công!
</div>
<%
}
// Lấy giá trị của tham số "message" từ URL
String refuse = request.getParameter("refuse");

// Kiểm tra xem tham số "message" có tồn tại không
if (refuse != null) {
%>
<div class="alert text-center" style="background: #dfa974">
	<span class="closebtn"><i class="fa-solid fa-xmark"></i></span> Bạn đã
	huỷ phòng thành công!
</div>
<%
}
%>
<div class="card shadow mb-4">
	<div class="card-body">
		<div class="d-flex justify-content-between">
			<h3 class="bk-btn rounded" style="margin-bottom: 20px;">Lịch sử
				đặt phòng</h3>
		</div>

		<div class=" mt-3"
			style="display: grid; grid-template-columns: repeat(3, minmax(0, 1fr));; grid-gap: 30px; background-color: #f3f3f3; padding: 10px;">
			<c:if test="${not empty modelOrder}">
				<c:forEach items="${modelOrder}" var="item" varStatus="status">
					<input type="hidden" id="roomIdInput_${status.index }"
						value="${item.getRoomId()}" />
					<div class="card ">
						<div class="card-body p-4">
							<h4 class="card-title position-relative"><i class="fa-solid fa-receipt"></i> Hoá đơn ${item.getId() }</h4>
							<p class="card-text">
								<strong>Thời gian: </strong>${item.getCheckinDate()} đến
								${item.getCheckoutDate() }
							</p>

							
							<p class="card-text">
								<strong>Tên phòng: </strong>${item.getRoomName() }
							</p>
							<p class="card-text">
								<strong>Số người: </strong>${item.getQuantity() } 
							</p>
							<p class="card-text">
								<strong>Thành giá: </strong>${item.getPriceFormat() } Đ
							</p>
							<c:if test="${item.getStatusId() == SystemConstant.PENDING || item.getStatusId() == SystemConstant.ACCEPT}">

								<div class="d-flex justify-content-end align-items-center">
									<form id="paymentForm_${status.index }" action="<c:url value='/thanh-toan'/>" method="post">
										<input type="hidden" value=${item.getId() } name="ma" /> <input
											type="hidden" value=${item.getTotalPrice() } name="total" />
										<button class="btn" type="submit"
											style="background: #dfa974; color: #fff; width: 150px">Thanh
											toán <i class="fa-solid fa-comment-dollar ml-1"></i></button>
									</form>
									<div title="Huỷ phòng" class="hover-container position-absolute" style="top:20px;right:30px"
										onclick="showAlert(event)">
										<a href="<c:url value='/huy-phong?ma=${item.getId() }'/>"
											id="cancel-link"> <i
											class="fa-solid fa-circle-xmark pt-1 ml-3"
											style="color: red; font-size: 30px;"></i>
										</a>
									</div>
								</div>
							</c:if>
							<c:if test="${item.getStatusId() == SystemConstant.PAID }">
								<div class="d-flex justify-content-end align-items-center">
									<a
										href="<c:url value='/feedback?roomId=${item.getRoomId() }&id=${item.getId() }'/>"
										class="text-center small btn btn-success btn-modal"
										style="font-size: 15px; color: #fff; width: 150px"> Phản hồi <i
										class="fas fa-envelope fa-fw mr-1 ml-1"></i>
									</a>
								</div>

							</c:if>
							<c:if test="${item.getStatusId() == SystemConstant.WAIT }">
								<div class="d-flex justify-content-end align-items-center">
									<p
										href="#"
										class="text-center small btn btn-primary btn-modal"
										style="font-size: 15px; color: #fff; width: 150px">Chờ duyệt <i class="fa-solid fa-spinner ml-1"></i>
									</p>
								</div>

							</c:if>
							<c:if test="${item.getStatusId() == SystemConstant.CANCEL }">
								<div class="d-flex justify-content-end align-items-center">
									<h5 class="text-center small btn btn-secondary"
										style="font-size: 15px; color: #fff; width: 150px;">
										</i>Phòng đã huỷ <i class="fa-solid fa-xmark ml-1"></i>
									</h5>
								</div>
							</c:if>
							<c:if test="${item.getStatusId() == SystemConstant.FEEDBACKED }">
								<div class="d-flex justify-content-end align-items-center">
									<h5 class="text-center small btn btn-info"
										style="font-size: 15px; color: #fff; width: 150px">
										</i>Đã phản hồi <i class="fa-solid fa-check ml-1"></i>
									</h5>
								</div>
							</c:if>
						</div>
					</div>
				</c:forEach>
			</c:if>
			
		</div>
			<c:if test="${empty modelOrder}">
				<div class="card" >
				  <div class="card-body text-center " style="background:#dfa974;color:#fff;font-size:20px">
				    Hiện tại quý khách chưa đặt phòng nào!
				  </div>
				</div>
			</c:if>
	</div>
	
</div>
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
	}, 2000);
	
	document.getElementById('paymentForm').addEventListener('submit', function (e) {
	    e.preventDefault();
	    var xhr = new XMLHttpRequest();
	    xhr.open("POST", "/thanh-toan", true);
	    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    xhr.onreadystatechange = function () {
	        if (xhr.readyState == 4 && xhr.status == 200) {
	            // Xử lý phản hồi từ server
	            window.location.href = xhr.responseText;
	        }
	    };
	    var params = 'ma=order123&total=1000';
	    xhr.send(params);
	});
	
	var roomIdValue = document.getElementById("roomIdInput").value;
	console.log(roomIdValue);
    document.getElementById("roomIdHidden").value = roomIdValue;
    
    function showAlert(event) {
        event.preventDefault(); // Ngăn chặn hành động mặc định của thẻ <a>
        
        Swal.fire({
        	type: "warning",
            title: "Bạn có muốn huỷ phòng này không?",
            showDenyButton: false,
            showCancelButton: true,
            confirmButtonText: "Chính xác",

        }).then((result) => {
            if (result.isConfirmed) {
                // Nếu người dùng xác nhận, điều hướng tới href của thẻ <a>
                window.location.href = document.getElementById('cancel-link').href;
            } else if (result.isDenied) {
                Swal.fire("Changes are not saved", "", "info");
            }
        });
    }
    
    $(document).ready(function () {
    	$(".btn-modal").click(function (e) {
    		e.preventDefault();
    		var link = $(this).prop("href");
    		console.log(link);
    		$.ajax({
    			url: link,
    			type: "GET",
    			error: function () {
    				alert("Your request is not valid!");
    			},
    			success: function (data) {
    				console.log("Dữ liệu nhận được từ phản hồi AJAX:", data);
    				$("#dialogModal").empty();
    				$("#dialogModal").html(data);
    				$("#dialogModal").modal();
    			}
    		});
    	});
    })
</script>


