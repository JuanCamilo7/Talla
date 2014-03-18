
package co.edu.uniandes.csw.usuario.logic.mock;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.usuario.logic.api.IUsuarioLogicService;

@Alternative
@Singleton
public class UsuarioMockLogicService extends _UsuarioMockLogicService implements IUsuarioLogicService {
	
}