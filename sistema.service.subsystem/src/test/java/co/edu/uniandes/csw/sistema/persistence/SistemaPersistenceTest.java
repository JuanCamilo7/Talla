
package co.edu.uniandes.csw.sistema.persistence;

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


import co.edu.uniandes.csw.sistema.logic.dto.SistemaDTO;
import co.edu.uniandes.csw.sistema.persistence.api.ISistemaPersistence;
import co.edu.uniandes.csw.sistema.persistence.entity.SistemaEntity;

@RunWith(Arquillian.class)
public class SistemaPersistenceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(SistemaPersistence.class.getPackage())
				.addPackage(SistemaEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private ISistemaPersistence sistemaPersistence;

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
		em.createQuery("delete from SistemaEntity").executeUpdate();
	}

	private List<SistemaEntity> data=new ArrayList<SistemaEntity>();

	private void insertData() {
		for(int i=0;i<3;i++){
			SistemaEntity entity=new SistemaEntity();
			em.persist(entity);
			data.add(entity);
		}
	}
	
	@Test
	public void createSistemaTest(){
		SistemaDTO dto=new SistemaDTO();
		
		
		SistemaDTO result=sistemaPersistence.createSistema(dto);
		
		Assert.assertNotNull(result);
		
		SistemaEntity entity=em.find(SistemaEntity.class, result.getId());
		
	}
	
	@Test
	public void getSistemasTest(){
		List<SistemaDTO> list=sistemaPersistence.getSistemas();
		Assert.assertEquals(list.size(), data.size());
        for(SistemaDTO dto:list){
        	boolean found=false;
            for(SistemaEntity entity:data){
            	if(dto.getId()==entity.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getSistemaTest(){
		SistemaEntity entity=data.get(0);
		SistemaDTO dto=sistemaPersistence.getSistema(entity.getId());
        Assert.assertNotNull(dto);
        
	}
	
	@Test
	public void deleteSistemaTest(){
		SistemaEntity entity=data.get(0);
		sistemaPersistence.deleteSistema(entity.getId());
        SistemaEntity deleted=em.find(SistemaEntity.class, entity.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateSistemaTest(){
		SistemaEntity entity=data.get(0);
		
		SistemaDTO dto=new SistemaDTO();
		dto.setId(entity.getId());
		
		
		sistemaPersistence.updateSistema(dto);
		
		
		SistemaEntity resp=em.find(SistemaEntity.class, entity.getId());
		
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