
package co.edu.uniandes.csw.promocion.logic.mock;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.promocion.logic.api.IPromocionLogicService;

@Alternative
@Singleton
public class PromocionMockLogicService extends _PromocionMockLogicService implements IPromocionLogicService {
	
}