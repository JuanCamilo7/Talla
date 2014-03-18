define([], function() {
    App.Model._CatalogoMasterModel = Backbone.Model.extend({
     
    });

    App.Model._CatalogoMasterList = Backbone.Collection.extend({
        model: App.Model._CatalogoMasterModel,
        initialize: function() {
        }

    });
    return App.Model._CatalogoMasterModel;
    
});