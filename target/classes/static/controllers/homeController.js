app.controller('HomeController', function HomeController($scope, $http,
		$location, growl) {
	$scope.flights = [];
	$scope.customer_name = "";
	$scope.contact_no = "";
	$scope.email = "";
	$scope.address = "";
	$scope.booking_date = "";
	$scope.departure_date = "";

	$scope.new_flight_code = "";
	$scope.new_flight_category = "";
	$scope.new_flight_airline = "";
	$scope.new_flight_seats = 0;
	$scope.new_flight_source = "";
	$scope.new_flight_destination = "";

	$scope.flightID = -1; // holds booking flight ID

	$scope.__init = function() {
		load();
	};
	
	function load(){
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
	}

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

	/**
	 * Save New Flight Details
	 */
	$scope.saveNewFlight = function() {
		if ($scope.new_flight_code == "" || $scope.new_flight_category == ""
				|| $scope.new_flight_airline == ""
				|| $scope.new_flight_seats == ""
				|| $scope.new_flight_source == ""
				|| $scope.new_flight_destination == "") {
			growl.error("Please Fill Form Details", config);
		} else {
			$http({
				method : 'POST',
				url : server_address + '/api/saveFlight',
				headers : {
					'Content-Type' : 'application/json'
				},
				data : {
					code : $scope.new_flight_code,
					category : $scope.new_flight_category,
					airline : $scope.new_flight_airline,
					seats : $scope.new_flight_seats,
					source : $scope.new_flight_source,
					destination : $scope.new_flight_destination
				}
			}).then(function(res) {
				console.log(res);
				growl.success("Flight Save Successful", config);
				load();
			}, function(error) {
				console.log(error);
				growl.error("Error Occured", config);
			});
		}
	}
});