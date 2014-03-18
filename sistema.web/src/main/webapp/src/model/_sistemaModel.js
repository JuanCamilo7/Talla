define([], function() {
    App.Model._SistemaModel = Backbone.Model.extend({
        defaults: {
        },
        initialize: function() {
        },
        getDisplay: function(name) {
         return this.get(name);
        }
    });

    App.Model._SistemaList = Backbone.Collection.extend({
        model: App.Model._SistemaModel,
        initialize: function() {
        }

    });
    return App.Model._SistemaModel;
});