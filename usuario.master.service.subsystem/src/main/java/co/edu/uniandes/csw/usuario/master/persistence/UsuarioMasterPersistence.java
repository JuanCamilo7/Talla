package co.edu.uniandes.csw.usuario.master.persistence;

import javax.ejb.Stateless;

import co.edu.uniandes.csw.usuario.master.persistence.api.IUsuarioMasterPersistence;
import javax.ejb.LocalBean;
import javax.enterprise.inject.Default;

@Default
@Stateless 
@LocalBean
public class UsuarioMasterPersistence extends _UsuarioMasterPersistence  implements IUsuarioMasterPersistence {

}