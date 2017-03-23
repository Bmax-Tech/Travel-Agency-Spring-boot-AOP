app.controller('UsersController', function UsersController($scope, $http,
		$location, growl) {
	$scope.users = [];
	$scope.user_id = -1;
	$scope.credit_limit = 0;

	$scope.__init = function() {
		load();
	};

	/**
	 * Remove Booking Action
	 */
	$scope.editUser = function(id, credit_limit) {
		$scope.user_id = id;
		$scope.credit_limit = credit_limit;
	}

	/**
	 * Update User
	 */
	$scope.update = function() {
		if ($scope.user_id == -1) {
			growl.error("Error Occured", config);
		} else {
			$http({
				method : 'POST',
				url : server_address + '/api/editUserCreditLimit',
				headers : {
					'Content-Type' : 'application/json'
				},
				data : {
					user_id : $scope.user_id,
					credit_limit : $scope.credit_limit
				}
			}).then(function(res) {
				console.log(res);
				growl.success("User Updated Successful", config);
				load();
			}, function(error) {
				console.log(error);
				growl.error("Error Occured", config);
			});
		}
	}

	/**
	 * Main data load method
	 * 
	 * @returns
	 */
	function load() {
		$http({
			method : 'POST',
			url : server_address + '/api/getAllUsers',
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function(res) {
			console.log(res);
			$scope.users = angular.copy(res.data);
		}, function(error) {
			console.log(error);
		});
	}

});