
$(document).ready(function () {

    //로그인 버튼 클릭 시 로그인 form 모달 보이기
    $("#loginBtn").on("click", function (event) {

        event.preventDefault();
        console.log(event.target);
        $("#loginArticle").show();
    });

    //로그인 버튼과 로그인 form 이외 요소 클릭 시 로그인 form 숨기기
    $(document).on("click", function (event) {
        var loginBtnclicked = $(event.target).closest($("#loginBtn")).length;
        var loginArticleClicked = $(event.target).closest($("#loginArticle")).length;

        if (loginArticleClicked === loginBtnclicked) {
            $("#loginArticle").hide();
        }
    });

    // 아이디, 비밀번호 입력 후 로그인 버튼 클릭 시 
    $("#loginForm #loginBtn").on("click", function (event) {
        event.preventDefault();
        alert("로그인 시도");
        var inputId = $("#LoginidInput").val();
        var inputPw = $("#pwInput").val();
        console.log("id" + inputId + "pw" + inputPw);
        $.ajax({
            url: "/login",
            type: "post",
            contentType: "application/json; charset=utf-8",
            dataType: "text",
            data: JSON.stringify({
                memberId: inputId,
                memberPw: inputPw
            }),
            success: function (response) {
                alert(response);
                location.reload();

            },
            error: function (xhr, status, error) {
                alert("아이디 및 비밀번호를 확인하세요");
            }
        });
    });

    //로그아웃 버튼 클릭 시 세션 삭제 요청 후 mainPage reload 
    $("#logoutBtn").on("click", function (event) {
		console.log("click");
        $.ajax({
            url: "/logout",
            type: "get",
            success: function (response) {
				alert(response);
				location.reload();
            },
            error: function (xhr, status, error) {

            }

        });


    });


});
