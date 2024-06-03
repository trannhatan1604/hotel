<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url value="/quan-tri/phieu-dat/luu" var="saveOrder" />
<c:url value="/quan-tri/phieu-dat/doi-phong" var="changeRoom" />
<style>
.list-room {
	height: 100vh;
	overflow-y: scroll;
}

.list-room::-webkit-scrollbar {
	display: none;
	/* Ẩn thanh cuộn trên trình duyệt WebKit (Chrome, Safari, etc.) */
}

.item-room {
	width: 100%;
}

.animate-left, .animate-right {
	transition: all 0.3s ease; /* Thời gian và kiểu hiệu ứng */
}

.collapsed .animate-left {
	transform: translateX(-100%);
	/* Di chuyển left side ra khỏi màn hình */
}

.collapsed .animate-right {
	transform: translateX(100%);
	/* Di chuyển right side ra khỏi màn hình */
}
/* Tạo hiệu ứng fade-in và fade-out cho modal */
@
keyframes fadeIn {from { opacity:0;
	
}

to {
	opacity: 1;
}

}
@
keyframes fadeOut {from { opacity:1;
	
}

to {
	opacity: 0;
}

}
.modal.fade .modal-dialog {
	animation-name: fadeIn;
	animation-duration: 0.5s; /* Thời gian hiệu ứng */
}

.modal.fade .modal-dialog.modal-hidden {
	animation-name: fadeOut;
	animation-duration: 0.5s; /* Thời gian hiệu ứng */
}
/* Định dạng chung cho các phần tử hiển thị giá */
.price-container {
	font-size: 16px;
	margin-bottom: 10px;
}

/* Định dạng giá cũ */
#oldPriceContainer {
	color: #ff0000; /* Màu đỏ cho giá cũ */
	text-decoration: line-through; /* Gạch ngang giá cũ */
}

/* Định dạng giá mới */
#newPriceContainer {
	color: #00b300; /* Màu xanh lá cho giá mới */
	font-weight: bold; /* In đậm giá mới */
}

/* Định dạng tổng giá */
#totalPrice {
	font-size: 18px;
	color: #0000ff; /* Màu xanh dương cho tổng giá */
	font-weight: bold; /* In đậm tổng giá */
	padding-top: 10px; /* Khoảng cách trên */
	border: none; /* Loại bỏ đường viền */
	background: none; /* Loại bỏ nền */
}
</style>
<div class="container-fluid ">
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary">Chỉnh sửa phiếu đặt</h6>
	</div>
	<div class="row m-2">
		<div id="leftSide" class="card shadow col-md-4 p-0 animate-left mb-3">
			<div id="roomResult"></div>
		</div>
		<div id="rightSide" class="col-md-7 offset-md-1 p-0 animate-right ">
			<div id="customerResult"></div>
			<div class="card shadow col-md-12 mb-3 p-0"
				style="min-height: 200px;">

				<nav class=" navbar navbar-expand navbar-light bg-light">
					<div>Thông tin phiếu đặt - 
						<c:if test="${orderModel.statusId eq 6 || orderModel.statusId eq 1}">
							<p class="badge badge-success mb-0 pl-3 pr-3">Đã thanh toán</p>
						</c:if>
						<c:if test="${orderModel.statusId ne 6 && orderModel.statusId ne 1}">
							<p class="badge badge-primary mb-0 pl-3 pr-3">${orderStatusMap[orderModel.statusId]}</p>
						</c:if>
					</div>
					<c:if test="${isAllowPaid or isAllowAccept or isAllowCancel}">
						<ul class="navbar-nav ml-auto">
							<li class="nav-item dropdown show"><a
								class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="true"> Chọn </a>
								<div
									class="dropdown-menu dropdown-menu-right animated--grow-in show"
									aria-labelledby="navbarDropdown">

									<c:if test="${isAllowPaid}">
										<a class="dropdown-item"
											href="<c:url value='/quan-tri/phieu-dat/cap-nhat-trang-thai'>
        <c:param name="orderId" value="${orderModel.id}"/>
        <c:param name="statusId" value="1"/>
    </c:url>">
											Hoàn thành </a>
									</c:if>

									<c:if test="${isAllowAccept}">
										<a class="dropdown-item"
											href="<c:url value='/quan-tri/phieu-dat/cap-nhat-trang-thai'>
        <c:param name="orderId" value="${orderModel.id}"/>
        <c:param name="statusId" value="5"/>
    </c:url>">
											Duyệt </a>
									</c:if>
									<div class="dropdown-divider"></div>
									<c:if test="${isAllowCancel}">

										<a class="dropdown-item"
											href="<c:url value='/quan-tri/phieu-dat/cap-nhat-trang-thai'>
        <c:param name="orderId" value="${orderModel.id}"/>
        <c:param name="statusId" value="3"/>
    </c:url>">
											Hủy </a>
									</c:if>
								</div></li>

						</ul>
					</c:if>
				</nav>
				<div class="card-body p-3">
					<form:form action="${saveOrder }" modelAttribute="orderModel"
						method="post">
						<form:hidden path="id" />
						<form:hidden path="roomId" id="roomId" />
						<form:hidden path="customerId" />
						<form:hidden path="statusId" />
						<input type="hidden" value="${orderModel.employeeId }"
							name="employeeId" />
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Ngày nhận phòng <span class="text-danger">*</span></label>
								<form:input type="date" path="checkinDate" id="checkinDate"
									cssClass="form-control inputPayed" onchange="calculateTotalPrice()" />
								<form:errors path="checkinDate" cssClass="mt-2"
									cssStyle="color:#e74a3b; padding-top:10px" />
								<span id="errorMessageCheckinDate"
									style="color: #e74a3b; padding-top: 10px;"></span>
							</div>
							<div class="form-group col-md-6">
								<label>Ngày trả phòng <span class="text-danger">*</span></label>
								<form:input type="date" path="checkoutDate" id="checkoutDate"
									cssClass="form-control inputPayed" onchange="calculateTotalPrice()" />
								<form:errors id="errorMessageCheckoutDate" path="checkoutDate"
									cssClass="mt-2" cssStyle="color:#e74a3b; padding-top:10px" />
								<span id="errorMessageCheckouDate"
									style="color: #e74a3b; padding-top: 10px;"></span>
							</div>
						</div>
						<div class="form-group ">
							<label>Khuyến mãi</label>
							<div class="d-flex  align-items-center">
								<form:select id="promotionId" path="promotionId" 
									cssClass="form-control selectPromotion" onchange="updateTotalPrice()">
									<form:option value="0" label="Không áp dụng..." />
									<form:options items="${promotions }" />
								</form:select>
								<form:errors path="promotionId" cssClass="mt-2"
									cssStyle="color:#e74a3b; padding-top:10px" />
							</div>
						</div>

						<%-- <div class="form-group">
							<label>Trạng thái</label>
							<div class="d-flex align-items-center ">

								<c:if test="${orderModel.statusId eq 6 }">
									<p class="form-control mb-0">Đã thanh toán</p>
								</c:if>
								<c:if test="${orderModel.statusId ne 6 }">
									<p class="form-control mb-0">${orderStatusMap[orderModel.statusId]}</p>
								</c:if>
							</div>
						</div> --%>

						<div class="form-group ">
							<label>Số lượng</label>
							<form:input path="quantity" type="number"
								cssClass="form-control inputPayed" />
							<form:errors path="quantity" cssClass="mt-2"
								cssStyle="color:#e74a3b; padding-top:10px" />
						</div>
						<div class="form-group ">
							<label class="col-md-12 m-0 p-0">Thành tiền: </label>
							<div >
								<!-- Hiển thị giá cũ và giá mới -->
								<p id="oldPriceContainer" style="display: none;">
									Giá cũ:
									<form:label path="oldPrice" id="newPrice"></form:label>
								</p>
								<p id="newPriceContainer" style="display: none;">
									Giá mới:
									<form:label path="newPrice" id="oldPrice"></form:label>
								</p>
								<!-- Hiển thị tổng giá sau khi đổi phòng -->

								<input path="formatTotalPrice" id="formatTotalPrice" readonly="readonly"
									class="form-control p-0 inputTotal pl-2" style="border: none;" />
								<form:hidden path="totalPrice" id="totalPrice" />

							</div>



						</div>
						<div class="d-flex justify-content-between align-items-start pt-3">
							<div>
								<p style="font-size:14px; color:red">
									<i class="fa-solid fa-circle-exclamation pr-2"></i> Có thể đổi
									ngày khi khách chưa nhận phòng!
								</p>
								<p style="font-size:14px;color:red">
									<i class="fa-solid fa-circle-exclamation pr-2"></i> Có thể đổi phòng khi khách đang sử dụng!
								</p>
								<!-- xổ ra -->
								<!-- <ul class="navbar-nav ml-auto ">
									<li class="nav-item dropdown show"><a
										class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
										role="button" data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="true"><p class="badge badge-danger p-2">
												<i class="fa-solid fa-circle-exclamation"></i> Lưu ý</p></a>
										<div class="dropdown-menu p-2" style="margin-top:-10px;width:350px"
											aria-labelledby="navbarDropdown">
											<p style="font-size:14px; color:red">
												<i class="fa-solid fa-circle-exclamation"></i> Có thể đổi
												ngày khi khách chưa nhận phòng!
											</p>
											<p style="font-size:14px;color:red">
												<i class="fa-solid fa-circle-exclamation"></i> Có thể đổi phòng khi khách đang sử dụng!
											</p>
											
											
										</div></li>

								</ul> -->
							</div>
							<div class="d-flex justify-content-end mb-3 mr-3">
								<c:if
									test="${orderModel.statusId ne 1 && orderModel.statusId ne 6}">
									<button type="submit" class="btn btn-primary mr-2" onclick="prepareForm()">Lưu
										dữ liệu</button>
								</c:if>

								<a href="<c:url value='/quan-tri/phieu-dat'/>"
									class="btn btn-secondary">Quay lại</a>
							</div>
						</div>

					</form:form>
				</div>
			</div>
		</div>
		<div class="col-md-3 collapse shadow ml-3 mb-3" id="collapseRoom"
			aria-labelledby="headingTwo" data-parent="#accordionSidebar">
			<div class="card-header">Danh sách phòng</div>
			<div class="list-room d-flex flex-wrap">
				<!-- Sử dụng d-flex và flex-wrap thay thế cho row -->
				<c:forEach items="${listRoom}" var="item">
					<hr class="sidebar-divider">
					<div id="room" class="item-room card mb-3 col-md-12">
						<input id="roomIdHidden" type="hidden" name="id"
							value="${item.id}" /> <img src="<c:url value='${item.image }'/>"
							class="card-img-top" alt="...">
						<div class="card-body pl-2">
							<h5 class="text-center">${item.getName()}</h5>
							<div class="dropdown-divider"></div>
							<h6>
								Giá phòng: <span class="text-danger font-weight-bolder price-format">${item.getPrice()}</span>
							</h6>
							<h6>
								Loại phòng: <span class="text-primary">${item.getTypename()}</span>
							</h6>
							<h6>
								Trạng thái: <span class="text-primary">${item.getStatusname()}</span>
							</h6>
							<h6></h6>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>

	</div>
</div>
<div class="modal fade" id="confirmChangeRoomModal" tabindex="-1"
	role="dialog" aria-labelledby="confirmChangeRoomModalLabel"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="confirmChangeRoomModalLabel">Xác
					nhận đổi phòng</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close" onclick="hideModal();">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">Bạn có chắc chắn muốn đổi phòng không?
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal"
					onclick="hideModal();">Đóng</button>
				<button type="button" class="btn btn-primary"
					id="confirmChangeRoomBtn">Xác nhận</button>
			</div>
		</div>
	</div>
</div>

<c:if test="${orderModel.statusId eq 1 || orderModel.statusId eq 6}">
	<!-- Điều kiện này đúng -->
	<script>
	var selectElement = document.getElementById('promotionId');
    
    // Vô hiệu hóa phần tử select
    selectElement.disabled = true;
    document.addEventListener('DOMContentLoaded', function() {
        // Lấy tất cả các phần tử có lớp inputPayed
        var inputs = document.getElementsByClassName('inputPayed');
        var inputTotal = document.getElementsByClassName('selectPromotion');
        
        //input thì readonly còn select là disabled
        
        // Duyệt qua từng phần tử và thay đổi thuộc tính
        for (var i = 0; i < inputs.length; i++) {
            var input = inputs[i];
            // Thêm thuộc tính readonly và đổi type thành text
            input.setAttribute('readonly', 'readonly');
            input.setAttribute('type', 'text');
        }
    });
    </script>
</c:if>

<script>
		var isChangeRoom = false;
		var isCollapse = false;
		//Mức giảm giá nếu có giảm giá
		var oldPromotionLevel = 0; 
		//Giá cũ mà có giảm giá
		var oldPromotionPrice = ${orderModel.getTotalPrice()};
		//Giá trước khi giảm giá
		var oldPrice;
		var roomId;
		var rooms = document.querySelectorAll('.item-room');
		var roomId;
		function formatMoney(number) {
		    return number.toLocaleString('vi-VN') + ' đ';
		}
		function prepareForm() {
            // Enable all disabled inputs before submitting the form
            $("#checkinDate").prop("disabled", false);
            $("#checkoutDate").prop("disabled", false);
            $("#promotionId").prop("disabled", false);

            // Submit the form
            document.getElementById('myForm').submit();
        }
		function showCollapse() {
			var leftSide = document.getElementById('leftSide');
			var rightSide = document.getElementById('rightSide');
			var collapseBtn = document.getElementById('collapseBtn')
			if (isCollapse == false) {
				leftSide.classList.remove("col-md-4")
				rightSide.classList.remove("col-md-7")
				rightSide.classList.remove("col-offset-1")
				leftSide.classList.add("collapsed");
				rightSide.classList.add("collapsed");

				leftSide.classList.add("col-md-3")
				rightSide.classList.add("col-md-5")
				rightSide.classList.add("ml-3")

				collapseBtn.style.color = '#1cc88a';
				isCollapse = !isCollapse;
			} else {
				leftSide.classList.remove("col-md-3")
				rightSide.classList.remove("col-md-5")
				rightSide.classList.remove("ml-3")
				leftSide.classList.remove("collapsed");
				rightSide.classList.remove("collapsed");

				leftSide.classList.add("col-md-4")
				rightSide.classList.add("col-md-7")
				rightSide.classList.add("col-offset-1")

				collapseBtn.style.color = '#858796';
				isCollapse = !isCollapse;
			}
		}
		
		rooms.forEach(function(room) {
			room.addEventListener('click', function() {
				// Xóa lớp border-primary từ tất cả các phòng
				rooms.forEach(function(room) {
					room.classList.remove('border-primary');
				});

				// Thêm lớp border-primary cho phòng được chọn
				room.classList.add('border-primary');

				// Cập nhật giá trị cho phần tử input ẩn
				roomId = room.querySelector('input[name="id"]').value;
				// Hiển thị modal khi phòng được chọn
				showModal();
				// Thêm phần tử div vào trước phần tử modal-backdrop hiện có
				document.body.insertBefore(backdropElement, document
						.querySelector('.modal-backdrop'));
			});
		});

		document.getElementById('confirmChangeRoomBtn').addEventListener(
				'click',
				function() {				
					//set value cho roomId
					$('#roomId').val(roomId)
					roomId = $('#roomId').val();
					
					
					//Gọi action đổi thông tin phòng
					changeRoom(roomId);
					//Set lại true cho isChangeRoom
					isChangeRoom = true;
					
					 hideModal();
				});
		 $(document).ready(function () {
			 checkDatesOnLoad();
			 getOldPromotion();
			 getOldPrice();
			 formatTotalPrice();
			// Initially load search results
		    changeRoom(${orderModel.roomId}); 
		    renderCustomer();
		    });
		function hideModal(){
			// Đóng modal sau khi hoàn thành hành động
			$('#confirmChangeRoomModal').removeClass('show d-block');
			// Lấy phần tử backdrop cần loại bỏ
			var backdropElement = document
			.querySelector('.modal-backdrop');
			// Xóa phần tử backdrop khỏi DOM
			backdropElement.parentNode.removeChild(backdropElement);
		}
		function showModal(){
			// Hiển thị modal khi phòng được chọn
			$('#confirmChangeRoomModal').addClass('show d-block');
			var backdropElement = document.createElement('div');
			backdropElement.classList.add('modal-backdrop');
			backdropElement.classList.add('fade');
			backdropElement.classList.add('show');

			// Thêm phần tử div vào trước phần tử modal-backdrop hiện có
			document.body.insertBefore(backdropElement, document
					.querySelector('.modal-backdrop'));
		}
		function renderCustomer(){
			$.ajax({
				url: '/hotel/quan-tri/phieu-dat/khach-hang?customerId= '+ ${orderModel.getCustomerId()},
	            type: 'GET',
	            error: function () {
	                alert("Xử lý thất bại khi!");
	            },
	            success: function (data) {
	                $('#customerResult').html(data);
	                
	                }
	        
	           
			 });
		}
		function changeRoom(id) {
			$.ajax({
				url: '/hotel/quan-tri/phieu-dat/doi-phong?newId=' + id + '&oldId=' + ${orderModel.getRoomId()},
	            type: 'GET',
	            error: function () {
	                alert("Đổi phòng thất bại!");
	            },
	            success: function (data) {
	                $('#roomResult').html(data);
	                if(isChangeRoom == true){
	                	 // Lấy giá cũ và giá mới từ dữ liệu trả về
	                    const oldRoomPrice = $('#newPriceHidden').val();
	                    const newRoomPrice = $('#oldPriceHidden').val();
	                    const totalPrice = $('#totalPriceHidden').val();
	                    
	                    oldPrice = totalPrice;
	                    
	                    oldPromtionPrice = $('#totalPriceHidden').val();
	                    console.log("Giam gia goc doi phong: " + oldPromotionLevel)
	                    console.log("Gia cu: " + oldRoomPrice );
	                    console.log("Gia moi: " + newRoomPrice );
	                    // Hiển thị giá cũ và giá mới
	                    $('#oldPrice').text(oldRoomPrice);
	                    $('#newPrice').text(newRoomPrice);
	                   
	                    
	                    console.log("Old price: " + oldPrice)
	                    // Hiển thị tổng giá sau khi đổi phòng
	                    $('#totalPrice').val(totalPrice - (totalPrice * oldPromotionLevel));
	                    formatTotalPrice();
	                    if(oldRoomPrice != 0 && newRoomPrice !=0){
			                 // Hiển thị container chứa giá cũ và giá mới
			                    $('#oldPriceContainer').show();
			                    $('#newPriceContainer').show();
		                    }
	                    isChangeRoom = false;	
	                }
	                checkOrderStatusAndCheckoutDate();
	            }
	        });
		}
		
		function checkOrderStatusAndCheckoutDate() {
		    var status = ${orderModel.statusId}; // Trạng thái đơn đặt hàng
		    var currentDate = new Date(); // Ngày hiện tại
		    var checkoutDate =new Date($('#checkoutDate').val());
		    var checkinDate = new Date($('#checkinDate').val());
			
		   
		    if (status == 1 || checkoutDate.getTime() <= currentDate.getTime() || checkinDate.getTime() >= currentDate.getTime()) {
		    	console.log("vo dc roi ne")
		    	// Ẩn nút đổi phòng
		        var collapseBtn = document.getElementById('collapseBtn');
		    	  console.log(collapseBtn)
		        if (collapseBtn) {
		            collapseBtn.style.display = 'none'; // Ẩn nút nếu tồn tại
		        }
		    }
		}
		//<----Xử lý giá--->
		//Cập nhật lại giá khi chọn giảm giá
		function updateTotalPrice() {
		    // Lấy văn bản của phần tử được chọn
		    var promotionText = $('#promotionId option:selected').text();
		    console.log(promotionText);
		  
		    // Sử dụng regular expression để tách số từ chuỗi văn bản
		    var myArray = promotionText.split(" - ");
			
		    // Kiểm tra nếu mảng có ít nhất 2 phần tử (nghĩa là chuỗi được tìm thấy)
		    if (myArray.length >= 2) {
		        // Chuyển đổi số thành dạng số nguyên
		        var promotionLevel = parseInt(myArray[1], 10) / 100;
		        const isSameType = ${isSameType} ;
		        if(isSameType != null){
		        	  $('#totalPrice').val(oldPrice - (oldPrice * promotionLevel));
		        }
		      
		       	oldPromotionLevel = promotionLevel;
		        // In ra số đã tìm được
		        console.log("Giam gia goc: " + oldPromotionLevel); // Kết quả sẽ là 0.1 (ví dụ: nếu chuỗi là "10%")
		    } else {
		            $('#totalPrice').val(oldPrice);
		            console.log("Không tìm thấy số trong chuỗi." + oldPrice);
		            isUpdateOldPrice = true;
		            oldPromotionLevel = 0;
		       
		    }
		    formatTotalPrice();
		}
		
		//Lấy giá cũ 
		function getOldPrice(){
			 const price = parseFloat(oldPromotionPrice / (1 - oldPromotionLevel));
			 oldPrice = price;
		}
		//Lấy mức giảm giá cũ
		function getOldPromotion(){
			var promotionText = $('#promotionId option:selected').text();
			  var myArray = promotionText.split(" - ");
			  if (myArray.length >= 2) {
				  var promotionLevel = parseInt(myArray[1], 10) / 100;
				  oldPromotionLevel = promotionLevel;
				  console.log("Gia tri khuyen mai ban dau la: " + oldPromotionLevel)
			  }
			  else{
				  oldPromotionLevel = 0;
				  console.log("Gia tri khuyen mai ban dau la: " + oldPromotionLevel)
			  }
			  
		}// Hàm kiểm tra ngày khi tải trang
        function checkDatesOnLoad() {
            var checkinDate = document.getElementById("checkinDate").value;
            var checkoutDate = document.getElementById("checkoutDate").value;
            var currentDate = new Date();

            if (checkinDate) {
                var startDate = new Date(checkinDate);

                if (startDate < currentDate) {
                    // Disable inputs nếu ngày nhận phòng đã qua
                    $("#checkinDate").prop("disabled", true);
                    $("#checkoutDate").prop("disabled", true);
          
                } else {
                    // Enable inputs nếu ngày nhận phòng hợp lệ
                    $("#checkinDate").prop("disabled", false);
                    $("#checkoutDate").prop("disabled", false);
                    $('#errorMessageCheckinDate').text("");
                }
            }
        }

        
		//Note
		function calculateTotalPrice() {
			var roomPrice = parseFloat($('#roomPrice').text().trim());
			console.log("Gia phong: " + roomPrice)
			var checkinDate = document.getElementById("checkinDate").value;
	        var checkoutDate = document.getElementById("checkoutDate").value;

	        if (checkinDate && checkoutDate) {
	            var startDate = new Date(checkinDate);
	            var endDate = new Date(checkoutDate);
	            var currentDate = new Date();
	            var timeDifference = endDate - startDate;
	            var dayDifference = timeDifference / (1000 * 3600 * 24);
	            
	         	//Xóa lỗi
	            $('#errorMessageCheckinDate').text("");
	            $('#errorMessageCheckoutDate').text("");

	            if (startDate < currentDate) {
	                $('#errorMessageCheckinDate').text("Ngày nhận phòng không thể ở quá khứ");
	                $("#checkinDate").val("");
	            }
	            else if (dayDifference >= 0) {
	                var totalPrice = dayDifference * roomPrice;
	                //Cập nhật lại oldPrice 
					oldPrice = totalPrice - (totalPrice * oldPromotionLevel );
	                console.log("calculateTotalPrice: " + oldPrice )
	                // Cập nhật giá vào các phần tử HTML
	                document.getElementById("formatTotalPrice").value = oldPrice.toLocaleString('vi-VN') + ' đ';
	                document.getElementById("totalPrice").value = oldPrice;
	                //Xóa hiển thị lỗi
	                $('#errorMessageCheckinDate').text("");
	                $('#errorMessageCheckoutDate').text("");
	            } else {
	            	console.log("E0");
	            	 if (startDate.getTime() >= endDate.getTime()) {
	                     console.log("E1");
	                     $('#errorMessageCheckinDate').text("Ngày nhận phòng phải trước ngày trả phòng");
	                   
	                 } else {
	                     console.log("E2");
	                     $('#errorMessageCheckoutDate').text("Ngày trả phòng phải sau ngày nhận phòng");
	      	           
	                 }
	               
	               $("#formatTotalPrice").val("0");
	               $("#totalPrice").val("0");
	            }
	        }
		}
		//Format tong gia tien
		function formatTotalPrice(){
			//Format lại totalPrice
	        let price = formatMoney(parseFloat($('#totalPrice').val()))
			 $('#formatTotalPrice').val(price);
	        let roomPrice = formatMoney(parseFloat($('#roomPrice').text()))
			 $('#roomPrice').val(price);
	        $('.price-format').each(function() {
	        	console.log($(this).text());
	            // Lấy giá trị từ thuộc tính data-price
	            let price = parseFloat($(this).text());
	            // Format lại giá trị
	            let formattedPrice = formatMoney(price);
	            // Cập nhật nội dung của phần tử
	            $(this).text(formattedPrice);
	        });
		}
		function formatMoney(number) {
		    return number.toLocaleString('vi-VN') + ' đ';
		}
		
	</script>