app.controller('BookingsController', function BookingsController($scope, $http,
		$location, growl) {
	$scope.bookings = [];

	$scope.__init = function() {
		load();
	};

	/**
	 * Remove Booking Action
	 */
	$scope.removeBooking = function(id) {
		$http({
			method : 'POST',
			url : server_address + '/api/removeBooking',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : {
				booking_id : id
			}
		}).then(function(res) {
			console.log(res);
			load();
			growl.success("Booking Removed Successful", config);
		}, function(error) {
			console.log(error);
			growl.error("Error Occured", config);
		});
	}

	/**
	 * Main data load method
	 * @returns
	 */
	function load() {
		$http({
			method : 'POST',
			url : server_address + '/api/getBookings',
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function(res) {
			console.log(res);
			$scope.bookings = angular.copy(res.data);
		}, function(error) {
			console.log(error);
		});
	}

});