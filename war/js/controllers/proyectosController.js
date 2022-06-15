app.service('ProyectosService', [
	'$http',
	'$q',
	'$location',
	'$rootScope',
	'$window',
	function($http, $q, $location,$rootScope, $window) {
	
	this.registraProyecto = function(newProyecto) {
		var d = $q.defer();
		$http.post("/proyectos/add/", newProyecto).then(
			function(response) {
				d.resolve(response.data);
			});
		return d.promise;
	}
	this.findProyectos = function(page) {
		var d = $q.defer();
		$http.get("/proyectos/findAll/"+page).then(function(response) {
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
	this.findProyectosFull= function(){
		var d = $q.defer();
		$http.get("/proyectos/findFull/").then(function(response) {
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
	this.findProyecto = function(id) {
		var d = $q.defer();
		$http.get("/proyectos/find/"+id).then(function(response) {
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
	this.buscar= function(search){
		var d = $q.defer();
		$http.get("/proyectos/search/"+search).then(function(response) {
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
	this.getPages= function(){
		var d = $q.defer();
		$http.get("/proyectos/pages").then(function(response) {
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
	
	
	this.borrar= function(Proyecto){
		var d = $q.defer();
		$http.post("/proyectos/delete/",Proyecto).then(function(response) {
			console.log(response);
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
	this.busqueda = function(buscar) {
		var d = $q.defer();
		$http.get("/proyectos/search/"+buscar).then(function(response) {
			console.log(response);
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	};
	
//		this.findPersonas = function() {
//		var d = $q.defer();
//		$http.get("/personas/findFull/").then(function(response) {
//			console.log(response);
//			d.resolve(response.data);
//		}, function(response) {
//			d.reject(response);
//		});	
//		return d.promise;
//	};
	
}])
app.controller("ProyectosController",[
	'$scope',
	'ProyectosService',
	'$routeParams',
	'$location',
	'$window',
	'PersonasService',
	
	function($scope, ProyectosService, $routeParams,$location,$window, PersonasService){
	
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

	
	$scope.registraProyecto = function(newProyecto) {
		//console.log(newProyecto);	
		ProyectosService.registraProyecto(newProyecto).then(function(newProyecto) {
					alert("Proyecto agregado");
//					$window.location.reload();
					$location.path("/proyectos");
				})
	}
	$scope.ProyectosPage = function(page) {
		$scope.paginaAcutal=page;
		ProyectosService.findProyectos(page).then(
			function(data) {
				$scope.Proyectos = data;				
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
		ProyectosService.getPersonas(page).then(
			function(data){
				$scope.personas=data;
				$scope.llenarPags();
		})
	} 	
	
	$scope.requerimientos = function(Proyecto) {			
		$location.path("/requerimientos/byProyecto/"+ Proyecto.nombre+"/1");
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
//	$scope.Proyecto={
//			fecha:new Date(),
//			hora: null,
//			asistentes:[],
//			proyecto: null
//						
//	};
	
	
	$scope.borrar=function(Proyecto){
		//alert("id:"+id);
		ProyectosService.borrar(Proyecto).then(function(data){
			if(data[0]=="0"){
				alert("El Proyecto se eliminó con éxito");
			}else{
				alert("Proyecto eliminado exitosamente...");
			}
			$window.location.reload();
		})
	}
	
//	$scope.agregarAsistente=function(persona){
//		
//		var asistente={}
//		asistente.id=persona.id;
//		asistente.nombre= persona.nombre;
//		asistente.cargo= persona.cargo
//		asistente.titulo=persona.titulo
//		
//		$scope.Proyecto.asistentes.push(asistente);
//		
//	
//	}
//	
//	$scope.cargaPersonas(1);
	
	$scope.ProyectosPage(1);
	
	ProyectosService.getPages().then(function(data){
		$scope.maxPage=data;
	})
	
	$scope.editar = function(id) {
		$location.path("/proyectos/edit/" + id);
	}
	
	$scope.buscar= function(){
		ProyectosService.buscar($scope.searchText).then(function(data){
			$scope.Proyectos= data;
			$scope.searchText="";
		})
	}
	
	$scope.busqueda=[];
	
	$scope.buscar = function(buscar){
		$scope.botonBuscar=true;
		$scope.todos=false;
		$scope.busqueda=[];
		$scope.paginaActual=-1;
		
				ProyectosService.busqueda(buscar.buscar).then(function(data) {
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
app.controller("ProyectosEditController",[
	'$scope',
	'ProyectosService',
	'$routeParams',
	'$location',
	'$window',
	function($scope, ProyectosService, $routeParams,$location, $window){
		ProyectosService.findProyecto($routeParams.id).then(function(data){
			$scope.newProyecto=data;
		})
		
		$scope.editaProyecto = function(newProyecto) {
		console.log(newProyecto);		
		ProyectosService.registraProyecto(newProyecto).then(function(newProyecto) {
					alert("Proyecto Modificada");
//					$window.location.reload();
					$location.path("/Proyectos");
				})
	}	
}]);
