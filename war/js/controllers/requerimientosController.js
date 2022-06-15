app.service('RequerimientosService', [
	'$http',
	'$q',
	'$location',
	'$rootScope',
	'$window',
	function($http, $q, $location,$rootScope, $window) {
	
	this.registraRequerimiento = function(newRequerimiento) {
		var d = $q.defer();
		$http.post("/requerimientos/add/", newRequerimiento).then(
			function(response) {
				d.resolve(response.data);
			});
		return d.promise;
	}
	this.findRequerimientos = function(page) {
		var d = $q.defer();
		$http.get("/requerimientos/findAll/"+page).then(function(response) {
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
		this.byProyecto = function(proyecto) {
		var d = $q.defer();
		$http.get("/requerimientos/byProyecto/"+proyecto).then(function(response) {
			console.log(response);
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
		this.byProyectoEtapa = function(proyecto,etapa) {
		var d = $q.defer();
		$http.get("/requerimientos/byProyectoEtapa/"+proyecto+"/"+etapa).then(function(response) {
			console.log(response);
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
	this.findRequerimientosFull= function(){
		var d = $q.defer();
		$http.get("/requerimientos/findFull/").then(function(response) {
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
	this.findRequerimiento = function(id) {
		var d = $q.defer();
		$http.get("/requerimientos/find/"+id).then(function(response) {
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
//	this.buscar= function(search){
//		var d = $q.defer();
//		$http.get("/requerimientos/search/"+search).then(function(response) {
//			d.resolve(response.data);
//		}, function(response) {
//			d.reject(response);
//		});
//		return d.promise;
//	}
	
	this.getPages= function(){
		var d = $q.defer();
		$http.get("/requerimientos/pages").then(function(response) {
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
	
	
	this.busqueda = function(buscar) {
		var d = $q.defer();
		$http.get("/requerimientos/search/"+buscar).then(function(response) {
			console.log(response);
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	this.findProyectosFull= function(){
		var d = $q.defer();
		$http.get("/proyectos/findFull").then(function(response) {
				console.log(response);
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
	this.borrar = function(id){
		var d = $q.defer();
		$http.get("/requerimientos/delete/"+id).then(function(response) {
			console.log(response);
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
}])
app.controller("RequerimientosController",[
	'$scope',
	'RequerimientosService',
	'$routeParams',
	'$location',
	'$window',
	'ProyectosService',
	
	function($scope, RequerimientosService, $routeParams,$location,$window, ProyectosService){
	
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

		$scope.newRequerimiento = {
					proyecto:$routeParams.proyecto,
				
			        };
			
			RequerimientosService.byProyecto($routeParams.proyecto,$scope.paginaActual).then(
					function(data) {
						$scope.newRequerimiento = data;
						if (!$scope.newRequerimiento.idProyecto) {
							$scope.newRequerimiento.proyecto = data.proyecto;
							$scope.newRequerimiento.idProyecto = data.idProyecto;
							
						}
					});
			
	
	$scope.registraRequerimiento = function(newRequerimiento) {
		//console.log(newRequerimiento);	
		RequerimientosService.registraRequerimiento(newRequerimiento).then(function(newRequerimiento) {
					alert("Requerimiento agregado");
//					$window.location.reload();

					$location.path("/requerimientos");
				})
	}
	
		$scope.RequerimientosPage = function(page) {
		$scope.paginaAcutal=page;
		RequerimientosService.findRequerimientos(page).then(
			function(data) {
				$scope.Requerimientos = data;				
				$scope.llenarPags();
				
			})
	}
	
	$scope.cargaProyectos=function(){
			RequerimientosService.findProyectosFull().then(
				function(data){
			$scope.Proyectos=data;
		})
	}
	

	$scope.cargaReqByProy=function(){
			RequerimientosService.byProyecto($scope.Requerimiento.proyecto).then(
				function(data){
			$scope.Requerimientos=data;
		})
	}
	
	$scope.cargaReqProyEtapa=function(){
			RequerimientosService.byProyectoEtapa($scope.Requerimiento.proyecto,$scope.Requerimiento.etapa ).then(
				function(data){
			$scope.Requerimientos=data;
		})
	}
	$scope.Requerimientos = function(proyecto) {
		RequerimientosService.byProyecto(proyecto).then(
			function(data) {
				$scope.Requerimientos = data;							
			})
	}
	//$scope.cargaReqByProy();
	
	$scope.Proyectos = function() {
		RequerimientosService.findProyectosFull().then(
			function(data) {
				$scope.Proyectos = data;							
			})
	}
	$scope.cargaProyectos();
	
	$scope.cargarPagina=function(pag){
		if($scope.paginaActual!=pag){
			$scope.paginaActual=pag;
			$scope.cargaPersonas(pag);
		}
	}
	
	$scope.cargaPersonas=function(page){
		//alert ("algo va aqui"+page);
		RequerimientosService.getPersonas(page).then(
			function(data){
				$scope.personas=data;
				$scope.llenarPags();
		})
	} 	
	

	
	$scope.borrar=function(id){
		//alert("id:"+id);
		RequerimientosService.borrar(id).then(function(data){
			if(data[0]=="0"){
				alert("El Requerimiento se eliminó con éxito");
			}else{
				alert("Requerimiento eliminado exitosamente...");
			}
			$window.location.reload();
		})
	}
	
	
	$scope.RequerimientosPage(1);

	
	
	RequerimientosService.getPages().then(function(data){
		$scope.maxPage=data;
	})
	
	$scope.editar = function(id) {
		$location.path("/requerimientos/edit/"+ id);
	}
	
	$scope.buscar= function(){
		RequerimientosService.buscar($scope.searchText).then(function(data){
			$scope.Requerimientos= data;
			$scope.searchText="";
		})
	}
	
	$scope.busqueda=[];
	
	$scope.buscar = function(buscar){
		$scope.botonBuscar=true;
		$scope.todos=false;
		$scope.busqueda=[];
		$scope.paginaActual=-1;
		
				RequerimientosService.busqueda(buscar.buscar).then(function(data) {
					// $scope.herramientas = data;
					$scope.personas=data;
					$scope.botonBuscar=false;
				});
			
	}
	
	
	
	
}]);
app.controller("RequerimientosEditController",[
	'$scope',
	'RequerimientosService',
	'$routeParams',
	'$location',
	'$window',
	function($scope, RequerimientosService, $routeParams,$location, $window){		
		RequerimientosService.findRequerimiento($routeParams.id).then(function(data){
			$scope.newRequerimiento=data;
		})
		
		$scope.editaRequerimiento = function(newRequerimiento) {
		console.log(newRequerimiento);		
		RequerimientosService.registraRequerimiento(newRequerimiento).then(function(newRequerimiento) {
					alert("Requerimiento Modificado");
//					$window.location.reload();
					$location.path("/requerimientos");
				})
	}	
	
}]);
