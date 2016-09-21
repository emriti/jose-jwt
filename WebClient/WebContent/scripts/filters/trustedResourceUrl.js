(function() {
    'use strict';
    angular.module('filters')
		.filter('trustAsResource', trustAsResource);
    
    trustAsResource.$inject = ['$sce'];
    
    function trustAsResource($sce) {
    	return function(input){
    	    return $sce.trustAsResourceUrl(input);
    	  };
    }
})();