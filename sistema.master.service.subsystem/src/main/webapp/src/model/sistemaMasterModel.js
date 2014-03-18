define(['model/_sistemaMasterModel'], function() { 
    App.Model.SistemaMasterModel = App.Model._SistemaMasterModel.extend({
		initialize: function() {
            this.on('invalid', function(model,error) {
                Backbone.trigger('sistema-master-model-error', error);
            });
        },
        validate: function(attrs, options){
        	var modelMaster = new App.Model.SistemaModel();
        	if(modelMaster.validate){
            	return modelMaster.validate(attrs.sistemaEntity,options);
            }
        }
    });

    App.Model.SistemaMasterList = App.Model._SistemaMasterList.extend({
        model: App.Model.SistemaMasterModel
    });

    return  App.Model.SistemaMasterModel;

});