
package co.edu.uniandes.csw.sistema.logic.ejb;

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
import co.edu.uniandes.csw.sistema.logic.api.ISistemaLogicService;
import co.edu.uniandes.csw.sistema.persistence.SistemaPersistence;
import co.edu.uniandes.csw.sistema.persistence.api.ISistemaPersistence;
import co.edu.uniandes.csw.sistema.persistence.entity.SistemaEntity;

@RunWith(Arquillian.class)
public class SistemaLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(SistemaLogicService.class.getPackage())
				.addPackage(SistemaPersistence.class.getPackage())
				.addPackage(SistemaEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private ISistemaLogicService sistemaLogicService;
	
	@Inject
	private ISistemaPersistence sistemaPersistence;	

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
		List<SistemaDTO> dtos=sistemaPersistence.getSistemas();
		for(SistemaDTO dto:dtos){
			sistemaPersistence.deleteSistema(dto.getId());
		}
	}

	private List<SistemaDTO> data=new ArrayList<SistemaDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			SistemaDTO pdto=new SistemaDTO();
			pdto=sistemaPersistence.createSistema(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createSistemaTest(){
		SistemaDTO ldto=new SistemaDTO();
		
		
		SistemaDTO result=sistemaLogicService.createSistema(ldto);
		
		Assert.assertNotNull(result);
		
		SistemaDTO pdto=sistemaPersistence.getSistema(result.getId());
		
	}
	
	@Test
	public void getSistemasTest(){
		List<SistemaDTO> list=sistemaLogicService.getSistemas();
		Assert.assertEquals(list.size(), data.size());
        for(SistemaDTO ldto:list){
        	boolean found=false;
            for(SistemaDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getSistemaTest(){
		SistemaDTO pdto=data.get(0);
		SistemaDTO ldto=sistemaLogicService.getSistema(pdto.getId());
        Assert.assertNotNull(ldto);
        
	}
	
	@Test
	public void deleteSistemaTest(){
		SistemaDTO pdto=data.get(0);
		sistemaLogicService.deleteSistema(pdto.getId());
        SistemaDTO deleted=sistemaPersistence.getSistema(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateSistemaTest(){
		SistemaDTO pdto=data.get(0);
		
		SistemaDTO ldto=new SistemaDTO();
		ldto.setId(pdto.getId());
		
		
		sistemaLogicService.updateSistema(ldto);
		
		
		SistemaDTO resp=sistemaPersistence.getSistema(pdto.getId());
		
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