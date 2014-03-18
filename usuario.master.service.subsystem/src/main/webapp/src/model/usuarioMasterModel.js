define(['model/_usuarioMasterModel'], function() { 
    App.Model.UsuarioMasterModel = App.Model._UsuarioMasterModel.extend({
		initialize: function() {
            this.on('invalid', function(model,error) {
                Backbone.trigger('usuario-master-model-error', error);
            });
        },
        validate: function(attrs, options){
        	var modelMaster = new App.Model.UsuarioModel();
        	if(modelMaster.validate){
            	return modelMaster.validate(attrs.usuarioEntity,options);
            }
        }
    });

    App.Model.UsuarioMasterList = App.Model._UsuarioMasterList.extend({
        model: App.Model.UsuarioMasterModel
    });

    return  App.Model.UsuarioMasterModel;

});