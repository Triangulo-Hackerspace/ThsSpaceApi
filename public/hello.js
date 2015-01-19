function Hello($scope, $http) {
    $http.get('http://localhost:9380/state').
   	success(function(data) {
            $scope.states = data;
       });
    
    $http.get('http://localhost:9380/state/now').
   	success(function(data) {
            $scope.state = data;
       });
}