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
	}
	
	this.findListasFull= function(){
		var d = $q.defer();
		$http.get("/listas/findFull/").then(function(response) {
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
	}
	
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
	
	this.getPersonas= function(page){
		var d = $q.defer();
		$http.get("/personas/findAll/"+page).then(function(response) {
			console.log(response);
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
	
	this.borrar= function(id){
		var d = $q.defer();
		$http.get("/listas/delete/"+id).then(function(response) {
			console.log(response);
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
	this.busqueda = function(buscar) {
		var d = $q.defer();
		$http.get("/listas/search/"+buscar).then(function(response) {
			console.log(response);
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	};
	
		this.findPersonas = function() {
		var d = $q.defer();
		$http.get("/personas/findFull/").then(function(response) {
			console.log(response);
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});	
		return d.promise;
	};
	
}])
app.controller("ListasController",[
	'$scope',
	'ListasService',
	'$routeParams',
	'$location',
	'$window',
	'PersonasService',
	
	function($scope, ListasService, $routeParams,$location,$window, PersonasService){
	
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
	$scope.paginaActual=1;
//	PersonasService.findPersonasFull=(function(data) {
//					
//					$scope.personas=data;
//	})
	
	$scope.registraLista = function(newLista) {
		//console.log(newLista);	
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
	
	$scope.cargarPagina=function(pag){
		if($scope.paginaActual!=pag){
			$scope.paginaActual=pag;
			$scope.cargaPersonas(pag);
		}
	}
	
	$scope.cargaPersonas=function(page){
		//alert ("algo va aqui"+page);
		ListasService.getPersonas(page).then(
			function(data){
				$scope.personas=data;
				$scope.llenarPags();
		})
	} 	
//	PersonasService.findPersonas(page).then(function(data){
//		$scope.personas=[];
//		$scope.todos=true;
//		for(var i =0; i<data[1].length;i++){
//			$scope.personas.push(data[1][i]);
//		}
//		for(var i =0; i<data[0].length;i++){
//			$scope.personas.push(data[0][i]);
//		}
//		$scope.llenarPags();
//	})
//	}
	$scope.Lista={
			fecha:new Date(),
			hora: null,
			asistentes:[],
			proyecto: null
						
	};
	
	
	$scope.borrar=function(id){
		ListasService.borrar(id).then(function(data){
			if(data[0]=="0"){
				alert("La lista de asistencia se eliminó con éxito");
			}else{
				alert("Lista eliminada exitosamente...");
			}
			$window.location.reload();
		})
	}
	
	$scope.agregarAsistente=function(persona){
		
		var asistente={}
		asistente.id=persona.id;
		asistente.nombre= persona.nombre;
		asistente.cargo= persona.cargo
		asistente.titulo=persona.titulo
		
		$scope.Lista.asistentes.push(asistente);
		
	
	}
	
	$scope.cargaPersonas(1);
	
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
	
	$scope.busqueda=[];
	
	$scope.buscar = function(buscar){
		$scope.botonBuscar=true;
		$scope.todos=false;
		$scope.busqueda=[];
		$scope.paginaActual=-1;
		
				ListasService.busqueda(buscar.buscar).then(function(data) {
					// $scope.herramientas = data;
					$scope.personas=data;
					$scope.botonBuscar=false;
				});
			
	}
	
	
		$scope.personas = function() {
		PersonasService.findPersonas($routeParams.id).then(
			function(data) {
				$scope.personas = data;				
				console.log(data);
			})
	}
	$scope.personas();
	
	
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
