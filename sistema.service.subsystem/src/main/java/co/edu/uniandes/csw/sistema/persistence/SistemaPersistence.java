
package co.edu.uniandes.csw.sistema.persistence;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.sistema.persistence.api.ISistemaPersistence;
import javax.ejb.LocalBean;

@Default
@Stateless 
@LocalBean
public class SistemaPersistence extends _SistemaPersistence  implements ISistemaPersistence {

}