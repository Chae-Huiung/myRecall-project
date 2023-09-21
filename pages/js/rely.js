$(document).ready(function () {

    var flag = false;

    //카카오톡 버튼 클릭 시 전송예약 모달 show 
    $("#sendMsgBtn").on("click", function (event) {

        $("#alarmArticle").show();
        console.log("flag :" + flag);

        //모달 및 카카오톡 버튼 이외의 요소 클릭 시 모달 hide
        $("*").not("#alarmArticle").on("click", function (event) {
            flag = true;

            if (flag == true) {
                $("#alarmArticle").hide();
            }

        });
    });












    //라디오 버튼 중 매일 선택되면 시간선택만 show 
    $("#everyDayTerm").on("change", function (event) {
        $("#daySel").hide();
        //$("#timeInput").css("margin-right","10px");
    });

    //라디오 버튼 중 매주 선택되면 요일과 시간 선택 show
    $("#everyWeekTerm").on("change", function (event) {
        $("#daySel").show();
    });
});
