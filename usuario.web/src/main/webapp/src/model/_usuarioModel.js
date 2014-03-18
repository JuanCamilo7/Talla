define([], function() {
    App.Model._UsuarioModel = Backbone.Model.extend({
        defaults: {
 
		 'name' : ''
 ,  
		 'direccionEnvio' : ''
        },
        initialize: function() {
        },
        getDisplay: function(name) {
         return this.get(name);
        }
    });

    App.Model._UsuarioList = Backbone.Collection.extend({
        model: App.Model._UsuarioModel,
        initialize: function() {
        }

    });
    return App.Model._UsuarioModel;
});