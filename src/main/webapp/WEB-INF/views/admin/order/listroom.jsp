<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:url value="/renderTotalPrice" var="renderTotalPrice" />
<c:url value="/updateTotalPrice" var="updateTotalPrice" />

<c:if test="${empty roomModel.getListResult()}">
	<div class="alert alert-info" role="alert">Không tìm thấy phòng
		nào.</div>
</c:if>
<c:if test="${not empty roomModel.getListResult()}">
	<h2 class="mb-3 font-weight-bold">Danh sách phòng</h2>
	<div class="list-room ">
		<c:forEach items="${roomModel.getListResult()}" var="room">

			<div id="room" class="item-room card">
				<div class="booking-periods" id="booking-periods-${room.id}">
					<table class="table table-bordered table-striped">
						<thead class="thead-light">
							<tr>
								<th scope="col" class="col-6">Ngày nhận phòng</th>
								<th scope="col" class="col-6">Ngày trả phòng</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="i" begin="0"
								end="${fn:length(room.listCheckinDates) > 0 ? fn:length(room.listCheckinDates) - 1 : 0}">
								<tr>
									<td class="col-6 date-cell">${room.listCheckinDates[i]}</td>
									<td class="col-6 date-cell">${room.listCheckoutDates[i]}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				
				<input id="roomId" type="hidden" name="id" value="${room.id }" /> <img style="object-fit: cover;width:100%;height:250px"
					src="<c:url value="${room.image }"/>"
					class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="text-center">${room.getName() }</h5>
					<div class="dropdown-divider"></div>
					<h6>
						Giá phòng: <span class="text-danger font-weight-bolder">${room.getPrice() }</span>
					</h6>
					<h6>
						Loại phòng: <span class="text-primary">${room.getTypename() }</span>
					</h6>
					<h6>
						Trạng thái: <span class="text-primary">${room.getStatusname() }</span>
					</h6>


				</div>
			</div>
		</c:forEach>


	</div>
	<div class="d-flex justify-content-center my-3">
		<div class="btn btn-sm btn-primary mr-1"
			onclick="doSearch('#formSearch', ${roomModel.page - 1})">
			<i class="fa-solid fa-backward"></i>
		</div>
		<div class="btn btn-sm btn-primary ml-1"
			onclick="doSearch('#formSearch', ${roomModel.page + 1})">
			<i class="fa-solid fa-forward"></i>
		</div>
	</div>
</c:if>
<script>
$(document).ready(function() {
    // Lặp qua từng ô td có class là date-cell
    $('.date-cell').each(function() {
        var dateString = $(this).text().trim(); // Lấy ngày từ nội dung của td
        if (isPastDate(dateString)) {
            $(this).addClass('past-date');
        } else {
            $(this).addClass('future-date');
        }
    });

    // Hàm kiểm tra ngày có phải là ngày quá khứ hay không
    function isPastDate(dateString) {
        const today = new Date();
        const dateToCheck = new Date(dateString);

        return dateToCheck < today;
    }
});
$("#totalPage").val(${roomModel.getTotalPage()});
var div = document.getElementsByClassName('item-room');
for (let i = 0; i < div.length; i++) {
    div[i].addEventListener('click', (event) => {
    	var roomId = event.target.closest('.item-room').querySelector('input[name="id"]').value;
        removeClass()
        div[i].classList.add('border', 'border-primary')
        $('#formRoomId').val(roomId);
       
    });
}

function removeClass() {
    for(let i = 0; i < div.length; i++){
        div[i].classList.remove('border', 'border-primary')
    }
}


function showBookingPeriods(roomId) {
    document.getElementById('booking-periods-' + roomId).style.display = 'block';
}

function hideBookingPeriods(roomId) {
    document.getElementById('booking-periods-' + roomId).style.display = 'none';
}


</script>
