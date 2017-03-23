app.controller('HomeController', function HomeController($scope, $http,
		$location, growl) {
	$scope.flights = [];
	$scope.customer_name = "";
	$scope.contact_no = "";
	$scope.email = "";
	$scope.address = "";
	$scope.booking_date = "";
	$scope.departure_date = "";
	$scope.flightID = -1; // holds booking flight ID

	$scope.__init = function() {
		$http({
			method : 'POST',
			url : server_address + '/api/getFlights',
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function(res) {
			console.log(res);
			$scope.flights = angular.copy(res.data);
		}, function(error) {
			console.log(error);
		});
	};

	$scope.openForm = function(id) {
		$scope.flightID = id;
	}

	/**
	 * Save Booking Details
	 */
	$scope.save = function() {
		if ($scope.customer_name == "" || $scope.contact_no == ""
				|| $scope.email == "" || $scope.address == ""
				|| $scope.booking_date == "" || $scope.departure_date == ""
				|| $scope.flightID == -1) {
			growl.error("Please Fill Form Details", config);
		} else {
			$http({
				method : 'POST',
				url : server_address + '/api/saveBooking',
				headers : {
					'Content-Type' : 'application/json'
				},
				data : {
					customer_name : $scope.customer_name,
					contact_no : $scope.contact_no,
					email : $scope.email,
					address : $scope.address,
					booking_date : $scope.booking_date,
					departure_date : $scope.departure_date,
					flight_id : $scope.flightID
				}
			}).then(function(res) {
				console.log(res);
				growl.success("Booking Successful", config);
			}, function(error) {
				console.log(error);
				growl.error("Error Occured", config);
			});
		}
	}
});