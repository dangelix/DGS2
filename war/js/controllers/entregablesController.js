app.service('EntregablesService', [
	'$http',
	'$q',
	'$location',
	'$rootScope',
	'$window',
	function($http, $q, $location,$rootScope, $window) {
	
	this.registraEntregable = function(newEntregable) {
		var d = $q.defer();
		$http.post("/entregables/add/", newEntregable).then(
			function(response) {
				d.resolve(response.data);
			});
		return d.promise;
	}
	this.findEntregables = function(page) {
		var d = $q.defer();
		$http.get("/entregables/findAll/"+page).then(function(response) {
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
		this.byProyecto = function(proyecto) {
		var d = $q.defer();
		$http.get("/entregables/byProyecto/"+proyecto).then(function(response) {
			console.log(response);
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
		this.byProyectoEstatus = function(proyecto,estatus) {
		var d = $q.defer();
		$http.get("/entregables/byProyEstatus/"+proyecto+"/"+estatus).then(function(response) {
			console.log(response);
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
	this.findEntregablesFull= function(){
		var d = $q.defer();
		$http.get("/entregables/findFull/").then(function(response) {
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
	this.findEntregable = function(id) {
		var d = $q.defer();
		$http.get("/entregables/find/"+id).then(function(response) {
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
//	this.buscar= function(search){
//		var d = $q.defer();
//		$http.get("/Entregables/search/"+search).then(function(response) {
//			d.resolve(response.data);
//		}, function(response) {
//			d.reject(response);
//		});
//		return d.promise;
//	}
	
	this.getPages= function(){
		var d = $q.defer();
		$http.get("/entregables/pages").then(function(response) {
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
	
	
	this.busqueda = function(buscar) {
		var d = $q.defer();
		$http.get("/entregables/search/"+buscar).then(function(response) {
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
		$http.get("/entregables/delete/"+id).then(function(response) {
			console.log(response);
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
}])
app.controller("EntregablesController",[
	'$scope',
	'EntregablesService',
	'$routeParams',
	'$location',
	'$window',
	'ProyectosService',
	
	function($scope, EntregablesService, $routeParams,$location,$window, ProyectosService){
	
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

		$scope.newEntregable = {
					proyecto:$routeParams.proyecto,
				
			        };
			
			EntregablesService.byProyecto($routeParams.proyecto,$scope.paginaActual).then(
					function(data) {
						$scope.newEntregable = data;
						if (!$scope.newEntregable.idProyecto) {
							$scope.newEntregable.proyecto = data.proyecto;
							$scope.newEntregable.idProyecto = data.idProyecto;
							
						}
					});
			
	
	$scope.registraEntregable = function(newEntregable) {
		//console.log(newEntregable);	
		EntregablesService.registraEntregable(newEntregable).then(function(newEntregable) {
					alert("Entregable agregado");
//					$window.location.reload();

					$location.path("/entregables");
				})
	}
	
		$scope.EntregablesPage = function(page) {
		$scope.paginaAcutal=page;
		EntregablesService.findEntregables(page).then(
			function(data) {
				$scope.Entregables = data;				
				$scope.llenarPags();
				
			})
	}
	
	$scope.cargaProyectos=function(){
			EntregablesService.findProyectosFull().then(
				function(data){
			$scope.Proyectos=data;
		})
	}
	

	$scope.cargaEntByProy=function(){
			EntregablesService.byProyecto($scope.Entregable.proyecto).then(
				function(data){
			$scope.Entregables=data;
		})
	}
	
	$scope.cargaEntProyEstatus=function(){
			EntregablesService.byProyectoEstatus($scope.Entregable.proyecto,$scope.Entregable.estatus ).then(
				function(data){
			$scope.Entregables=data;
		})
	}
	$scope.Entregables = function(proyecto) {
		EntregablesService.byProyecto(proyecto).then(
			function(data) {
				$scope.Entregables = data;							
			})
	}
	//$scope.cargaReqByProy();
	
	$scope.Proyectos = function() {
		EntregablesService.findProyectosFull().then(
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
		EntregablesService.getPersonas(page).then(
			function(data){
				$scope.personas=data;
				$scope.llenarPags();
		})
	} 	
	

	
	$scope.borrar=function(id){
		//alert("id:"+id);
		EntregablesService.borrar(id).then(function(data){
			if(data[0]=="0"){
				alert("El Entregable se eliminó con éxito");
			}else{
				alert("Entregable eliminado exitosamente...");
			}
			$window.location.reload();
		})
	}
	
	
	$scope.EntregablesPage(1);

	
	
	EntregablesService.getPages().then(function(data){
		$scope.maxPage=data;
	})
	
	$scope.editar = function(id) {
		$location.path("/entregables/edit/"+ id);
	}
	
	$scope.buscar= function(){
		EntregablesService.buscar($scope.searchText).then(function(data){
			$scope.Entregables= data;
			$scope.searchText="";
		})
	}
	
	$scope.busqueda=[];
	
	$scope.buscar = function(buscar){
		$scope.botonBuscar=true;
		$scope.todos=false;
		$scope.busqueda=[];
		$scope.paginaActual=-1;
		
				EntregablesService.busqueda(buscar.buscar).then(function(data) {
					// $scope.herramientas = data;
					$scope.personas=data;
					$scope.botonBuscar=false;
				});
			
	}
	
	
	
	
}]);
app.controller("EntregablesEditController",[
	'$scope',
	'EntregablesService',
	'$routeParams',
	'$location',
	'$window',
	function($scope, EntregablesService, $routeParams,$location, $window){		
		EntregablesService.findEntregable($routeParams.id).then(function(data){
			$scope.newEntregable=data;
		})
		
		$scope.editaEntregable = function(newEntregable) {
		console.log(newEntregable);		
		EntregablesService.registraEntregable(newEntregable).then(function(newEntregable) {
					alert("Entregable Modificado");
//					$window.location.reload();
					$location.path("/entregables");
				})
	}	
	
}]);
