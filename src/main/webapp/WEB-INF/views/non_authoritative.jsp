<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<style>
@keyframes pulse {
  0% {
    border-color: transparent;
  }
  50% {
    border-color: red;
  }
  100% {
    border-color: transparent;
  }
}
</style>

<div class="container">
    <div class="row">
        <div class="col-md-6 mx-auto" >
        	<img class="rounded mx-auto d-block " style="width: 200px; margin:30px; animation: pulse 2s infinite; border:5px solid; padding:10px" src="<c:url value='/template/admin/img/warning.png'/>"/>
        </div>
    </div>
    <div class="row">
        <div class="col-md-5 mx-auto">
        	<text style="color:red; font-size:30px " class="font-weight-bold">Bạn không có quyền truy cập</text>
        </div>
    </div>
</div>