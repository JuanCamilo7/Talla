
package co.edu.uniandes.csw.usuario.persistence;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.usuario.persistence.api.IUsuarioPersistence;
import javax.ejb.LocalBean;

@Default
@Stateless 
@LocalBean
public class UsuarioPersistence extends _UsuarioPersistence  implements IUsuarioPersistence {

}