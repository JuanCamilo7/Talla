package co.edu.uniandes.csw.catalogo.master.persistence;

import javax.ejb.Stateless;

import co.edu.uniandes.csw.catalogo.master.persistence.api.ICatalogoMasterPersistence;
import javax.ejb.LocalBean;
import javax.enterprise.inject.Default;

@Default
@Stateless 
@LocalBean
public class CatalogoMasterPersistence extends _CatalogoMasterPersistence  implements ICatalogoMasterPersistence {

}