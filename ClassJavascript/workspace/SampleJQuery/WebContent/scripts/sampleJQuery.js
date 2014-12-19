$(document).ready(function () {
	"use strict";
	
	console.log($("body"));
	console.log($("#list1"));
	console.log($(".pretty"));
	console.log($("li.pretty"));
	console.log($("ul .pretty"));
	
	var $list = $("#list1");
	console.log($list.children());
	
	console.log($('.pretty'));
	console.log($list.find('.pretty'));
	
	$("li.pretty").click(function () {
		alert('click');
	});
	
	$("li.pretty").on('mouseenter', function () {
		console.log('mouse entered');
	});
	
	$list.append($("<li>").text('some'));
	
	$("<li />").text("new").appendTo($list);
	
	
	var $itemNameInput = $("<input/>");
	
	$itemNameInput.insertBefore($list);

	var $addButton = $("<button/>").text("Add");
	
	$addButton.insertBefore($list);
	
	$addButton.click(function () {
		var name = $itemNameInput.val();
		if (!name) {
			alert("You must enter a name!");
			
			return;
		}
		
		$list.append($("<li/>").text(name).attr("id", new Date().getTime()));
		$itemNameInput.val("");
	});
	
});
