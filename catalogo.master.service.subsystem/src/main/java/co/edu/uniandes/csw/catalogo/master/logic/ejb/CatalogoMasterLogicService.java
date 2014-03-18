package co.edu.uniandes.csw.catalogo.master.logic.ejb;

import co.edu.uniandes.csw.catalogo.master.logic.api.ICatalogoMasterLogicService;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

@Default
@Stateless
@LocalBean
public class CatalogoMasterLogicService extends _CatalogoMasterLogicService implements ICatalogoMasterLogicService {

}