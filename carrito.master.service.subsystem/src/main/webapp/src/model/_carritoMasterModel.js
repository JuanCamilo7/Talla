define([], function() {
    App.Model._CarritoMasterModel = Backbone.Model.extend({
     
    });

    App.Model._CarritoMasterList = Backbone.Collection.extend({
        model: App.Model._CarritoMasterModel,
        initialize: function() {
        }

    });
    return App.Model._CarritoMasterModel;
    
});