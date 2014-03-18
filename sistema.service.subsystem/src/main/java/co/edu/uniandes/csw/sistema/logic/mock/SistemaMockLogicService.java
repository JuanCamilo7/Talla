
package co.edu.uniandes.csw.sistema.logic.mock;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.sistema.logic.api.ISistemaLogicService;

@Alternative
@Singleton
public class SistemaMockLogicService extends _SistemaMockLogicService implements ISistemaLogicService {
	
}