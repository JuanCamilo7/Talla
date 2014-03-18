
package co.edu.uniandes.csw.promocion.persistence;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.promocion.persistence.api.IPromocionPersistence;
import javax.ejb.LocalBean;

@Default
@Stateless 
@LocalBean
public class PromocionPersistence extends _PromocionPersistence  implements IPromocionPersistence {

}