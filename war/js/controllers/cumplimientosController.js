app.service('CumplimientosService', [
	'$http',
	'$q',
	'$location',
	'$rootScope',
	'$window',
	function($http, $q, $location,$rootScope, $window) {
	
	this.registraCumplimiento = function(newCumplimiento) {
		var d = $q.defer();
		$http.post("/cumplimientos/add/", newCumplimiento).then(
			function(response) {
				d.resolve(response.data);
			});
		return d.promise;
	}
	this.findCumplimientos = function(page) {
		var d = $q.defer();
		$http.get("/cumplimientos/findAll/"+page).then(function(response) {
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
		this.byProyecto = function(proyecto) {
		var d = $q.defer();
		$http.get("/cumplimientos/byProyecto/"+proyecto).then(function(response) {
			console.log(response);
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
		this.byProveedor = function(proveedor) {
		var d = $q.defer();
		$http.get("/cumplimientos/byProveedor/"+proveedor).then(function(response) {
			console.log(response);
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
		this.byProyectoEstatus = function(proyecto,estatus) {
		var d = $q.defer();
		$http.get("/cumplimientos/byProyEstatus/"+proyecto+"/"+estatus).then(function(response) {
			console.log(response);
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
	this.findCumplimientosFull= function(){
		var d = $q.defer();
		$http.get("/cumplimientos/findFull/").then(function(response) {
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
	this.findCumplimiento = function(id) {
		var d = $q.defer();
		$http.get("/cumplimientos/find/"+id).then(function(response) {
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
//	this.buscar= function(search){
//		var d = $q.defer();
//		$http.get("/Cumplimientos/search/"+search).then(function(response) {
//			d.resolve(response.data);
//		}, function(response) {
//			d.reject(response);
//		});
//		return d.promise;
//	}
	
	this.getPages= function(){
		var d = $q.defer();
		$http.get("/cumplimientos/pages").then(function(response) {
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
	
	
	this.busqueda = function(buscar) {
		var d = $q.defer();
		$http.get("/cumplimientos/search/"+buscar).then(function(response) {
			console.log(response);
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
	
	this.borrar = function(id){
		var d = $q.defer();
		$http.get("/cumplimientos/delete/"+id).then(function(response) {
			console.log(response);
			d.resolve(response.data);
		}, function(response) {
			d.reject(response);
		});
		return d.promise;
	}
	
}])
app.controller("CumplimientosController",[
	'$scope',
	'CumplimientosService',
	'$routeParams',
	'$location',
	'$window',
	'ProyectosService',
	
	function($scope, CumplimientosService, $routeParams,$location,$window, ProyectosService){
	
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

		$scope.newCumplimiento = {
					proyecto:$routeParams.proyecto,
				
			        };
			
			CumplimientosService.byProyecto($routeParams.proyecto,$scope.paginaActual).then(
					function(data) {
						$scope.newCumplimiento = data;
						if (!$scope.newCumplimiento.idProyecto) {
							$scope.newCumplimiento.proyecto = data.proyecto;
						
							
						}
					});
			
	
	$scope.registraCumplimiento = function(newCumplimiento) {
		//console.log(newCumplimiento);	
		CumplimientosService.registraCumplimiento(newCumplimiento).then(function(newCumplimiento) {
					alert("Cumplimiento agregado");
//					$window.location.reload();

					$location.path("/cumplimientos");
				})
	}
	
		$scope.CumplimientosPage = function(page) {
		$scope.paginaAcutal=page;
		CumplimientosService.findCumplimientos(page).then(
			function(data) {
				$scope.Cumplimientos = data;				
				$scope.llenarPags();
				
			})
	}
	
	
	

	
	
	$scope.cargaCumProv=function(){
			CumplimientosService.byProveedor($scope.Cumplimiento.proveedor).then(
				function(data){
			$scope.Cumplimientos=data;
		})
	}
	$scope.Cumplimientos = function(proyecto) {
		CumplimientosService.byProyecto(proyecto).then(
			function(data) {
				$scope.Cumplimientos = data;							
			})
	}
	

	//$scope.cargaReqByProy();
	

	
	$scope.cargarPagina=function(pag){
		if($scope.paginaActual!=pag){
			$scope.paginaActual=pag;
			$scope.cargaPersonas(pag);
		}
	}
	
	$scope.cargaPersonas=function(page){
		//alert ("algo va aqui"+page);
		CumplimientosService.getPersonas(page).then(
			function(data){
				$scope.personas=data;
				$scope.llenarPags();
		})
	} 	
	

	
	$scope.borrar=function(id){
		//alert("id:"+id);
		CumplimientosService.borrar(id).then(function(data){
			if(data[0]=="0"){
				alert("El Cumplimiento se elimin?? con ??xito");
			}else{
				alert("Cumplimiento eliminado exitosamente...");
			}
			$window.location.reload();
		})
	}
	
	
	$scope.CumplimientosPage(1);

	
	
	CumplimientosService.getPages().then(function(data){
		$scope.maxPage=data;
	})
	
	$scope.editar = function(id) {
		$location.path("/cumplimientos/edit/"+ id);
	}
	
	$scope.buscar= function(){
		CumplimientosService.buscar($scope.searchText).then(function(data){
			$scope.Cumplimientos= data;
			$scope.searchText="";
		})
	}
	
	$scope.busqueda=[];
	
	$scope.buscar = function(buscar){
		$scope.botonBuscar=true;
		$scope.todos=false;
		$scope.busqueda=[];
		$scope.paginaActual=-1;
		
				CumplimientosService.busqueda(buscar.buscar).then(function(data) {
					// $scope.herramientas = data;
					$scope.personas=data;
					$scope.botonBuscar=false;
				});
			
	}
	
	
	
	
}]);
app.controller("CumplimientosEditController",[
	'$scope',
	'CumplimientosService',
	'$routeParams',
	'$location',
	'$window',
	function($scope, CumplimientosService, $routeParams,$location, $window){		
		CumplimientosService.findCumplimiento($routeParams.id).then(function(data){
			$scope.newCumplimiento=data;
		})
		
		$scope.editaCumplimiento = function(newCumplimiento) {
		console.log(newCumplimiento);		
		CumplimientosService.registraCumplimiento(newCumplimiento).then(function(newCumplimiento) {
					alert("Cumplimiento Modificado");
//					$window.location.reload();
					$location.path("/cumplimientos");
				})
	}	
	
}]);
