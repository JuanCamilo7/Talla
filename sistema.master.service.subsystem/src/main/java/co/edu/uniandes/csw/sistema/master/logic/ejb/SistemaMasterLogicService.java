package co.edu.uniandes.csw.sistema.master.logic.ejb;

import co.edu.uniandes.csw.sistema.master.logic.api.ISistemaMasterLogicService;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

@Default
@Stateless
@LocalBean
public class SistemaMasterLogicService extends _SistemaMasterLogicService implements ISistemaMasterLogicService {

}