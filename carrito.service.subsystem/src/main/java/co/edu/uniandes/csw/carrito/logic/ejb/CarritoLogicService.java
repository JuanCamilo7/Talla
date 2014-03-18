
package co.edu.uniandes.csw.carrito.logic.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.carrito.logic.api.ICarritoLogicService;

@Default
@Stateless
@LocalBean
public class CarritoLogicService extends _CarritoLogicService implements ICarritoLogicService {

}