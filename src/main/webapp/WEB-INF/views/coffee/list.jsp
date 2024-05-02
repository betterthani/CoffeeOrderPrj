<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
    	<base href="..\" >
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>5TRILLION COFFEE</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <!-- jquery -->
        <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body id="page-top" >
    <script type="text/javascript">
    	window.onload = function(){
    		var message = '${message}';
    		if(message){
    			alert(message);
    		}
    	}
    </script>
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg bg-secondary text-dark fixed-top" id="mainNav" style="height: 80px;" >
            <div class="container">
                <!-- <a class="navbar-brand" href="#page-top">Coffee Saltux</a> -->
                <img class="navbar-brand" src="assets/img/portfolio/5_logo_long_white.png" alt="..."  width="250" />
                <button class="navbar-toggler text-uppercase font-weight-bold bg-primary text-white rounded" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    Menu
                    <i class="fas fa-bars"></i>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="/coffee/list?custId=${custId}"><i class="fas fa-coffee"></i>메뉴</a></li>
	                    <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded " href="/coffee/orderList?custId=${custId}"><i class="fas fa-shopping-cart fa-fw"></i>주문</a></li>
	                    <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="/coffee/logout"><i class="fas fa-xmark"></i>로그아웃</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        
        <!-- Coffee menu Section-->
        <section class="page-section portfolio" id="portfolio">
            <div class="container">
                <!-- Portfolio Section Heading-->
                <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">Menu</h2>
                <!-- Icon Divider-->
                <div class="divider-custom">
                    <div class="divider-custom-line"></div>
                    <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                    <div class="divider-custom-line"></div>
                </div>
                <!-- Portfolio Grid Items-->
                <div class="row justify-content-center">
                
                	<!-- <div id="menulist"></div> -->
                	
                    <!-- Portfolio Item 1-->            
                    <c:forEach var="coffee" items="${coffeeList}">
	                    <div class="col-md-6 col-lg-4 mb-5">
	                        <div class="portfolio-item mx-auto" data-bs-toggle="modal" data-bs-target="#portfolioModal${coffee.COFFEE_ID}">
	                            <div class="portfolio-item-caption d-flex align-items-center justify-content-center h-100 w-100">
	                                <div class="portfolio-item-caption-content text-center text-white"><i class="fas fa-plus fa-3x"></i></div>
	                           </div>
	                           <img class="img-fluid" src="${coffee.COFFEE_IMAGE}" />
	                        </div>
	                    </div>
                    </c:forEach>
                </div>
            </div>
            
        </section>
        <!-------------------------------------------------- 메뉴 상세 설명 및 주문---------------------------------------------------->
        <!-- Portfolio Modal 1-->
        <c:forEach var="coffee" items="${coffeeList}">
        <div class="portfolio-modal modal fade" id="portfolioModal${coffee.COFFEE_ID}" tabindex="-1" aria-labelledby="portfolioModal${coffee.COFFEE_ID}" aria-hidden="true">
            <div class="modal-dialog modal-xl">
                <div class="modal-content">
                    <div class="modal-header border-0"><button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button></div>
                    <div class="modal-body text-center pb-5">
                        <div class="container">
                            <div class="row justify-content-center">
                                <div class="col-lg-8">
                                    <!-- Portfolio Modal - Title-->
                                    <h2 class="portfolio-modal-title text-secondary text-uppercase mb-0">${coffee.COFFEE_NAME}</h2>
                                    <!-- Icon Divider-->
                                    <div class="divider-custom">
                                        <div class="divider-custom-line"></div>
                                        <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                                        <div class="divider-custom-line"></div>
                                    </div>
                                    <!-- Portfolio Modal - Image-->
                                    <div>
                                    	<img class="img-fluid rounded mb-5" src="${coffee.COFFEE_IMAGE}" alt="..." width="400"/>
                                    </div>
                                    <!-- Portfolio Modal - Text-->
                                    
                                    <h5 class="text-uppercase mb-3"  style="display:inline;">Category: </h5>
                                    <p style="display:inline; font-size: 22px;" >${coffee.CATEGORY}</p>
                                    
                                    <h5 class="text-uppercase mb-1">kcal: ${coffee.KCAL_INFO}</h5>
                                    
                                    <h5 class="text-uppercase mb-4"style="display:inline;" >menu_price: </h5>
                                    <p class="mb-4"  style="display:inline; font-size: 22px; ">${coffee.AMOUNT} 원</p>
                                    <br>
                                    
                                    <div class="d-flex justify-content-center">
	                                    <form action="/coffee/buy/a?coffeeId=${coffee.COFFEE_ID}&custId=${custId}" method="post">
		                                    <button type="submit" class="btn btn-primary" data-bs-dismiss="modal" id="${coffee.COFFEE_ID}">
	                                    </form>
	                                        <i class="fas fa-shopping-cart fa-fw"></i>
	                                        	주문하기
	                                    <c:if test="${role eq 'ROLE_ADMIN'}">
		                                    <form action="/coffee/info/c?coffeeId=${coffee.COFFEE_ID}" method="post">
			                                    <button type="submit" class="btn btn-primary" data-bs-dismiss="modal" id="${coffee.COFFEE_ID}">
		                                    </form>
		                                        <i class="fas fa-shopping-cart fa-fw"></i>
		                                        	삭제하기
	                                    </c:if>
                                    </div>
                                       	
                                     
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </c:forEach>

		<c:if test="${role eq 'ROLE_ADMIN'}">
    	    <button class="btn btn-primary btn-xl " id="addButton" type="submit" style="float: right; margin-right: 5px; margin-left: 5px;" onclick="location.href='/coffee/list/a'"><i class="fas fa-plus fa-fw"></i>add menu</button>
		</c:if>
        <!----------------------------------------------- 메뉴추가 add menu--------------------------------------------------------->
        <div class="col-md-6 col-lg-4 mb-5">
            <div class="portfolio-item mx-auto" data-bs-toggle="modal" data-bs-target="#addmenuMoal">
                <div class="portfolio-item-caption d-flex align-items-center justify-content-center h-100 w-100">
                    <div class="portfolio-item-caption-content text-center text-white"><i class="fas fa-plus fa-3x"></i></div>
                </div> 
                <!-- <img class="img-fluid" src="assets/img/portfolio/add menu.png" alt="..." /> -->
            </div>
        </div>


        <!-- Footer-->
        <footer class="footer text-center">
            <div class="container">
                <div class="row">
                    <!-- Footer Location-->
                    <div class="col-lg-4 mb-5 mb-lg-0">
                        <h4 class="text-uppercase mb-4">Location</h4>
                        <p class="lead mb-0">
                            SalTux
                            <br />
                            Clark, MO 65243
                        </p>
                    </div>
                    <!-- Footer Social Icons-->
                    <div class="col-lg-4 mb-5 mb-lg-0">
                        <h4 class="text-uppercase mb-4">Around the Web</h4>
                        <a class="btn btn-outline-light btn-social mx-1" href="#!"><i class="fab fa-fw fa-facebook-f"></i></a>
                        <a class="btn btn-outline-light btn-social mx-1" href="#!"><i class="fab fa-fw fa-twitter"></i></a>
                        <a class="btn btn-outline-light btn-social mx-1" href="#!"><i class="fab fa-fw fa-linkedin-in"></i></a>
                        <a class="btn btn-outline-light btn-social mx-1" href="#!"><i class="fab fa-fw fa-dribbble"></i></a>
                    </div>
                    <!-- Footer About Text-->
                    <div class="col-lg-4">
                        <h4 class="text-uppercase mb-4">About Team</h4>
                        <p class="lead mb-0">
                            Ha-eun, Myoung-geun, Byeong-hun, Min-seo
                            <a href="http://startbootstrap.com">Feel Free to meet!</a>
                            .

                        </p>
                    </div>
                </div>
            </div>
        </footer>
        <!-- Copyright Section-->
        <div class="copyright py-4 text-center text-white">
            <div class="container"><small>Copyright &copy; Saltux Website 2024</small></div>교육 프로그램
        </div>
        <!-- Portfolio Modals-->
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
