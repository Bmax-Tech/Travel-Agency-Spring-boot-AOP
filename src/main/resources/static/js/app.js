var app = angular.module('spring_aop', [ 'angular-growl' ]);

app.config([ 'growlProvider', function(growlProvider) {
	growlProvider.globalTimeToLive(3000);
} ]);

var server_address = "http://localhost:8080";
var config = {
	disableCountDown : true
};