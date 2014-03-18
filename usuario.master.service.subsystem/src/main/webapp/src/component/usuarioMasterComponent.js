define(['controller/selectionController', 'model/cacheModel', 'model/usuarioMasterModel', 'component/_CRUDComponent', 'controller/tabController', 'component/usuarioComponent',
 'component/facturaComponent'
 
 ],function(SelectionController, CacheModel, UsuarioMasterModel, CRUDComponent, TabController, UsuarioComponent,
 FacturaComponent
 ) {
    App.Component.UsuarioMasterComponent = App.Component.BasicComponent.extend({
        initialize: function() {
            var self = this;
            this.configuration = App.Utils.loadComponentConfiguration('usuarioMaster');
            var uComponent = new UsuarioComponent();
            uComponent.initialize();
            uComponent.render('main');
            Backbone.on(uComponent.componentId + '-post-usuario-create', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-post-usuario-edit', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-pre-usuario-list', function() {
                self.hideChilds();
            });
            Backbone.on('usuario-master-model-error', function(error) {
                Backbone.trigger(uComponent.componentId + '-' + 'error', {event: 'usuario-master-save', view: self, error: error});
            });
            Backbone.on(uComponent.componentId + '-instead-usuario-save', function(params) {
                self.model.set('usuarioEntity', params.model);
                if (params.model) {
                    self.model.set('id', params.model.id);
                } else {
                    self.model.unset('id');
                }
                var facturaModels = self.facturaComponent.componentController.facturaModelList;
                self.model.set('listFactura', []);
                self.model.set('createFactura', []);
                self.model.set('updateFactura', []);
                self.model.set('deleteFactura', []);
                for (var i = 0; i < facturaModels.models.length; i++) {
                    var m = facturaModels.models[i];
                    var modelCopy = m.clone();
                    if (m.isCreated()) {
                        //set the id to null
                        modelCopy.unset('id');
                        self.model.get('createFactura').push(modelCopy.toJSON());
                    } else if (m.isUpdated()) {
                        self.model.get('updateFactura').push(modelCopy.toJSON());
                    }
                }
                for (var i = 0; i < facturaModels.deletedModels.length; i++) {
                    var m = facturaModels.deletedModels[i];
                    self.model.get('deleteFactura').push(m.toJSON());
                }
                self.model.save({}, {
                    success: function() {
                        uComponent.componentController.list();
                    },
                    error: function(error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'usuario-master-save', view: self, error: error});
                    }
                });
            });
        },
        renderChilds: function(params) {
            var self = this;
            this.tabModel = new App.Model.TabModel(
                    {
                        tabs: [
                            {label: "Factura", name: "factura", enable: true},
                        ]
                    }
            );

            this.tabs = new TabController({model: this.tabModel});

            this.tabs.render('tabs');
            App.Model.UsuarioMasterModel.prototype.urlRoot = this.configuration.context;
            var options = {
                success: function() {
					self.facturaComponent = new FacturaComponent();
                    self.facturaModels = App.Utils.convertToModel(App.Utils.createCacheModel(App.Model.FacturaModel), self.model.get('listFactura'));
                    self.facturaComponent.initialize({
                        modelClass: App.Utils.createCacheModel(App.Model.FacturaModel),
                        listModelClass: App.Utils.createCacheList(App.Model.FacturaModel, App.Model.FacturaList, self.facturaModels)
                    });
                    self.facturaComponent.render(self.tabs.getTabHtmlId('factura'));
                    Backbone.on(self.facturaComponent.componentId + '-post-factura-create', function(params) {
                        params.view.currentFacturaModel.setCacheList(params.view.facturaModelList);
                    });
                    self.facturaToolbarModel = self.facturaComponent.toolbarModel.set(App.Utils.Constans.containmentToolbarConfiguration);
                    self.facturaComponent.setToolbarModel(self.facturaToolbarModel);
                	
                     
                
                    Backbone.on(self.facturaComponent.componentId + '-toolbar-button1', function() {
                        var selection = new App.Controller.SelectionController();
                        App.Utils.getComponentList('facturaComponent', function(componentName, model) {
                            if (model.models.length == 0) {
                                alert('There is no facturas to select.');
                            } else {
                                selection.showSelectionList({list: model, name: 'name', title: 'Factura List'});
                            }
                            ;
                        });
                    });
                    Backbone.on('post-selection', function(model) {
                        var model = new self.facturaComponent.componentController.modelClass(model.toJSON());
                        model.setCacheList(self.facturaComponent.componentController.facturaModelList);
                        model.save({
                            success: function() {
                                self.facturaComponent.componentController.list();
                            }
                        });
                    });
                    $('#tabs').show();
                },
                error: function() {
                    Backbone.trigger(self.componentId + '-' + 'error', {event: 'usuario-edit', view: self, id: id, data: data, error: error});
                }
            };
            if (params.id) {
                self.model = new App.Model.UsuarioMasterModel({id: params.id});
                self.model.fetch(options);
            } else {
                self.model = new App.Model.UsuarioMasterModel();
                options.success();
            }


        },
        hideChilds: function() {
            $('#tabs').hide();
        }
    });

    return App.Component.UsuarioMasterComponent;
});