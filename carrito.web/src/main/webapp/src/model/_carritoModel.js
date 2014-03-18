define([], function() {
    App.Model._CarritoModel = Backbone.Model.extend({
        defaults: {
 
		 'name' : ''
        },
        initialize: function() {
        },
        getDisplay: function(name) {
         return this.get(name);
        }
    });

    App.Model._CarritoList = Backbone.Collection.extend({
        model: App.Model._CarritoModel,
        initialize: function() {
        }

    });
    return App.Model._CarritoModel;
});