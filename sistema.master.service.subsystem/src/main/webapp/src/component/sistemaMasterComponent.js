define(['controller/selectionController', 'model/cacheModel', 'model/sistemaMasterModel', 'component/_CRUDComponent', 'controller/tabController', 'component/sistemaComponent',
 'component/usuarioComponent'
 ,
 'component/facturaComponent'
 
 ],function(SelectionController, CacheModel, SistemaMasterModel, CRUDComponent, TabController, SistemaComponent,
 UsuarioComponent
 ,
 FacturaComponent
 ) {
    App.Component.SistemaMasterComponent = App.Component.BasicComponent.extend({
        initialize: function() {
            var self = this;
            this.configuration = App.Utils.loadComponentConfiguration('sistemaMaster');
            var uComponent = new SistemaComponent();
            uComponent.initialize();
            uComponent.render('main');
            Backbone.on(uComponent.componentId + '-post-sistema-create', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-post-sistema-edit', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-pre-sistema-list', function() {
                self.hideChilds();
            });
            Backbone.on('sistema-master-model-error', function(error) {
                Backbone.trigger(uComponent.componentId + '-' + 'error', {event: 'sistema-master-save', view: self, error: error});
            });
            Backbone.on(uComponent.componentId + '-instead-sistema-save', function(params) {
                self.model.set('sistemaEntity', params.model);
                if (params.model) {
                    self.model.set('id', params.model.id);
                } else {
                    self.model.unset('id');
                }
                var usuarioModels = self.usuarioComponent.componentController.usuarioModelList;
                self.model.set('listUsuario', []);
                self.model.set('createUsuario', []);
                self.model.set('updateUsuario', []);
                self.model.set('deleteUsuario', []);
                for (var i = 0; i < usuarioModels.models.length; i++) {
                    var m = usuarioModels.models[i];
                    var modelCopy = m.clone();
                    if (m.isCreated()) {
                        //set the id to null
                        modelCopy.unset('id');
                        self.model.get('createUsuario').push(modelCopy.toJSON());
                    } else if (m.isUpdated()) {
                        self.model.get('updateUsuario').push(modelCopy.toJSON());
                    }
                }
                for (var i = 0; i < usuarioModels.deletedModels.length; i++) {
                    var m = usuarioModels.deletedModels[i];
                    self.model.get('deleteUsuario').push(m.toJSON());
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
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'sistema-master-save', view: self, error: error});
                    }
                });
            });
        },
        renderChilds: function(params) {
            var self = this;
            this.tabModel = new App.Model.TabModel(
                    {
                        tabs: [
                            {label: "Usuario", name: "usuario", enable: true},
                            ,
                            {label: "Factura", name: "factura", enable: true},
                        ]
                    }
            );

            this.tabs = new TabController({model: this.tabModel});

            this.tabs.render('tabs');
            App.Model.SistemaMasterModel.prototype.urlRoot = this.configuration.context;
            var options = {
                success: function() {
					self.usuarioComponent = new UsuarioComponent();
                    self.usuarioModels = App.Utils.convertToModel(App.Utils.createCacheModel(App.Model.UsuarioModel), self.model.get('listUsuario'));
                    self.usuarioComponent.initialize({
                        modelClass: App.Utils.createCacheModel(App.Model.UsuarioModel),
                        listModelClass: App.Utils.createCacheList(App.Model.UsuarioModel, App.Model.UsuarioList, self.usuarioModels)
                    });
                    self.usuarioComponent.render(self.tabs.getTabHtmlId('usuario'));
                    Backbone.on(self.usuarioComponent.componentId + '-post-usuario-create', function(params) {
                        params.view.currentUsuarioModel.setCacheList(params.view.usuarioModelList);
                    });
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
                    self.usuarioToolbarModel = self.usuarioComponent.toolbarModel.set(App.Utils.Constans.containmentToolbarConfiguration);
                    self.usuarioComponent.setToolbarModel(self.usuarioToolbarModel);
                    self.facturaToolbarModel = self.facturaComponent.toolbarModel.set(App.Utils.Constans.containmentToolbarConfiguration);
                    self.facturaComponent.setToolbarModel(self.facturaToolbarModel);
                	
                     
                
                    Backbone.on(self.usuarioComponent.componentId + '-toolbar-button1', function() {
                        var selection = new App.Controller.SelectionController();
                        App.Utils.getComponentList('usuarioComponent', function(componentName, model) {
                            if (model.models.length == 0) {
                                alert('There is no usuarios to select.');
                            } else {
                                selection.showSelectionList({list: model, name: 'name', title: 'Usuario List'});
                            }
                            ;
                        });
                    });
                    Backbone.on('post-selection', function(model) {
                        var model = new self.usuarioComponent.componentController.modelClass(model.toJSON());
                        model.setCacheList(self.usuarioComponent.componentController.usuarioModelList);
                        model.save({
                            success: function() {
                                self.usuarioComponent.componentController.list();
                            }
                        });
                    });
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
                    Backbone.trigger(self.componentId + '-' + 'error', {event: 'sistema-edit', view: self, id: id, data: data, error: error});
                }
            };
            if (params.id) {
                self.model = new App.Model.SistemaMasterModel({id: params.id});
                self.model.fetch(options);
            } else {
                self.model = new App.Model.SistemaMasterModel();
                options.success();
            }


        },
        hideChilds: function() {
            $('#tabs').hide();
        }
    });

    return App.Component.SistemaMasterComponent;
});