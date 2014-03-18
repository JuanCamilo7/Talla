
package co.edu.uniandes.csw.carrito.logic.mock;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.carrito.logic.api.ICarritoLogicService;

@Alternative
@Singleton
public class CarritoMockLogicService extends _CarritoMockLogicService implements ICarritoLogicService {
	
}