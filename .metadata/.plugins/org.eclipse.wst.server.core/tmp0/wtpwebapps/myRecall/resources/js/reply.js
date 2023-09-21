$(document).ready(function () {
    // 댓글 delete 버튼 클릭 시 댓글 삭제
    $(".replyDeleteBtn").on("click", function (event) {
        var replyNum = $(this).data("value").toString(); // 클릭된 버튼의 data-value 가져오기

        $.ajax({
            url: "recallPost/deleteReply",
            type: "post",
            contentType: "text/plain;charset=utf-8",
            dataType:"text",
            data:replyNum,
            success: function (response) {
    
                alert(response);
                location.reload();
            },
            error: function (xhr, status, error) { // 수정: error 함수 구문 수정
                console.log("http status: " + xhr.status);
            }
        });
    });

    //댓글 modify 버튼 클릭 시 댓글 수정 
    $(".replyModifyBtn").on("click", function (event) {
    	var recallNum = $(this).data("recallNum").toString();
    	var replyNum = $(this).data("replyNum").toString();
    	var replyText = $(this).data("replyText").toString();
    	
    	console.log(replyNum);
    	console.log(replyText);
        var text = $("textarea").val(replyText);
        
        $("#replyForm").attr("action" ,"recallPost/"+recallNum+"/"+replyNum + "/updateReply");
        $("#replySubmitBtn").html("modify");    
    });
});