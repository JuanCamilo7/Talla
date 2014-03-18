define(['model/_usuarioModel'], function() {
    App.Model.UsuarioModel = App.Model._UsuarioModel.extend({

    });

    App.Model.UsuarioList = App.Model._UsuarioList.extend({
        model: App.Model.UsuarioModel
    });

    return  App.Model.UsuarioModel;

});