define(['component/_CRUDComponent', 'controller/toolbarController', 'model/toolbarModel', 'model/sistemaModel', 'controller/sistemaController'], function() {
    App.Component.SistemaComponent = App.Component._CRUDComponent.extend({
        name: 'sistema',
        model: App.Model.SistemaModel,
        listModel: App.Model.SistemaList,
        controller : App.Controller.SistemaController
    });
    return App.Component.SistemaComponent;
});