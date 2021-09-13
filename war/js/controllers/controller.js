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