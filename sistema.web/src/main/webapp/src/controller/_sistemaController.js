define(['model/sistemaModel'], function(sistemaModel) {
    App.Controller._SistemaController = Backbone.View.extend({
        initialize: function(options) {
            this.modelClass = options.modelClass;
            this.listModelClass = options.listModelClass;
            this.showEdit = true;
            this.showDelete = true;
            this.editTemplate = _.template($('#sistema').html());
            this.listTemplate = _.template($('#sistemaList').html());
            if (!options || !options.componentId) {
                this.componentId = _.random(0, 100) + "";
            }else{
				this.componentId = options.componentId;
		    }
            var self = this;
            Backbone.on(this.componentId + '-' + 'sistema-create', function(params) {
                self.create(params);
            });
            Backbone.on(this.componentId + '-' + 'sistema-list', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'sistema-edit', function(params) {
                self.edit(params);
            });
            Backbone.on(this.componentId + '-' + 'sistema-delete', function(params) {
                self.destroy(params);
            });
            Backbone.on(this.componentId + '-' + 'post-sistema-delete', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'sistema-save', function(params) {
                self.save(params);
            });
            if(self.postInit){
            	self.postInit();
            }
        },
        create: function() {
            if (App.Utils.eventExists(this.componentId + '-' +'instead-sistema-create')) {
                Backbone.trigger(this.componentId + '-' + 'instead-sistema-create', {view: this});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-sistema-create', {view: this});
                this.currentSistemaModel = new this.modelClass();
                this._renderEdit();
                Backbone.trigger(this.componentId + '-' + 'post-sistema-create', {view: this});
            }
        },
        list: function(params) {
            if (params) {
                var data = params.data;
            }
            if (App.Utils.eventExists(this.componentId + '-' +'instead-sistema-list')) {
                Backbone.trigger(this.componentId + '-' + 'instead-sistema-list', {view: this, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-sistema-list', {view: this, data: data});
                var self = this;
				if(!this.sistemaModelList){
                 this.sistemaModelList = new this.listModelClass();
				}
                this.sistemaModelList.fetch({
                    data: data,
                    success: function() {
                        self._renderList();
                        Backbone.trigger(self.componentId + '-' + 'post-sistema-list', {view: self});
                    },
                    error: function(mode, error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'sistema-list', view: self, error: error});
                    }
                });
            }
        },
        edit: function(params) {
            var id = params.id;
            var data = params.data;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-sistema-edit')) {
                Backbone.trigger(this.componentId + '-' + 'instead-sistema-edit', {view: this, id: id, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-sistema-edit', {view: this, id: id, data: data});
                if (this.sistemaModelList) {
                    this.currentSistemaModel = this.sistemaModelList.get(id);
                    this._renderEdit();
                    Backbone.trigger(this.componentId + '-' + 'post-sistema-edit', {view: this, id: id, data: data});
                } else {
                    var self = this;
                    this.currentSistemaModel = new this.modelClass({id: id});
                    this.currentSistemaModel.fetch({
                        data: data,
                        success: function() {
                            self._renderEdit();
                            Backbone.trigger(self.componentId + '-' + 'post-sistema-edit', {view: this, id: id, data: data});
                        },
                        error: function() {
                            Backbone.trigger(self.componentId + '-' + 'error', {event: 'sistema-edit', view: self, id: id, data: data, error: error});
                        }
                    });
                }
            }
        },
        destroy: function(params) {
            var id = params.id;
            var self = this;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-sistema-delete')) {
                Backbone.trigger(this.componentId + '-' + 'instead-sistema-delete', {view: this, id: id});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-sistema-delete', {view: this, id: id});
                var deleteModel;
                if (this.sistemaModelList) {
                    deleteModel = this.sistemaModelList.get(id);
                } else {
                    deleteModel = new this.modelClass({id: id});
                }
                deleteModel.destroy({
                    success: function() {
                        Backbone.trigger(self.componentId + '-' + 'post-sistema-delete', {view: self, model: deleteModel});
                    },
                    error: function() {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'sistema-delete', view: self, error: error});
                    }
                });
            }
        },
        save: function() {
            var self = this;
            var model = $('#' + this.componentId + '-sistemaForm').serializeObject();
            if (App.Utils.eventExists(this.componentId + '-' +'instead-sistema-save')) {
                Backbone.trigger(this.componentId + '-' + 'instead-sistema-save', {view: this, model : model});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-sistema-save', {view: this, model : model});
                this.currentSistemaModel.set(model);
                this.currentSistemaModel.save({},
                        {
                            success: function(model) {
                                Backbone.trigger(self.componentId + '-' + 'post-sistema-save', {model: self.currentSistemaModel});
                            },
                            error: function(error) {
                                Backbone.trigger(self.componentId + '-' + 'error', {event: 'sistema-save', view: self, error: error});
                            }
                        });
            }
        },
        _renderList: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.listTemplate({sistemas: self.sistemaModelList.models, componentId: self.componentId, showEdit : self.showEdit , showDelete : self.showDelete}));
                self.$el.slideDown("fast");
            });
        },
        _renderEdit: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.editTemplate({sistema: self.currentSistemaModel, componentId: self.componentId , showEdit : self.showEdit , showDelete : self.showDelete
 
				}));
                self.$el.slideDown("fast");
            });
        }
    });
    return App.Controller._SistemaController;
});