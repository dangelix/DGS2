var app = angular.module("app", [ 'ngRoute', 'ngCookies' ]);
app.config([ '$routeProvider', function($routeProvider) {

	$routeProvider.when('/inicio', {
		templateUrl : "pages/inicio.html",
		controller : "inicioController"
	});
	
	$routeProvider.when('/personas', {
		templateUrl : "pages/personas.html",
		controller : "PersonasController"
	});

	$routeProvider.when('/altaPersona', {
		templateUrl : "pages/altaPersona.html",
		controller : "PersonasController"
	});
	
	$routeProvider.when('/personas/editar/:id', {
		templateUrl : "pages/personasEdita.html",
		controller : "PersonasEditController"
	});
	
	
	$routeProvider.when('/listas', {
		templateUrl : "pages/listas.html",
		controller : "ListasController"
	});

	$routeProvider.when('/altaLista', {
		templateUrl : "pages/altaLista.html",
		controller : "ListasController"
	});
	
	$routeProvider.when('/listas/edit/:id', {
		templateUrl : "pages/listasEdita.html",
		controller : "ListasEditController"
	});
	
	//////////////////////////////////////
		$routeProvider.when('/proyectos', {
		templateUrl : "pages/proyectos.html",
		controller : "ProyectosController"
	});

	$routeProvider.when('/altaProyecto', {
		templateUrl : "pages/altaProyecto.html",
		controller : "ProyectosController"
	});
	
	$routeProvider.when('/proyectos/edit/:id', {
		templateUrl : "pages/proyectosEdita.html",
		controller : "ProyectosEditController"
	});
	
	/////////////////////////////////////
	
	$routeProvider.when('/requerimientos', {
		templateUrl : "pages/requerimientos.html",
		controller : "RequerimientosController"
	});

	$routeProvider.when('/requerimientos/byProyecto/:id/:pag', {
		templateUrl : "pages/requerimientos.html",
		controller : "RequerimientosController"
	});
	$routeProvider.when('/altaRequerimiento', {
		templateUrl : "pages/altaRequerimiento.html",
		controller : "RequerimientosController"
	});
	$routeProvider.when('/altaRequerimiento/:id', {
		templateUrl : "pages/altaRequerimiento.html",
		controller : "RequerimientosController"
	});
	
	
	$routeProvider.when('/requerimientos/edit/:id', {
		templateUrl : "pages/requerimientosEdita.html",
		controller : "RequerimientosEditController"
	});
	
	/////////////////////////////////////
	
	$routeProvider.when('/entregables', {
		templateUrl : "pages/entregables.html",
		controller : "EntregablesController"
	});

	$routeProvider.when('/entregables/byProyecto/:id/:pag', {
		templateUrl : "pages/entregables.html",
		controller : "EntregablesController"
	});
	$routeProvider.when('/altaEntregable', {
		templateUrl : "pages/altaEntregable.html",
		controller : "EntregablesController"
	});
	$routeProvider.when('/altaEntregable/:id', {
		templateUrl : "pages/altaEntregable.html",
		controller : "EntregablesController"
	});
	
	$routeProvider.when('/entregables/edit/:id', {
		templateUrl : "pages/entregablesEdita.html",
		controller : "EntregablesEditController"
	});
	
	/////////////////////////////////////
	
	$routeProvider.when('/cumplimientos', {
		templateUrl : "pages/cumplimientos.html",
		controller : "CumplimientosController"
	});
	
	$routeProvider.when('/cumFull', {
		templateUrl : "pages/cumFull.html",
		controller : "CumplimientosFullController"
	});

	$routeProvider.when('/cumplimientos/byProyecto/:id/:pag', {
		templateUrl : "pages/cumplimientos.html",
		controller : "CumplimientosController"
	});
	$routeProvider.when('/altaCumplimiento', {
		templateUrl : "pages/altaCumplimiento.html",
		controller : "CumplimientosController"
	});
	$routeProvider.when('/altaCumplimiento/:id', {
		templateUrl : "pages/altaCumplimiento.html",
		controller : "CumplimientoController"
	});
	
	$routeProvider.when('/cumplimientos/edit/:id', {
		templateUrl : "pages/cumplimientosEdita.html",
		controller : "CumplimientosEditController"
	});
	
	/////////////////////////////////////
	
	
	$routeProvider.when('/requerimientos/edit/:id', {
		templateUrl : "pages/requerimientosEdita.html",
		controller : "RequerimientosEditController"
	});
	
	/////////////////////////////////////
	
	$routeProvider.when('/login' ,  {
		templateUrl : "pages/login.html" ,
		controller : "navigation"
	}) ;
	
	$routeProvider.when('/altausuarios', {
		templateUrl : "pages/altausuario.html",
		controller : "usuarioController"
	})

	$routeProvider.when('/modificarusuarios', {
		templateUrl : "pages/modificausuarios.html",
		controller : "controladorListaUsuarios"
	})

	$routeProvider.when('/resetPass', {
		templateUrl : "pages/resetpass.html",
		controller : "controladorReset"
	})
	$routeProvider.when('/archivo', {
		templateUrl : "pages/archivo.html",
		controller : "archivoController"
	});
	
		
	$routeProvider.otherwise({
		redirectTo : '/inicio'
	});
} ]);

app.controller('inicioController',function(){
	
})

app.run(['$rootScope','sessionService',function ($rootScope,sessionService) {
	sessionService.isAuthenticated();
}]);