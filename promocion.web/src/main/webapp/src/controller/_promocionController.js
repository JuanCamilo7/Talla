define(['model/promocionModel'], function(promocionModel) {
    App.Controller._PromocionController = Backbone.View.extend({
        initialize: function(options) {
            this.modelClass = options.modelClass;
            this.listModelClass = options.listModelClass;
            this.showEdit = true;
            this.showDelete = true;
            this.editTemplate = _.template($('#promocion').html());
            this.listTemplate = _.template($('#promocionList').html());
            if (!options || !options.componentId) {
                this.componentId = _.random(0, 100) + "";
            }else{
				this.componentId = options.componentId;
		    }
            var self = this;
            Backbone.on(this.componentId + '-' + 'promocion-create', function(params) {
                self.create(params);
            });
            Backbone.on(this.componentId + '-' + 'promocion-list', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'promocion-edit', function(params) {
                self.edit(params);
            });
            Backbone.on(this.componentId + '-' + 'promocion-delete', function(params) {
                self.destroy(params);
            });
            Backbone.on(this.componentId + '-' + 'post-promocion-delete', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'promocion-save', function(params) {
                self.save(params);
            });
            if(self.postInit){
            	self.postInit();
            }
        },
        create: function() {
            if (App.Utils.eventExists(this.componentId + '-' +'instead-promocion-create')) {
                Backbone.trigger(this.componentId + '-' + 'instead-promocion-create', {view: this});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-promocion-create', {view: this});
                this.currentPromocionModel = new this.modelClass();
                this._renderEdit();
                Backbone.trigger(this.componentId + '-' + 'post-promocion-create', {view: this});
            }
        },
        list: function(params) {
            if (params) {
                var data = params.data;
            }
            if (App.Utils.eventExists(this.componentId + '-' +'instead-promocion-list')) {
                Backbone.trigger(this.componentId + '-' + 'instead-promocion-list', {view: this, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-promocion-list', {view: this, data: data});
                var self = this;
				if(!this.promocionModelList){
                 this.promocionModelList = new this.listModelClass();
				}
                this.promocionModelList.fetch({
                    data: data,
                    success: function() {
                        self._renderList();
                        Backbone.trigger(self.componentId + '-' + 'post-promocion-list', {view: self});
                    },
                    error: function(mode, error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'promocion-list', view: self, error: error});
                    }
                });
            }
        },
        edit: function(params) {
            var id = params.id;
            var data = params.data;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-promocion-edit')) {
                Backbone.trigger(this.componentId + '-' + 'instead-promocion-edit', {view: this, id: id, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-promocion-edit', {view: this, id: id, data: data});
                if (this.promocionModelList) {
                    this.currentPromocionModel = this.promocionModelList.get(id);
                    this._renderEdit();
                    Backbone.trigger(this.componentId + '-' + 'post-promocion-edit', {view: this, id: id, data: data});
                } else {
                    var self = this;
                    this.currentPromocionModel = new this.modelClass({id: id});
                    this.currentPromocionModel.fetch({
                        data: data,
                        success: function() {
                            self._renderEdit();
                            Backbone.trigger(self.componentId + '-' + 'post-promocion-edit', {view: this, id: id, data: data});
                        },
                        error: function() {
                            Backbone.trigger(self.componentId + '-' + 'error', {event: 'promocion-edit', view: self, id: id, data: data, error: error});
                        }
                    });
                }
            }
        },
        destroy: function(params) {
            var id = params.id;
            var self = this;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-promocion-delete')) {
                Backbone.trigger(this.componentId + '-' + 'instead-promocion-delete', {view: this, id: id});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-promocion-delete', {view: this, id: id});
                var deleteModel;
                if (this.promocionModelList) {
                    deleteModel = this.promocionModelList.get(id);
                } else {
                    deleteModel = new this.modelClass({id: id});
                }
                deleteModel.destroy({
                    success: function() {
                        Backbone.trigger(self.componentId + '-' + 'post-promocion-delete', {view: self, model: deleteModel});
                    },
                    error: function() {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'promocion-delete', view: self, error: error});
                    }
                });
            }
        },
        save: function() {
            var self = this;
            var model = $('#' + this.componentId + '-promocionForm').serializeObject();
            if (App.Utils.eventExists(this.componentId + '-' +'instead-promocion-save')) {
                Backbone.trigger(this.componentId + '-' + 'instead-promocion-save', {view: this, model : model});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-promocion-save', {view: this, model : model});
                this.currentPromocionModel.set(model);
                this.currentPromocionModel.save({},
                        {
                            success: function(model) {
                                Backbone.trigger(self.componentId + '-' + 'post-promocion-save', {model: self.currentPromocionModel});
                            },
                            error: function(error) {
                                Backbone.trigger(self.componentId + '-' + 'error', {event: 'promocion-save', view: self, error: error});
                            }
                        });
            }
        },
        _renderList: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.listTemplate({promocions: self.promocionModelList.models, componentId: self.componentId, showEdit : self.showEdit , showDelete : self.showDelete}));
                self.$el.slideDown("fast");
            });
        },
        _renderEdit: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.editTemplate({promocion: self.currentPromocionModel, componentId: self.componentId , showEdit : self.showEdit , showDelete : self.showDelete
 
				}));
                self.$el.slideDown("fast");
            });
        }
    });
    return App.Controller._PromocionController;
});