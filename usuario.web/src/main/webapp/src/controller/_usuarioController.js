define(['model/usuarioModel'], function(usuarioModel) {
    App.Controller._UsuarioController = Backbone.View.extend({
        initialize: function(options) {
            this.modelClass = options.modelClass;
            this.listModelClass = options.listModelClass;
            this.showEdit = true;
            this.showDelete = true;
            this.editTemplate = _.template($('#usuario').html());
            this.listTemplate = _.template($('#usuarioList').html());
            if (!options || !options.componentId) {
                this.componentId = _.random(0, 100) + "";
            }else{
				this.componentId = options.componentId;
		    }
            var self = this;
            Backbone.on(this.componentId + '-' + 'usuario-create', function(params) {
                self.create(params);
            });
            Backbone.on(this.componentId + '-' + 'usuario-list', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'usuario-edit', function(params) {
                self.edit(params);
            });
            Backbone.on(this.componentId + '-' + 'usuario-delete', function(params) {
                self.destroy(params);
            });
            Backbone.on(this.componentId + '-' + 'post-usuario-delete', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'usuario-save', function(params) {
                self.save(params);
            });
            if(self.postInit){
            	self.postInit();
            }
        },
        create: function() {
            if (App.Utils.eventExists(this.componentId + '-' +'instead-usuario-create')) {
                Backbone.trigger(this.componentId + '-' + 'instead-usuario-create', {view: this});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-usuario-create', {view: this});
                this.currentUsuarioModel = new this.modelClass();
                this._renderEdit();
                Backbone.trigger(this.componentId + '-' + 'post-usuario-create', {view: this});
            }
        },
        list: function(params) {
            if (params) {
                var data = params.data;
            }
            if (App.Utils.eventExists(this.componentId + '-' +'instead-usuario-list')) {
                Backbone.trigger(this.componentId + '-' + 'instead-usuario-list', {view: this, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-usuario-list', {view: this, data: data});
                var self = this;
				if(!this.usuarioModelList){
                 this.usuarioModelList = new this.listModelClass();
				}
                this.usuarioModelList.fetch({
                    data: data,
                    success: function() {
                        self._renderList();
                        Backbone.trigger(self.componentId + '-' + 'post-usuario-list', {view: self});
                    },
                    error: function(mode, error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'usuario-list', view: self, error: error});
                    }
                });
            }
        },
        edit: function(params) {
            var id = params.id;
            var data = params.data;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-usuario-edit')) {
                Backbone.trigger(this.componentId + '-' + 'instead-usuario-edit', {view: this, id: id, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-usuario-edit', {view: this, id: id, data: data});
                if (this.usuarioModelList) {
                    this.currentUsuarioModel = this.usuarioModelList.get(id);
                    this._renderEdit();
                    Backbone.trigger(this.componentId + '-' + 'post-usuario-edit', {view: this, id: id, data: data});
                } else {
                    var self = this;
                    this.currentUsuarioModel = new this.modelClass({id: id});
                    this.currentUsuarioModel.fetch({
                        data: data,
                        success: function() {
                            self._renderEdit();
                            Backbone.trigger(self.componentId + '-' + 'post-usuario-edit', {view: this, id: id, data: data});
                        },
                        error: function() {
                            Backbone.trigger(self.componentId + '-' + 'error', {event: 'usuario-edit', view: self, id: id, data: data, error: error});
                        }
                    });
                }
            }
        },
        destroy: function(params) {
            var id = params.id;
            var self = this;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-usuario-delete')) {
                Backbone.trigger(this.componentId + '-' + 'instead-usuario-delete', {view: this, id: id});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-usuario-delete', {view: this, id: id});
                var deleteModel;
                if (this.usuarioModelList) {
                    deleteModel = this.usuarioModelList.get(id);
                } else {
                    deleteModel = new this.modelClass({id: id});
                }
                deleteModel.destroy({
                    success: function() {
                        Backbone.trigger(self.componentId + '-' + 'post-usuario-delete', {view: self, model: deleteModel});
                    },
                    error: function() {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'usuario-delete', view: self, error: error});
                    }
                });
            }
        },
        save: function() {
            var self = this;
            var model = $('#' + this.componentId + '-usuarioForm').serializeObject();
            if (App.Utils.eventExists(this.componentId + '-' +'instead-usuario-save')) {
                Backbone.trigger(this.componentId + '-' + 'instead-usuario-save', {view: this, model : model});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-usuario-save', {view: this, model : model});
                this.currentUsuarioModel.set(model);
                this.currentUsuarioModel.save({},
                        {
                            success: function(model) {
                                Backbone.trigger(self.componentId + '-' + 'post-usuario-save', {model: self.currentUsuarioModel});
                            },
                            error: function(error) {
                                Backbone.trigger(self.componentId + '-' + 'error', {event: 'usuario-save', view: self, error: error});
                            }
                        });
            }
        },
        _renderList: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.listTemplate({usuarios: self.usuarioModelList.models, componentId: self.componentId, showEdit : self.showEdit , showDelete : self.showDelete}));
                self.$el.slideDown("fast");
            });
        },
        _renderEdit: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.editTemplate({usuario: self.currentUsuarioModel, componentId: self.componentId , showEdit : self.showEdit , showDelete : self.showDelete
 
				}));
                self.$el.slideDown("fast");
            });
        }
    });
    return App.Controller._UsuarioController;
});