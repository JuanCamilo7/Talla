
package co.edu.uniandes.csw.usuario.logic.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.usuario.logic.api.IUsuarioLogicService;

@Default
@Stateless
@LocalBean
public class UsuarioLogicService extends _UsuarioLogicService implements IUsuarioLogicService {

}