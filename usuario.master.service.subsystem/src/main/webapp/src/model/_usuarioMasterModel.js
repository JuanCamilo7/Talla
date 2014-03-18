define([], function() {
    App.Model._UsuarioMasterModel = Backbone.Model.extend({
     
    });

    App.Model._UsuarioMasterList = Backbone.Collection.extend({
        model: App.Model._UsuarioMasterModel,
        initialize: function() {
        }

    });
    return App.Model._UsuarioMasterModel;
    
});