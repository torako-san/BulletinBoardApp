$(document).ready(function(){
	$('#submit').on('click', function(){
		var content = $(".content").val();

		$.ajax({
			url: "/add",  // リクエストを送信するURLを指定（action属性のurlを抽出）
			type: "POST",  // HTTPメソッドを指定（デフォルトはGET）
			data: {
				"content" : content
			},
			dataType: "json"
		})
		.done(function(data) {
			var data_stringify = JSON.stringify(data);
			var data_json = JSON.parse(data_stringify);
			var data_id = data_json["id"];
			var content = data_json["content"];

			$("#addContent").prepend(`<h4>${content}</h4>`);
			$(".content").val("");
		})
		.fail(function(jqXHR, textStatus, errorThrown) {
			alert("error!");  //
			console.log("jqXHR          : " + jqXHR.status);
			console.log("textStatus     : " + textStatus);
			console.log("errorThrown    : " + errorThrown.message);
		})
	})
})