package co.edu.uniandes.csw.carrito.master.persistence;

import javax.ejb.Stateless;

import co.edu.uniandes.csw.carrito.master.persistence.api.ICarritoMasterPersistence;
import javax.ejb.LocalBean;
import javax.enterprise.inject.Default;

@Default
@Stateless 
@LocalBean
public class CarritoMasterPersistence extends _CarritoMasterPersistence  implements ICarritoMasterPersistence {

}