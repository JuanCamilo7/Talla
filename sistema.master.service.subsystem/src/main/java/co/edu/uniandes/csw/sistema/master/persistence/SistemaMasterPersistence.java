package co.edu.uniandes.csw.sistema.master.persistence;

import javax.ejb.Stateless;

import co.edu.uniandes.csw.sistema.master.persistence.api.ISistemaMasterPersistence;
import javax.ejb.LocalBean;
import javax.enterprise.inject.Default;

@Default
@Stateless 
@LocalBean
public class SistemaMasterPersistence extends _SistemaMasterPersistence  implements ISistemaMasterPersistence {

}