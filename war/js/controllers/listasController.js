app.service('ListasService', [
	'$http',
	'$q',
	'$location',
	'$rootScope',
	'$window',
	function($http, $q, $location,$rootScope, $window) {
	
	this.registraLista = function(newLista) {
		var d = $q.defer();
		$http.post("/listas/add/", newLista).then(
			function(response) {
				d.resolve(response.data);
			});
		return d.promise;
	}
	this.findListas = function(page) {
		var d = $q.defer();
		$http.get("/listas/findAll/"+page).then(function(response) {
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	};
	
	this.findListasFull= function(){
		var d = $q.defer();
		$http.get("/listas/findFull").then(function(response) {
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
	this.findLista = function(id) {
		var d = $q.defer();
		$http.get("/listas/find/"+id).then(function(response) {
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	};
	
	this.buscar= function(search){
		var d = $q.defer();
		$http.get("/listas/search/"+search).then(function(response) {
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
	this.getPages= function(){
		var d = $q.defer();
		$http.get("/listas/pages").then(function(response) {
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
}])
app.controller("ListasController",[
	'$scope',
	'ListasService',
	'$routeParams',
	'$location',
	'$window',
	function($scope, ListasService, $routeParams,$location,$window){
	
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
		
	$scope.registraLista = function(newLista) {
		console.log(newLista);		
		ListasService.registraLista(newLista).then(function(newLista) {
					alert("Lista Agregada");
//					$window.location.reload();
					$location.path("/listas");
				})
	}
	$scope.ListasPage = function(page) {
		$scope.paginaAcutal=page;
		ListasService.findListas(page).then(
			function(data) {
				$scope.Listas = data;				
				$scope.llenarPags();
				
			})
	}
	
	$scope.ListasPage(1);
	
	ListasService.getPages().then(function(data){
		$scope.maxPage=data;
	})
	
	$scope.editar = function(id) {
		$location.path("/listas/edit/" + id);
	}
	
	$scope.buscar= function(){
		ListasService.buscar($scope.searchText).then(function(data){
			$scope.Listas= data;
			$scope.searchText="";
		})
	}
	
}]);
app.controller("ListasEditController",[
	'$scope',
	'ListasService',
	'$routeParams',
	'$location',
	'$window',
	function($scope, ListasService, $routeParams,$location, $window){
		ListasService.findLista($routeParams.id).then(function(data){
			$scope.newLista=data;
		})
		
		$scope.editaLista = function(newLista) {
		console.log(newLista);		
		ListasService.registraLista(newLista).then(function(newLista) {
					alert("Lista Modificada");
//					$window.location.reload();
					$location.path("/Listas");
				})
	}	
}]);
