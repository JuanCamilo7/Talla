
package co.edu.uniandes.csw.usuario.logic.ejb;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.*;


import co.edu.uniandes.csw.usuario.logic.dto.UsuarioDTO;
import co.edu.uniandes.csw.usuario.logic.api.IUsuarioLogicService;
import co.edu.uniandes.csw.usuario.persistence.UsuarioPersistence;
import co.edu.uniandes.csw.usuario.persistence.api.IUsuarioPersistence;
import co.edu.uniandes.csw.usuario.persistence.entity.UsuarioEntity;

@RunWith(Arquillian.class)
public class UsuarioLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(UsuarioLogicService.class.getPackage())
				.addPackage(UsuarioPersistence.class.getPackage())
				.addPackage(UsuarioEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IUsuarioLogicService usuarioLogicService;
	
	@Inject
	private IUsuarioPersistence usuarioPersistence;	

	@Before
	public void configTest() {
		try {
			clearData();
			insertData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clearData() {
		List<UsuarioDTO> dtos=usuarioPersistence.getUsuarios();
		for(UsuarioDTO dto:dtos){
			usuarioPersistence.deleteUsuario(dto.getId());
		}
	}

	private List<UsuarioDTO> data=new ArrayList<UsuarioDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			UsuarioDTO pdto=new UsuarioDTO();
			pdto.setName(generateRandom(String.class));
			pdto.setDireccionEnvio(generateRandom(String.class));
			pdto=usuarioPersistence.createUsuario(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createUsuarioTest(){
		UsuarioDTO ldto=new UsuarioDTO();
		ldto.setName(generateRandom(String.class));
		ldto.setDireccionEnvio(generateRandom(String.class));
		
		
		UsuarioDTO result=usuarioLogicService.createUsuario(ldto);
		
		Assert.assertNotNull(result);
		
		UsuarioDTO pdto=usuarioPersistence.getUsuario(result.getId());
		
		Assert.assertEquals(ldto.getName(), pdto.getName());	
		Assert.assertEquals(ldto.getDireccionEnvio(), pdto.getDireccionEnvio());	
	}
	
	@Test
	public void getUsuariosTest(){
		List<UsuarioDTO> list=usuarioLogicService.getUsuarios();
		Assert.assertEquals(list.size(), data.size());
        for(UsuarioDTO ldto:list){
        	boolean found=false;
            for(UsuarioDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getUsuarioTest(){
		UsuarioDTO pdto=data.get(0);
		UsuarioDTO ldto=usuarioLogicService.getUsuario(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getName(), ldto.getName());
		Assert.assertEquals(pdto.getDireccionEnvio(), ldto.getDireccionEnvio());
        
	}
	
	@Test
	public void deleteUsuarioTest(){
		UsuarioDTO pdto=data.get(0);
		usuarioLogicService.deleteUsuario(pdto.getId());
        UsuarioDTO deleted=usuarioPersistence.getUsuario(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateUsuarioTest(){
		UsuarioDTO pdto=data.get(0);
		
		UsuarioDTO ldto=new UsuarioDTO();
		ldto.setId(pdto.getId());
		ldto.setName(generateRandom(String.class));
		ldto.setDireccionEnvio(generateRandom(String.class));
		
		
		usuarioLogicService.updateUsuario(ldto);
		
		
		UsuarioDTO resp=usuarioPersistence.getUsuario(pdto.getId());
		
		Assert.assertEquals(ldto.getName(), resp.getName());	
		Assert.assertEquals(ldto.getDireccionEnvio(), resp.getDireccionEnvio());	
	}
	
	public <T> T generateRandom(Class<T> objectClass){
		Random r=new Random();
		if(objectClass.isInstance(String.class)){
			String s="";
			for(int i=0;i<10;i++){
				char c=(char)(r.nextInt()/('Z'-'A')+'A');
				s=s+c;
			}
			return objectClass.cast(s);
		}else if(objectClass.isInstance(Integer.class)){
			Integer s=r.nextInt();
			return objectClass.cast(s);
		}else if(objectClass.isInstance(Long.class)){
			Long s=r.nextLong();
			return objectClass.cast(s);
		}else if(objectClass.isInstance(java.util.Date.class)){
			java.util.Calendar c=java.util.Calendar.getInstance();
			c.set(java.util.Calendar.MONTH, r.nextInt()/12);
			c.set(java.util.Calendar.DAY_OF_MONTH,r.nextInt()/30);
			c.setLenient(false);
			return objectClass.cast(c.getTime());
		} 
		return null;
	}
	
}