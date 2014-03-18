package co.edu.uniandes.csw.usuario.master.logic.ejb;

import co.edu.uniandes.csw.usuario.master.logic.api.IUsuarioMasterLogicService;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

@Default
@Stateless
@LocalBean
public class UsuarioMasterLogicService extends _UsuarioMasterLogicService implements IUsuarioMasterLogicService {

}