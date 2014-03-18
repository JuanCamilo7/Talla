define([], function() {
    App.Model._SistemaMasterModel = Backbone.Model.extend({
     
    });

    App.Model._SistemaMasterList = Backbone.Collection.extend({
        model: App.Model._SistemaMasterModel,
        initialize: function() {
        }

    });
    return App.Model._SistemaMasterModel;
    
});