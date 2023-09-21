$(document).ready(function () {

	$(window).on("pageshow",function(event){ //뒤로가기 버튼 클릭 시 selection 초기화
	
		$(".brandSel option:nth-child(1)").attr("selected","selected");
		$(".carSel option:nth-child(1)").attr("selected","selected");
		$(".yearSel option:nth-child(1)").attr("selected","selected");
	
	});
    //mainPage 차량 selection 
    //제조사 select의 option이 선택되었을 때 차량 list 검색하는 ajax 요청
    $(".brandSel").on("change", function (event) {
        var selectValue = $(this).val();
        console.log("brand:" + selectValue);

        $.ajax({
            url: "/selection/car",
            type: "GET",
            data: { brand: selectValue },
            dataType: "text",
            success: function (response) {
                $(".carSel").html("");
                var defaultOpt = $("<option>").text("차량명");
                $(".carSel").append(defaultOpt);
                
                 $(".yearSel").html("");
                var defaultOpt = $("<option>").text("생산기간");
                $(".yearSel").append(defaultOpt);

                var brands = JSON.parse(response); // 서버로 부터 받은 json 데이터를 js 객체로 변환

                brands.forEach(function (element) {
                    var brand = $("<option>").val(element).text(element);
                    $(".carSel").append(brand);
                });

            },
            error: function (xhr, status, error) {
                console.log("error :" + error);
            }

        });
    });

    //차량 select의 option이 선택되었을 때 생산년도 list 검색하는 ajax 요청 
    $(".carSel").on("change", function (event) {
        var brand = $(".brandSel").val();
        var car = $(".carSel").val();

        console.log("brand: " + brand);
        console.log("car: " + car);
        $.ajax({
            url: "/selection/year",
            type: "GET",
            data: { brand: brand, car: car },
            dataType: "json",
            success: function (cars) {
                $(".yearSel").html("");
                var defaultOpt = $("<option>").text("생산기간");
                $(".yearSel").append(defaultOpt);
                console.log(cars);
                cars.forEach(function (car) {
                    console.log(car);
                    var year = $("<option>").val(car.fromDate).text(car.fromDate + "~" + car.toDate);
                    $(".yearSel").append(year);
                });

            },
            error: function (xhr, status, error) {

                console.log("error :" + status);
            }
        });

    });


    //회원가입 차량 selection
    //제조사 select의 option이 선택되었을 때 차량 list 검색하는 ajax 요청
    $("#brandSel").on("change", function (event) {
        var selectValue = $(this).val();
        console.log("brand:" + selectValue);

        $.ajax({
            url: "/selection/car",
            type: "GET",
            data: { brand: selectValue },
            dataType: "text",
            success: function (response) {
                $("#carSel").html("");
                var defaultOpt = $("<option>").text("차량명");
                $("#carSel").append(defaultOpt);

                var brands = JSON.parse(response);

                brands.forEach(function (element) {
                    var brand = $("<option>").val(element).text(element);
                    $("#carSel").append(brand);
                });

            },
            error: function (xhr, status, error) {
                console.log("error :" + error);
            }

        });
    });

    //차량 select의 option이 선택되었을 때 생산년도 list 검색하는 ajax 요청 
    $("#carSel").on("change", function (event) {
        var brand = $("#brandSel").val();
        var car = $("#carSel").val();

        console.log("brand: " + brand);
        console.log("car: " + car);
        $.ajax({
            url: "/selection/year",
            type: "GET",
            data: { brand: brand, car: car },
            dataType: "json",
            success: function (cars) {
               
                $("#yearSel").html("");
                var defaultOpt = $("<option>").text("생산기간");
                $("#yearSel").append(defaultOpt);
                console.log(cars);
                cars.forEach(function (car) {
                    console.log(car);
                    var year = $("<option>").val(car.fromDate).text(car.fromDate + "~" + car.toDate);
                    $("#yearSel").append(year);
                });

            },
            error: function (xhr, status, error) {

                console.log("error :" + status);
            }
        });

    });

});
