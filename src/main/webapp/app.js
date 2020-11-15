var app = angular.module('myApp', []);

app.controller('controller', function ($scope, $http) {

    $scope.postagemPage = 0;
    $scope.storyPage = 0;
    $scope.postagens = [];
    $scope.carregarMaisPostagens = carregarMaisPostagens;
    $scope.postar = postar;
    $scope.comentar = comentar;
    $scope.selecionaUsuario = selecionaUsuario;

    function selecionaUsuario(usuario) {
        $scope.usuario = usuario;
    }

    function carregarPostagem() {
        $http({
            method: "GET",
            url: "api/postagem?page=" + $scope.postagemPage + "&limit=10",
        }).then(function mySuccess(response) {
            $scope.postagens = $scope.postagens.concat(response.data);
        }, function myError(response) {
            $scope.postagens = [];
        });
    }

    function postar() {
        if ($scope.textoPostagem) {
            var postagem = {usuario: $scope.usuario, conteudo: $scope.textoPostagem, criadoEm: new Date()};
            $http({
                method: "POST",
                url: "api/postagem",
                data: postagem
            }).then(function mySuccess(response) {
                $scope.postagens = [];
                carregarPostagem();
                $scope.textoPostagem = "";
            }, function myError(response) {
                alert("Erro ao postar ", response);
            });
        }
    }

    function carregarMaisPostagens() {
        $scope.postagemPage++;
        carregarPostagem();
    }

    function carregarUsuarios() {
        $http({
            method: "GET",
            url: "api/usuario?page=" + $scope.postagemPage + "&limit=10",
        }).then(function mySuccess(response) {
            $scope.usuarios = response.data;
            $scope.usuario = $scope.usuarios[$scope.usuarios.length - 1]
        }, function myError(response) {
            $scope.usuarios = [];
        });
    }

    function comentar(postagem) {
        if (postagem.novoComentario) {
            var comentario = {postagem: postagem, usuario: $scope.usuario, conteudo: postagem.novoComentario, criadoEm: new Date()};
            $http({
                method: "POST",
                url: "api/comentario",
                data: comentario
            }).then(function mySuccess(response) {
                postagem.comentarios.push(response.data);
                postagem.novoComentario = "";
            }, function myError(response) {
                alert("Erro ao comentar ", response);
            });
        }
    }


    carregarUsuarios();
    carregarPostagem();

});
