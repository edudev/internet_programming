(function () {
	"use strict";
	
	alert('Hello world');
	
	var i;
	i = 1;
	++i;
	
	if (i === 2) {
		alert('TWO')
	}
	
	var array = [];
	array.push("Hello");
	array.push("World");
	alert(array[0]);
	alert(array.length);
	
	var o = {
		some: "value"	
	};
	
	alert(o.some);
	o.some  = "value2";
	o.newField = "newField";
	alert(o.newField);
	o["newField2"] = "newValue";
	
	o[o.newField] = "z";
	alert(o[o.newField]);
	
	var otherObject = {};
	o.subObject = otherObject;
	o.subObject.id = 1;
	
	if (o.subObject.id === otherObject.id) {
		alert("TRUE");
	}
	
	var func = function () {
		alert("in func");
	};
	
	func();
	
	var withParameters = function (name) {
		alert("Hello, " + name);
	}
	
	withParameters("World");
		
})();
