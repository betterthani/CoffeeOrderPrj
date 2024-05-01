<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>커피 주문 완료</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        h1, h2 {
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>${employeeNumber}님의 커피 주문이 완료되었습니다.</h1> <hr>
    <h2>주문 리스트</h2>

    <table>
        <thead>
        <tr>
            <th>주문 번호</th>
            <th>주문 커피</th>
            <th>주문 수량</th>
            <th>샷 추가</th>
        </tr>
        </thead>
        <tbody id="buyList">

        <c:forEach var="order" items="${buyList}">
        <tr>
            <td>${order.BUY_ID}</td>
            <td>${order.BUY_MENU}</td>
            <td>${order.BUY_COUNT}</td>
            <td>${order.ADD_SHOT}</td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
    
    <a href="/coffee/list"><button type="button">뒤로가기</button></a>
</div>

</body>
</html>
