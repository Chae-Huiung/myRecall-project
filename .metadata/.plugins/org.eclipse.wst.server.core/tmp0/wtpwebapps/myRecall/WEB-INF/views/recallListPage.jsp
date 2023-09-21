<%@page import="org.zerock.domain.RecallVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="cache-control" content="no-cache, no-store, must-revalidate">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<title>recall list page</title>
<link rel="shortcut icon" type="image/x-icon" href="./img/logo.png">

<!-- jquery cdn -->
<script src="https://code.jquery.com/jquery-3.7.0.js"
	integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM="
	crossorigin="anonymous"></script>
<!--css -->
<link rel="stylesheet" href="resources/css/header.css">
<link rel="stylesheet" href="resources/css/footer.css">
<link rel="stylesheet" href="resources/css/mainPage.css">
<link rel="stylesheet" href="resources/css/login.css">
<link rel="stylesheet" href="resources/css/signup.css">
<link rel="stylesheet" href="resources/css/listPage.css">

<!--구글 폰트 링크 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
</head>

<body>
	<header>
		<div class="desktopContainer">
			<div id="logoContainer">
				<a href="/mainPage"><img id="mainLogo" src="resources/img/logo.png"></a>
			</div>
			<div id="searchContainer">
				<div id="searchInput">
					<input type="text" placeholder="차량명 및 리콜사유 등을 검색해보세요!" />
					<button id ="searchInputBtn">
						<i class="fas fa-search-plus"></i>
					</button>
				</div>

			</div>
			<div id="BtnContainer">
				<c:choose>
					<c:when test="${memberId==null}">
						<button id="signupBtn">회원가입</button>
						<button id="loginBtn">로그인</button>
					</c:when>
					<c:when test="${memberId!=null}">
						<button id="myPageBtn">마이페이지</button>
						<button id="logoutBtn">로그아웃</button>
					</c:when>

				</c:choose>

			</div>
		</div>
	</header>

	<main>
		<!--selection 선택 후 검색하는 섹션 -->
		<section id="selectionSection">
			<div id="container">

				<p>
					차량결함 리콜 조회 및<br>카카오톡 알림 서비스
				</p>

				<div class="selectContainer">
					<select name="brand" class="select brandSel">
						<option>제조사</option>
						<c:forEach items="${brands}" var="brand">
							<option value="${brand}">${brand}</option>
						</c:forEach>
					</select>

					<!--https://pulpul8282.tistory.com/18  -->
					<select name="car" class="select carSel">
						<option>차량명</option>

					</select> <select name="year" class="select yearSel">
						<option >생산기간</option>
					</select>
					<button id="searchBtn" type="submit">검색</button>
				</div>
			</div>
		</section>
		<!--검색결과 게시판 보여주는 섹션-->
		<section id="listSection">
			<table>
				<thead>
					<tr class="postRow">
						<th>번호</th>
						<th>제작사</th>
						<th>차량명</th>
						<th>리콜사유</th>
						<th>개시일</th>
						<!--카카오톡 전송 시간 , 관리자만 확인 가능  -->
				 	<c:if test="${member.memberRole == false}"> <!--관리자 memberRole 0, false  -->
						<th>전송시간</th>
						<th>예약시간</th>
					    </c:if>
						
					</tr>
				</thead>

				<c:choose>
					<c:when test="${!posts.isEmpty()}">
						<!--게시글이 있을 때  -->
						<c:set var="postNum" value="${pageCri.firstPostNum}" />
						<c:forEach items="${posts}" var="post">
							<tr>
								<td><a href="/recallPost?recallNum=${post.recallNum}">${postNum}</a></td>
								<td><a href="/recallPost?recallNum=${post.recallNum}"><c:out value="${post.brand}" /></a></td>
							<!--리콜사유  -->
								<td><a href="/recallPost?recallNum=${post.recallNum}"><c:out value="${post.carName}" /></a></td>
								<c:set var="subStr" value="..." />
								<c:choose>
									<c:when test="${post.recallReason.length()<20}">
										<td><a href="/recallPost?recallNum=${post.recallNum}"><c:out value="${post.recallReason}" /></a></td>
									</c:when>
									<c:otherwise>
										<td><a href="/recallPost?recallNum=${post.recallNum}"><c:out value="${post.recallReason.substring(0,20)}${subStr}" /></a></td>
									</c:otherwise>
								</c:choose>
							<!--리콜사유 끝 -->
								<td><a href="/recallPost?recallNum=${post.recallNum}"><c:out value="${post.recallDate}" /></a></td>
							
							<!--카카오톡 전송 시간 , 관리자만 확인 가능  -->
								<c:if test="${member.memberRole == false}">
								<td><a href="/recallPost?recallNum=${post.recallNum}"><c:out value="x" /></a></td>
								<td><a href="/recallPost?recallNum=${post.recallNum}"><c:out value="x" /></a></td>
								</c:if>
							
							</tr>
							<c:set var="postNum" value="${postNum +1}" />
						</c:forEach>
					</c:when>
				</c:choose>

			</table>
			<!-- 페이징 버튼 -->
			<c:choose>
				<c:when test="${pageCri.prev ==true || pageCri.next ==true}">
					<div id="pageBtn">
						<ul>
							<!--화면에 표시할 버튼 개수 -->
							<c:set var="ListSize" value="10" />
							<!--이전 페이지가 있을 시 prev 버튼 활성화 -->
							<c:choose>
								<c:when test="${pageCri.prev ==true}">
									<li class="pageBtnList"><c:url var="prevUrl" value="">
											<c:forEach items="${param}" var="entry">
												<c:if test="${entry.key != 'page'}">
													<c:param name="${entry.key}" value="${entry.value}"></c:param>
												</c:if>
											</c:forEach>
											<c:param name="page" value="${param.page -1}" />
										</c:url> <a href="${prevUrl}">prev</a></li>
								</c:when>
							</c:choose>

							<!--첫째자리 수를 제외한 페이지 버튼의 수 ex)page = 10 일 때 lastDigitValInt =0 -->
							<c:set var="lastDigitVal" value="${((pageCri.page-1)/ListSize)}" />
							<fmt:parseNumber var="lastDigitIntVal" integerOnly="true"
								type="number" value="${lastDigitVal}" />
							<c:set var="loopFlag" value="true" />

							<c:forEach var="firstDigitVal" begin="1" end="${ListSize}">
								<c:set var="selectedPage"
									value="${(lastDigitIntVal*ListSize)+firstDigitVal}" />

								<!--전체 페이지 수 초과 시 page 버튼 생성 중단하기위해 loop flag 변경 -->
								<c:if test="${selectedPage> pageCri.totalPages}">
									<c:set var="loopFlag" value="false" />
								</c:if>

								<!--page의 마지막 자리가 1일 때 마다 listSize 크기만큼 페이지 버튼 생성   -->
								<c:if test="${loopFlag != false}">
									<li class="pageBtnList"><c:url var="selectedPageUrl"
											value="">
											<c:forEach items="${param}" var="entry">
												<c:if test="${entry.key != 'page'}">
													<c:param name="${entry.key}" value="${entry.value}"></c:param>
												</c:if>
											</c:forEach>
											<c:param name="page" value="${selectedPage}" />
										</c:url> <a href="${selectedPageUrl}">${selectedPage}</a></li>
								</c:if>
							</c:forEach>

							<!--다음 페이지가 있을 때 next버튼 활성화  -->
							<c:choose>
								<c:when test="${pageCri.next ==true}">
									<li class="pageBtnList"><c:url var="nextUrl" value="">
											<c:forEach items="${param}" var="entry">
												<c:if test="${entry.key != 'page'}">
													<c:param name="${entry.key}" value="${entry.value}"></c:param>
												</c:if>
											</c:forEach>
											<c:param name="page" value="${param.page+1}" />
										</c:url> <a href="${nextUrl}">next</a></li>
								</c:when>
							</c:choose>

						</ul>
					</div>



				</c:when>
			</c:choose>
		</section>

		<!-- 로그인 버튼 클릭 시 모달로 로그인 화면 보여주는 article -->
		<article id="loginArticle">
			<div id="loginContainer">
				<a href=""> <img id="logoImg" src="resources/img/logo.png">
				</a>
				<form id="loginForm" action="/login" post="post">
					<div class="loginInput">
						<input id="LoginidInput" type="email" placeholder="이메일"> <input
							id="pwInput" type="password" placeholder="비밀번호">
					</div>
					<button id="loginBtn">로그인</button>
				</form>
				<p>
					<a href="">아이디 찾기</a> <a href="">비밀번호 찾기</a> <a href="">회원가입</a>
				</p>
				<div class="otherLoginContainer">
					<button id="googleLoginBtn" type="button">
						<img src="resources/img/googleLoginButton.png">
					</button>
				</div>
			</div>
		</article>
		<!-- 회원가입 버튼 클릭 시 모달로 회원가입 화면 보여주는 article -->
		<article id="signupArticle">
			<div id="signupContainer">
				<a href=""> <img id="logoImg" src="resources/img/logo.png">
				</a>
				<form id="signupForm" action="/signUp/register" method="post">

					<div class="inputContainer">
						<input id="SignUpidInput" type="email" placeholder="이메일">
						<button class="checkBtn" id="emailCheckBtn" type="button">check</button>
					</div>
					<div class="inputContainer">
						<input class="pwInput" type="password" placeholder="비밀번호">
						<input class="pwInput" type="password" placeholder="비밀번호">
						<button class="checkBtn" id="pwCheckBtn" type="button">check</button>
					</div>
					<div class="inputContainer">
						<input id="phoneNumInput" type="tel" placeholder="전화번호">
						<button class="checkBtn" id="sendCodeBtn" type="button">send
							code</button>
					</div>
					<div class="inputContainer">
						<input id="codeInput" type="text" placeholder="코드">
						<button class="checkBtn" id="codeCheckBtn" type="button">check
							code</button>
					</div>
					<div class="inputContainer">
						<select id="brandSel">
							<option>제조사</option>
							<c:forEach items="${brands}" var="brand">
								<option value="${brand}">${brand}</option>
							</c:forEach>
						</select> <select id="carSel">
							<option>차량명</option>
						</select> <select id="yearSel">
							<option>생산일</option>
						</select>
					</div>
					<button id="enrollBtn">회원가입</button>
					<div id="restTime" style="display: none;"></div>
				</form>
			</div>

		</article>
	</main>
	<footer> </footer>
</body>
<script type="text/javascript" src="resources/js/login.js"></script>
<script type="text/javascript" src="resources/js/signup.js"></script>
<script type="text/javascript" src="resources/js/selection.js"></script>
<script type="text/javascript" src="resources/js/search.js"></script>
<script type="text/javascript" src="resources/js/pageBtn.js"></script>
<script src="https://kit.fontawesome.com/6478f529f2.js"
	crossorigin="anonymous">
</script>

</html>