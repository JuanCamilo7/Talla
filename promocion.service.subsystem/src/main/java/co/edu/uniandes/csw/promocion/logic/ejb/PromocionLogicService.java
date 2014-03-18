
package co.edu.uniandes.csw.promocion.logic.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.promocion.logic.api.IPromocionLogicService;

@Default
@Stateless
@LocalBean
public class PromocionLogicService extends _PromocionLogicService implements IPromocionLogicService {

}