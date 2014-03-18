define(['model/_sistemaModel'], function() {
    App.Model.SistemaModel = App.Model._SistemaModel.extend({

    });

    App.Model.SistemaList = App.Model._SistemaList.extend({
        model: App.Model.SistemaModel
    });

    return  App.Model.SistemaModel;

});