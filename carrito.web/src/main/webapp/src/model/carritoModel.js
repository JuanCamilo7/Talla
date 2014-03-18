define(['model/_carritoModel'], function() {
    App.Model.CarritoModel = App.Model._CarritoModel.extend({

    });

    App.Model.CarritoList = App.Model._CarritoList.extend({
        model: App.Model.CarritoModel
    });

    return  App.Model.CarritoModel;

});