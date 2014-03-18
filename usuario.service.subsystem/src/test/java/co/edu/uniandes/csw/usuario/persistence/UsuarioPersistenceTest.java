
package co.edu.uniandes.csw.usuario.persistence;

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
import co.edu.uniandes.csw.usuario.persistence.api.IUsuarioPersistence;
import co.edu.uniandes.csw.usuario.persistence.entity.UsuarioEntity;

@RunWith(Arquillian.class)
public class UsuarioPersistenceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(UsuarioPersistence.class.getPackage())
				.addPackage(UsuarioEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IUsuarioPersistence usuarioPersistence;

	@PersistenceContext
	private EntityManager em;

	@Inject
	UserTransaction utx;

	@Before
	public void configTest() {
		System.out.println("em: " + em);
		try {
			utx.begin();
			clearData();
			insertData();
			utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				utx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void clearData() {
		em.createQuery("delete from UsuarioEntity").executeUpdate();
	}

	private List<UsuarioEntity> data=new ArrayList<UsuarioEntity>();

	private void insertData() {
		for(int i=0;i<3;i++){
			UsuarioEntity entity=new UsuarioEntity();
			entity.setName(generateRandom(String.class));
			entity.setDireccionEnvio(generateRandom(String.class));
			em.persist(entity);
			data.add(entity);
		}
	}
	
	@Test
	public void createUsuarioTest(){
		UsuarioDTO dto=new UsuarioDTO();
		dto.setName(generateRandom(String.class));
		dto.setDireccionEnvio(generateRandom(String.class));
		
		
		UsuarioDTO result=usuarioPersistence.createUsuario(dto);
		
		Assert.assertNotNull(result);
		
		UsuarioEntity entity=em.find(UsuarioEntity.class, result.getId());
		
		Assert.assertEquals(dto.getName(), entity.getName());	
		Assert.assertEquals(dto.getDireccionEnvio(), entity.getDireccionEnvio());	
	}
	
	@Test
	public void getUsuariosTest(){
		List<UsuarioDTO> list=usuarioPersistence.getUsuarios();
		Assert.assertEquals(list.size(), data.size());
        for(UsuarioDTO dto:list){
        	boolean found=false;
            for(UsuarioEntity entity:data){
            	if(dto.getId()==entity.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getUsuarioTest(){
		UsuarioEntity entity=data.get(0);
		UsuarioDTO dto=usuarioPersistence.getUsuario(entity.getId());
        Assert.assertNotNull(dto);
		Assert.assertEquals(entity.getName(), dto.getName());
		Assert.assertEquals(entity.getDireccionEnvio(), dto.getDireccionEnvio());
        
	}
	
	@Test
	public void deleteUsuarioTest(){
		UsuarioEntity entity=data.get(0);
		usuarioPersistence.deleteUsuario(entity.getId());
        UsuarioEntity deleted=em.find(UsuarioEntity.class, entity.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateUsuarioTest(){
		UsuarioEntity entity=data.get(0);
		
		UsuarioDTO dto=new UsuarioDTO();
		dto.setId(entity.getId());
		dto.setName(generateRandom(String.class));
		dto.setDireccionEnvio(generateRandom(String.class));
		
		
		usuarioPersistence.updateUsuario(dto);
		
		
		UsuarioEntity resp=em.find(UsuarioEntity.class, entity.getId());
		
		Assert.assertEquals(dto.getName(), resp.getName());	
		Assert.assertEquals(dto.getDireccionEnvio(), resp.getDireccionEnvio());	
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