$(document).ready(function () {

    //회원가입 버튼 클릭 시 회원가입 form 모달 보이기
    $("#signupBtn").on("click", function (event) {

        event.preventDefault();
        console.log(event.target);
        $("#signupArticle").show();
    });
    //회원가입 버튼과 회원가입 form 요소 이외의 요소 클릭했을 때 회원가입 form hide
    $(document).on("click", function (event) {
        var signupBtnclicked = $(event.target).closest("#signupBtn").length;
        var signupArticleclicked = $(event.target).closest("#signupArticle").length;

        if (signupArticleclicked === signupBtnclicked) {
            $("#signupArticle").hide();
        }
    });


    var passedSignUpInfo = []; // 검증된 input 요소 값 저장하는 배열
    var sendCodeTime; // 코드 발송 시간 
    var codeInputCounter; // 코드 발송 후 유효시간(60초) 카운터

    //input 요소 값 
    var signUpIdInput;
    var firstPw;
    var secndPw;
    var phoneNum;
    var codeInput;


    //email check 버튼 : 이메일(아이디) 유효성 검사 및 중복 검사 진행
    $("#emailCheckBtn").on("click", validateEmailInput);
    //PW check 버튼 : 비밀번호 유효성 검사 진행 
    $("#pwCheckBtn").on("click", validatePwInput);
    //send code 버튼 : 국내 전화번호 형식 검사 후 문자로 코드 전송 
    $("#sendCodeBtn").on("click", sendCodeToPhone);
    //check code 버튼 : 전송받은 코드와 입력한 코드의 일치 여부 검사 
    $("#codeCheckBtn").on("click", checkCode);
    //회원가입 버튼 : input 요소값과 기존의 검증된 값 비교 후 사용자 등록 
    $("#enrollBtn").on("click", function (event) {
        preventDefault(event);
        signUpIdInput = $("#SignUpidInput").val();
        firstPw = $(".pwInput").eq(0).val();
        secndPw = $(".pwInput").eq(1).val();
        phoneNum = $("#phoneNumInput").val();
        codeInput = $("#codeInput").val();
        brand = $("#brandSel").val();
        carName = $("#carSel").val();
        fromDate = $("#yearSel").val();

        if (passedSignUpInfo[0] != signUpIdInput) {
            alert("아이디 검사를 다시하세요");
        }
        else if (passedSignUpInfo[1] != firstPw || passedSignUpInfo[1] != secndPw) {
            alert("비밀번호 검사를 다시하세요");
        }
        else if (passedSignUpInfo[2] != phoneNum) {

            alert("전화번호 검사를 다시하세요");

        }
        else if (passedSignUpInfo[3] != codeInput) {
            alert("코드 인증을 다시하세요");
        }

        else { // 검증된 완료된 값과 input 요소의 값이 같을 때(변화가 없을 때) 회원등록 요청 
            $.ajax({
                url: "signUp/register",
                type: "post",
                contentType: "application/json; charset=utf-8",
                dataType: "text",
                data: JSON.stringify({
                    memberId: passedSignUpInfo[0],
                    memberPw: passedSignUpInfo[1],
                    phoneNum: passedSignUpInfo[2],
                    car: {
                        brand: brand,
                        carName: carName,
                        fromDate: fromDate
                    }
                }),
                success: function (response, xhr) {
                    console.log("http status: " + xhr.status);
                    alert(response);
                    location.reload();
                },
                error(xhr, status, error) {
                    console.log("http status: " + xhr.status);
                    console.log("error :" + error);
                }
            });
        }


    });

    //email(아이디) 유효성 및 중복검사 함수
    function validateEmailInput() {

        signUpIdInput = $("#SignUpidInput").val();
        console.log("input id :" + signUpIdInput);
        var emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

        //이메일 패턴 유효성 검사 
        if (emailPattern.test(signUpIdInput)) {

            console.log("이메일 형식 ok");
            //ajax 요청 : 이메일 중복 검사 실행 
            $.ajax({
                url: "/signUp/checkIdDupl",
                type: "post",
                contentType: "text/plain; charset=utf-8",
                dataType: "text",
                data: signUpIdInput,
                success: function (data) {
                    //ajax 요청 후 중복검사 통과 시 var passedSignUpInfo[0] = signUpIdInput
                    console.log("return data :" + data);
                    if (data === "") { //서버에서 중복되는 이메일(아이디)이 없을 때
                        alert("사용가능한 이메일(아이디)입니다.");
                        passedSignUpInfo[0] = signUpIdInput;
                        confirmedId = signUpIdInput;
                        console.log("passedSignUpInfgo[0] :" + passedSignUpInfo[0]);
                    } else { //중복되는 아이디가 있는 경우
                        alert("이미 사용중인 이메일(아이디)입니다.");
                    }
                },
                error: function (xhr, status, error) {
                    console.log(status);
                }
            });


        } else {
            alert("이메일 형식을 확인하세요");
        }
    };
    //pw 유효성 검사 함수
    function validatePwInput() {
        // 영문 대소문자,특수문자 포함 8자 이상
        var pwPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;
        firstPw = $(".pwInput").eq(0).val();
        secndPw = $(".pwInput").eq(1).val();
        var pw;

        if (firstPw !== secndPw) {
            alert("비밀번호가 불일치 합니다");
        }
        else {
            pw = secndPw;
            //비밀번호 유효성 검사 
            if (pwPattern.test(pw)) {
                // 유효성 검사 통과 시
                passedSignUpInfo[1] = pw;
                alert("사용가능한 비밀번호 입니다.");
                console.log("passedSignUpInfo[1]:" + passedSignUpInfo[1]);
                confirmedPw = pw;
            } else {
                alert("대소문자,특수문자 포함 8자리 이상이어야합니다.");
            }
        }
    };

    //전화번호 유효성 검사 및 code 전송 요청 함수
    function sendCodeToPhone() {
        phoneNum = $("#phoneNumInput").val();
        var phoneNumPattern = /^010\d{8}$/; // 010으로 시작하고 8자리 숫자만 가능 
        //전화번호 유효성 검사 
        if (phoneNumPattern.test(phoneNum)) {
            console.log("유효성 검사 완료");
            //ajax:서버로 입력받은 전화번호 전달 
            $.ajax({
                url: "/signUp/sendCode",
                type: "post",
                contentType: "text/plain; charset=utf-8",
                dataType: "text",
                data: phoneNum,
                success: function (responseData) {
                    //1. 서버에서 sms code 보낸 후 response 전달 받음 
                    console.log(responseData);
                    alert("code를 확인하세요");
                    sendCodeTime = new Date(); // code 전송 시점
                    passedSignUpInfo[2] = phoneNum;
                    //코드 인증 시간 count 
                    $("#restTime").css("display", "block");
                    codeInputCounter = setInterval(function () {
                        var currTimeSeconds = new Date().getTime();
                        var sendCodeTimeSeconds = sendCodeTime.getTime();
                        var restSecond = 60 - Math.floor((currTimeSeconds - sendCodeTimeSeconds) / 1000);
                        // console.log("currTimeSeconds:" + currTimeSeconds);
                        // console.log("sendCodTimeSeconds:" + sendCodeTimeSeconds);
                        // console.log("restSecond :" + restSecond);
                        $("#restTime").html("남은시간:" + restSecond);
                        if (restSecond == "0") {
                            clearInterval(codeInputCounter);
                            $("#restTime").html("시간경과: 코드를 다시 발급받으세요");
                        }
                    }, 1000);

                },
                error: function (xhr, status, error) {
                    var errorMessage = xhr.responseText;
                    alert('Error: ' + errorMessage);
                }
            });


        }
        else { //전화번호 유효성 검사 불합 시 
            alert("전화번호 형식을 확인하세요");
        }
    };

    //code 전송 후 code 값 입력받아 일치 여부 검사하는 함수 
    function checkCode() {
        var checkCodeTime = new Date().getTime(); //밀리초 단위 , 코드 체크 시점 
        //코드 체크 유효시간 1분(60초*10000)
        if (checkCodeTime - sendCodeTime < 60 * 1000) {
            codeInput = $("#codeInput").val();
            console.log("코드 인증 가능");

            //ajax:입력한 코드를 서버에 보내서 일치 여부 return 받기 
            $.ajax({
                url: "signUp/checkCode",
                type: "GET",
                dataType: "text",
                data: { code: codeInput },
                success: function (responseData) {
                    console.log(responseData);
                    if (responseData === "true") {
                        alert("코드일치");
                        //전송된 코드와 입력한 코드 일치
                        passedSignUpInfo[3] = codeInput;
                        confirmedCode = codeInput;
                        console.log("passedSignUpInfo[2] :" + "코드인증 완료");
                        clearInterval(codeInputCounter); // 코드 인증 완료 시 코드입력 counter 정지 
                        $("#restTime").html("");
                        $("#restTime").css("display", "none");
                    }
                },
                error: function (xhr, status, error) {
                    console.log(error);
                }
            });

        }
        else {
            alert("코드 인증시간 경과:코드를 다시 발급 받으세요");
        }
    };

});
