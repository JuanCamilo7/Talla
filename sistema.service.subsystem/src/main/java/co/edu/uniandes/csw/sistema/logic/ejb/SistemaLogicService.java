
package co.edu.uniandes.csw.sistema.logic.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.sistema.logic.api.ISistemaLogicService;

@Default
@Stateless
@LocalBean
public class SistemaLogicService extends _SistemaLogicService implements ISistemaLogicService {

}