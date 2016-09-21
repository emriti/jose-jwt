(function() {
	var app = angular.module('lookupNim', []);
	app.controller('lookupNimController', function() {
		this.nim;
		this.open = function() {
			var tmp = this;
			tmp.nim = "";
		};
	});
	app.directive('lookupNim', function() {
		return {
			restrict : 'AE',
			templateUrl : "lookup-nim.html"
		};
	});

})();