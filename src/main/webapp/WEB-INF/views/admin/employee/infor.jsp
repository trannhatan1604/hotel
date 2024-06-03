<%@page import="com.hotel.util.SecurityUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/common/taglib.jsp"%>
<style>
.gradient-custom {
	/* fallback for old browsers */
	background: #f6d365;
	/* Chrome 10-25, Safari 5.1-6 */
	background: -webkit-linear-gradient(to right bottom, rgb(78, 115, 223),
		rgba(253, 160, 133, 1));
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
	background: linear-gradient(to right bottom, rgb(78, 115, 223),
		rgb(78, 115, 81))
}
</style>
<c:url value='/quan-tri/thong-tin/doi-mat-khau' var="changePassword"/>

<c:if test="${not empty message}">

	<div class="alert alert-${alert} position-fixed mx-auto" style="top: 30px; left: 50%" role="alert">
		<button type="button" class="close ml-3" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		${message}
	</div>
</c:if>

<section class="vh-100 py-5" style="background-color: #f4f5f7;">
		
	<div class="container">
		
			<div class="row d-flex justify-content-center align-items-center h-100">
				<div class="col col-lg-10 mb-6 mb-lg-0">
					<div class="card mb-3" style="border-radius: .5rem;">
						<div class="row g-0">
							<div class="col-md-4 gradient-custom text-center text-white"
								style="border-top-left-radius: .5rem; border-bottom-left-radius: .5rem;">
								<img src="<c:url value='${model.getImage() }' />" alt="Avatar"
									class="img-fluid my-5"
									style="width: 120px; heigth: 120px; border-radius: 50%;" />
								<h5>${model.getFullName() }</h5>
								<p>Nhân Viên</p>
								<a
									href="<c:url value="/quan-tri/nhan-vien/chinh-sua?id=${SecurityUtils.getPrincipal().getId() }"/>">
									<i class="far fa-edit mb-5" style="color: #fff"></i>
								</a>
							</div>
							<div class="col-md-8">
								<div class="card-body p-4">
									<h6>Thông tin cá nhân</h6>
									<hr class="mt-0 mb-4">
									<div class="row pt-1">
										<div class="col-6 mb-3">
											<h6>
												<i class="fa-regular fa-address-book"></i> Địa chỉ
											</h6>
											<p class="text-muted">${model.getAddress() }</p>
										</div>
										<div class="col-6 mb-3">
											<h6>
												<i class="fa-solid fa-phone"></i> Số điện thoại
											</h6>
											<p class="text-muted">${model.getPhone() }</p>
										</div>
									</div>
									<hr>
									<div>
									<form action="${changePassword }" method="post" data-container="#searchResult" id="formSearch">
										<div id="searchResult"></div>
									</form>
									</div>
										
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

	</div>
</section>
<script>
    
    $(document).ready(function () {
        $("#formSearch").submit(function (e) {
            e.preventDefault();
            doSearch(this);
        });
        // Initially load search results
        doSearch("#formSearch");
    });

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
    window.setTimeout(function() {
		$(".alert").fadeTo(300, 0).slideUp(300, function() {
			$(this).remove();
		});
	}, 1500);
</script>