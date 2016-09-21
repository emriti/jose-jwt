(function() {
	'use strict';
	function npwpController() {
		this.npwp;
		this.open = function() {
			var tmp = this;
			tmp.npwp = "";
		};
	}
	
	var app = angular.module('lookupNpwpApp', []);
	app.component('lookupNpwp', {
		templateUrl: "/WebClient/lookups/npwp/lookup-npwp.html",
		controller: npwpController,
		bindings: {
			npwp: "="
		}
	});

})();