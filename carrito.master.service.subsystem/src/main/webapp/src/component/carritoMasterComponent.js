define(['controller/selectionController', 'model/cacheModel', 'model/carritoMasterModel', 'component/_CRUDComponent', 'controller/tabController', 'component/carritoComponent',
 'component/productoComponent'
 
 ],function(SelectionController, CacheModel, CarritoMasterModel, CRUDComponent, TabController, CarritoComponent,
 ProductoComponent
 ) {
    App.Component.CarritoMasterComponent = App.Component.BasicComponent.extend({
        initialize: function() {
            var self = this;
            this.configuration = App.Utils.loadComponentConfiguration('carritoMaster');
            var uComponent = new CarritoComponent();
            uComponent.initialize();
            uComponent.render('main');
            Backbone.on(uComponent.componentId + '-post-carrito-create', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-post-carrito-edit', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-pre-carrito-list', function() {
                self.hideChilds();
            });
            Backbone.on('carrito-master-model-error', function(error) {
                Backbone.trigger(uComponent.componentId + '-' + 'error', {event: 'carrito-master-save', view: self, error: error});
            });
            Backbone.on(uComponent.componentId + '-instead-carrito-save', function(params) {
                self.model.set('carritoEntity', params.model);
                if (params.model) {
                    self.model.set('id', params.model.id);
                } else {
                    self.model.unset('id');
                }
                var productoModels = self.productoComponent.componentController.productoModelList;
                self.model.set('listProducto', []);
                self.model.set('createProducto', []);
                self.model.set('updateProducto', []);
                self.model.set('deleteProducto', []);
                for (var i = 0; i < productoModels.models.length; i++) {
                    var m = productoModels.models[i];
                    var modelCopy = m.clone();
                    if (m.isCreated()) {
                        //set the id to null
                        modelCopy.unset('id');
                        self.model.get('createProducto').push(modelCopy.toJSON());
                    } else if (m.isUpdated()) {
                        self.model.get('updateProducto').push(modelCopy.toJSON());
                    }
                }
                for (var i = 0; i < productoModels.deletedModels.length; i++) {
                    var m = productoModels.deletedModels[i];
                    self.model.get('deleteProducto').push(m.toJSON());
                }
                self.model.save({}, {
                    success: function() {
                        uComponent.componentController.list();
                    },
                    error: function(error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'carrito-master-save', view: self, error: error});
                    }
                });
            });
        },
        renderChilds: function(params) {
            var self = this;
            this.tabModel = new App.Model.TabModel(
                    {
                        tabs: [
                            {label: "Producto", name: "producto", enable: true},
                        ]
                    }
            );

            this.tabs = new TabController({model: this.tabModel});

            this.tabs.render('tabs');
            App.Model.CarritoMasterModel.prototype.urlRoot = this.configuration.context;
            var options = {
                success: function() {
					self.productoComponent = new ProductoComponent();
                    self.productoModels = App.Utils.convertToModel(App.Utils.createCacheModel(App.Model.ProductoModel), self.model.get('listProducto'));
                    self.productoComponent.initialize({
                        modelClass: App.Utils.createCacheModel(App.Model.ProductoModel),
                        listModelClass: App.Utils.createCacheList(App.Model.ProductoModel, App.Model.ProductoList, self.productoModels)
                    });
                    self.productoComponent.render(self.tabs.getTabHtmlId('producto'));
                    Backbone.on(self.productoComponent.componentId + '-post-producto-create', function(params) {
                        params.view.currentProductoModel.setCacheList(params.view.productoModelList);
                    });
                    self.productoToolbarModel = self.productoComponent.toolbarModel.set(App.Utils.Constans.containmentToolbarConfiguration);
                    self.productoComponent.setToolbarModel(self.productoToolbarModel);
                	
                     
                
                    Backbone.on(self.productoComponent.componentId + '-toolbar-button1', function() {
                        var selection = new App.Controller.SelectionController();
                        App.Utils.getComponentList('productoComponent', function(componentName, model) {
                            if (model.models.length == 0) {
                                alert('There is no productos to select.');
                            } else {
                                selection.showSelectionList({list: model, name: 'name', title: 'Producto List'});
                            }
                            ;
                        });
                    });
                    Backbone.on('post-selection', function(model) {
                        var model = new self.productoComponent.componentController.modelClass(model.toJSON());
                        model.setCacheList(self.productoComponent.componentController.productoModelList);
                        model.save({
                            success: function() {
                                self.productoComponent.componentController.list();
                            }
                        });
                    });
                    $('#tabs').show();
                },
                error: function() {
                    Backbone.trigger(self.componentId + '-' + 'error', {event: 'carrito-edit', view: self, id: id, data: data, error: error});
                }
            };
            if (params.id) {
                self.model = new App.Model.CarritoMasterModel({id: params.id});
                self.model.fetch(options);
            } else {
                self.model = new App.Model.CarritoMasterModel();
                options.success();
            }


        },
        hideChilds: function() {
            $('#tabs').hide();
        }
    });

    return App.Component.CarritoMasterComponent;
});