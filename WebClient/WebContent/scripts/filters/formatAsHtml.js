(function() {
    'use strict';
    angular.module('filters')
		.filter('formatAsHtml', formatAsHtml);
    
    formatAsHtml.$inject = ['$sce'];
    
    function formatAsHtml($sce) {
    	return function(input){
    	    return $sce.trustAsHtml(input);
    	  };
    }
})();