define(['component/_CRUDComponent', 'controller/toolbarController', 'model/toolbarModel', 'model/carritoModel', 'controller/carritoController'], function() {
    App.Component.CarritoComponent = App.Component._CRUDComponent.extend({
        name: 'carrito',
        model: App.Model.CarritoModel,
        listModel: App.Model.CarritoList,
        controller : App.Controller.CarritoController
    });
    return App.Component.CarritoComponent;
});