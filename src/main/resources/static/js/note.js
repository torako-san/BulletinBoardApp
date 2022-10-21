$(document).ready(function(){
	$('#submit').on('click', function(){
		let content = $(".content").val(); 
		
		$.ajax({
			url: "/add", 
			type: "POST", 
			data: {
				"content" : content
			},
			datatype: "json"
		})
		.done(function(data){
			var data_stringify = JSON.stringify(data);
			var data_json = JSON.parse(stringify);
			var data_id = data_json["id"];
			var dcontent = data_json["content"];
			
			$("#addContent").prepend('<h4>${content}</h4>');
			$("conent").val("");
		})
		.fail(function(jqXHR, textStatis, errorThrown) {
			alreat("errer!");
			console.log("jqXHR			: " + jqXHR.status);
			console.log("textStatus		: " + textStatus);
			console.log("errorThrown	: " + errorThrow.message.status);
		})
	})	
})