
package co.edu.uniandes.csw.promocion.logic.ejb;

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


import co.edu.uniandes.csw.promocion.logic.dto.PromocionDTO;
import co.edu.uniandes.csw.promocion.logic.api.IPromocionLogicService;
import co.edu.uniandes.csw.promocion.persistence.PromocionPersistence;
import co.edu.uniandes.csw.promocion.persistence.api.IPromocionPersistence;
import co.edu.uniandes.csw.promocion.persistence.entity.PromocionEntity;

@RunWith(Arquillian.class)
public class PromocionLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(PromocionLogicService.class.getPackage())
				.addPackage(PromocionPersistence.class.getPackage())
				.addPackage(PromocionEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IPromocionLogicService promocionLogicService;
	
	@Inject
	private IPromocionPersistence promocionPersistence;	

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
		List<PromocionDTO> dtos=promocionPersistence.getPromocions();
		for(PromocionDTO dto:dtos){
			promocionPersistence.deletePromocion(dto.getId());
		}
	}

	private List<PromocionDTO> data=new ArrayList<PromocionDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			PromocionDTO pdto=new PromocionDTO();
			pdto.setName(generateRandom(String.class));
			pdto.setFechaInicial(generateRandom(Date.class));
			pdto.setFechaFinal(generateRandom(Date.class));
			pdto.setNuevoPrecio(generateRandom(Double.class));
			pdto=promocionPersistence.createPromocion(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createPromocionTest(){
		PromocionDTO ldto=new PromocionDTO();
		ldto.setName(generateRandom(String.class));
		ldto.setFechaInicial(generateRandom(Date.class));
		ldto.setFechaFinal(generateRandom(Date.class));
		ldto.setNuevoPrecio(generateRandom(Double.class));
		
		
		PromocionDTO result=promocionLogicService.createPromocion(ldto);
		
		Assert.assertNotNull(result);
		
		PromocionDTO pdto=promocionPersistence.getPromocion(result.getId());
		
		Assert.assertEquals(ldto.getName(), pdto.getName());	
		Assert.assertEquals(ldto.getFechaInicial(), pdto.getFechaInicial());	
		Assert.assertEquals(ldto.getFechaFinal(), pdto.getFechaFinal());	
		Assert.assertEquals(ldto.getNuevoPrecio(), pdto.getNuevoPrecio());	
	}
	
	@Test
	public void getPromocionsTest(){
		List<PromocionDTO> list=promocionLogicService.getPromocions();
		Assert.assertEquals(list.size(), data.size());
        for(PromocionDTO ldto:list){
        	boolean found=false;
            for(PromocionDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getPromocionTest(){
		PromocionDTO pdto=data.get(0);
		PromocionDTO ldto=promocionLogicService.getPromocion(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getName(), ldto.getName());
		Assert.assertEquals(pdto.getFechaInicial(), ldto.getFechaInicial());
		Assert.assertEquals(pdto.getFechaFinal(), ldto.getFechaFinal());
		Assert.assertEquals(pdto.getNuevoPrecio(), ldto.getNuevoPrecio());
        
	}
	
	@Test
	public void deletePromocionTest(){
		PromocionDTO pdto=data.get(0);
		promocionLogicService.deletePromocion(pdto.getId());
        PromocionDTO deleted=promocionPersistence.getPromocion(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updatePromocionTest(){
		PromocionDTO pdto=data.get(0);
		
		PromocionDTO ldto=new PromocionDTO();
		ldto.setId(pdto.getId());
		ldto.setName(generateRandom(String.class));
		ldto.setFechaInicial(generateRandom(Date.class));
		ldto.setFechaFinal(generateRandom(Date.class));
		ldto.setNuevoPrecio(generateRandom(Double.class));
		
		
		promocionLogicService.updatePromocion(ldto);
		
		
		PromocionDTO resp=promocionPersistence.getPromocion(pdto.getId());
		
		Assert.assertEquals(ldto.getName(), resp.getName());	
		Assert.assertEquals(ldto.getFechaInicial(), resp.getFechaInicial());	
		Assert.assertEquals(ldto.getFechaFinal(), resp.getFechaFinal());	
		Assert.assertEquals(ldto.getNuevoPrecio(), resp.getNuevoPrecio());	
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