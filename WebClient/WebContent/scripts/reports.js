(function() {
	var app = angular.module('Report', []);
	app.controller('ReportController', function($http,$sce) {
		
		this.show = false; 
		this.iframeReport;
			
		this.search = function() {
			
			var vm = this;
			
			vm.iframeReport = "";
			var url = "http://localhost:8080/ApiService/reportsampleapi/show";
			
			/*
			var data = {
					"groupType" : vm.groupType,
					"groupNumber" : (vm.admGroup == null ? null : vm.admGroup.groupNumber),
					"billingPeriodYear" : vm.billingPeriodeYear,
					"billingPeriodMonth": vm.billingPeriodeMonth.id,
					"invoiceNumber" : vm.invoiceNumber
			}
			*/
			
			$http({
				method: "GET",
				url : url
			}).success(function(result){
				//console.log(result);
				vm.show = true;
				vm.iframeReport = result + "#toolbar=0";
				//document.getElementById('contentReport').contentDocument.location.reload(true);
			}).error(function(error){
				console.log(error)
			});
		}
		
	})
})();