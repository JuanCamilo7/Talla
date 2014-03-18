define(['model/carritoModel'], function(carritoModel) {
    App.Controller._CarritoController = Backbone.View.extend({
        initialize: function(options) {
            this.modelClass = options.modelClass;
            this.listModelClass = options.listModelClass;
            this.showEdit = true;
            this.showDelete = true;
            this.editTemplate = _.template($('#carrito').html());
            this.listTemplate = _.template($('#carritoList').html());
            if (!options || !options.componentId) {
                this.componentId = _.random(0, 100) + "";
            }else{
				this.componentId = options.componentId;
		    }
            var self = this;
            Backbone.on(this.componentId + '-' + 'carrito-create', function(params) {
                self.create(params);
            });
            Backbone.on(this.componentId + '-' + 'carrito-list', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'carrito-edit', function(params) {
                self.edit(params);
            });
            Backbone.on(this.componentId + '-' + 'carrito-delete', function(params) {
                self.destroy(params);
            });
            Backbone.on(this.componentId + '-' + 'post-carrito-delete', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'carrito-save', function(params) {
                self.save(params);
            });
            if(self.postInit){
            	self.postInit();
            }
        },
        create: function() {
            if (App.Utils.eventExists(this.componentId + '-' +'instead-carrito-create')) {
                Backbone.trigger(this.componentId + '-' + 'instead-carrito-create', {view: this});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-carrito-create', {view: this});
                this.currentCarritoModel = new this.modelClass();
                this._renderEdit();
                Backbone.trigger(this.componentId + '-' + 'post-carrito-create', {view: this});
            }
        },
        list: function(params) {
            if (params) {
                var data = params.data;
            }
            if (App.Utils.eventExists(this.componentId + '-' +'instead-carrito-list')) {
                Backbone.trigger(this.componentId + '-' + 'instead-carrito-list', {view: this, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-carrito-list', {view: this, data: data});
                var self = this;
				if(!this.carritoModelList){
                 this.carritoModelList = new this.listModelClass();
				}
                this.carritoModelList.fetch({
                    data: data,
                    success: function() {
                        self._renderList();
                        Backbone.trigger(self.componentId + '-' + 'post-carrito-list', {view: self});
                    },
                    error: function(mode, error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'carrito-list', view: self, error: error});
                    }
                });
            }
        },
        edit: function(params) {
            var id = params.id;
            var data = params.data;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-carrito-edit')) {
                Backbone.trigger(this.componentId + '-' + 'instead-carrito-edit', {view: this, id: id, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-carrito-edit', {view: this, id: id, data: data});
                if (this.carritoModelList) {
                    this.currentCarritoModel = this.carritoModelList.get(id);
                    this._renderEdit();
                    Backbone.trigger(this.componentId + '-' + 'post-carrito-edit', {view: this, id: id, data: data});
                } else {
                    var self = this;
                    this.currentCarritoModel = new this.modelClass({id: id});
                    this.currentCarritoModel.fetch({
                        data: data,
                        success: function() {
                            self._renderEdit();
                            Backbone.trigger(self.componentId + '-' + 'post-carrito-edit', {view: this, id: id, data: data});
                        },
                        error: function() {
                            Backbone.trigger(self.componentId + '-' + 'error', {event: 'carrito-edit', view: self, id: id, data: data, error: error});
                        }
                    });
                }
            }
        },
        destroy: function(params) {
            var id = params.id;
            var self = this;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-carrito-delete')) {
                Backbone.trigger(this.componentId + '-' + 'instead-carrito-delete', {view: this, id: id});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-carrito-delete', {view: this, id: id});
                var deleteModel;
                if (this.carritoModelList) {
                    deleteModel = this.carritoModelList.get(id);
                } else {
                    deleteModel = new this.modelClass({id: id});
                }
                deleteModel.destroy({
                    success: function() {
                        Backbone.trigger(self.componentId + '-' + 'post-carrito-delete', {view: self, model: deleteModel});
                    },
                    error: function() {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'carrito-delete', view: self, error: error});
                    }
                });
            }
        },
        save: function() {
            var self = this;
            var model = $('#' + this.componentId + '-carritoForm').serializeObject();
            if (App.Utils.eventExists(this.componentId + '-' +'instead-carrito-save')) {
                Backbone.trigger(this.componentId + '-' + 'instead-carrito-save', {view: this, model : model});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-carrito-save', {view: this, model : model});
                this.currentCarritoModel.set(model);
                this.currentCarritoModel.save({},
                        {
                            success: function(model) {
                                Backbone.trigger(self.componentId + '-' + 'post-carrito-save', {model: self.currentCarritoModel});
                            },
                            error: function(error) {
                                Backbone.trigger(self.componentId + '-' + 'error', {event: 'carrito-save', view: self, error: error});
                            }
                        });
            }
        },
        _renderList: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.listTemplate({carritos: self.carritoModelList.models, componentId: self.componentId, showEdit : self.showEdit , showDelete : self.showDelete}));
                self.$el.slideDown("fast");
            });
        },
        _renderEdit: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.editTemplate({carrito: self.currentCarritoModel, componentId: self.componentId , showEdit : self.showEdit , showDelete : self.showDelete
 
				}));
                self.$el.slideDown("fast");
            });
        }
    });
    return App.Controller._CarritoController;
});