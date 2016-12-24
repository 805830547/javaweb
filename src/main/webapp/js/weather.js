$(document).ready(function () {
	getProvince();
	getCity();
	queryWeather();
});
$("#query").click(function() {
	queryWeather()
});
$("#countrylist").change(function() {
	getProvince();
});

$("#provincelist").change(function() {
	getCity();
	queryWeather();
});

$("#citylist").change(function() {
	queryWeather();
});

function queryWeather(){
	$.ajax({
		type : "POST",
		async:true,
		url : "weather",
		dataType:"text",
		data : "location="+$("#citylist").val()+"&language=zh-Hans&unit=c",

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
}

function getProvince(){
	$.ajax({
		type : "POST",
		async:false,
		url : "getProvince",
		dataType:"json",
		data : "country='" + $("#countrylist").val() + "'",

		success : function(data) {
			var insert="";
			for (var i = 0 ;i<data.length;i++) {
				insert += "<option id="+i+">"+data[i].province+"</option>";
			}
			document.getElementById("provincelist").innerHTML = (insert);
		},
		error : function(data) {
			$("#city").text("error");
		}
	});
}

function getCity(){
	$.ajax({
		type : "POST",
		async:false,
		url : "getCity",
		dataType:"json",
		data : "province='" + $("#provincelist").val() + "'",

		success : function(data) {
			var insert="";
			for (var i = 0 ;i<data.length;i++) {
				insert += "<option id="+i+">"+data[i].city+"</option>";
			}
			document.getElementById("citylist").innerHTML = (insert);
		},
		error : function(data) {
			$("#city").text("error");
		}
	});
}
