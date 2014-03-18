package co.edu.uniandes.csw.carrito.master.logic.ejb;

import co.edu.uniandes.csw.carrito.master.logic.api.ICarritoMasterLogicService;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

@Default
@Stateless
@LocalBean
public class CarritoMasterLogicService extends _CarritoMasterLogicService implements ICarritoMasterLogicService {

}