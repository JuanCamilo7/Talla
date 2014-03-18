define([], function() {
    App.Model._CatalogoModel = Backbone.Model.extend({
        defaults: {
 
		 'name' : ''
        },
        initialize: function() {
        },
        getDisplay: function(name) {
         return this.get(name);
        }
    });

    App.Model._CatalogoList = Backbone.Collection.extend({
        model: App.Model._CatalogoModel,
        initialize: function() {
        }

    });
    return App.Model._CatalogoModel;
});