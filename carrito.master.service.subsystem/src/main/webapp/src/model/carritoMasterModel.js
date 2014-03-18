define(['model/_carritoMasterModel'], function() { 
    App.Model.CarritoMasterModel = App.Model._CarritoMasterModel.extend({
		initialize: function() {
            this.on('invalid', function(model,error) {
                Backbone.trigger('carrito-master-model-error', error);
            });
        },
        validate: function(attrs, options){
        	var modelMaster = new App.Model.CarritoModel();
        	if(modelMaster.validate){
            	return modelMaster.validate(attrs.carritoEntity,options);
            }
        }
    });

    App.Model.CarritoMasterList = App.Model._CarritoMasterList.extend({
        model: App.Model.CarritoMasterModel
    });

    return  App.Model.CarritoMasterModel;

});