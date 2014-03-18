define(['controller/selectionController', 'model/cacheModel', 'model/productoMasterModel', 'component/_CRUDComponent', 'controller/tabController', 'component/productoComponent',
 'component/promocionComponent'
 
 ],function(SelectionController, CacheModel, ProductoMasterModel, CRUDComponent, TabController, ProductoComponent,
 PromocionComponent
 ) {
    App.Component.ProductoMasterComponent = App.Component.BasicComponent.extend({
        initialize: function() {
            var self = this;
            this.configuration = App.Utils.loadComponentConfiguration('productoMaster');
            var uComponent = new ProductoComponent();
            uComponent.initialize();
            uComponent.render('main');
            Backbone.on(uComponent.componentId + '-post-producto-create', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-post-producto-edit', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-pre-producto-list', function() {
                self.hideChilds();
            });
            Backbone.on('producto-master-model-error', function(error) {
                Backbone.trigger(uComponent.componentId + '-' + 'error', {event: 'producto-master-save', view: self, error: error});
            });
            Backbone.on(uComponent.componentId + '-instead-producto-save', function(params) {
                self.model.set('productoEntity', params.model);
                if (params.model) {
                    self.model.set('id', params.model.id);
                } else {
                    self.model.unset('id');
                }
                var promocionModels = self.promocionComponent.componentController.promocionModelList;
                self.model.set('listPromocion', []);
                self.model.set('createPromocion', []);
                self.model.set('updatePromocion', []);
                self.model.set('deletePromocion', []);
                for (var i = 0; i < promocionModels.models.length; i++) {
                    var m = promocionModels.models[i];
                    var modelCopy = m.clone();
                    if (m.isCreated()) {
                        //set the id to null
                        modelCopy.unset('id');
                        self.model.get('createPromocion').push(modelCopy.toJSON());
                    } else if (m.isUpdated()) {
                        self.model.get('updatePromocion').push(modelCopy.toJSON());
                    }
                }
                for (var i = 0; i < promocionModels.deletedModels.length; i++) {
                    var m = promocionModels.deletedModels[i];
                    self.model.get('deletePromocion').push(m.toJSON());
                }
                self.model.save({}, {
                    success: function() {
                        uComponent.componentController.list();
                    },
                    error: function(error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'producto-master-save', view: self, error: error});
                    }
                });
            });
        },
        renderChilds: function(params) {
            var self = this;
            this.tabModel = new App.Model.TabModel(
                    {
                        tabs: [
                            {label: "Promocion", name: "promocion", enable: true},
                        ]
                    }
            );

            this.tabs = new TabController({model: this.tabModel});

            this.tabs.render('tabs');
            App.Model.ProductoMasterModel.prototype.urlRoot = this.configuration.context;
            var options = {
                success: function() {
					self.promocionComponent = new PromocionComponent();
                    self.promocionModels = App.Utils.convertToModel(App.Utils.createCacheModel(App.Model.PromocionModel), self.model.get('listPromocion'));
                    self.promocionComponent.initialize({
                        modelClass: App.Utils.createCacheModel(App.Model.PromocionModel),
                        listModelClass: App.Utils.createCacheList(App.Model.PromocionModel, App.Model.PromocionList, self.promocionModels)
                    });
                    self.promocionComponent.render(self.tabs.getTabHtmlId('promocion'));
                    Backbone.on(self.promocionComponent.componentId + '-post-promocion-create', function(params) {
                        params.view.currentPromocionModel.setCacheList(params.view.promocionModelList);
                    });
                    self.promocionToolbarModel = self.promocionComponent.toolbarModel.set(App.Utils.Constans.containmentToolbarConfiguration);
                    self.promocionComponent.setToolbarModel(self.promocionToolbarModel);
                	
                     
                
                    Backbone.on(self.promocionComponent.componentId + '-toolbar-button1', function() {
                        var selection = new App.Controller.SelectionController();
                        App.Utils.getComponentList('promocionComponent', function(componentName, model) {
                            if (model.models.length == 0) {
                                alert('There is no promocions to select.');
                            } else {
                                selection.showSelectionList({list: model, name: 'name', title: 'Promocion List'});
                            }
                            ;
                        });
                    });
                    Backbone.on('post-selection', function(model) {
                        var model = new self.promocionComponent.componentController.modelClass(model.toJSON());
                        model.setCacheList(self.promocionComponent.componentController.promocionModelList);
                        model.save({
                            success: function() {
                                self.promocionComponent.componentController.list();
                            }
                        });
                    });
                    $('#tabs').show();
                },
                error: function() {
                    Backbone.trigger(self.componentId + '-' + 'error', {event: 'producto-edit', view: self, id: id, data: data, error: error});
                }
            };
            if (params.id) {
                self.model = new App.Model.ProductoMasterModel({id: params.id});
                self.model.fetch(options);
            } else {
                self.model = new App.Model.ProductoMasterModel();
                options.success();
            }


        },
        hideChilds: function() {
            $('#tabs').hide();
        }
    });

    return App.Component.ProductoMasterComponent;
});