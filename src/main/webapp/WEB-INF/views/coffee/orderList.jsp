<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
    	<base href="..\" >
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Freelancer - Start Bootstrap Theme</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body id="page-top">
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg bg-secondary text-uppercase fixed-top" id="mainNav">
            <div class="container">
                <!-- <a class="navbar-brand" href="#page-top">Coffee Saltux</a> -->
                <img class="navbar-brand" src="assets/img/portfolio/5_logo_long_white.png" alt="..."  width="250" />
                <!-- <button class="navbar-toggler text-uppercase font-weight-bold bg-primary text-white rounded" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    Menu
                    <i class="fas fa-bars"></i>
                </button> -->
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ms-auto">
                        <!-- <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="#portfolio">Portfolio</a></li> -->
                        <!-- <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="#about">About</a></li>
                        <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="#contact">login</a></li> -->
                    </ul>
                </div>
            </div>
        </nav>
        <!-- Masthead-->
        <header class="masthead bg-primary text-black text-center bg-opacity-25" >
            <div class="container d-flex align-items-center flex-column">
                <!-- Masthead Avatar Image-->
                <img class="masthead-avatar mb-5" src="../../../assets/img/receipt.png" alt="..." />
                <!-- Masthead Heading-->
                <h1 class="masthead-heading text-uppercase mb-0" style="font-size: 40px;">${employeeNumber}님의 커피 주문내역 입니다.</h1>
                <!-- Icon Divider-->
                <div class="divider-custom divider-light">
                    <div class="divider-custom-line "></div>
                    <div class="divider-custom-icon "><i class="fas fa-star"></i></div>
                    <div class="divider-custom-line "></div>
                </div>
                <!-- Masthead Subheading-->

                <h2 class="page-section-heading text-center text-uppercase text-black mb-0">주문 리스트</h2>
                <div class="col-lg-4 mb-5 mb-lg-0">
                </div>
                <br>
                <table>
                    <thead>
                    <tr>
                        <th><h4 class="text-uppercase mb-4">주문 번호</h4></th>
                        <th><h4 class="text-uppercase mb-4">주문 커피</h4></th>
                        <th><h4 class="text-uppercase mb-4">주문 수량</h4></th>
                        <th><h4 class="text-uppercase mb-4">샷 추가</h4></th>
                        <th><h4 class="text-uppercase mb-4">주문 시간</h4></th>
                        <!-- <th>주문 커피</th>
                        <th>주문 수량</th>
                        <th>샷 추가</th>
                        <th>주문시간</th> -->
                    </tr>
                    </thead>
                    <tbody id="buyList">
            
                    <c:forEach var="order" items="${buyList}">
                        <tr>
                            <td>${order.BUY_ID}</td>
                            <td>${order.BUY_MENU}</td>
                            <td>${order.BUY_COUNT}</td>
                            <td>${order.ADD_SHOT}</td>
                            <td>
                                <fmt:formatDate value="${order.BUY_TIME}" type="both" />
                            </td>
                            
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <br>
                <br>
                <br>

                <button class="btn btn-primary btn-xl " id="addButton" type="submit" style="float: right; margin-right: 5px; margin-left: 5px;" onclick="location.href='/coffee/list'"><i class="fas fa-backward fa-fw"></i>주문페이지 돌아가기</button>
            </div>
        </header>
        <!------------------------------------------- 아래 배너----------------------------------------->

        <!-- Copyright Section-->
        <div class="copyright py-4 text-center text-white">
            <div class="container"><small>Copyright &copy; Saltux Website 2024</small></div>교육 프로그램
        </div>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <!-- * *                               SB Forms JS                               * *-->
        <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
    </body>
</html>