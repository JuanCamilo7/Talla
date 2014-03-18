define(['model/_promocionModel'], function() {
    App.Model.PromocionModel = App.Model._PromocionModel.extend({

    });

    App.Model.PromocionList = App.Model._PromocionList.extend({
        model: App.Model.PromocionModel
    });

    return  App.Model.PromocionModel;

});