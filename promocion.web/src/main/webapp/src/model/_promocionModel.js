define([], function() {
    App.Model._PromocionModel = Backbone.Model.extend({
        defaults: {
 
		 'name' : ''
 ,  
		 'fechaInicial' : ''
 ,  
		 'fechaFinal' : ''
 ,  
		 'nuevoPrecio' : ''
        },
        initialize: function() {
        },
        getDisplay: function(name) {
             if(name=='fechaInicial'){
                   var dateConverter = App.Utils.Converter.date;
                   return dateConverter.unserialize(this.get('fechaInicial'), this);
             }
             if(name=='fechaFinal'){
                   var dateConverter = App.Utils.Converter.date;
                   return dateConverter.unserialize(this.get('fechaFinal'), this);
             }
         return this.get(name);
        }
    });

    App.Model._PromocionList = Backbone.Collection.extend({
        model: App.Model._PromocionModel,
        initialize: function() {
        }

    });
    return App.Model._PromocionModel;
});