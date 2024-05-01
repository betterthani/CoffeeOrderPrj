<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Coffee</title>
<!-- jquery : bootstrap, datepicker -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>  

<!-- bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<!-- 아이콘 모양 부트스트랩 -->
<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
<style>
.contents {
	min-height: 500px;
}

.edit-box {
	width: 450px;
}

.imgBox {
	width: 200px;
	height: 200px;
}

.buttonBox {
	display: flex;
	flex-wrap: wrap;
	flex-direction: column; /*수직 정렬*/
	align-items: center;
	justify-content: center;
}
</style>
</head>
<body>
<div class="d-flex justify-content-center w-100 my-3">
	<div class="edit-box">
		<!-- 이미지 박스 -->
		<div class="d-flex justify-content-center">
			<img alt="사진" src="/assets/img/portfolio/profileImg.png" class="imgBox">
		</div>
		<form action="/coffee/list/a" method="post" enctype="multipart/form-data">
			<!-- 파일 선택 버튼 -->
			<div class="d-flex justify-content-end">
				<button class="btn-sm btn-success pictureBtn">사진 선택</button>
				<input type="file" name="coffeeImage" id="file" class="d-none" accept=".gif, .jpg, .png, .jpeg">
				<div id="fileName" class="ml-2"></div>
			</div>
			
			<!-- input박스 -->
			<div class="d-flex justify-content-center">
				<div class="w-75 my-3">
					<input type="text" id="coffeeName" name= "coffeeName" class="form-control name" placeholder="커피 이름을 입력해주세요.">
					<input type="text" id="kcalInfo"  name= "kcalInfo" class="form-control mt-3 statusMessage" placeholder="칼로리 등 정보를 입력해주세요.">
					<input type="text" id="amount"  name= "amount" class="form-control mt-3 " placeholder="가격을 입력해주세요.">
					<select id="category"  name="category" class="form-control mt-3 " >
			            <option value="coffee">coffee</option>
			            <option value="nonCoffee">non-coffee</option>
			        </select>
				</div>
			</div>
			
			<!-- 버튼 -->
			<div class="buttonBox my-2">
				<button type="submit" class="btn btn-success w-75" id="coffeeInsertBtn">커피 등록</button>
			</div>
		</form>
	</div>
</div>

<script>
	$(document).ready(function(){
		// 사진 선택
		$('.pictureBtn').on('click',function(e){
			e.preventDefault();
			$('#file').click();
		});
		
		// 이미지 선택시 사진 보여지기
		 $('#file').on('change',function(){
			 setImageFromFile(this, '.imgBox');
		 });
		 function setImageFromFile(input, expression) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
			    reader.onload = function (e) {
			    $(expression).attr('src', e.target.result);
				}
			reader.readAsDataURL(input.files[0]);
			}
		}
		 
		// 커피 등록 버튼
		$('#coffeeInsertBtn').on("click",function(){
			let coffeeName = $('#coffeeName').val();
			let kaclInfo = $('#kaclInfo').val();
			let amount = $('#amount').val();
			let category = $('#category').val();
			
			if(coffeeName == ''){
				alert("커피 이름 입력해주세요.");
				return false;
			}
			if(kaclInfo == ''){
				alert("정보 입력해주세요.");
				return false;
			}
			if(amount == ''){
				alert("가격 입력해주세요.");
				return false;
			}
			if(category == ''){
				alert("카테고리 입력해주세요.");
				return false;
			}
			
		});// 커피 등록 버튼 끝
		
	});//->document끝
 </script>
</body>