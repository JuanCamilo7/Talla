define(['component/_CRUDComponent', 'controller/toolbarController', 'model/toolbarModel', 'model/promocionModel', 'controller/promocionController'], function() {
    App.Component.PromocionComponent = App.Component._CRUDComponent.extend({
        name: 'promocion',
        model: App.Model.PromocionModel,
        listModel: App.Model.PromocionList,
        controller : App.Controller.PromocionController
    });
    return App.Component.PromocionComponent;
});