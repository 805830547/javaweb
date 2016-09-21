$(document).ready(function () {

});
$("#query").click(function() {
	$.ajax({
		type : "POST",
		async:false,
		url : "weather",
		dataType:"text",
		data : "location=dalian&language=zh-Hans&unit=c",

		success : function(data) {
			var weatherDate = $.parseJSON(data);
			$("#city").text(weatherDate.results[0].location.path);
			$("#weather").text(weatherDate.results[0].now.text);
			$("#speed").text(weatherDate.results[0].now.code);
			$("#temperature").text(weatherDate.results[0].now.temperature);
		},
		error : function(data) {
			$("#city").text("error");
		}

	});
});
$("#ccc").click(function() {
	$.ajax({
		type : "POST",
		async:false,
		url : "getProvince",
		dataType:"text",
		data : "country='中国'",

		success : function(data) {
			$("#city").text(data);
		},
		error : function(data) {
			$("#city").text("error");
		}

	});
});

