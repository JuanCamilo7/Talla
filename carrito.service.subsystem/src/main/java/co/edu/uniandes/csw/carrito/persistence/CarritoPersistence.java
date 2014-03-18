
package co.edu.uniandes.csw.carrito.persistence;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.carrito.persistence.api.ICarritoPersistence;
import javax.ejb.LocalBean;

@Default
@Stateless 
@LocalBean
public class CarritoPersistence extends _CarritoPersistence  implements ICarritoPersistence {

}