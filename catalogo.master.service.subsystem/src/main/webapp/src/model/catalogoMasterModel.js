define(['model/_catalogoMasterModel'], function() { 
    App.Model.CatalogoMasterModel = App.Model._CatalogoMasterModel.extend({
		initialize: function() {
            this.on('invalid', function(model,error) {
                Backbone.trigger('catalogo-master-model-error', error);
            });
        },
        validate: function(attrs, options){
        	var modelMaster = new App.Model.CatalogoModel();
        	if(modelMaster.validate){
            	return modelMaster.validate(attrs.catalogoEntity,options);
            }
        }
    });

    App.Model.CatalogoMasterList = App.Model._CatalogoMasterList.extend({
        model: App.Model.CatalogoMasterModel
    });

    return  App.Model.CatalogoMasterModel;

});