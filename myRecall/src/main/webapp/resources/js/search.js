$(document).ready(function () {
    var input = $("#searchInput input");

    //검색창 초기화
    $(window).on("pageshow", function (event) {
        input.val("");
        input.attr("placeholder", "차량명 및 리콜사유 등을 검색해보세요!");
    });



    //검색창 검색 
    $("#searchInputBtn").on("click", function (event) {
        searchByStr();
    });
	// 검색 창 엔터키
    $("#searchInput input").on("keyup", function (key) {
        if (key.keyCode == 13) {
            searchByStr();
        }
    });

    function searchByStr() {
        var searchStr = input.val();
        var url = "/searchByStr?searchStr=" + encodeURIComponent(searchStr);
        $(location).attr("href", url);
    };


    //selection 메뉴의 검색 버튼 클릭 시 
    $("#searchBtn").on("click", function (event) {
        var brand = $(".brandSel").val();
        var car = $(".carSel").val();
        var year = $(".yearSel").val();
        var page = 1;
        var size = 10;
        console.log(brand + " " + car + " " + year);

        if (brand === "제조사") {
            alert("제조사를 선택하세요");
        } else {

            var url = "/searchBySelection?brand="
                + encodeURIComponent(brand)
                + "&car=" + encodeURIComponent(car)
                + "&year=" + encodeURIComponent(year)
                + "&page=" + encodeURIComponent(page)
                + "&size=" + encodeURIComponent(size);
            $(location).attr("href", url);
        }
    });
});
