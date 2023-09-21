<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이리콜 메인페이지</title>
    <link rel="shortcut icon" type="image/x-icon" href="./img/logo.png">

    <!-- jquery cdn -->
    <script src="https://code.jquery.com/jquery-3.7.0.js"
        integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
    <!--css -->
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/mainPage.css">
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="css/signup.css">

    <!--구글 폰트 링크 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
</head>

<body>
    <header>
        <div class="desktopContainer">
            <div id="logoContainer">
                <a href=""><img id="mainLogo" src="img/logo.png"></a>
            </div>
            <div id="searchContainer">
                <div id="searchInput">
                    <input type="text" placeholder="차량명 및 리콜사유 등을 검색해보세요!" />
                    <button>
                        <i class="fas fa-search-plus"></i>
                    </button>
                </div>

            </div>
            <!--로그아웃 상태-->
            <div id="BtnContainer">
                <button id="signupBtn">회원가입</button>
                <button id="loginBtn">로그인</button>
            </div>

            <!--로그인 상태-->
            <!-- <div class="dropdown">
                <i class="fas fa-user"></i>
                <div class="dropdownMenu">
                    <a href="">로그아웃</a>
                    <a href="">마이페이지</a>

                </div>
            </div> -->
        </div>
    </header>

    <main>
        <!--selection 선택 후 검색하는 섹션 -->
        <section id="selectionSection">
            <div id="container">

                <p>차량결함 리콜 조회 및<br>카카오톡 알림 서비스</p>

                <div class="selectContainer">
                    <select name="brand" class="select">
                        <option>brand</option>
                        <option value="apple">현대</option>
                        <option value="orange">기아</option>
                        <option value="grape">쌍용</option>
                        <option value="melon">벤츠</option>
                    </select>

                    <!--https://pulpul8282.tistory.com/18  -->
                    <select name="car" class="select">
                        <option>car</option>
                        <option value="apple">소나타</option>
                        <option value="orange">그랜져</option>
                        <option value="grape">산타페</option>
                        <option value="melon">아반떼</option>
                    </select>
                    <select name="year" class="select">
                        <option>year</option>
                        <option value="orange">2023</option>
                        <option value="grape">2022</option>
                        <option value="melon">2021</option>
                    </select>
                    <button id="searchBtn" type="submit">검색</button>
                </div>
            </div>
        </section>
        <!-- 최신 리콜 게시물 보여주는 섹션 -->
        <section id="newPostSection">
            <div id="headLineContainer">
                <p>최신 리콜현황</p>
            </div>
            <div id="newPostContainer">
                <div class="newPosts">
                    <div class="postImage">
                        <img src="img/honda.jpg">
                    </div>
                    <div class="postContents">
                        <p>제작사: 혼다코리아</p>
                        <p>차명: ACCORD,CR-V</p>
                        <p>생산기간: 2003-10-06~2011-07-27</p>
                    </div>
                </div>
                <div class="newPosts">
                    <div class="postImage">
                        <img src="img/honda.jpg">
                    </div>
                    <div class="postContents">
                        <p>제작사: 혼다코리아</p>
                        <p>차명: ACCORD,CR-V</p>
                        <p>생산기간: 2003-10-06~2011-07-27</p>
                    </div>
                </div>
                <div class="newPosts">
                    <div class="postImage">
                        <img src="img/honda.jpg">
                    </div>
                    <div class="postContents">
                        <p>제작사: 혼다코리아</p>
                        <p>차명: ACCORD,CR-V</p>
                        <p>생산기간: 2003-10-06~2011-07-27</p>
                    </div>
                </div>
                <div class="newPosts">
                    <div class="postImage">
                        <img src="img/honda.jpg">
                    </div>
                    <div class="postContents">
                        <p>제작사: 혼다코리아</p>
                        <p>차명: ACCORD,CR-V</p>
                        <p>생산기간: 2003-10-06~2011-07-27</p>
                    </div>
                </div>
                <div class="newPosts">
                    <div class="postImage">
                        <img src="img/honda.jpg">
                    </div>
                    <div class="postContents">
                        <p>제작사:혼다코리아</p>
                        <p>차명:ACCORD,CR-V</p>
                        <p>생산기간:2003-10-06~2011-07-27</p>
                    </div>
                </div>


            </div>
            </div>
            <!--최신게시물 next 버튼 -->
            <div id="nextBtnContainer">
                <div class="nextBtn">
                    <i class="fas fa-arrow-circle-left"></i>
                </div>
                <div class="nextBtn">
                    <i class="fas fa-arrow-circle-right"></i>
                </div>
            </div>


        </section>
        <!-- 로그인 버튼 클릭 시 모달로 로그인 화면 보여주는 article -->
        <article id="loginArticle">
            <div id="loginContainer">
                <a href="">
                    <img id="logoImg" src="img/logo.png">
                </a>
                <form id=" loginForm" action="" post="get">
                    <div class="loginInput">
                        <input id="LoginidInput" type="email" placeholder="이메일">
                        <input id="pwInput" type="password" placeholder="비밀번호">
                    </div>
                    <button id="loginBtn">로그인</button>
                </form>
                <p>
                    <a href="">아이디 찾기</a>
                    <a href="">비밀번호 찾기</a>
                    <a href="">회원가입</a>
                </p>
                <div class="otherLoginContainer">
                    <button id="googleLoginBtn" type="button"><img src="img/googleLoginButton.png"></button>
                </div>
            </div>
        </article>
        <!-- 회원가입 버튼 클릭 시 모달로 회원가입 화면 보여주는 article -->
        <article id="signupArticle">
            <div id="signupContainer">
                <a href="">
                    <img id="logoImg" src="img/logo.png">
                </a>
                <form id="signupForm" action="" method="post">

                    <div class="inputContainer">
                        <input id="SignUpidInput" type="email" placeholder="이메일">
                        <button class="checkBtn" id="emailCheckBtn" type="button">check</button>
                    </div>
                    <div class="inputContainer">
                        <input class="pwInput" type="password" placeholder="비밀번호">
                        <input class="pwInput" type="password" placeholder="비밀번호">
                        <button class="checkBtn" type="button">check</button>
                    </div>
                    <div class="inputContainer">
                        <input id="phoneNumInput" type="tel" placeholder="전화번호">
                        <button class="checkBtn" type="button">send code</button>
                    </div>
                    <div class="inputContainer">
                        <input id="codeInput" type="text" placeholder="코드">
                        <button class="checkBtn" type="button">check code</button>
                    </div>
                    <div class="inputContainer">
                        <select id="brandSel">
                            <option>제조사</option>
                        </select>
                        <select id="carSel">
                            <option>차량</option>
                        </select>
                        <select id="yearSel">
                            <option>year</option>
                        </select>
                    </div>
                    <button id="enrollBtn">회원가입</button>
                </form>
            </div>

        </article>
    </main>
    <footer>
    </footer>
</body>
<script type="text/javascript" src="js/login.js"></script>
<script type="text/javascript" src="js/signup.js"></script>
<script src="https://kit.fontawesome.com/6478f529f2.js" crossorigin="anonymous">
</script>

</html>