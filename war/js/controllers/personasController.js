app.service('PersonasService', [
	'$http',
	'$q',
	'$location',
	'$rootScope',
	'$window',
	function($http, $q, $location,$rootScope, $window) {
	
	this.registraPersona = function(newPersona) {
		var d = $q.defer();
		$http.post("/personas/add/", newPersona).then(
			function(response) {
				d.resolve(response.data);
			});
		return d.promise;
	}
	this.findPersonas = function(page) {
		var d = $q.defer();
		$http.get("/personas/findAll/"+page).then(function(response) {
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;	
	}
	
	this.findPersonasFull= function(){
		var d = $q.defer();
		$http.get("/personas/findFull").then(function(response) {
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
	this.findPersona = function(id) {
		var d = $q.defer();
		$http.get("/personas/find/"+id).then(function(response) {
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
	this.buscar= function(search){
		var d = $q.defer();
		$http.get("/personas/search/"+search).then(function(response) {
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
	this.getPages= function(){
		var d = $q.defer();
		$http.get("/personas/pages").then(function(response) {
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
}])
app.controller("PersonasController",[
	'$scope',
	'PersonasService',
	'$routeParams',
	'$location',
	'$window',
	function($scope, PersonasService, $routeParams,$location,$window){
	
		$scope.llenarPags=function(){
			var inicio=0;
			if($scope.paginaActual>3){
				inicio=$scope.paginaActual-3;
			}
			var fin = inicio+5;
			if(fin>$scope.maxPage){
				fin=$scope.maxPage;
			}
			$scope.paginas=[];
			for(var i = inicio; i< fin; i++){
				$scope.paginas.push(i+1);
			}
			for(var i = inicio; i<= fin; i++){
				$('#pag'+i).removeClass("active");
			}
			$('#pag'+$scope.paginaActual).addClass("active");
		}
		
	$scope.registraPersona = function(newPersona) {
		console.log(newPersona);		
		PersonasService.registraPersona(newPersona).then(function(newPersona) {
					alert("Persona Agregada");
//					$window.location.reload();
					$location.path("/personas");
				})
	}
	$scope.PersonasPage = function(page) {
		$scope.paginaAcutal=page;
		PersonasService.findPersonas(page).then(
			function(data) {
				$scope.Personas = data;				
				$scope.llenarPags();
				
			})
	}
	
	$scope.PersonasPage(1);
	
	PersonasService.getPages().then(function(data){
		$scope.maxPage=data;
	})
	
	$scope.editar = function(id) {
		$location.path("/personas/edit/" + id);
	}
	
	$scope.buscar= function(){
		PersonasService.buscar($scope.searchText).then(function(data){
			$scope.Personas= data;
			$scope.searchText="";
		})
	}
	
}]);
app.controller("PersonasEditController",[
	'$scope',
	'PersonasService',
	'$routeParams',
	'$location',
	'$window',
	function($scope, PersonasService, $routeParams,$location, $window){
		PersonasService.findPersona($routeParams.id).then(function(data){
			$scope.newPersona=data;
		})
		
		$scope.editaPersona = function(newPersona) {
		console.log(newPersona);		
		PersonasService.registraPersona(newPersona).then(function(newPersona) {
					alert("Persona Modificado");
//					$window.location.reload();
					$location.path("/Personas");
				})
	}	
}]);
