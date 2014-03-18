define(['component/_CRUDComponent', 'controller/toolbarController', 'model/toolbarModel', 'model/usuarioModel', 'controller/usuarioController'], function() {
    App.Component.UsuarioComponent = App.Component._CRUDComponent.extend({
        name: 'usuario',
        model: App.Model.UsuarioModel,
        listModel: App.Model.UsuarioList,
        controller : App.Controller.UsuarioController
    });
    return App.Component.UsuarioComponent;
});