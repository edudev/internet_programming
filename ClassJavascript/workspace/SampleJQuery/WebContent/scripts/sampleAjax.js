$(document).ready(function () {
	"use strict";
	
	var appendToList = function ($list, post) {
		$list.append($("<li/>").text(post.title));
	};
	
	var processResponse = function (response) {
		var $list = $("#list1");
		
		$.each(response, function () {
			appendToList($list, this);
		});
	};
	
	$.ajax("http://jsonplaceholder.typicode.com/posts", {
		method: 'GET'
	}).then(processResponse);

});
